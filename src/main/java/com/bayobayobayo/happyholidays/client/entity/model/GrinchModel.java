package com.bayobayobayo.happyholidays.client.entity.model;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.entity.christmas.GrinchEntity;
import com.bayobayobayo.happyholidays.common.entity.christmas.elf.SantaElfEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrinchModel<T extends GrinchEntity> extends AnimatedGeoModel<GrinchEntity> {
    @Override
    public ResourceLocation getModelLocation(GrinchEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/grinch.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GrinchEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entities/grinch.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GrinchEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/grinch.animation.json");
    }
}
