package rs.onako2.iwie.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class NautilusEntity extends AbstractNautilusEntity{
    public NautilusEntity(EntityType<? extends SquidEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void initGoals() {
        super.initGoals();
        this.goalSelector
                .add(
                        4,
                        new TemptGoal.HappyGhastTemptGoal(
                                this, 1.0, stack -> stack.isOf(Items.PUFFERFISH), false, 7.0
                        )
                );
    }
}
