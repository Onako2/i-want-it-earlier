package rs.onako2.iwie.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rs.onako2.iwie.Init;
import rs.onako2.iwie.block.CreakingHeartBlock;

import java.util.concurrent.ThreadLocalRandom;

public class CreakingHeartBlockEntity extends BlockEntity {
    public CreakingEntity creakingEntity = null;
    public int timing = 100;

    public CreakingHeartBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, CreakingHeartBlockEntity blockEntity) {
        // check if heart is activated because I forgot it so I didn't want to forget it :)
        if (state.get(CreakingHeartBlock.ACTIVATED)) {
            blockEntity.checkAndSpawnCreaking(world, pos);
            if (state.getBlock() instanceof CreakingHeartBlock) {
                if (blockEntity.timing <= 100) {
                    blockEntity.timing++;
                }
            }
        }
    }

    public BlockPos findAirBlockPos() {
        BlockPos basePos = this.getPos();
        int[][] offsets = {
                {1, 0, 0},  // Front
                {-1, 0, 0}, // Behind
                {0, 1, 0},  // Top
                {0, -1, 0}, // Bottom
                {0, 0, 1},  // Right
                {0, 0, -1}  // Left
        };

        for (int[] offset : offsets) {
            try {
                BlockPos pos = basePos.add(offset[0], offset[1], offset[2]);
                if (this.world.getBlockState(pos).getBlock() == Blocks.AIR) {
                    return pos;
                }
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    public void trySpawnResin() {
        if (this.timing >= 100) {
            World world = this.getWorld();
            ItemStack itemStack = new ItemStack(Init.RESIN_CLUMP);
            itemStack.setCount(ThreadLocalRandom.current().nextInt(1, 4));
            BlockPos blockPos = this.getPos();
            BlockPos blockPos2 = findAirBlockPos();
            if (blockPos2 != null) blockPos = blockPos2;
            try {
                world.spawnEntity(new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), itemStack));
            } catch (Exception ignored) {
            }
            this.timing = 0;
        }
    }

    private void checkAndSpawnCreaking(World world, BlockPos pos) {
        //boolean creakingExists = !world.getEntitiesByClass(CreakingEntity.class, new Box(pos).expand(32), entity -> true).isEmpty();
        boolean creakingExists = this.creakingEntity != null && this.creakingEntity.isAlive();
        if (!creakingExists && (world.isNight())) {
            CreakingEntity creaking = new CreakingEntity(Init.CREAKING, world);
            creaking.boundHeart = this.getPos();
            creaking.isHeartSpawn = true;
            this.creakingEntity = creaking;

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
