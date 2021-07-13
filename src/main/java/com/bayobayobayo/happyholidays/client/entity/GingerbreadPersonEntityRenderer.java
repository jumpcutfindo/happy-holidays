package com.bayobayobayo.happyholidays.client.entity;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.client.entity.model.GingerbreadPersonModel;
import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadEntities;
import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadPersonEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GingerbreadPersonEntityRenderer extends MobRenderer<GingerbreadPersonEntity, GingerbreadPersonModel<GingerbreadPersonEntity>> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(
            HappyHolidaysMod.MOD_ID,
            "textures/entities/gingerbread_man.png"
    );

    public GingerbreadPersonEntityRenderer(EntityRendererManager manager) {
        super(manager, new GingerbreadPersonModel<>(), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(GingerbreadPersonEntity entity) {
        return TEXTURE_LOCATION;
    }
}
