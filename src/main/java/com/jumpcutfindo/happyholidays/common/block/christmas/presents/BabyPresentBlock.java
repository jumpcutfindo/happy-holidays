package com.jumpcutfindo.happyholidays.common.block.christmas.presents;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class BabyPresentBlock extends PresentBlock {
    public static final String BLOCK_ID = "baby_present";
    public static final VoxelShape SHAPE = VoxelShapes.or(
            Block.box(5.0, 0.0, 5.0, 11.0, 4.0, 11.0),
            Block.box(4.25, 4.0, 4.25, 11.75, 5.5 ,11.75)
    );

    public BabyPresentBlock() {
        super(BLOCK_ID, SHAPE);
    }
}
