package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class GingerbreadManOrnamentBlock extends LegendaryOrnamentBlock {
    public static final String BLOCK_ID = "gingerbread_man_ornament_block";

    public static final VoxelShape[] ORNAMENT_SHAPES = {
            Block.box(2.5, 0.0, 2.5, 13.5, 1.0, 13.5),
            Block.box(2.5, 3.5, 6.5, 13.5, 14.5, 9.5),
            Block.box(2.5, 0.0, 0.0, 13.5, 12.0, 5.0)
    };

    public GingerbreadManOrnamentBlock() {
        super(BLOCK_ID, ORNAMENT_SHAPES);
    }
}
