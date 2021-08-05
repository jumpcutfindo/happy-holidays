package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.lights;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ConnectedOrnamentBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class ChristmasLightBlock extends ConnectedOrnamentBlock {
    private static final VoxelShape CHRISTMAS_LIGHT_SHAPE = VoxelShapes.or(
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0 ,1.0)
    );

    private static final AbstractBlock.Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.GLASS)
                    .harvestLevel(-1)
                    .strength(0.1f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .noCollission()
                    .lightLevel((blockState) -> 14);

    public ChristmasLightBlock(String blockId) {
        super(blockId, BLOCK_PROPERTIES, CHRISTMAS_LIGHT_SHAPE);
    }
}
