package rs.onako2.iwie.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import rs.onako2.iwie.Init;

public class PaleHangingMossPlantBlock extends AbstractPlantBlock {

    public static final MapCodec<WeepingVinesPlantBlock> CODEC = createCodec(WeepingVinesPlantBlock::new);
    public static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public PaleHangingMossPlantBlock(Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
    }

    @Override
    public MapCodec<WeepingVinesPlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) Init.PALE_HANGING_MOSS;
    }
}
