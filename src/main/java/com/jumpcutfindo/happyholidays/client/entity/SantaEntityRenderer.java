package com.jumpcutfindo.happyholidays.client.entity;

import com.jumpcutfindo.happyholidays.client.entity.model.SantaModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SantaEntityRenderer extends GeoEntityRenderer<BaseSantaEntity> {
    public SantaEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SantaModel<>());
        this.shadowRadius = 1.0F;
    }
}
