package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import com.jumpcutfindo.happyholidays.common.block.MultifaceDecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class TinselBlock extends MultifaceDecorationBlock implements ChristmasBlock, BasicOrnament {
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
}
