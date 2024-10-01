package rs.onako2.iwie;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.Entity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import rs.onako2.iwie.entity.CreakingHeartBlockEntity;

public class Util {
    public static Block createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return new PillarBlock(AbstractBlock.Settings.create().mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable());
    }

    public static boolean isHeartNear(Entity entity, World world, int radius) {
        BlockPos entityBlockPos = entity.getBlockPos();

        // Loop through blocks in the defined radius around the creaking
        for (int x = -radius; x <= radius; x++) {
            for (int y = world.getBottomY(); y <= world.getTopY(); y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos currentPos = entityBlockPos.add(x, y, z);
                    BlockEntity blockEntity = world.getBlockEntity(currentPos);

                    if (blockEntity instanceof CreakingHeartBlockEntity) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
