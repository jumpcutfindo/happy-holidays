package com.jumpcutfindo.happyholidays.client.renderer.entity;

import com.jumpcutfindo.happyholidays.client.model.entity.WalnutModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.WalnutEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class WalnutRenderer extends GeoProjectilesRenderer<WalnutEntity> {
    public WalnutRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WalnutModel());
        this.shadowRadius = 0.25F;
    }

    @Override
    public void render(WalnutEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
