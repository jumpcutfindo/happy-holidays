package com.jumpcutfindo.happyholidays.client.entity;

import com.jumpcutfindo.happyholidays.client.entity.model.ExplosivePresentModel;
import com.jumpcutfindo.happyholidays.client.entity.model.SleighModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.ExplosivePresentEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.SleighEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TNTMinecartRenderer;
import net.minecraft.client.renderer.entity.TNTRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class ExplosivePresentRenderer extends GeoProjectilesRenderer<ExplosivePresentEntity> {
    public ExplosivePresentRenderer(EntityRendererManager renderManager) {
        super(renderManager, new ExplosivePresentModel<>());
        this.shadowRadius = 0.0F;
    }

    @Override
    public void render(ExplosivePresentEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0D, 0.5D, 0.0D);
        if ((float) entityIn.getLife() - partialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float) entityIn.getLife() - partialTicks + 1.0F) / 10.0F;
            f = MathHelper.clamp(f, 0.0F, 1.0F);
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
