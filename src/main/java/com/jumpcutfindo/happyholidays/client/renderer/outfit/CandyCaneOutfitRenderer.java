package com.jumpcutfindo.happyholidays.client.renderer.outfit;

import com.jumpcutfindo.happyholidays.client.model.outfit.CandyCaneOutfitModel;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.OutfitItem;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class CandyCaneOutfitRenderer extends GeoArmorRenderer<OutfitItem> {
    public CandyCaneOutfitRenderer() {
        super(new CandyCaneOutfitModel());
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
