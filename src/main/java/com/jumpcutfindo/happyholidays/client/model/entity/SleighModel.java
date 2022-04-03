package com.jumpcutfindo.happyholidays.client.model.entity;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.SleighEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SleighModel<T extends SleighEntity> extends AnimatedGeoModel<SleighEntity> {
    @Override
    public ResourceLocation getModelLocation(SleighEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/sleigh.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SleighEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/sleigh.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SleighEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/sleigh.animation.json");
    }
}
