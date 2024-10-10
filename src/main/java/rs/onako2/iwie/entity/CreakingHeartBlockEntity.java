package rs.onako2.iwie.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import rs.onako2.iwie.Init;
import rs.onako2.iwie.block.CreakingHeartBlock;

public class CreakingHeartBlockEntity extends BlockEntity {
    public CreakingHeartBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, CreakingHeartBlockEntity blockEntity) {
        // check if heart is activated because I forgot it so I didn't want to forget it :)
        if (state.get(CreakingHeartBlock.ACTIVATED)) {
            blockEntity.checkAndSpawnCreaking(world, pos);
        }
    }

    private void checkAndSpawnCreaking(World world, BlockPos pos) {
        boolean creakingExists = !world.getEntitiesByClass(CreakingEntity.class, new Box(pos).expand(32), entity -> true).isEmpty();
        if (!creakingExists && world.isNight()) {
            CreakingEntity creaking = new CreakingEntity(Init.CREAKING, world);
            creaking.isHeartSpawn = true;

            // get nearest air block
            for (int x = -2; x <= 2; x++) {
                for (int y = -2; y <= 2; y++) {
                    for (int z = -2; z <= 2; z++) {
                        BlockPos airPos = pos.add(x, y, z);
                        if (world.isAir(airPos)) {
                            creaking.refreshPositionAndAngles(airPos.getX(), airPos.getY(), airPos.getZ(), 0.0F, 0.0F);
                            world.spawnEntity(creaking);
                            return;
                        }
                    }
                }
            }
        }
    }
}
