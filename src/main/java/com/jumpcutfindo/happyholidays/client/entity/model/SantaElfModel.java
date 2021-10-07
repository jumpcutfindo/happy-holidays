package com.jumpcutfindo.happyholidays.client.entity.model;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SantaElfModel<T extends SantaElfEntity> extends AnimatedGeoModel<SantaElfEntity> {
    @Override
    public ResourceLocation getModelLocation(SantaElfEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/santa_elf.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SantaElfEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/santa_elf.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SantaElfEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/santa_elf.animation.json");
    }

    @Override
    public void setLivingAnimations(SantaElfEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
