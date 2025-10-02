package rs.onako2.iwie.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NautilusEntity extends AbstractNautilusEntity {
    public NautilusEntity(EntityType<? extends SquidEntity> entityType, World world) {
        super(entityType, world);
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
}
