package com.jumpcutfindo.happyholidays.common.entity.christmas.santa;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.entity.christmas.IChristmasEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.server.data.SantaSavedData;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BaseSantaEntity extends PathfinderMob implements IAnimatable, IChristmasEntity {
    public static final float MAX_HEALTH = 200.0f;

    public static final AttributeSupplier ENTITY_ATTRIBUTES =
            createMobAttributes()
                    .add(Attributes.MAX_HEALTH, MAX_HEALTH)
                    .add(Attributes.MOVEMENT_SPEED, 0.30D)
                    .add(Attributes.ATTACK_DAMAGE, 8.0D)
                    .add(Attributes.ATTACK_KNOCKBACK, 2.0D)
                    .add(Attributes.ARMOR, 6.0D)
                    .build();

    public static final float ENTITY_BOX_SIZE = 18.0f / 16.0f;
    public static final float ENTITY_BOX_HEIGHT = 56.0f / 16.0f;

    public static final int SUMMON_SANTA_DURATION = 200;

    public static final int NAUGHTY_NICE_CONSIDERATION_RADIUS = 40;

    private AnimationFactory factory = new AnimationFactory(this);


    public BaseSantaEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
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

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return ChristmasSounds.SANTA_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ChristmasSounds.SANTA_HURT.get();
    }
    
    @Override
    protected int calculateFallDamage(float p_225508_1_, float p_225508_2_) {
        return 0;
    }

    public void onDefeat(ServerLevel serverLevel) {
        SantaSavedData santaData = serverLevel.getDataStorage().computeIfAbsent(
                SantaSavedData::createFromTag,
                SantaSavedData::new,
                SantaSavedData.DATA_NAME
        );

        santaData.setDefeated();
    }
}
