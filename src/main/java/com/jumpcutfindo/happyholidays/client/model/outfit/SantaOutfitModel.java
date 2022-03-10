package com.jumpcutfindo.happyholidays.client.model.outfit;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.outfits.OutfitItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SantaOutfitModel extends AnimatedGeoModel<OutfitItem> {
    @Override
    public ResourceLocation getModelLocation(OutfitItem object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/outfits/santa_outfit.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(OutfitItem object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/outfit/christmas/santa_outfit.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(OutfitItem animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/outfits/santa_outfit.animation.json");
    }
}
