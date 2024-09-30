package rs.onako2.iwie;

import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

public class PaleLeavesColorProvider implements BlockColorProvider {
    @Override
    public int getColor(BlockState state, BlockRenderView world, BlockPos pos, int tintIndex) {
        if (world != null && pos != null) {
            // Get the biome's foliage color
            return BiomeColors.getFoliageColor(world, pos);
        }
        // Default fallback color if world or pos is null
        return 0xFFFFFF;
    }
}
