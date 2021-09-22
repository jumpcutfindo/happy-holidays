package com.jumpcutfindo.happyholidays.client.entity;

import com.jumpcutfindo.happyholidays.client.entity.model.ExplosivePresentModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.ExplosivePresentEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class ExplosivePresentRenderer extends GeoProjectilesRenderer<ExplosivePresentEntity> {
    public ExplosivePresentRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ExplosivePresentModel<>());
        this.shadowRadius = 0.0F;
    }

    @Override
    public void render(ExplosivePresentEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0D, 0.5D, 0.0D);
        if ((float) entityIn.getLife() - partialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float) entityIn.getLife() - partialTicks + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f = f * f;
            f = f * f;
            float f1 = 1.0F + f * 0.3F;
            matrixStackIn.scale(f1, f1, f1);
        }

        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        matrixStackIn.translate(-0.5D, -0.5D, 0.5D);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));

        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
