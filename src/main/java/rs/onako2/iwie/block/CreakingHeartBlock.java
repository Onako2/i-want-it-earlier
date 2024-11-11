package rs.onako2.iwie.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import rs.onako2.iwie.Init;
import rs.onako2.iwie.entity.CreakingBlockEntityTypes;
import rs.onako2.iwie.entity.CreakingHeartBlockEntity;

public class CreakingHeartBlock extends BlockWithEntity {
    public static BooleanProperty ACTIVATED = BooleanProperty.of("activated");

    public CreakingHeartBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ACTIVATED, true));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(CreakingHeartBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVATED);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        this.updateActivationState(world, pos, state);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
        this.updateActivationState(world, pos, state);
    }

    private void updateActivationState(World world, BlockPos pos, BlockState state) {
        boolean isActivated = world.getBlockState(pos.up()).isOf(Init.PALE_OAK_LOG) && world.getBlockState(pos.down()).isOf(Init.PALE_OAK_LOG);
        world.setBlockState(pos, state.with(ACTIVATED, isActivated), 3);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CreakingHeartBlockEntity(CreakingBlockEntityTypes.CREAKING_HEART_BLOCK, pos, state);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        neighborUpdate(state, world, pos, asBlock(), pos, true);
    }

    @Override
    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity != null) {
            CreakingHeartBlockEntity creakingHeartBlock = ((CreakingHeartBlockEntity) blockEntity);
            if (creakingHeartBlock.creakingEntity != null) {
                BlockPos pos1 = creakingHeartBlock.creakingEntity.getBlockPos();
                BlockPos pos2 = pos;
                int distance = (int) Math.sqrt(
                        Math.pow(pos1.getX() - pos2.getX(), 2) +
                                Math.pow(pos1.getY() - pos2.getY(), 2) +
                                Math.pow(pos1.getZ() - pos2.getZ(), 2)
                );
                int powerResult = 15 - distance;
                return powerResult < 0 ? 0 : powerResult;
            }
        }
        return 0;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        // Make sure to check world.isClient if you only want to tick only on serverside.
        return validateTicker(type, CreakingBlockEntityTypes.CREAKING_HEART_BLOCK, CreakingHeartBlockEntity::tick);
    }
}
