package com.jumpcutfindo.happyholidays.client.model.entity;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GrinchModel<T extends GrinchEntity> extends AnimatedGeoModel<GrinchEntity> {
    @Override
    public ResourceLocation getModelLocation(GrinchEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/grinch.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GrinchEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/grinch.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GrinchEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/grinch.animation.json");
    }

    @Override
    public void setLivingAnimations(GrinchEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        IBone head = this.getAnimationProcessor().getBone("head_half");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
