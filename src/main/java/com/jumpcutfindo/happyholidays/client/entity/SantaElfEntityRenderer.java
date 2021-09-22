package com.jumpcutfindo.happyholidays.client.entity;

import com.jumpcutfindo.happyholidays.client.entity.model.SantaElfModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SantaElfEntityRenderer extends GeoEntityRenderer<SantaElfEntity> {
    public SantaElfEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SantaElfModel<>());
        this.shadowRadius = 0.5F;
    }
}
