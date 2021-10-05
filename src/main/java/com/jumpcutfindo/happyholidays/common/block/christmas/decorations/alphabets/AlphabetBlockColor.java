package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.alphabets;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ChristmasBlockColor;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class AlphabetBlockColor implements BlockColor {
    @Override
    public int getColor(BlockState blockState, @Nullable BlockAndTintGetter tintGetter, @Nullable BlockPos blockPos, int p_92570_) {
        if (!(blockState.getBlock() instanceof AlphabetOrnamentBlock)) return -1;
        else {
            ChristmasBlockColor blockColor = blockState.getValue(AlphabetOrnamentBlock.BLOCK_COLOR);

            switch (blockColor) {
            case RED -> {
                return 15278884;
            }
            case BLUE -> {
                return 2631923;
            }
            case YELLOW -> {
                return 16773640;
            }
            case GREEN -> {
                return 3000365;
            }
            case GOLD -> {
                return 16764738;
            }
            case SILVER -> {
                return -1;
            }
            case NONE -> {
                return 14811135;
            }
            }
        }

        return 0;
    }
}
