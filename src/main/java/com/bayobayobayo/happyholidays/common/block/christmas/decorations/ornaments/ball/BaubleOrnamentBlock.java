package com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball;

import com.bayobayobayo.happyholidays.common.block.christmas.decorations.OrnamentBlock;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class BaubleOrnamentBlock extends OrnamentBlock {
    public static final VoxelShape[] BAUBLE_SHAPES = new VoxelShape[] {
            Block.box(6.0, 0.0, 6.0, 10.0, 5.0, 10.0),
            Block.box(6.0, 9.5, 6.0, 10.0, 13.5, 10.0),
            Block.box(6.0, 0.0, 4.0, 10.0, 6.0, 0.0)
    };

    public BaubleOrnamentBlock(String blockId) {
        super(blockId, BAUBLE_SHAPES);
    }
}
