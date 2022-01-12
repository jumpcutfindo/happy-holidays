package com.jumpcutfindo.happyholidays.client.entity.model;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NutcrackerModel<T extends NutcrackerEntity> extends AnimatedGeoModel<NutcrackerEntity> {
    @Override
    public ResourceLocation getModelLocation(NutcrackerEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/nutcracker.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NutcrackerEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/nutcracker.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NutcrackerEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/nutcracker.animation.json");
    }

    @Override
    public void setLivingAnimations(NutcrackerEntity nutcracker, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(nutcracker, uniqueID, customPredicate);

        // Handle mouth open / closed state
        IBone openMouthBone = this.getAnimationProcessor().getBone("openMouth");
        IBone closedMouthBone = this.getAnimationProcessor().getBone("closedMouth");
        openMouthBone.setHidden(!nutcracker.isMouthOpen());
        closedMouthBone.setHidden(nutcracker.isMouthOpen());
    }
}
