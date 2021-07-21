package com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel;

import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ConnectedOrnamentBlock;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class TinselBlock extends ConnectedOrnamentBlock {
    private static final VoxelShape TINSEL_SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(0.0, 0.0, 0.0, 16.0, 16.0 ,1.0)
    );

    public TinselBlock(String blockId) {
        super(blockId, TINSEL_SHAPE);
    }
}
