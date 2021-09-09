package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class AdultPresentOrnamentBlock extends LegendaryOrnamentBlock {
    public static final String BLOCK_ID = "adult_present_ornament";

    public static final VoxelShape[][] ORNAMENT_SHAPES = {
            {
                    Block.box(6.0, 0.0, 6.0, 10.0, 3.0, 10.0),
                    Block.box(5.5, 3.0, 5.5, 10.5, 4.0, 10.5)
            },
            {
                    Block.box(6.0, 10.5, 6.0, 10.0, 13.5, 10.0),
                    Block.box(5.5, 13.5, 5.5, 10.5, 14.5, 10.5)
            },
            { Block.box(5.0, 0.0, 0.0, 11.0, 5.0, 4.5) }
    };

    public AdultPresentOrnamentBlock() {
        super(BLOCK_ID, ORNAMENT_SHAPES);
    }
}
