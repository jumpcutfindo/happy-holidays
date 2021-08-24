package com.jumpcutfindo.happyholidays.common.entity.christmas.santa;

import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasEntity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BaseSantaEntity extends ChristmasEntity implements IAnimatable {
    public static final AttributeModifierMap ENTITY_ATTRIBUTES =
            createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 150.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.23D)
                    .add(Attributes.ATTACK_DAMAGE, 7.0D)
                    .add(Attributes.ATTACK_KNOCKBACK, 2.0D)
                    .build();

    public static final float ENTITY_BOX_SIZE = 18.0f / 16.0f;
    public static final float ENTITY_BOX_HEIGHT = 56.0f / 16.0f;

    public static final int SUMMON_SANTA_DURATION = 200;

    public static final int NAUGHTY_NICE_CONSIDERATION_RADIUS = 40;

    private AnimationFactory factory = new AnimationFactory(this);


    public BaseSantaEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving()) event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.walk", true));
        else event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.idle", true));

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
