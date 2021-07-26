package com.bayobayobayo.happyholidays.client.block.model;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.tileentity.christmas.MusicBoxTileEntity;

import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MusicBoxModel extends AnimatedGeoModel<MusicBoxTileEntity> {
    @Override
    public ResourceLocation getModelLocation(MusicBoxTileEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/music_box_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MusicBoxTileEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/blocks/music_box_block.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MusicBoxTileEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/music_box_block.animation.json");
    }
}
