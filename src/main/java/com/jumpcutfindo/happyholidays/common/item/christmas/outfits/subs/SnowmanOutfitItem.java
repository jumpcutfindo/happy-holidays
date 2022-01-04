package com.jumpcutfindo.happyholidays.common.item.christmas.outfits.subs;

import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.OutfitItem;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class SnowmanOutfitItem extends OutfitItem {
    public SnowmanOutfitItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @Override
    public <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.snowman_outfit.walk"));
        }

        return super.predicate(event);
    }

    @Override
    public void registerControllers(AnimationData data) {
        super.registerControllers(data);
    }
}
