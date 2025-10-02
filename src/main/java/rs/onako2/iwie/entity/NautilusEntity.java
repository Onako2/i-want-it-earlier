package rs.onako2.iwie.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NautilusEntity extends AbstractNautilusEntity {
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
