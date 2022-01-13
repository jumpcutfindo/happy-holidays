package com.jumpcutfindo.happyholidays.client.entity.model;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.WalnutEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WalnutModel extends AnimatedGeoModel<WalnutEntity> {
    @Override
    public ResourceLocation getModelLocation(WalnutEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/walnut.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WalnutEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/walnut.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WalnutEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/walnut.animation.json");
    }
}
