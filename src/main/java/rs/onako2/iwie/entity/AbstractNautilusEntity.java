package rs.onako2.iwie.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AbstractNautilusEntity extends SquidEntity {
    private static final TrackedData<Boolean> CHILD = DataTracker.registerData(AbstractNautilusEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> SADDLED =
            DataTracker.registerData(AbstractNautilusEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public int harnessColor = -2;
    public boolean hasPassenger = false;
    public boolean isTempted = false;
    protected int breedingAge;
    protected int forcedAge;

    public AbstractNautilusEntity(EntityType<? extends SquidEntity> entityType, World world) {
        super(entityType, world);
    }

    public static net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createAbstractNautilusAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, 20.0).add(EntityAttributes.FOLLOW_RANGE, 40.0).add(EntityAttributes.TEMPT_RANGE, 64.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new SquidEntity.EscapeAttackerGoal());
    }

    @Override
    public void tickMovement() {
        if (this.getFirstPassenger() instanceof ServerPlayerEntity player) {
            PlayerInput playerInput = player.getPlayerInput();

            this.setYaw(player.getYaw());
            this.setPitch(player.getPitch());

            this.swimVec = player.getInputVelocityForMinecart().multiply(0.2);


            if (playerInput.sprint() && Math.abs(player.getPitch()) >= 10.0f && (playerInput.forward() || playerInput.backward())) {
                float pitchFactor = player.getPitch() / (playerInput.backward() ? 80.0f : -80.0f);
                this.swimVec = swimVec.add(0, Math.clamp(pitchFactor, -0.2f, 0.2f), 0);
            }

        }
        super.tickMovement();
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(CHILD, false);
        builder.add(SADDLED, false);
    }

    public boolean canBeSaddled() {
        return !isBaby() && !isSaddled();
    }

    public boolean isSaddled() {
        return this.dataTracker.get(SADDLED);
    }

    public void saddle(@Nullable SoundCategory sound) {
        this.dataTracker.set(SADDLED, true);
    }

    public int getBreedingAge() {
        if (this.getWorld().isClient) {
            return this.dataTracker.get(CHILD) ? -1 : 1;
        } else {
            return this.breedingAge;
        }
    }

    public void setBreedingAge(int age) {
        int i = this.getBreedingAge();
        this.breedingAge = age;
        if (i < 0 && age >= 0 || i >= 0 && age < 0) {
            this.dataTracker.set(CHILD, age < 0);
            this.onGrowUp();
        }
    }

    public void growUp(int age, boolean overGrow) {
        int i = this.getBreedingAge();
        i += age * 20;
        if (i > 0) {
            i = 0;
        }

        int k = 0;
        this.setBreedingAge(i);
        if (overGrow) {
            this.forcedAge += k;
        }

        if (this.getBreedingAge() == 0) {
            this.setBreedingAge(this.forcedAge);
        }
    }

    public void growUp(int age) {
        this.growUp(age, false);
    }

    @Override
    public boolean hasPassengers() {
        return hasPassenger || super.hasPassengers();
    }

    protected void putPlayerOnBack(PlayerEntity player) {
        if (!this.getWorld().isClient) {
            player.setYaw(this.getYaw());
            player.setPitch(this.getPitch());
            player.startRiding(this);
        }
    }

    public void equipSaddle(ItemStack stack) {
        this.equipLootStack(EquipmentSlot.SADDLE, stack);
    }

    public void equipNautilusArmor(PlayerEntity player, ItemStack stack) {
        if (this.canEquip(stack, EquipmentSlot.BODY)) {
            this.equipBodyArmor(stack.splitUnlessCreative(1, player));
        }
    }

    @Override
    public void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putInt("HarnessColor", harnessColor);
        view.putInt("Age", this.getBreedingAge());
        view.putInt("ForcedAge", this.forcedAge);
        view.putBoolean("Saddled", this.isSaddled());
    }

    @Override
    public void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.setBreedingAge(view.getInt("Age", 0));
        this.forcedAge = view.getInt("ForcedAge", 0);
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (CHILD.equals(data)) {
            this.calculateDimensions();
        }

        super.onTrackedDataSet(data);
    }

    protected void onGrowUp() {
        if (!this.isBaby() && this.hasVehicle() && this.getVehicle() instanceof AbstractBoatEntity abstractBoatEntity && !abstractBoatEntity.isSmallerThanBoat(this)) {
            this.stopRiding();
        }
    }

    @Override
    public boolean isBaby() {
        return this.getBreedingAge() < 0;
    }

    @Override
    public void setBaby(boolean baby) {
        this.setBreedingAge(baby ? -24000 : 0);
    }

    @Override
    public EntityNavigation createNavigation(World world) {
        SwimNavigation nav = new SwimNavigation(this, world);
        nav.setCanSwim(true); // ensure navigator is allowed to swim
        return nav;
    }

    public static class SwimGoal extends SquidEntity.SwimGoal {
        private final AbstractNautilusEntity squid;

        public SwimGoal(SquidEntity squid) {
            super(squid);
            this.squid = (AbstractNautilusEntity) squid;
        }

        @Override
        public boolean canStart() {
            return !squid.hasPassengers() && !squid.isTempted;
        }

        @Override
        public void tick() {
            if (squid.isTempted) {
                stop();
                return;
            }
            super.tick();
        }

        @Override
        public void stop() {
            super.stop();
            squid.swimVec = Vec3d.ZERO;
        }
    }
}
