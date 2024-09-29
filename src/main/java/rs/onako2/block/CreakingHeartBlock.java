package rs.onako2.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

// TODO: Implement CreakingHeartBlock, that new block that seems to be complicated lol
public class CreakingHeartBlock extends PillarBlock {
    public static BooleanProperty activated = BooleanProperty.of("activated");

    public CreakingHeartBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(activated);
    }

}
