package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import com.jumpcutfindo.happyholidays.common.entity.christmas.IChristmasEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class NutcrackerEntity extends PathfinderMob implements IAnimatable, IChristmasEntity, RangedAttackMob {
    public static final String ENTITY_ID = "nutcracker";
    public static final AttributeSupplier ENTITY_ATTRIBUTES =
            Mob.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 25.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.23f)
                    .build();

    public static final float ENTITY_BOX_SIZE = 0.8f;
    public static final float ENTITY_BOX_HEIGHT = 3.0f;

    public static final int FIRING_DELAY = 5;

    public static final EntityDataAccessor<Boolean> DATA_MOUTH_OPEN = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> DATA_IS_FIRING = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.BOOLEAN);

    private AnimationFactory factory = new AnimationFactory(this);

    private LivingEntity target;

    public NutcrackerEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new WalnutAttackGoal(this, 1.25D, 10, 10.0F));
        this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new RandomMouthMovementGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (p_29932_) -> {
            return p_29932_ instanceof Enemy;
        }));
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

    public boolean isFiring() {
        return this.entityData.get(DATA_IS_FIRING);
    }

    public void setFiring(boolean isFiring) {
        this.entityData.set(DATA_IS_FIRING, isFiring);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MOUTH_OPEN, false);
        this.entityData.define(DATA_IS_FIRING, false);
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

    @Override
    public void performRangedAttack(LivingEntity target, float p_33318_) {
        WalnutEntity walnutEntity = new WalnutEntity(ChristmasEntities.WALNUT.get(), this.getLevel());
        walnutEntity.setPos(this.getX(), this.getY() + 1.8d, this.getZ());

        double d0 = target.getEyeY() - (double)1.4F;
        double d1 = target.getX() - this.getX();
        double d2 = d0 - walnutEntity.getY();
        double d3 = target.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
        walnutEntity.shoot(d1, d2 + d4, d3, 1.6F, 12.0F);
        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(walnutEntity);
    }

    @Override
    protected float getStandingEyeHeight(Pose p_21131_, EntityDimensions p_21132_) {
        return 41.0f / 16.0f;
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

    private static class WalnutAttackGoal extends RangedAttackGoal {
        private final NutcrackerEntity nutcracker;

        public WalnutAttackGoal(NutcrackerEntity nutcracker, double p_25769_, int p_25770_, float p_25771_) {
            super(nutcracker, p_25769_, p_25770_, p_25771_);
            this.nutcracker = nutcracker;
        }

        @Override
        public boolean canUse() {
            boolean flag = super.canUse();
            nutcracker.setFiring(flag);
            return flag;
        }
    }

}
