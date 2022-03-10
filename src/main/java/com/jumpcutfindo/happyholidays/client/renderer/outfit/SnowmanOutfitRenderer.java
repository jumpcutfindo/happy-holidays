package com.jumpcutfindo.happyholidays.client.renderer.outfit;

import com.jumpcutfindo.happyholidays.client.model.outfit.SnowmanOutfitModel;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.ChristmasOutfits;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.world.entity.decoration.ArmorStand;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.util.GeoUtils;

public class SnowmanOutfitRenderer extends GeoArmorRenderer<ChristmasOutfits.SnowmanOutfitItem> {
    public SnowmanOutfitRenderer() {
        super(new SnowmanOutfitModel());
        this.headBone = "helmet";
        this.bodyBone = "chestplate";
        this.rightArmBone = "rightArm";
        this.leftArmBone = "leftArm";
        this.rightLegBone = "rightLeg";
        this.leftLegBone = "leftLeg";
        this.rightBootBone = "rightBoot";
        this.leftBootBone = "leftBoot";
    }

    @Override
    protected void fitToBiped() {
        AnimatedGeoModel<ChristmasOutfits.SnowmanOutfitItem> modelProvider = getGeoModelProvider();
        if (!(this.entityLiving instanceof ArmorStand)) {
            if (this.headBone != null) {
                IBone headBone = modelProvider.getBone(this.headBone);
                GeoUtils.copyRotations(this.head, headBone);
                headBone.setPositionX(this.head.x);
                headBone.setPositionY(-this.head.y);
                headBone.setPositionZ(this.head.z);
            }

            if (this.bodyBone != null) {
                IBone bodyBone = modelProvider.getBone(this.bodyBone);
                GeoUtils.copyRotations(this.body, bodyBone);
                bodyBone.setPositionX(this.body.x);
                bodyBone.setPositionY(-this.body.y);
                bodyBone.setPositionZ(this.body.z);
            }

            if (this.rightArmBone != null) {
                IBone rightArmBone = modelProvider.getBone(this.rightArmBone);
                GeoUtils.copyRotations(this.rightArm, rightArmBone);
                rightArmBone.setPositionX(this.rightArm.x + 5);
                rightArmBone.setPositionY(2 - this.rightArm.y);
                rightArmBone.setPositionZ(this.rightArm.z);
            }

            if (this.leftArmBone != null) {
                IBone leftArmBone = modelProvider.getBone(this.leftArmBone);
                GeoUtils.copyRotations(this.leftArm, leftArmBone);
                leftArmBone.setPositionX(this.leftArm.x - 5);
                leftArmBone.setPositionY(2 - this.leftArm.y);
                leftArmBone.setPositionZ(this.leftArm.z);
            }
        }
    }

    @Override
    public void render(float partialTicks, PoseStack stack, VertexConsumer bufferIn, int packedLightIn) {
        this.body.setRotation(0.0f, 0.0f, 0.0f);
        super.render(partialTicks, stack, bufferIn, packedLightIn);
    }
}
