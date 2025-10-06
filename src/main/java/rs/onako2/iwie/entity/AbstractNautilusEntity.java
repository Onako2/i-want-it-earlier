package rs.onako2.iwie.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.PlayerInput;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AbstractNautilusEntity extends SquidEntity {

    public AbstractNautilusEntity(EntityType<? extends SquidEntity> entityType, World world) {
        super(entityType, world);
    }

    public static net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createAbstractNautilusAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, 14.0).add(EntityAttributes.FOLLOW_RANGE, 40.0).add(EntityAttributes.TEMPT_RANGE, 64.0);
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
    public EntityNavigation createNavigation(World world) {
        SwimNavigation nav = new SwimNavigation(this, world);
        nav.setCanSwim(true); // ensure navigator is allowed to swim
        return nav;
    }
}
