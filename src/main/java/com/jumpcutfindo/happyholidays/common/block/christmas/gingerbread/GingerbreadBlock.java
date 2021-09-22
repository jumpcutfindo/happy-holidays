package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public class GingerbreadBlock extends BaseGingerbreadBlock {
    public static final String BLOCK_ID = "gingerbread_block";

    public GingerbreadBlock() {
        super();
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  LevelAccessor world, BlockPos blockPos, BlockPos otherBlockPos) {
        return world.getBlockState(otherBlockPos).is(Blocks.WATER) ? ChristmasBlocks.GINGERBREAD_BLOCK.get().defaultBlockState()
                : blockState;
    }
}
