package com.jumpcutfindo.happyholidays.client.renderer.outfit;

import com.jumpcutfindo.happyholidays.client.model.outfit.GingerbreadOutfitModel;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.OutfitItem;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class GingerbreadOutfitRenderer extends GeoArmorRenderer<OutfitItem> {
    public GingerbreadOutfitRenderer() {
        super(new GingerbreadOutfitModel());
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
