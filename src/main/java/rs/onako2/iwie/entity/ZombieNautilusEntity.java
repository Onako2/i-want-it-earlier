package rs.onako2.iwie.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.world.World;

public class ZombieNautilusEntity extends AbstractNautilusEntity {
    public ZombieNautilusEntity(EntityType<? extends SquidEntity> entityType, World world) {
        super(entityType, world);
    }
}
