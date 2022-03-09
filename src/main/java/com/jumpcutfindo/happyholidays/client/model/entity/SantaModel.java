package com.jumpcutfindo.happyholidays.client.model.entity;

import org.jetbrains.annotations.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.happy.HappySantaEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class SantaModel<T extends BaseSantaEntity> extends AnimatedGeoModel<BaseSantaEntity> {
    @Override
    public ResourceLocation getModelLocation(BaseSantaEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/santa.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BaseSantaEntity object) {
        if (object instanceof HappySantaEntity) {
            return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/happy_santa.png");
        } else {
            return new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/angry_santa.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BaseSantaEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/santa.animation.json");
    }

    @Override
    public void setLivingAnimations(BaseSantaEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        // Add head rotation
        IBone head = this.getAnimationProcessor().getBone("head");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
