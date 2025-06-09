package rs.onako2.entity;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.PlayerInput;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import rs.onako2.IWantItEarlier;
import rs.onako2.entity.ai.FlyingTemptGoal;
import rs.onako2.network.HappyGhastInformationPayload;

import java.util.Objects;

public class HappyGhastEntity extends GhastEntity {
    private static final TrackedData<Boolean> CHILD = DataTracker.registerData(PassiveEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public int harnessColor = -2;
    public boolean hasPassenger = false;
    protected int breedingAge;
    protected int forcedAge;

    public HappyGhastEntity(EntityType<? extends GhastEntity> entityType, World world) {
        super(entityType, world);
    }

    public static net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createHappyGhastAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, 40.0).add(EntityAttributes.FOLLOW_RANGE, 100.0).add(EntityAttributes.TEMPT_RANGE, 100.0);
    }

    public static int toGrowUpAge(int breedingAge) {
        return (int) (breedingAge / 20 * 0.1F);
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
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(CHILD, false);
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

    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        this.getWorld().getPlayers().forEach(player -> {
            if (player instanceof ServerPlayerEntity) {
                ServerPlayNetworking.send((ServerPlayerEntity) player, new HappyGhastInformationPayload(uuid, hasPassengers(), harnessColor));
            }
        });
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (!this.hasPassengers()) {
            this.getWorld().getPlayers().forEach(player -> {
                if (player instanceof ServerPlayerEntity) {
                    ServerPlayNetworking.send((ServerPlayerEntity) player, new HappyGhastInformationPayload(uuid, hasPassengers(), harnessColor));
                }
            });
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new FlyingTemptGoal(this, stack -> stack.getItem() == IWantItEarlier.HARNESS && (this.harnessColor == -1 || this.harnessColor == -2)));
        this.goalSelector.add(5, new FlyRandomlyGoal(this));
        this.moveControl = new GhastMoveControl(this);
        setPersistent();
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ActionResult actionResult = super.interactMob(player, hand);
        if (actionResult != ActionResult.PASS) {
            return actionResult;
        } else {
            Iterable<ItemStack> handItems = player.getHandItems();
            ItemStack currentItem = handItems.iterator().next();
            if (!this.getWorld().isClient && !this.isHarnessed() && Objects.equals(currentItem.getItem(), IWantItEarlier.HARNESS)) {
                this.harness(currentItem, null);
                return ActionResult.SUCCESS;
            }
            return player.shouldCancelInteraction() || !this.getWorld().isClient && this.isHarnessed() && !player.startRiding(this)
                    ? ActionResult.PASS
                    : ActionResult.SUCCESS;
        }
    }

    public boolean canBeHarnessed() {
        return this.isAlive() && !this.isBaby();
    }

    public boolean isHarnessed() {
        return harnessColor != -1 && harnessColor != -2;
    }

    public int getHarnessColor() {
        return harnessColor;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("HarnessColor", harnessColor);
        nbt.putInt("Age", this.getBreedingAge());
        nbt.putInt("ForcedAge", this.forcedAge);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("HarnessColor")) {
            harnessColor = nbt.getInt("HarnessColor");
        } else {
            harnessColor = -1;
        }
        this.setBreedingAge(nbt.getInt("Age"));
        this.forcedAge = nbt.getInt("ForcedAge");
    }

    public void harness(ItemStack stack, @Nullable SoundCategory soundCategory) {
        DyedColorComponent component = stack.get(DataComponentTypes.DYED_COLOR);
        if (component == null) {
            return;
        }
        stack.withItem(Items.AIR);
        harnessColor = component.rgb();
        if (soundCategory != null) {
            this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_STRIDER_SADDLE, soundCategory, 0.5F, 1.0F);
        }
        this.moveControl.moveTo(this.getX(), this.getY(), this.getZ(), 1.0f);
        this.moveControl.state = MoveControl.State.WAIT;
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
    public void tick() {
        super.tick();
        if (this.getFirstPassenger() instanceof ServerPlayerEntity player) {
            PlayerInput playerInput = player.getPlayerInput();
            this.rotate(player.headYaw, player.getPitch());
            if (playerInput.forward() && !playerInput.backward()) {
                this.forwardSpeed = 1.0f;
            }
            if (playerInput.backward() && !playerInput.forward()) {
                this.forwardSpeed = -1.0f;
            }
            if (playerInput.left() && !playerInput.right()) {
                this.sidewaysSpeed = 1.0f;
            }
            if (playerInput.right() && !playerInput.left()) {
                this.sidewaysSpeed = -1.0f;
            }

            // upward and downward movement
            if (playerInput.sprint() && Math.abs(player.getPitch()) >= 10.0f && (playerInput.forward() || playerInput.backward())) {
                this.setUpwardSpeed(Math.clamp(player.getPitch() / (playerInput.backward() ? 80.0f : -80.0f), -1.0f, 1.0f));
            } else {
                this.setUpwardSpeed(Math.abs(this.upwardSpeed) > 0.1f ? this.upwardSpeed / 1.1f : 0.0f);
            }
        } else {
            this.sidewaysSpeed = 0.0f;
            this.forwardSpeed = 0.0f;
        }
    }

    @Override
    public boolean collidesWith(Entity other) {
        return true;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    public static class PassiveData implements EntityData {
        private final boolean babyAllowed;
        private final float babyChance;
        private int spawnCount;

        public PassiveData(boolean babyAllowed, float babyChance) {
            this.babyAllowed = babyAllowed;
            this.babyChance = babyChance;
        }

        public PassiveData(boolean babyAllowed) {
            this(babyAllowed, 0.05F);
        }

        public PassiveData(float babyChance) {
            this(true, babyChance);
        }

        public int getSpawnedCount() {
            return this.spawnCount;
        }

        public void countSpawned() {
            this.spawnCount++;
        }

        public boolean canSpawnBaby() {
            return this.babyAllowed;
        }

        public float getBabyChance() {
            return this.babyChance;
        }
    }

    public static class FlyRandomlyGoal extends GhastEntity.FlyRandomlyGoal {
        private final HappyGhastEntity happyGhast;

        public FlyRandomlyGoal(HappyGhastEntity happyGhast) {
            super(happyGhast);
            this.happyGhast = happyGhast;
        }

        @Override
        public boolean canStart() {
            return !happyGhast.isHarnessed() && super.canStart();
        }
    }
}
