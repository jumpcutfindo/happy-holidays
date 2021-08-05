package com.jumpcutfindo.happyholidays.client.block;

import com.jumpcutfindo.happyholidays.client.block.model.MusicBoxModel;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.MusicBoxTileEntity;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class MusicBoxRenderer extends GeoBlockRenderer<MusicBoxTileEntity> {
    public MusicBoxRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new MusicBoxModel());
    }
}
