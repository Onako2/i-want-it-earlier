package rs.onako2.entity.ai;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.function.Predicate;

public class FlyingTemptGoal extends Goal {
    private static final TargetPredicate TEMPTING_ENTITY_PREDICATE = TargetPredicate.createNonAttackable().ignoreVisibility();
    protected final FlyingEntity mob;
    private final TargetPredicate predicate;
    private final Predicate<ItemStack> foodPredicate;
    @Nullable
    protected PlayerEntity closestPlayer;
    private int cooldown;
    private boolean active;
    
    public FlyingTemptGoal(FlyingEntity entity, Predicate<ItemStack> foodPredicate) {
        this.mob = entity;
        this.foodPredicate = foodPredicate;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        this.predicate = TEMPTING_ENTITY_PREDICATE.copy().setPredicate((entityx, world) -> this.foodPredicate.test(entityx.getMainHandStack()) || this.foodPredicate.test(entityx.getOffHandStack()));
    }
    
    @Override
    public boolean canStart() {
        if (this.cooldown > 0) {
            this.cooldown--;
            return false;
        } else {
            this.closestPlayer = getServerWorld(this.mob).getClosestPlayer(this.predicate.setBaseMaxDistance(this.mob.getAttributeValue(EntityAttributes.TEMPT_RANGE)), this.mob);
            return this.closestPlayer != null;
        }
    }
    
    
    @Override
    public boolean shouldContinue() {
        return this.canStart();
    }
    
    @Override
    public void start() {
        this.active = true;
    }
    
    @Override
    public void stop() {
        this.closestPlayer = null;
        this.mob.stopMovement();
        this.cooldown = toGoalTicks(100);
        this.active = false;
    }
    
    @Override
    public void tick() {
        if (this.closestPlayer != null) {
            this.mob.getMoveControl().state = MoveControl.State.WAIT;
            this.mob.lookAtEntity(this.closestPlayer, this.mob.getMaxHeadRotation() + 20, this.mob.getMaxLookPitchChange());
            if (this.mob.distanceTo(this.closestPlayer) < 6) {
                this.mob.forwardSpeed = 0.0f;
                this.mob.upwardSpeed = 0.0f;
            } else {
                this.mob.forwardSpeed = 1.0f;
                if (mob.getY() > this.closestPlayer.getY() - 2) {
                    this.mob.upwardSpeed = -0.75f;
                }
                if (mob.getY() < this.closestPlayer.getY() - 2) {
                    this.mob.upwardSpeed = 0.75f;
                }
                if (Math.abs(mob.getY() - this.closestPlayer.getY()) < 4) {
                    this.mob.upwardSpeed = 0.0f;
                }
            }
        }
    }
    
}
