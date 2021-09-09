package com.jumpcutfindo.happyholidays.common.block.christmas.presents;

import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class AdultPresentBlock extends PresentBlock {
    public static final String BLOCK_ID = "adult_present";
    public static final VoxelShape SHAPE = VoxelShapes.or(
            box(3.5, 0.0, 3.5, 12.5, 6.0, 12.5),
            box(2.5, 6.0, 2.5, 13.5, 8.0, 13.5)
    );

    public AdultPresentBlock() {
        super(BLOCK_ID, SHAPE);
    }
}
