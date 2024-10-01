package rs.onako2.iwie.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import rs.onako2.iwie.Init;


public class CreakingHeartDecorator extends TreeDecorator {

    public static final MapCodec<CreakingHeartDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(CreakingHeartDecorator::new, (decorator) -> {
        return decorator.probability;
    });

    private final float probability;

    public CreakingHeartDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return null;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        TestableWorld testableWorld = generator.getWorld();
        float floatRandom = random.nextFloat();

        System.out.println("floatRandom: " + floatRandom);
        if (floatRandom <= probability) {
            ObjectArrayList<BlockPos> logPositions = generator.getLogPositions();
            ObjectArrayList<BlockPos> filteredLogPositions = new ObjectArrayList<>();
            logPositions.forEach((blockPos) -> {
                // check if there is one air around the log
                if ((testableWorld.testBlockState(blockPos.north(), AbstractBlock.AbstractBlockState::isAir) || testableWorld.testBlockState(blockPos.east(), AbstractBlock.AbstractBlockState::isAir) || testableWorld.testBlockState(blockPos.south(), AbstractBlock.AbstractBlockState::isAir) || testableWorld.testBlockState(blockPos.west(), AbstractBlock.AbstractBlockState::isAir)) && (testableWorld.testBlockState(blockPos.down(2), state -> state.isOf(Blocks.DIRT)) || testableWorld.testBlockState(blockPos.down(3), state -> state.isOf(Blocks.DIRT)))) {
                    filteredLogPositions.add(blockPos);
                }
            });
            BlockPos creakingPosition = filteredLogPositions.get(random.nextInt(filteredLogPositions.size()));
            generator.replace(creakingPosition, Init.CREAKING_HEART.getDefaultState());
        }

    }
}
