package com.jumpcutfindo.happyholidays.client.renderer.outfit;

import com.jumpcutfindo.happyholidays.client.model.outfit.SantaElfOutfitModel;
import com.jumpcutfindo.happyholidays.common.item.outfits.OutfitItem;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SantaElfOutfitRenderer extends GeoArmorRenderer<OutfitItem> {
    public SantaElfOutfitRenderer() {
        super(new SantaElfOutfitModel());
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
