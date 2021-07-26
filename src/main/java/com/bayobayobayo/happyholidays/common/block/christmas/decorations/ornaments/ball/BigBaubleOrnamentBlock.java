package com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball;

import com.bayobayobayo.happyholidays.common.block.christmas.decorations.OrnamentBlock;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class BigBaubleOrnamentBlock extends OrnamentBlock {
    public static final VoxelShape[] BIG_BAUBLE_SHAPES = new VoxelShape[] {
            Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0),
            Block.box(4.0, 3.75, 4.0, 12.0, 11.75, 12.0),
            Block.box(4.0, 0.0, 0.0, 12.0, 11.75, 8.0)
    };

    public BigBaubleOrnamentBlock(String blockId) {
        super(blockId, BIG_BAUBLE_SHAPES);
    }
}
