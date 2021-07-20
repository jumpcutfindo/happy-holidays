package com.bayobayobayo.happyholidays.common.block.christmas.presents;

import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class ElderPresentBlock extends PresentBlock {
    public static final String BLOCK_ID = "elder_present_block";
    public static final VoxelShape SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0),
            HappyHolidaysUtils.createShape(1.0, 8.0, 1.0, 15.0, 11.0, 15.0)
    );

    public ElderPresentBlock() {
        super(BLOCK_ID, SHAPE);
    }
}