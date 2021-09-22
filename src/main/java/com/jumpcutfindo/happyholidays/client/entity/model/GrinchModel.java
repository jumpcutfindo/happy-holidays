package com.jumpcutfindo.happyholidays.client.entity.model;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrinchModel<T extends GrinchEntity> extends AnimatedGeoModel<GrinchEntity> {
    @Override
    public ResourceLocation getModelLocation(GrinchEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/grinch.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GrinchEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/grinch.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GrinchEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/grinch.animation.json");
    }
}
