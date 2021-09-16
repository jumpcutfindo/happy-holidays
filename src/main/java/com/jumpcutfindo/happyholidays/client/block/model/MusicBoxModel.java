package com.jumpcutfindo.happyholidays.client.block.model;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.MusicBoxTileEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MusicBoxModel extends AnimatedGeoModel<MusicBoxTileEntity> {
    @Override
    public ResourceLocation getModelLocation(MusicBoxTileEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/music_box.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MusicBoxTileEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/block/music_box.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MusicBoxTileEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/music_box.animation.json");
    }
}
