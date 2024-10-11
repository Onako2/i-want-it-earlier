package rs.onako2.iwie.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import rs.onako2.iwie.Init;
import rs.onako2.iwie.entity.ai.goal.CreakingMeleeAttackGoal;

public class CreakingEntity extends HostileEntity {
    private static final TrackedData<Boolean> IS_HEART_SPAWN = DataTracker.registerData(CreakingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public boolean isHeartSpawn = false;
    public boolean deathLock = false;
    public BlockPos boundHeart;

    public CreakingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0);
    }

    public boolean isHeartNear() {
        if (this.boundHeart != null && this.getWorld().getBlockState(this.boundHeart).getBlock() == Init.CREAKING_HEART) {
            Vec3d entityPos = this.getPos();
            Vec3d heartPos = new Vec3d(this.boundHeart.getX(), this.boundHeart.getY(), this.boundHeart.getZ());
            double distance = entityPos.distanceTo(heartPos);

            if (distance <= 34.0) {
                var blockEntity = this.getWorld().getBlockEntity(this.boundHeart);
                if (blockEntity instanceof CreakingHeartBlockEntity) {
                    ((CreakingHeartBlockEntity) blockEntity).creakingEntity = this;
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            return;
        }
        boolean isHeartNear = false;
        isHeartNear = this.isHeartNear();

        if (this.isHeartSpawn && this.getWorld().isDay() && !this.getWorld().isThundering() || (this.isHeartSpawn && !isHeartNear)) {
            this.deathLock = true;
            this.kill();
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(IS_HEART_SPAWN, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("is_heart_spawn", this.isHeartSpawn);

        if (this.boundHeart != null) {
            NbtCompound heartNbt = new NbtCompound();
            heartNbt.putInt("x", this.boundHeart.getX());
            heartNbt.putInt("y", this.boundHeart.getY());
            heartNbt.putInt("z", this.boundHeart.getZ());
            nbt.put("bound_heart", heartNbt);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.isHeartSpawn = nbt.getBoolean("is_heart_spawn");

        if (nbt.contains("bound_heart", NbtElement.COMPOUND_TYPE)) {
            NbtCompound heartNbt = nbt.getCompound("bound_heart");
            this.boundHeart = new BlockPos(heartNbt.getInt("x"), heartNbt.getInt("y"), heartNbt.getInt("z"));
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new CreakingMeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(2, new WanderAroundGoal(this, 0.7));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, false));
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        if (this.boundHeart != null) {
            BlockEntity blockEntity = this.getWorld().getBlockEntity(this.boundHeart);
            if (blockEntity instanceof CreakingHeartBlockEntity) {
                ((CreakingHeartBlockEntity) blockEntity).creakingEntity = null;
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.isHeartSpawn && this.isHeartNear()) {
            if (this.getWorld().isNight() || this.getWorld().isThundering()) {
                return false;
            }
        }

        return super.damage(source, amount);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }
}