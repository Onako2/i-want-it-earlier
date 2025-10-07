package rs.onako2.iwie.mixin;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombieHorseEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import rs.onako2.iwie.IWantItEarlier;

@Mixin(ZombieHorseEntity.class)
public class ZombieHorseEntityMixin extends AbstractHorseEntity {

    protected ZombieHorseEntityMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        if (spawnReason == SpawnReason.NATURAL) {
            ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, (World) world);
            zombie.equipStack(EquipmentSlot.MAINHAND, new ItemStack(IWantItEarlier.WOODEN_SPEAR));
            zombie.refreshPositionAndAngles(getPos(), getYaw(), getPitch());
            if (world.spawnEntity(zombie)) {
                zombie.startRiding(this, true);
            }
        }

        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
