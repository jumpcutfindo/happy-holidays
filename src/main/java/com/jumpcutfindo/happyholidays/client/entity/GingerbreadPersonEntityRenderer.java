package com.jumpcutfindo.happyholidays.client.entity;

import com.jumpcutfindo.happyholidays.client.entity.model.GingerbreadPersonModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadPersonEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GingerbreadPersonEntityRenderer extends GeoEntityRenderer<GingerbreadPersonEntity> {
    public GingerbreadPersonEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GingerbreadPersonModel<>());
        this.shadowRadius = 0.5F;
    }
}
