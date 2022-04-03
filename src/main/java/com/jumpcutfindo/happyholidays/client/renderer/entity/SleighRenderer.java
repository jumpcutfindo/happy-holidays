package com.jumpcutfindo.happyholidays.client.renderer.entity;

import com.jumpcutfindo.happyholidays.client.model.entity.SleighModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.SleighEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class SleighRenderer extends GeoProjectilesRenderer<SleighEntity> {
    public SleighRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SleighModel<>());
        this.shadowRadius = 1.0F;
    }

    @Override
    public void render(SleighEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        if (entityIn.isDiagonal()) matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0f));
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
