package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class CandyCaneOrnamentBlock extends LegendaryOrnamentBlock {
    public static final String BLOCK_ID = "candy_cane_ornament_block";

    public static final VoxelShape[] ORNAMENT_SHAPES = {
            Block.box(4.0, 0.0, 3.0, 12.0, 1.0, 13.0),
            Block.box(4.0, 2.0, 7.5, 12.0, 14.0, 8.5),
            Block.box(3.75, 0.0, 0.0, 11.0, 11.0, 3.0)
    };

    public CandyCaneOrnamentBlock() {
        super(BLOCK_ID, ORNAMENT_SHAPES);
    }
}
