package com.bayobayobayo.happyholidays.common.entity.christmas;

import com.bayobayobayo.happyholidays.common.block.christmas.presents.PresentBlock;
import com.bayobayobayo.happyholidays.common.registry.ItemRegistry;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GrinchEntity extends CreatureEntity implements IAnimatable {
    public static final DataParameter<Integer> BREAK_ANIM_TIME = EntityDataManager.defineId(GrinchEntity.class,
            DataSerializers.INT);

    public static final String ENTITY_ID = "grinch";
    public static final AttributeModifierMap ENTITY_ATTRIBUTES =
            MobEntity.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 20.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.21D)
                    .build();

    private static final int BREAK_ANIM_DURATION = 80;
    private static final int NEXT_BREAK_DELAY = 0;

    private AnimationFactory factory = new AnimationFactory(this);

    private int breakAnimationTimer = 0;
    private int nextBreakTimer = 0;
    private BlockPos targetPresentBlockPos = null;
    private boolean isBreakingPresent = false;

    private int presentsBrokenCount = 0;

    public GrinchEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);

        this.presentsBrokenCount = this.getPersistentData().getInt("PresentsBroken");
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BREAK_ANIM_TIME, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new BreakPresentsGoal(this));
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GENERIC_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }

    public void breakPresent(BlockPos presentBlockPos) {
        if (this.level.getBlockState(presentBlockPos).getBlock() instanceof PresentBlock) {
            this.breakAnimationTimer = GrinchEntity.BREAK_ANIM_DURATION;
            this.nextBreakTimer = GrinchEntity.NEXT_BREAK_DELAY;
            this.isBreakingPresent = true;
            this.targetPresentBlockPos = presentBlockPos;

            this.entityData.set(BREAK_ANIM_TIME, this.breakAnimationTimer);
        }
    }

    public boolean isReadyToBreakAnotherPresent() {
        return this.nextBreakTimer <= 0;
    }

    public boolean isBreakingPresent() {
        return this.isBreakingPresent;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.nextBreakTimer > 0) this.nextBreakTimer--;

        if (this.breakAnimationTimer > 0) {
            this.breakAnimationTimer--;

            this.navigation.stop();
            this.lookControl.setLookAt(new Vector3d(targetPresentBlockPos.getX(), targetPresentBlockPos.getY(),
                    targetPresentBlockPos.getZ()));

            this.entityData.set(BREAK_ANIM_TIME, this.breakAnimationTimer);

            if (this.breakAnimationTimer == 0) {
                this.level.destroyBlock(targetPresentBlockPos, false);
                this.isBreakingPresent = false;
                this.targetPresentBlockPos = null;
                this.presentsBrokenCount++;
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putInt("PresentsBroken", this.presentsBrokenCount);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
        ItemStack scraps = ItemRegistry.PRESENT_SCRAPS.get().getDefaultInstance();
        scraps.setCount(this.presentsBrokenCount);

        this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), scraps));
    }

    private <E extends GrinchEntity> PlayState predicate(AnimationEvent<E> event) {
        if (this.entityData.get(BREAK_ANIM_TIME) > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.break", true));
        } else if (event.isMoving()) {
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

    private static class BreakPresentsGoal extends Goal {
        private static final int SEARCH_RADIUS = 4;
        private static final int SEARCH_INTERVAL = 100;

        private final GrinchEntity grinchEntity;

        private BlockPos targetBlockPos = null;
        private int timeToNextCheck = 0;
        private boolean isBlockFound = false;

        public BreakPresentsGoal(GrinchEntity grinchEntity) {
            this.grinchEntity = grinchEntity;
        }

        @Override
        public boolean canUse() {
            if (this.isBlockFound) return true;
            if (this.grinchEntity.isReadyToBreakAnotherPresent() && --timeToNextCheck <= 0) {
                BlockPos startPos = grinchEntity.blockPosition().offset(-SEARCH_RADIUS, -SEARCH_RADIUS, -SEARCH_RADIUS);

                this.targetBlockPos = null;
                for (int x = 0; x < SEARCH_RADIUS * 2; x++) {
                    for (int y = 0; y < SEARCH_RADIUS * 2; y++) {
                        for (int z = 0; z < SEARCH_RADIUS * 2; z++) {
                            BlockPos checkingPos = startPos.offset(x, y, z);
                            if (grinchEntity.level.getBlockState(checkingPos).getBlock() instanceof PresentBlock) {
                                this.targetBlockPos = checkingPos;
                                this.isBlockFound = true;
                                return true;
                            }
                        }
                    }
                }

                this.timeToNextCheck = SEARCH_INTERVAL;
            }

            return false;
        }

        @Override
        public void tick() {
            if (this.grinchEntity.level.getBlockState(targetBlockPos).is(Blocks.AIR)) {
              this.targetBlockPos = null;
              this.isBlockFound = false;
            } else if (isBlockFound && !this.grinchEntity.isBreakingPresent()) {
                this.grinchEntity.navigation.moveTo(targetBlockPos.getX(), targetBlockPos.getY(),
                        targetBlockPos.getZ(), 0.75f);

                if (this.grinchEntity.distanceToSqr(targetBlockPos.getX(), targetBlockPos.getY(),
                        targetBlockPos.getZ()) <= 1.5f) {
                    this.grinchEntity.lookControl.setLookAt(new Vector3d(targetBlockPos.getX(), targetBlockPos.getY(),
                            targetBlockPos.getZ()));
                    this.grinchEntity.breakPresent(targetBlockPos);
                }
            }
        }
    }
}
