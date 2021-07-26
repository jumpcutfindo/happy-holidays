package com.bayobayobayo.happyholidays.client.block;

import com.bayobayobayo.happyholidays.client.block.model.MusicBoxModel;
import com.bayobayobayo.happyholidays.common.tileentity.christmas.MusicBoxTileEntity;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class MusicBoxRenderer extends GeoBlockRenderer<MusicBoxTileEntity> {
    public MusicBoxRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new MusicBoxModel());
    }
}
