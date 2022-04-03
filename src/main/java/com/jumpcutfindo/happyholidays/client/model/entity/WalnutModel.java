package com.jumpcutfindo.happyholidays.client.model.entity;

import org.jetbrains.annotations.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.WalnutEntity;
import com.jumpcutfindo.happyholidays.common.item.christmas.WalnutAmmo;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WalnutModel extends AnimatedGeoModel<WalnutEntity> {
    @Override
    public ResourceLocation getModelLocation(WalnutEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/walnut.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(WalnutEntity object) {
        return object.getResourceLocation();
    }

    @Override
    public ResourceLocation getAnimationFileLocation(WalnutEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/walnut.animation.json");
    }

    @Override
    public void setLivingAnimations(WalnutEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        IBone walnutTop = this.getAnimationProcessor().getBone("walnutTop");

        walnutTop.setHidden(entity.getAmmoType() == WalnutAmmo.HALVED);
    }
}
