package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.slab;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.BaseGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BaseGingerbreadSlabBlock extends SlabBlock {
    public BaseGingerbreadSlabBlock(Properties blockProperties) {
        super(blockProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState expectedState = super.getStateForPlacement(context);

        return BaseGingerbreadBlock.getPlacementState(context, expectedState, () -> ChristmasBlocks.SOGGY_GINGERBREAD_SLAB.get().defaultBlockState());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  LevelAccessor level, BlockPos blockPos, BlockPos otherBlockPos) {
        return BaseGingerbreadBlock.getUpdatedState(blockState, direction, otherBlockState, level, blockPos, otherBlockPos, () -> ChristmasBlocks.SOGGY_GINGERBREAD_SLAB.get().defaultBlockState());
    }
}
