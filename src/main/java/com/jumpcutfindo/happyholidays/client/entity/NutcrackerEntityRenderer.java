package com.jumpcutfindo.happyholidays.client.entity;

import com.jumpcutfindo.happyholidays.client.entity.model.NutcrackerModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NutcrackerEntityRenderer extends GeoEntityRenderer<NutcrackerEntity> {
    public NutcrackerEntityRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NutcrackerModel<>());
        this.shadowRadius = 0.6f;
    }
}
