package com.jumpcutfindo.happyholidays.client.entity.model.outfits;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.SnowmanOutfitItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SnowmanOutfitModel extends AnimatedGeoModel<SnowmanOutfitItem> {
    @Override
    public ResourceLocation getModelLocation(SnowmanOutfitItem object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/outfits/snowman_outfit.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SnowmanOutfitItem object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/outfit/christmas/snowman_outfit.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SnowmanOutfitItem animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/outfits/snowman_outfit.animation.json");
    }
}
