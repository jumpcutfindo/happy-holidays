package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class PresentOrnamentBlock extends LegendaryOrnamentBlock {
    public static final String BLOCK_ID = "present_ornament_block";

    public static final VoxelShape[] ORNAMENT_SHAPES = {
            Block.box(5.5, 0, 5.5, 10.5, 4.0, 10.5),
            Block.box(5.5, 9.75, 5.5, 10.5, 16.0, 10.5),
            Block.box(5.5, 4.0, 0.0, 11.5, 10.0, 6.0)
    };

    public PresentOrnamentBlock() {
        super(BLOCK_ID, ORNAMENT_SHAPES);
    }
}
