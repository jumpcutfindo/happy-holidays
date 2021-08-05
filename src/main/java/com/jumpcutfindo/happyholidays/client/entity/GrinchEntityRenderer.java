package com.jumpcutfindo.happyholidays.client.entity;

import com.jumpcutfindo.happyholidays.client.entity.model.GrinchModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.GrinchEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GrinchEntityRenderer extends GeoEntityRenderer<GrinchEntity> {
    public GrinchEntityRenderer(EntityRendererManager renderManager) {
        super(renderManager, new GrinchModel<>());
        this.shadowRadius = 0.6f;
    }
}
