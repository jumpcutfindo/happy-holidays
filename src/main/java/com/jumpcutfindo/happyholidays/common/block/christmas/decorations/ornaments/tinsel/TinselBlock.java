package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.tinsel;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ConnectedOrnamentBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class TinselBlock extends ConnectedOrnamentBlock {
    private static final VoxelShape TINSEL_SHAPE = VoxelShapes.or(
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0 ,1.0)
    );

    private static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.WOOL)
                    .harvestLevel(-1)
                    .strength(0.1f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .noCollission();

    public TinselBlock(String blockId) {
        super(BLOCK_PROPERTIES, TINSEL_SHAPE);
    }
}
