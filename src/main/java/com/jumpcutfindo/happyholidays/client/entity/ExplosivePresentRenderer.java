package com.jumpcutfindo.happyholidays.client.entity;

import com.jumpcutfindo.happyholidays.client.entity.model.ExplosivePresentModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.ExplosivePresentEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
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
        matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(-90.0F));
        int i = entityIn.getLife();
        if ((float)i - partialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - partialTicks + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            matrixStackIn.scale(f1, f1, f1);
        }

        // Render white overlay
        if (i / 5 % 2 == 0) {
            matrixStackIn.pushPose();
            matrixStackIn.mulPose(Vector3f.YN.rotationDegrees(90.0F));
            matrixStackIn.translate(-0.5d, 0.0d, -0.5d);
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ChristmasBlocks.EXPLOSIVE_PRESENT.get().defaultBlockState(), matrixStackIn, bufferIn, packedLightIn, OverlayTexture.pack(OverlayTexture.u(1.0F), 10));
            matrixStackIn.popPose();
        } else {
            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }

        matrixStackIn.popPose();
    }
}
