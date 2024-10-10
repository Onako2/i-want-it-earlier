package rs.onako2.iwie.entity;

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
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import rs.onako2.iwie.Util;
import rs.onako2.iwie.entity.ai.goal.CreakingMeleeAttackGoal;

public class CreakingEntity extends HostileEntity {
    private static final TrackedData<Boolean> IS_HEART_SPAWN = DataTracker.registerData(CreakingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public boolean isHeartSpawn = false;
    public boolean deathLock = false;

    public CreakingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
    }

    public static DefaultAttributeContainer.Builder createMobAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
            return;
        }
        if(this.isHeartSpawn && this.getWorld().isDay() || (this.isHeartSpawn && !Util.isHeartNear(this, this.getWorld(), 16))) {
            this.deathLock = true;
            this.kill();
        }
    }
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(IS_HEART_SPAWN,false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("is_heart_spawn", this.isHeartSpawn);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("is_heart_spawn", NbtElement.BYTE_TYPE)) {
            this.isHeartSpawn = nbt.getBoolean("is_heart_spawn");
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
    public boolean damage(DamageSource source, float amount) {
        if(this.isHeartSpawn && !this.deathLock) {
            return false;
        }

        if (!Util.isHeartNear(this, this.getWorld(), 16)) {
            return super.damage(source, amount);
        }

        return false;
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
