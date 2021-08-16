package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.wall;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.WallDecorationBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.shapes.VoxelShape;

public class ChristmasWreathBlock extends WallDecorationBlock {
    public static final String BLOCK_ID = "christmas_wreath_block";

    public static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.WOOL)
                    .harvestLevel(-1)
                    .strength(0.1f)
                    .sound(SoundType.CROP)
                    .noOcclusion()
                    .noCollission();

    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);

    public ChristmasWreathBlock() {
        super(BLOCK_ID, BLOCK_PROPERTIES, SHAPE);
    }
}
