package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.wall;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.WallOrnamentBlock;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class ChristmasWreathBlock extends WallOrnamentBlock {
    public static final String BLOCK_ID = "christmas_wreath_block";
    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);

    public ChristmasWreathBlock() {
        super(BLOCK_ID, SHAPE);
    }
}
