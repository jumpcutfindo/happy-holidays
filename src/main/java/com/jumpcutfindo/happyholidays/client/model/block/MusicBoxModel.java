package com.jumpcutfindo.happyholidays.client.model.block;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.MusicBoxBlockEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MusicBoxModel extends AnimatedGeoModel<MusicBoxBlockEntity> {
    @Override
    public ResourceLocation getModelLocation(MusicBoxBlockEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/music_box.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MusicBoxBlockEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/block/music_box.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MusicBoxBlockEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/music_box.animation.json");
    }
}
