package rs.onako2.iwie.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;

public class CreakingMeleeAttackGoal extends MeleeAttackGoal {
    protected final PathAwareEntity mob;

    public CreakingMeleeAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        this.mob = mob;
    }

    @Override
    public boolean canStart() {
        if (isPlayerNotLookingAtEntity(this.mob.getTarget(), (HostileEntity) this.mob)) {
            return super.canStart();
        } else {
            return false;
        }
    }

    @Override
    public boolean shouldContinue() {
        if (isPlayerNotLookingAtEntity(this.mob.getTarget(), (HostileEntity) this.mob)) {
            return super.shouldContinue();
        } else {
            return false;
        }
    }

    private boolean isPlayerNotLookingAtEntity(LivingEntity player, HostileEntity entity) {
        if (player == null) {
            return true;
        }
        double deltaX = entity.getX() - player.getX();
        double deltaZ = entity.getZ() - player.getZ();
        float targetYaw = player.getYaw(1.0F);
        float angleToEntity = (float) (Math.atan2(deltaZ, deltaX) * (180 / Math.PI)) - 90.0F;
        float angleDifference = Math.abs(targetYaw - angleToEntity);
        return !(angleDifference < 45.0F) && !(angleDifference > 315.0F);
    }
}
