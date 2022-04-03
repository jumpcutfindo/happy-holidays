package com.jumpcutfindo.happyholidays.client.renderer.block;

import com.jumpcutfindo.happyholidays.client.model.block.MusicBoxModel;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.MusicBoxBlockEntity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class MusicBoxRenderer extends GeoBlockRenderer<MusicBoxBlockEntity> {
    public MusicBoxRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new MusicBoxModel());
    }
}
