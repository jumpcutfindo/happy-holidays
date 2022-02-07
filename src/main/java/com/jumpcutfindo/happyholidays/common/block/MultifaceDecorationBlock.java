package com.jumpcutfindo.happyholidays.common.block;

import java.util.Map;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public abstract class MultifaceDecorationBlock extends MultifaceBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    public MultifaceDecorationBlock(Properties blockProperties) {
        super(blockProperties);

        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    protected boolean isFaceSupported(Direction direction) {
        return direction != Direction.UP && direction != Direction.DOWN;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos otherBlockPos) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }

        BlockState newState = super.updateShape(blockState, direction, otherBlockState, levelAccessor, blockPos, otherBlockPos);

        if (!levelAccessor.isClientSide() && !newState.isAir()) {
            ServerLevel serverLevel = (ServerLevel) levelAccessor;
            int oldStateCount = 0;
            int newStateCount = 0;

            for (Map.Entry<Direction, BooleanProperty> entry : PipeBlock.PROPERTY_BY_DIRECTION.entrySet()) {
                Direction dir = entry.getKey();
                if (dir.getAxis().isHorizontal()) {
                    oldStateCount += blockState.getValue(entry.getValue()) ? 1 : 0;
                    newStateCount += newState.getValue(entry.getValue()) ? 1 : 0;
                }
            }

            if (oldStateCount - newStateCount > 0) {
                serverLevel.playSound(null, blockPos, this.soundType.getBreakSound(), SoundSource.BLOCKS, this.soundType.volume, this.soundType.pitch - 0.1f);
                popResource(serverLevel, blockPos, new ItemStack(this, oldStateCount - newStateCount));
            }
        }

        return newState;
    }

    public FluidState getFluidState(BlockState p_153311_) {
        return p_153311_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_153311_);
    }

    public boolean propagatesSkylightDown(BlockState p_181225_, BlockGetter p_181226_, BlockPos p_181227_) {
        return p_181225_.getFluidState().isEmpty();
    }

    @Override
    public boolean canBeReplaced(BlockState blockState, BlockPlaceContext blockPlaceContext) {
        return blockPlaceContext.getPlayer().getItemInHand(blockPlaceContext.getHand()).is(this.asItem()) && super.canBeReplaced(blockState, blockPlaceContext);
    }
}
