package rs.onako2;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;

public class Util {
    public static Block createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return new PillarBlock(AbstractBlock.Settings.create().mapColor((state) -> {
            return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable());
    }
}
