package com.jumpcutfindo.happyholidays.client.entity.model.outfits;

import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.OutfitItem;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ReindeerOutfitRenderer extends GeoArmorRenderer<OutfitItem> {
    public ReindeerOutfitRenderer() {
        super(new ReindeerOutfitModel());
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
