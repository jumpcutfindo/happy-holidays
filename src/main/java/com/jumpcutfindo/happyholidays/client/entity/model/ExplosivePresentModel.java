package com.jumpcutfindo.happyholidays.client.entity.model;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.ExplosivePresentEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.SleighEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ExplosivePresentModel<T extends ExplosivePresentEntity> extends AnimatedGeoModel<ExplosivePresentEntity> {
    @Override
    public ResourceLocation getModelLocation(ExplosivePresentEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/explosive_present.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ExplosivePresentEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/explosive_present.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ExplosivePresentEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/explosive_present.animation.json");
    }
}
