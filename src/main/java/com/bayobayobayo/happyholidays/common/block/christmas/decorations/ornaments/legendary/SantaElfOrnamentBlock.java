package com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class SantaElfOrnamentBlock extends LegendaryOrnamentBlock {
    public static final String BLOCK_ID = "santa_elf_ornament_block";

    public static final VoxelShape[] ORNAMENT_SHAPES = {
            Block.box(4.0, 0.0, 4.0, 12.0, 10.0, 12.0),
            Block.box(4.0, 4.75, 4.0, 12.0, 14.75, 12.0),
            Block.box(4.0, 0.0, 0.0, 12.0, 11.0, 9.0)
    };

    public SantaElfOrnamentBlock() {
        super(BLOCK_ID, ORNAMENT_SHAPES);
    }
}
