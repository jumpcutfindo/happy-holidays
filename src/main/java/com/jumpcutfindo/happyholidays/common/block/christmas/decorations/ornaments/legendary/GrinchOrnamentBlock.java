package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class GrinchOrnamentBlock extends LegendaryOrnamentBlock {
    public static final String BLOCK_ID = "grinch_ornament_block";

    public static final VoxelShape[][] ORNAMENT_SHAPES = {
            {
                    Block.box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0),
                    Block.box(4.0, 4.0, 3.0, 12.0, 10.0, 13.0)
            },
            {
                    Block.box(3.0, 3.75, 3.0, 13.0, 7.75, 13.0),
                    Block.box(4.0, 7.75, 3.0, 12.0, 13.75, 13.0)
            },
            { Block.box(3.0, 0.0, 0.0, 13.0, 11.0, 10.0) }

    };

    public GrinchOrnamentBlock() {
        super(BLOCK_ID, ORNAMENT_SHAPES);
    }
}
