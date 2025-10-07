package rs.onako2.iwie.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import rs.onako2.iwie.IWantItEarlier;

public class NautilusEntity extends AbstractNautilusEntity {
    private static final TrackedData<Boolean> CHILD = DataTracker.registerData(NautilusEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> SADDLED =
            DataTracker.registerData(NautilusEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public int harnessColor = -2;
    public boolean hasPassenger = false;
    public boolean isTempted = false;
    protected int breedingAge;
    protected int forcedAge;

    public NautilusEntity(EntityType<? extends SquidEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (this.hasPassengers() || this.isBaby()) {
            return super.interactMob(player, hand);
        } else {
            ItemStack itemStack = player.getStackInHand(hand);
            if (!itemStack.isEmpty()) {
                ActionResult actionResult = itemStack.useOnEntity(player, this, hand);
                if (player.getStackInHand(hand).isOf(Items.SADDLE) && !this.hasSaddleEquipped()) {
                    this.equipSaddle(itemStack);
                    return ActionResult.SUCCESS;
                }

                if (!this.isWearingBodyArmor()) {
                    this.equipNautilusArmor(player, itemStack);
                    return ActionResult.SUCCESS;
                }

                if (actionResult.isAccepted()) {
                    return actionResult;
                }
            }

            if (this.equipment.get(EquipmentSlot.SADDLE) != null && this.equipment.get(EquipmentSlot.SADDLE).getCount() >= 1) {
                this.putPlayerOnBack(player);
            }
            return ActionResult.SUCCESS;
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

    protected void putPlayerOnBack(PlayerEntity player) {
        if (!this.getWorld().isClient) {
            player.setYaw(this.getYaw());
            player.setPitch(this.getPitch());
            player.startRiding(this);
        }
    }


    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        if (entityData == null) {
            entityData = new PassiveEntity.PassiveData(true);
        }

        PassiveEntity.PassiveData passiveData = (PassiveEntity.PassiveData) entityData;
        if (passiveData.canSpawnBaby() && passiveData.getSpawnedCount() > 0 && world.getRandom().nextFloat() <= passiveData.getBabyChance()) {
            this.setBreedingAge(-24000);
        }

        passiveData.countSpawned();
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (CHILD.equals(data)) {
            this.calculateDimensions();
        }

        super.onTrackedDataSet(data);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(CHILD, false);
        builder.add(SADDLED, false);
    }

    @Override
    public boolean hasPassengers() {
        return hasPassenger || super.hasPassengers();
    }

    @Override
    public void initGoals() {
        super.initGoals();
        this.goalSelector
                .add(
                        0,
                        new TemptGoal(
                                this, 1.0, stack -> stack.isOf(Items.PUFFERFISH), false, 3.0
                        ) {
                            @Override
                            protected void startMovingTo(PlayerEntity player) {
                                Vec3d vec3d = player.getPos().subtract(this.mob.getPos());
                                vec3d = vec3d.normalize().multiply(0.1);
                                this.mob.move(MovementType.SELF, vec3d);
                            }

                            @Override
                            public boolean canStart() {
                                return super.canStart() && this.mob.canSee(this.closestPlayer);
                            }

                            @Override
                            public void start() {
                                NautilusEntity.this.isTempted = true;
                                NautilusEntity.this.swimVec = Vec3d.ZERO;
                                super.start();
                            }

                            @Override
                            public void stop() {
                                NautilusEntity.this.isTempted = false;
                                NautilusEntity.this.swimVec = Vec3d.ZERO;
                                super.stop();
                            }
                        }
                );
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

    @Override
    public void tick() {
        if (hasPassengers()) {
            if (getFirstPassenger() instanceof PlayerEntity player) {
                if (player.isSubmergedInWater()) {
                    player.addStatusEffect(new StatusEffectInstance(IWantItEarlier.BREATH_OF_NAUTILUS, 39, 0, true, false, true));
                }
            }
        }
        super.tick();
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
    public void writeCustomData(WriteView view) {
        super.writeCustomData(view);
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

    public static class SwimGoal extends SquidEntity.SwimGoal {
        private final NautilusEntity squid;

        public SwimGoal(SquidEntity squid) {
            super(squid);
            this.squid = (NautilusEntity) squid;
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
