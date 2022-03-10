package com.jumpcutfindo.happyholidays.client.renderer.outfit;

import com.jumpcutfindo.happyholidays.client.model.outfit.NutcrackerOutfitModel;
import com.jumpcutfindo.happyholidays.common.item.outfits.OutfitItem;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class NutcrackerOutfitRenderer extends GeoArmorRenderer<OutfitItem> {
    public NutcrackerOutfitRenderer() {
        super(new NutcrackerOutfitModel());
        this.headBone = "helmet";
        this.bodyBone = "chestplate";
        this.rightArmBone = "rightArm";
        this.leftArmBone = "leftArm";
        this.rightLegBone = "rightLeg";
        this.leftLegBone = "leftLeg";
        this.rightBootBone = "rightBoot";
        this.leftBootBone = "leftBoot";
    }
}
