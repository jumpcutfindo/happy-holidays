package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BaseGingerbreadStairBlock extends StairBlock {
    public BaseGingerbreadStairBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState expectedState = super.getStateForPlacement(context);

        return BaseGingerbreadBlock.getPlacementState(context, expectedState, () -> ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get().defaultBlockState());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  LevelAccessor level, BlockPos blockPos, BlockPos otherBlockPos) {
        return BaseGingerbreadBlock.getUpdatedState(blockState, direction, otherBlockState, level, blockPos, otherBlockPos, () -> ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get().defaultBlockState());
    }
}
