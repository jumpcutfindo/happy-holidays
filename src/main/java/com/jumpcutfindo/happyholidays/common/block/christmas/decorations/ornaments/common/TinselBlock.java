package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common;

import com.jumpcutfindo.happyholidays.common.block.MultifaceDecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.BasicOrnament;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class TinselBlock extends MultifaceDecorationBlock implements ChristmasLike, ChristmasBlock, BasicOrnament {
    public static final String RED_TINSEL_ID = "red_tinsel";
    public static final String BLUE_TINSEL_ID = "blue_tinsel";
    public static final String YELLOW_TINSEL_ID = "yellow_tinsel";
    public static final String GREEN_TINSEL_ID = "green_tinsel";
    public static final String GOLD_TINSEL_ID = "gold_tinsel";
    public static final String SILVER_TINSEL_ID = "silver_tinsel";

    private static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.WOOL)
                    .strength(0.1f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .noCollission();

    public TinselBlock() {
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
