package com.jumpcutfindo.happyholidays.client.entity.model;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.SoggyGingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.HappySantaEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SantaModel<T extends BaseSantaEntity> extends AnimatedGeoModel<BaseSantaEntity> {
    @Override
    public ResourceLocation getModelLocation(BaseSantaEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/santa.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BaseSantaEntity object) {
        if (object instanceof HappySantaEntity) {
            return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entities/happy_santa.png");
        } else {
            return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entities/angry_santa.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BaseSantaEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/santa.animation.json");
    }
}
