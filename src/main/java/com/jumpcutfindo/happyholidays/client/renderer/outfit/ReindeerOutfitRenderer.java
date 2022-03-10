package com.jumpcutfindo.happyholidays.client.renderer.outfit;

import com.jumpcutfindo.happyholidays.client.model.outfit.ReindeerOutfitModel;
import com.jumpcutfindo.happyholidays.common.item.outfits.OutfitItem;

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
