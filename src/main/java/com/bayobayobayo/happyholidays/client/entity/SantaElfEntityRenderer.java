package com.bayobayobayo.happyholidays.client.entity;

import com.bayobayobayo.happyholidays.client.entity.model.SantaElfModel;
import com.bayobayobayo.happyholidays.common.entity.christmas.elf.SantaElfEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SantaElfEntityRenderer extends GeoEntityRenderer<SantaElfEntity> {
    public SantaElfEntityRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SantaElfModel<>());
        this.shadowRadius = 0.5F;
    }
}
