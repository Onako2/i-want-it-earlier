package rs.onako2.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.MossBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import rs.onako2.Init;

public class PaleMossBlock extends MossBlock {
    public PaleMossBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.getRegistryManager().getOptional(RegistryKeys.CONFIGURED_FEATURE).flatMap((key) -> {
            return key.getEntry(Init.PALE_MOSS_PATCH_BONEMEAL_FEATURE_ID);
        }).ifPresent((entry) -> {
            ((ConfiguredFeature)entry.value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos.up());
        });
    }
}
