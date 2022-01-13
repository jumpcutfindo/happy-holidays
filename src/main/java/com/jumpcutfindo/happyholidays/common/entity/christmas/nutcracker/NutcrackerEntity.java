package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import com.jumpcutfindo.happyholidays.common.entity.christmas.IChristmasEntity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class NutcrackerEntity extends PathfinderMob implements IAnimatable, IChristmasEntity {
    public static final String ENTITY_ID = "nutcracker";
    public static final AttributeSupplier ENTITY_ATTRIBUTES =
            Mob.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 25.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.23f)
                    .build();

    public static final float ENTITY_BOX_SIZE = 0.8f;
    public static final float ENTITY_BOX_HEIGHT = 3.0f;

    public static final EntityDataAccessor<Boolean> DATA_MOUTH_OPEN = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.BOOLEAN);

    private AnimationFactory factory = new AnimationFactory(this);

    public NutcrackerEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new RandomMouthMovementGoal(this));
    }

    public boolean isMouthOpen() {
        return this.entityData.get(DATA_MOUTH_OPEN);
    }

    public void openMouth() {
        this.entityData.set(DATA_MOUTH_OPEN, true);
    }

    public void closeMouth() {
        this.entityData.set(DATA_MOUTH_OPEN, false);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MOUTH_OPEN, false);
    }

    private <E extends NutcrackerEntity> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle", true));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private static class RandomMouthMovementGoal extends Goal {
        private final NutcrackerEntity nutcracker;

        private boolean isActive, isStart = true;
        private int timer = 0;

        public RandomMouthMovementGoal(NutcrackerEntity nutcracker) {
            this.nutcracker = nutcracker;
        }

        @Override
        public boolean canUse() {
            boolean flag = this.nutcracker.getRandom().nextFloat() < 0.02F;
            if (flag) this.isActive = true;
            return flag || this.isActive;
        }

        @Override
        public void tick() {
            if (this.isStart) {
                this.timer = this.nutcracker.getRandom().nextInt(40, 80);
                this.isStart = false;
                this.nutcracker.openMouth();
            }

            if (this.timer == 0) {
                this.nutcracker.closeMouth();
                this.isActive = false;
                this.isStart = true;
            }

            this.timer--;
        }
    }
}
