package com.jumpcutfindo.happyholidays.client.renderer.entity;

import com.jumpcutfindo.happyholidays.client.model.entity.GrinchModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GrinchEntityRenderer extends GeoEntityRenderer<GrinchEntity> {
    public GrinchEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GrinchModel<>());
        this.shadowRadius = 0.6f;
    }
}
