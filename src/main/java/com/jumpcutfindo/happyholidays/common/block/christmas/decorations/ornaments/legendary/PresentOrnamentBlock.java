package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class PresentOrnamentBlock extends LegendaryOrnamentBlock {
    public static final String BLOCK_ID = "present_ornament_block";

    public static final VoxelShape[] ORNAMENT_SHAPES = {
            VoxelShapes.or(
                    Block.box(6.0, 0.0, 6.0, 10.0, 3.0, 10.0),
                    Block.box(5.5, 3.0, 5.5, 10.5, 4.0, 10.5)
            ),
            VoxelShapes.or(
                    Block.box(6.0, 10.5, 6.0, 10.0, 13.5, 10.0),
                    Block.box(5.5, 13.5, 5.5, 10.5, 14.5, 10.5)
            ),
            Block.box(5.0, 0.0, 0.0, 11.0, 5.0, 4.5)
    };

    public PresentOrnamentBlock() {
        super(BLOCK_ID, ORNAMENT_SHAPES);
    }
}
