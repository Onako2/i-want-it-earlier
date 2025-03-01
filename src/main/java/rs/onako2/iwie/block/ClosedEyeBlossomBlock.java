package rs.onako2.iwie.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import static rs.onako2.iwie.Init.OPEN_EYE_BLOSSOM_BLOCK;

public class ClosedEyeBlossomBlock extends FlowerBlock {
    
    
    public ClosedEyeBlossomBlock(RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, Settings settings) {
        super(stewEffect, effectLengthInSeconds, settings);
    }
    
    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isNight()) {
            world.setBlockState(pos, OPEN_EYE_BLOSSOM_BLOCK.getDefaultState());
        }
        super.randomTick(state, world, pos, random);
    }
}
