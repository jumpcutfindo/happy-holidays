package com.jumpcutfindo.happyholidays.common.utils;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BlockUtils {
    public static boolean isWet(BlockState blockState) {
        return blockState.is(Blocks.WATER) || isWaterlogged(blockState);
    }

    public static boolean isWaterlogged(BlockState blockState) {
        return blockState.hasProperty(BlockStateProperties.WATERLOGGED) && blockState.getValue(BlockStateProperties.WATERLOGGED);
    }
}
