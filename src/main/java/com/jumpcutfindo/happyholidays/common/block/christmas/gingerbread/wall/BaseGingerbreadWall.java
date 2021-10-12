package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.wall;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.BaseGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BaseGingerbreadWall extends WallBlock {
    public BaseGingerbreadWall(Properties blockProperties) {
        super(blockProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState expectedState = super.getStateForPlacement(context);

        return BaseGingerbreadBlock.getPlacementState(context, expectedState, () -> ChristmasBlocks.SOGGY_GINGERBREAD_WALL.get().defaultBlockState());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  LevelAccessor level, BlockPos blockPos, BlockPos otherBlockPos) {
        BlockState expectedState = super.updateShape(blockState, direction, otherBlockState, level, blockPos, otherBlockPos);

        return BaseGingerbreadBlock.getUpdatedState(expectedState, direction, otherBlockState, level, blockPos, otherBlockPos, () -> ChristmasBlocks.SOGGY_GINGERBREAD_WALL.get().defaultBlockState());
    }
}
