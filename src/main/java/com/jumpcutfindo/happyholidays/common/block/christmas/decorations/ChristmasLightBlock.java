package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import com.jumpcutfindo.happyholidays.common.block.MultifaceDecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ChristmasLightBlock extends MultifaceDecorationBlock implements ChristmasLike, ChristmasBlock, BasicOrnament {
    public static final String RED_CHRISTMAS_LIGHTS_ID = "red_christmas_lights";
    public static final String BLUE_CHRISTMAS_LIGHTS_ID = "blue_christmas_lights";
    public static final String YELLOW_CHRISTMAS_LIGHTS_ID = "yellow_christmas_lights";
    public static final String GREEN_CHRISTMAS_LIGHTS_ID = "green_christmas_lights";
    public static final String GOLD_CHRISTMAS_LIGHTS_ID = "gold_christmas_lights";
    public static final String SILVER_CHRISTMAS_LIGHTS_ID = "silver_christmas_lights";

    private static final BlockBehaviour.Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.GLASS)
                    .strength(0.1f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .noCollission()
                    .lightLevel((blockState) -> 14);

    public ChristmasLightBlock() {
        super(BLOCK_PROPERTIES);
    }

    @Override
    public void configure() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.translucent());
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.COMMON;
    }
}
