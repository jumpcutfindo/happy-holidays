package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ConnectedOrnamentBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class ChristmasLightBlock extends ConnectedOrnamentBlock {
    public static final String RED_CHRISTMAS_LIGHTS_ID = "red_christmas_lights";
    public static final String BLUE_CHRISTMAS_LIGHTS_ID = "blue_christmas_lights";
    public static final String YELLOW_CHRISTMAS_LIGHTS_ID = "yellow_christmas_lights";
    public static final String GREEN_CHRISTMAS_LIGHTS_ID = "green_christmas_lights";
    public static final String GOLD_CHRISTMAS_LIGHTS_ID = "gold_christmas_lights";
    public static final String SILVER_CHRISTMAS_LIGHTS_ID = "silver_christmas_lights";

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

    public ChristmasLightBlock() {
        super(BLOCK_PROPERTIES, CHRISTMAS_LIGHT_SHAPE);
    }
}
