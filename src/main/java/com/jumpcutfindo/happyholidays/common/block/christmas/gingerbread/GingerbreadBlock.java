package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class GingerbreadBlock extends BaseGingerbreadBlock {
    public static final String BLOCK_ID = "gingerbread_block";

    public GingerbreadBlock() {
        super(BLOCK_ID);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  IWorld world, BlockPos blockPos, BlockPos otherBlockPos) {
        return world.getBlockState(otherBlockPos).is(Blocks.WATER) ? BlockRegistry.GINGERBREAD_BLOCK.get().defaultBlockState()
                : blockState;
    }
}
