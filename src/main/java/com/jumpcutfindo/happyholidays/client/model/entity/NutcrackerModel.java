package com.jumpcutfindo.happyholidays.client.model.entity;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class NutcrackerModel<T extends NutcrackerEntity> extends AnimatedGeoModel<NutcrackerEntity> {
    @Override
    public ResourceLocation getModelLocation(NutcrackerEntity object) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "geo/christmas/nutcracker.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NutcrackerEntity object) {
        return object.getTexture();
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NutcrackerEntity animatable) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "animations/christmas/nutcracker.animation.json");
    }

    @Override
    public void setLivingAnimations(NutcrackerEntity nutcracker, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(nutcracker, uniqueID, customPredicate);

        IBone head = this.getAnimationProcessor().getBone("head");
        IBone openMouthBone = this.getAnimationProcessor().getBone("openMouth");
        IBone closedMouthBone = this.getAnimationProcessor().getBone("closedMouth");
        IBone nutBone = this.getAnimationProcessor().getBone("nut");
        IBone patrolFlagBone = this.getAnimationProcessor().getBone("patrolFlag");

        // Handle mouth open / closed state
        openMouthBone.setHidden(!nutcracker.isMouthOpen());
        closedMouthBone.setHidden(nutcracker.isMouthOpen());

        // Handle nutcracker's firing state
        if (nutcracker.isFiring()) {
            openMouthBone.setHidden(false);
            closedMouthBone.setHidden(true);
            nutBone.setHidden(false);
        } else {
            nutBone.setHidden(true);
        }

        // Handle damage level
        NutcrackerEntity.Damage damageLevel = nutcracker.getDamageLevel();
        this.adjustHatToDamage(damageLevel);

        // Handle patrol flag
        patrolFlagBone.setHidden(!nutcracker.isPatrolling());

        // Add head rotation

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }

    private void adjustHatToDamage(NutcrackerEntity.Damage damageLevel) {
        switch (damageLevel) {
        case NONE -> {
            this.setHatLayerHidden(false, false, false);
        }
        case LOW -> {
            this.setHatLayerHidden(true, false, false);
        }
        case MEDIUM -> {
            this.setHatLayerHidden(true, true, false);
        }
        case HIGH -> {
            this.setHatLayerHidden(true, true, true);
        }
        }
    }

    private void setHatLayerHidden(boolean layer1, boolean layer2, boolean layer3) {
        IBone hat1 = this.getAnimationProcessor().getBone("hat_1");
        IBone hat2 = this.getAnimationProcessor().getBone("hat_2");
        IBone hat3 = this.getAnimationProcessor().getBone("hat_3");

        hat1.setHidden(layer1);
        hat2.setHidden(layer2);
        hat3.setHidden(layer3);
    }
}
