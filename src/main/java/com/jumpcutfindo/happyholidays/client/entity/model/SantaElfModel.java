package com.jumpcutfindo.happyholidays.client.entity.model;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SantaElfModel<T extends SantaElfEntity> extends AnimatedGeoModel<SantaElfEntity> {
    @Override
    public ResourceLocation getModelLocation(SantaElfEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/santa_elf.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SantaElfEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entities/santa_elf.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SantaElfEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/santa_elf.animation.json");
    }
}
