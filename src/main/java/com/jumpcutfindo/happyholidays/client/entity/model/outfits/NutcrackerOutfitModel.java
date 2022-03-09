package com.jumpcutfindo.happyholidays.client.entity.model.outfits;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.OutfitItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NutcrackerOutfitModel extends AnimatedGeoModel<OutfitItem> {
    @Override
    public ResourceLocation getModelLocation(OutfitItem object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/outfits/nutcracker_outfit.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(OutfitItem object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/outfit/christmas/nutcracker_outfit.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(OutfitItem animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/outfits/nutcracker_outfit.animation.json");
    }
}
