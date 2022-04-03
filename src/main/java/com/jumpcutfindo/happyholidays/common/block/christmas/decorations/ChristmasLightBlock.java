package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import com.jumpcutfindo.happyholidays.common.block.MultifaceDecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ChristmasLightBlock extends MultifaceDecorationBlock implements ChristmasBlock, BasicOrnament {
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
}
