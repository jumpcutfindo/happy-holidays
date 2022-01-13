package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.jumpcutfindo.happyholidays.common.container.christmas.nutcracker.NutcrackerContainer;
import com.jumpcutfindo.happyholidays.common.entity.christmas.IChristmasEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class NutcrackerEntity extends TamableAnimal implements IAnimatable, IChristmasEntity, MenuProvider, RangedAttackMob, NeutralMob {
    public static final String ENTITY_ID = "nutcracker";
    public static final AttributeSupplier ENTITY_ATTRIBUTES =
            Mob.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 25.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.23f)
                    .build();

    public static final float ENTITY_BOX_SIZE = 0.8f;
    public static final float ENTITY_BOX_HEIGHT = 3.0f;

    public static final int FIRING_DELAY = 10;

    public static final EntityDataAccessor<Boolean> DATA_MOUTH_OPEN = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> DATA_IS_FIRING = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.INT);

    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);

    private AnimationFactory factory = new AnimationFactory(this);

    private IItemHandler inventory = new NutcrackerInventory();
    private final LazyOptional<IItemHandler> inventoryOptional = LazyOptional.of(() -> this.inventory);

    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    private Player interactingPlayer;

    public NutcrackerEntity(EntityType<? extends TamableAnimal> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new WalnutAttackGoal(this, 1.25D, FIRING_DELAY, 10.0F));
        this.goalSelector.addGoal(0, new LookAndFollowInteractingPlayerGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.25D, Ingredient.of(ChristmasItems.WALNUT.get()), false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new RandomMouthMovementGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, (p_29932_) -> {
            return p_29932_ instanceof Enemy;
        }));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        // Handle taming
        ItemStack heldItem = player.getItemInHand(interactionHand);
        if (!this.isTame() && heldItem.is(ChristmasItems.WALNUT.get())) {
            if (!player.getAbilities().instabuild) {
                heldItem.shrink(1);
            }

            if (this.random.nextInt(5) == 0) {
                this.tame(player);
                this.navigation.stop();
                this.setTarget((LivingEntity) null);
                this.level.broadcastEntityEvent(this, (byte) 7);
                return InteractionResult.CONSUME;
            } else {
                this.level.broadcastEntityEvent(this, (byte) 6);
                return InteractionResult.CONSUME;
            }
        }

        if (!this.level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND && this.isTame()) {
            NetworkHooks.openGui((ServerPlayer) player, this, buf -> buf.writeInt(this.getId()));
            this.interactingPlayer = player;
        }

        return super.mobInteract(player, interactionHand);
    }

    public boolean isInteractingWithPlayer() {
        return this.interactingPlayer != null;
    }

    public void setInteractingPlayer(@Nullable Player interactingPlayer) {
        this.interactingPlayer = interactingPlayer;
    }

    public Player getInteractingPlayer() {
        return interactingPlayer;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return this.inventoryOptional.cast();
        }

        return getCapability(cap, null);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.inventoryOptional.invalidate();
    }

    @Override
    public boolean isFood(ItemStack foodItem) {
        return foodItem.is(ChristmasTags.Items.NUTCRACKER_FOOD);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_MOUTH_OPEN, false);
        this.entityData.define(DATA_IS_FIRING, false);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
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

    public boolean canFire() {
        return this.isTame() || this.getTarget() instanceof Player;
    }

    public boolean isFiring() {
        return this.entityData.get(DATA_IS_FIRING);
    }

    public void setFiring(boolean isFiring) {
        this.entityData.set(DATA_IS_FIRING, isFiring);
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

        double d0 = target.getEyeY();
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

    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int p_30404_) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, p_30404_);
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @javax.annotation.Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID p_30400_) {
        this.persistentAngerTarget = p_30400_;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.put("NutInventory", ((NutcrackerInventory)this.inventory).serializeNBT());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        CompoundTag invTag = tag.getCompound("NutInventory");
        ((NutcrackerInventory)this.inventory).deserializeNBT(invTag);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
        NutcrackerContainer container = new NutcrackerContainer(i, this.level, this.blockPosition(), playerInventory, playerEntity, this);
        return container;
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
            boolean flag = super.canUse() && nutcracker.canFire();
            nutcracker.setFiring(flag);
            return flag;
        }
    }

    private static class LookAndFollowInteractingPlayerGoal extends Goal {
        private final NutcrackerEntity nutcracker;

        public LookAndFollowInteractingPlayerGoal(NutcrackerEntity nutcracker) {
            this.nutcracker = nutcracker;
        }

        @Override
        public boolean canUse() {
            return this.nutcracker.isInteractingWithPlayer();
        }

        @Override
        public void tick() {
            super.tick();

            Player player = this.nutcracker.getInteractingPlayer();

            if (this.nutcracker.distanceToSqr(this.nutcracker.getInteractingPlayer()) > 2.0d) {
                this.nutcracker.navigation.moveTo(player, 1.0d);
            } else {
                this.nutcracker.navigation.stop();
            }

            this.nutcracker.lookAt(player, 45.0f, 45.0f);
        }
    }
}
