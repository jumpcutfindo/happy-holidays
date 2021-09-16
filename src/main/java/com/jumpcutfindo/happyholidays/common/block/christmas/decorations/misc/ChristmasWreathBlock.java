package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChristmasWreathBlock extends WallDecorationBlock {
    public static final String BLOCK_ID = "christmas_wreath";

    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.WOOL)
                    .strength(0.1f)
                    .sound(SoundType.CROP)
                    .noOcclusion()
                    .noCollission();

    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);

    public ChristmasWreathBlock() {
        super(BLOCK_PROPERTIES, SHAPE);
    }
}
