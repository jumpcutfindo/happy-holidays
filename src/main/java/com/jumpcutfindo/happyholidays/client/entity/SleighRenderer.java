package com.jumpcutfindo.happyholidays.client.entity;

import com.jumpcutfindo.happyholidays.client.entity.model.SantaModel;
import com.jumpcutfindo.happyholidays.client.entity.model.SleighModel;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.SleighEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class SleighRenderer extends GeoProjectilesRenderer<SleighEntity> {
    public SleighRenderer(EntityRendererManager renderManager) {
        super(renderManager, new SleighModel<>());
        this.shadowRadius = 1.0F;
    }

    @Override
    public void render(SleighEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entityIn.isDiagonal()) matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0f));
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
