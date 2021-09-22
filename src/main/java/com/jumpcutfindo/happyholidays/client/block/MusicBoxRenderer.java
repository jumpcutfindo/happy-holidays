package com.jumpcutfindo.happyholidays.client.block;

import com.jumpcutfindo.happyholidays.client.block.model.MusicBoxModel;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.MusicBoxBlockEntity;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class MusicBoxRenderer extends GeoBlockRenderer<MusicBoxBlockEntity> {
    public MusicBoxRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new MusicBoxModel());
    }
}
