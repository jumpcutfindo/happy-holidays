package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceAction;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasRewards;
import com.jumpcutfindo.happyholidays.common.events.christmas.NutcrackerEvent;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.NutcrackerContainer;
import com.jumpcutfindo.happyholidays.common.item.christmas.PatrolOrdersItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.WalnutAmmo;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;
import com.jumpcutfindo.happyholidays.common.utils.EntityUtils;
import com.jumpcutfindo.happyholidays.server.data.HolidayAvailabilityData;
import com.jumpcutfindo.happyholidays.server.data.structs.Availability;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
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

public class NutcrackerEntity extends TamableAnimal implements IAnimatable, ChristmasEntity, MenuProvider, RangedAttackMob, NeutralMob {
    public static final int SPAWN_WEIGHT = 5;
    public static final int MIN_SPAWN_COUNT = 2;
    public static final int MAX_SPAWN_COUNT = 3;

    public static final AttributeSupplier ENTITY_ATTRIBUTES =
            Mob.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 30.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.23f)
                    .add(Attributes.FOLLOW_RANGE, 20.0f)
                    .add(Attributes.KNOCKBACK_RESISTANCE, 0.5f)
                    .build();

    public static final float ENTITY_BOX_SIZE = 0.8f;
    public static final float ENTITY_BOX_HEIGHT = 3.0f;

    public static final int FIRING_DELAY = 20;

    public static final float LOG_HEAL_AMOUNT = 2.0f;

    public static final double ORNAMENT_DROP_BASE_CHANCE = 0.01d;
    public static final double ORNAMENT_DROP_CHANCE_STEP = 0.1d;

    public static final EntityDataAccessor<Boolean> DATA_MOUTH_OPEN = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Boolean> DATA_IS_FIRING = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.INT);
    public static final EntityDataAccessor<Boolean> DATA_IS_PATROLLING = SynchedEntityData.defineId(NutcrackerEntity.class,
            EntityDataSerializers.BOOLEAN);

    public static final Map<Integer, ResourceLocation> TAMED_TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (map) -> {
        map.put(0, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/nutcracker_a.png"));
        map.put(1, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/nutcracker_b.png"));
        map.put(2, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/nutcracker_c.png"));
        map.put(3, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/nutcracker_d.png"));
    });

    public static final Map<Integer, ResourceLocation> UNTAMED_TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (map) -> {
        map.put(0, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/nutcracker_a_untamed.png"));
        map.put(1, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/nutcracker_b_untamed.png"));
        map.put(2, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/nutcracker_c_untamed.png"));
        map.put(3, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/nutcracker_d_untamed.png"));
    });

    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);

    private AnimationFactory factory = new AnimationFactory(this);

    private NutcrackerInventory inventory = new NutcrackerInventory();
    private final LazyOptional<IItemHandler> inventoryOptional = LazyOptional.of(() -> this.inventory);

    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    private Player interactingPlayer;
    private int droppedOrdersCooldown;

    private int routeNextPointIndex;
    private int assemblyPos;

    public NutcrackerEntity(EntityType<? extends TamableAnimal> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new WalnutAttackGoal(this, 1.25D, 10.0F));
        this.goalSelector.addGoal(1, new PatrolGoal(this));
        this.goalSelector.addGoal(1, new PickupPatrolOrdersGoal(this));
        this.goalSelector.addGoal(1, new LookAndFollowInteractingPlayerGoal(this));
        this.goalSelector.addGoal(2, new AssembleGoal(this));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(ChristmasItems.WALNUT.get()), false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new RandomMouthMovementGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestNuttableTargetGoal<>(this, Monster.class, 10, true, false, (p_29932_) -> {
            return p_29932_ instanceof Enemy;
        }));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_, MobSpawnType p_146748_, @Nullable SpawnGroupData p_146749_, @Nullable CompoundTag p_146750_) {
        this.setNutcrackerType(this.random.nextInt(0, 4));
        this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inventory -> {
            ((NutcrackerInventory) inventory).setPatrolOrders(ChristmasItems.PATROL_ORDERS.get().getDefaultInstance());
        });

        return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_, p_146750_);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        // Handle taming
        ItemStack heldItem = player.getItemInHand(interactionHand);
        if (heldItem.is(ChristmasItems.WALNUT.get())) {
            if (!this.level.isClientSide() && !this.isTame()) {
                if (!player.getAbilities().instabuild) {
                    heldItem.shrink(1);
                }

                if (this.random.nextInt(5) == 0) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget((LivingEntity) null);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte) 6);
                }
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }

        // Handle healing
        if (this.isTame() && heldItem.is(ItemTags.LOGS)) {
            if (this.getHealth() < this.getMaxHealth()) {
                this.usePlayerItem(player, interactionHand, heldItem);
                this.heal(LOG_HEAL_AMOUNT);

                this.playSound(ChristmasSounds.NUTCRACKER_REPAIR.get(), 1.0F, 1.0F);
                if (!this.level.isClientSide()) ((ServerLevel) this.level).sendParticles(ParticleTypes.CLOUD, this.getX(), this.getY() + 2.0d, this.getZ(), 3, 0.5, 0.0, 0.5, 0.0D);
            }
            return InteractionResult.SUCCESS;
        }

        // Handle retrieving of patrol orders
        if (!this.level.isClientSide() && this.isTame() && heldItem.is(ChristmasItems.SWAGGER_STICK.get())) {
            this.dropPatrolOrders();
            return InteractionResult.SUCCESS;
        }

        // Handle interacting with player
        if (!this.level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND && this.isTame()) {
            NetworkHooks.openGui((ServerPlayer) player, this, buf -> buf.writeInt(this.getId()));
            this.interactingPlayer = player;
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }

        return InteractionResult.sidedSuccess(this.level.isClientSide);
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

    public void onFinishPlayerInteraction(Player interactingPlayer) {
        if (this.level.isClientSide()) return;

        this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inventory -> {
            NutcrackerInventory nutcrackerInventory = (NutcrackerInventory) inventory;
            if (nutcrackerInventory.hasSpecialWalnuts()) {
                MinecraftForge.EVENT_BUS.post(new NutcrackerEvent.ReceiveSpecialWalnuts(this, interactingPlayer));
            }

            if (nutcrackerInventory.hasArmor()) {
                MinecraftForge.EVENT_BUS.post(new NutcrackerEvent.ReceiveArmor(this, interactingPlayer));
            }

            if (nutcrackerInventory.isFullOf(ChristmasItems.EXPLOSIVE_WALNUT.get())) {
                MinecraftForge.EVENT_BUS.post(new NutcrackerEvent.FullOfExplosives(this, interactingPlayer));
            }

            if (nutcrackerInventory.getArmorValue() >= 56) {
                MinecraftForge.EVENT_BUS.post(new NutcrackerEvent.HighArmorRating(this, interactingPlayer));
            }
        });
    }

    @Override
    public void tick() {
        super.tick();
        if (this.droppedOrdersCooldown > 0) this.droppedOrdersCooldown--;

        if (!this.level.isClientSide() && this.level.getGameTime() % 60L == 0) {
            List<Player> playersAround = EntityUtils.findPlayersInRadius(this.level, this.position(), 6.0f);

            for (Player playerEntity : playersAround) MinecraftForge.EVENT_BUS.post(new NutcrackerEvent.Encounter(this, playerEntity));
        }
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
        this.entityData.define(DATA_TYPE_ID, 0);
        this.entityData.define(DATA_MOUTH_OPEN, false);
        this.entityData.define(DATA_IS_FIRING, false);
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
        this.entityData.define(DATA_IS_PATROLLING, false);
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

    public boolean canTakePatrolOrders() {
        return this.isTame() && !this.inventory.hasPatrolOrders();
    }

    public void pickupPatrolOrders(ItemEntity itemEntity) {
        if (itemEntity.hasPickUpDelay() || this.droppedOrdersCooldown > 0) return;

        this.take(itemEntity, itemEntity.getItem().getCount());

        ItemStack patrolOrders = itemEntity.getItem();
        this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inventory -> {
            ((NutcrackerInventory) inventory).setPatrolOrders(patrolOrders);
        });

        itemEntity.setRemoved(RemovalReason.DISCARDED);

        if (PatrolOrdersItem.isCompletedPatrolOrders(patrolOrders)) {
            this.playSound(ChristmasSounds.NUTCRACKER_RECEIVE_ORDERS.get(), 1.0F, 1.0F);

            Player thrower = this.level.getPlayerByUUID(itemEntity.getThrower());
            if (!this.level.isClientSide() &&  thrower != null) {
                MinecraftForge.EVENT_BUS.post(new NutcrackerEvent.ReceiveCompleteOrders(this, thrower));
            }
        }
    }

    public void dropPatrolOrders() {
        ItemStack patrolOrders = this.inventory.popPatrolOrders();
        if (!patrolOrders.isEmpty()) {
            this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inventory -> {
                ((NutcrackerInventory) inventory).setPatrolOrders(ItemStack.EMPTY);
            });

            this.spawnAtLocation(patrolOrders);
            this.droppedOrdersCooldown = 40;
        }
    }

    public ItemStack getPatrolOrders() {
        return this.inventory.getPatrolOrders();
    }

    public boolean hasValidOrders() {
        return this.inventory.hasPatrolOrders() && PatrolOrdersItem.isValidPatrolOrders(this.inventory.getPatrolOrders());
    }

    public int getNutcrackerType() {
        return this.entityData.get(DATA_TYPE_ID);
    }

    public void setNutcrackerType(int id) {
        this.entityData.set(DATA_TYPE_ID, id);
    }

    public void setPatrolling(boolean isPatrolling) {
        this.entityData.set(DATA_IS_PATROLLING, isPatrolling);
    }

    public boolean isPatrolling() {
        return this.entityData.get(DATA_IS_PATROLLING);
    }

    public void updateRoutePointIndex(int index) {
        this.routeNextPointIndex = index;
    }

    public int getRouteNextPointIndex() {
        return this.routeNextPointIndex;
    }

    public void setAssemblyPos(int assemblyPos) {
        this.assemblyPos = assemblyPos;
    }

    public int getAssemblyPos() {
        return this.assemblyPos;
    }

    public void tryDroppingOrnament() {
        double modifier = ChristmasRewards.computeModifier(this.level, this.position(), ORNAMENT_DROP_CHANCE_STEP);
        double ornamentDropChance = ORNAMENT_DROP_BASE_CHANCE * modifier;

        if (this.isPatrolling()) ornamentDropChance *= 2.0D;

        if (this.random.nextDouble() <= ornamentDropChance) {
            this.spawnAtLocation(ChristmasItems.NUTCRACKER_ORNAMENT.get());
        }
    }

    @Override
    public boolean canAttack(LivingEntity entity) {
        return entity instanceof Enemy && entity.isAlive();
    }

    @Override
    public int getArmorValue() {
        return this.inventory.getArmorValue();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ChristmasSounds.NUTCRACKER_PASSIVE.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return ChristmasSounds.NUTCRACKER_HURT.get();
    }

    @Override
    public void setTarget(@Nullable LivingEntity p_21544_) {
        super.setTarget(p_21544_);
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
    public void tame(Player player) {
        super.tame(player);
        if (!this.level.isClientSide()) {
            NaughtyNiceMeter.evaluateAction(player, NaughtyNiceAction.TAME_NUTCRACKER_EVENT);

            NutcrackerEvent tameEvent = new NutcrackerEvent.Tame(this, player);
            MinecraftForge.EVENT_BUS.post(tameEvent);
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    public ResourceLocation getTexture() {
        if (this.isTame()) return TAMED_TEXTURE_BY_TYPE.get(this.getNutcrackerType());
        else return UNTAMED_TEXTURE_BY_TYPE.get(this.getNutcrackerType());
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public int getFiringDelay() {
        return this.inventory.getCurrentAmmo() == WalnutAmmo.SUGARED ? FIRING_DELAY / 2 : FIRING_DELAY;
    }

    public int getTargetingRadius() {
        return this.inventory.getCurrentAmmo() == WalnutAmmo.METALLIC ? 6 : this.inventory.getCurrentAmmo() == WalnutAmmo.HALVED ? 20 : 12;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float p_33318_) {
        WalnutEntity walnutEntity = new WalnutEntity(ChristmasEntities.WALNUT.get(), this.getLevel());
        walnutEntity.setPos(this.getX(), this.getY() + 2.2d, this.getZ());
        walnutEntity.setAmmoType(this.inventory.getCurrentAmmo());
        walnutEntity.setOwner(this);

        double d0 = target.getX() - this.getX() + target.getDeltaMovement().x * 10;
        double d1 = target.getY(0.33D) - walnutEntity.getY();
        double d2 = target.getZ() - this.getZ() + target.getDeltaMovement().z * 10;

        double sqrDist = Math.sqrt(d0 * d0 + d2 * d2);
        double d3 = sqrDist * (sqrDist >= 20.0D ? (double) 0.35F : (double) 0.2F);
        walnutEntity.shoot(d0, d1 + d3, d2, 1.1F, 10.0F);
        this.playSound(ChristmasSounds.NUTCRACKER_SHOOT.get(), 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!this.level.isClientSide()) ((ServerLevel) this.level).sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + 2.0d, this.getZ(), 2, 0.5, 0.0, 0.5, 0.0D);
        this.level.addFreshEntity(walnutEntity);

        this.inventory.useAmmo();
    }

    public NutcrackerEntity.Damage getDamageLevel() {
        return NutcrackerEntity.Damage.getDamageLevel(this.getHealth() / this.getMaxHealth());
    }

    @Override
    protected float getStandingEyeHeight(Pose p_21131_, EntityDimensions p_21132_) {
        return 42.0f / 16.0f;
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

        tag.put("NutcrackerInventory", ((NutcrackerInventory)this.inventory).serializeNBT());
        tag.putInt("NutcrackerType", this.getNutcrackerType());
        tag.putInt("RouteNextPointIndex", this.routeNextPointIndex);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        CompoundTag invTag = tag.getCompound("NutcrackerInventory");
        ((NutcrackerInventory)this.inventory).deserializeNBT(invTag);

        this.entityData.set(DATA_TYPE_ID, tag.getInt("NutcrackerType"));

        this.routeNextPointIndex = tag.getInt("RouteNextPointIndex");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
        NutcrackerContainer container = new NutcrackerContainer(i, this.level, this.blockPosition(), playerInventory, playerEntity, this);
        return container;
    }

    @Override
    public void die(DamageSource p_21809_) {
        super.die(p_21809_);
        if (this.getTarget() instanceof Monster monster && monster.getTarget() instanceof NutcrackerEntity nutcracker && nutcracker.is(this)) {
            monster.setTarget(null);
        }
    }

    @Override
    public boolean removeWhenFarAway(double p_27598_) {
        return false;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_21385_, int p_21386_, boolean p_21387_) {
        super.dropCustomDeathLoot(p_21385_, p_21386_, p_21387_);

        for (ItemStack itemStack : this.inventory.getAllItems()) {
            this.spawnAtLocation(itemStack);
        }
    }

    public static boolean checkNutcrackerSpawnRules(EntityType<? extends NutcrackerEntity> entity,
                                                    LevelAccessor world, MobSpawnType spawnReason, BlockPos pos, Random rand) {
        if (world instanceof ServerLevel serverLevel) {
            return Availability.isAvailable(serverLevel, Holiday.CHRISTMAS, HolidayAvailabilityData.CHRISTMAS_NUTCRACKER_SPAWN);
        }

        return world.getRawBrightness(pos,0) > 8 && world.getBlockState(pos.below()).is(ChristmasTags.Blocks.NUTCRACKER_SPAWNABLE_ON);
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

    private static class AssembleGoal extends Goal {
        private static final TargetingConditions TEMP_TARGETING = TargetingConditions.forNonCombat().range(20.0D).ignoreLineOfSight();

        private final Ingredient items;
        private final TargetingConditions targetingConditions;

        private Queue<BlockPos> positionQueue;
        private NutcrackerEntity nutcracker;
        private Player player;
        private BlockPos targetPos;
        private boolean isInitialised;

        public AssembleGoal(NutcrackerEntity nutcracker) {
            this.items = Ingredient.of(ChristmasItems.SWAGGER_STICK.get());
            this.nutcracker = nutcracker;
            this.targetingConditions = TEMP_TARGETING.copy().selector(this::shouldFollow);
        }

        @Override
        public boolean canUse() {
            this.player = this.nutcracker.level.getNearestPlayer(this.targetingConditions, this.nutcracker);
            boolean flag = nutcracker.isTame() && !nutcracker.isPatrolling() && this.player != null;

            if (flag && !this.isInitialised) {
                this.initialise();
            }
            if (!flag) {
                this.reset();
            }

            return flag;
        }

        @Override
        public void tick() {
            super.tick();

            if (this.nutcracker.position().x != targetPos.getX() + 0.5d || this.nutcracker.position().z != targetPos.getZ() + 0.5d) {
                this.nutcracker.getNavigation().moveTo(targetPos.getX() + 0.5d, targetPos.getY(), targetPos.getZ() + 0.5d, 1.4f);
            } else {
                this.nutcracker.getNavigation().stop();
            }
            this.nutcracker.lookControl.setLookAt(this.nutcracker.getX(), this.nutcracker.getY() + this.nutcracker.getEyeHeight(), this.nutcracker.getZ() + 3.0d);
        }

        private boolean shouldFollow(LivingEntity p_148139_) {
            return this.items.test(p_148139_.getMainHandItem()) || this.items.test(p_148139_.getOffhandItem());
        }

        private void initialise() {
            this.positionQueue = new LinkedList<>();
            this.positionQueue.add(new BlockPos(this.player.getX(), this.player.getY(), this.player.getZ() - 3.0d));
            this.determineAssemblyPos();

            this.isInitialised = true;
        }

        private void reset() {
            this.positionQueue = new LinkedList<>();

            this.targetPos = null;
            this.isInitialised = false;

            this.nutcracker.setAssemblyPos(-1);
        }

        private void determineAssemblyPos() {
            List<NutcrackerEntity> nutcrackers = this.nutcracker.level.getEntitiesOfClass(NutcrackerEntity.class, new AABB(this.player.blockPosition()).inflate(20.0d));

            int maxPos = 0;
            for (NutcrackerEntity nutcracker : nutcrackers) {
                if (nutcracker.getAssemblyPos() >= maxPos) maxPos = nutcracker.getAssemblyPos() + 1;
            }

            this.nutcracker.setAssemblyPos(maxPos);

            for (int i = 0; i < maxPos + 1; i++) {
                this.targetPos = this.nextPos();
            }
        }

        private BlockPos nextPos() {
            BlockPos pos = this.positionQueue.poll();

            if (pos != null) {
                int spacing = 4;

                this.positionQueue.add(pos.north(spacing));
                this.positionQueue.add(pos.east(spacing));
                this.positionQueue.add(pos.west(spacing));
            }

            return pos;
        }
    }

    private static class PatrolGoal extends Goal {
        private final NutcrackerEntity nutcracker;

        private PatrolRoute patrolRoute;
        private int routeLength, currentIndex;

        public PatrolGoal(NutcrackerEntity nutcracker) {
            this.nutcracker = nutcracker;
        }

        @Override
        public boolean canUse() {
            if (this.nutcracker.getTarget() != null) return false;

            if (!this.nutcracker.hasValidOrders()) {
                this.reset();
                return false;
            }

            return true;
        }

        @Override
        public void tick() {
            super.tick();

            if (this.patrolRoute == null) {
                this.initialiseRoute();
            }

            BlockPos targetBlockPos = this.patrolRoute.getPoints().get(currentIndex);
            Vec3 targetPoint = new Vec3(targetBlockPos.getX() + 0.5D, targetBlockPos.getY() + 1.0d, targetBlockPos.getZ() + 0.5D);
            this.nutcracker.navigation.moveTo(targetPoint.x, targetPoint.y, targetPoint.z, 0.9D);
            this.nutcracker.lookControl.setLookAt(targetPoint.x, targetPoint.y + nutcracker.getEyeHeight(), targetPoint.z);

            if (this.nutcracker.distanceToSqr(targetPoint) < 3.0D) {
                this.updatePoints();
            }

            if (this.nutcracker.navigation.isStuck()) this.nutcracker.jumpControl.jump();
        }

        private void initialiseRoute() {
            ItemStack patrolOrders = nutcracker.getPatrolOrders();
            this.patrolRoute = PatrolOrdersItem.extractRoute(patrolOrders);
            this.routeLength = this.patrolRoute.getLength();

            this.currentIndex = this.nutcracker.getRouteNextPointIndex();
            if (this.currentIndex >= this.patrolRoute.getLength()) this.currentIndex = 0;

            this.nutcracker.setPatrolling(true);
        }

        private void updatePoints() {
            this.currentIndex++;
            if (this.currentIndex >= this.routeLength) this.currentIndex = 0;

            this.nutcracker.updateRoutePointIndex(this.currentIndex);
        }

        private void reset() {
            this.patrolRoute = null;
            this.currentIndex = 0;
            this.routeLength = 0;

            this.nutcracker.setPatrolling(false);
        }
    }

    private static class PickupPatrolOrdersGoal extends Goal {
        private NutcrackerEntity nutcracker;
        private ItemEntity targetedEntity;

        public PickupPatrolOrdersGoal(NutcrackerEntity nutcracker) {
            this.nutcracker = nutcracker;
        }

        @Override
        public boolean canUse() {
            if (!nutcracker.canTakePatrolOrders()) return false;

            List<ItemEntity> nearbyEntities = this.nutcracker.level.getEntitiesOfClass(ItemEntity.class,
                    this.nutcracker.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));

            if (nearbyEntities.size() != 0) {
                for (ItemEntity entity : nearbyEntities) {
                    if (entity.getItem().is(ChristmasItems.PATROL_ORDERS.get())) {
                        targetedEntity = entity;
                        return true;
                    }
                }
            }

            return false;
        }

        @Override
        public void tick() {
            if (targetedEntity != null && targetedEntity.isAlive()) {
                this.nutcracker.getNavigation().moveTo(targetedEntity.position().x, targetedEntity.position().y,
                        targetedEntity.position().z, 1.0f);

                if (targetedEntity.distanceToSqr(this.nutcracker) < 2.0f) {
                    this.nutcracker.pickupPatrolOrders(targetedEntity);
                }
            }
        }
    }

    private static class WalnutAttackGoal extends Goal {
        private final Mob mob;
        private final RangedAttackMob rangedAttackMob;
        @javax.annotation.Nullable
        private LivingEntity target;
        private int attackTime = -1;
        private final double speedModifier;
        private int seeTime;
        private final float attackRadius;
        private final float attackRadiusSqr;

        private final NutcrackerEntity nutcracker;

        public WalnutAttackGoal(NutcrackerEntity p_25773_, double p_25774_, float p_25777_) {
            if (p_25773_ == null) {
                throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
            } else {
                this.rangedAttackMob = p_25773_;
                this.mob = (Mob)p_25773_;
                this.speedModifier = p_25774_;
                this.attackRadius = p_25777_;
                this.attackRadiusSqr = p_25777_ * p_25777_;
                this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));

                this.nutcracker = p_25773_;
            }
        }

        public boolean canUse() {
            LivingEntity livingentity = this.nutcracker.getTarget();
            boolean flag = nutcracker.canFire();
            if (livingentity != null) {
                if (!livingentity.isAlive()) {
                    this.nutcracker.forgetCurrentTargetAndRefreshUniversalAnger();
                    return false;
                }
                this.target = livingentity;
                flag = true && flag;
            } else {
                this.target = null;
                flag = false && flag;
            }

            nutcracker.setFiring(flag);

            return flag;
        }

        public boolean canContinueToUse() {
            return this.canUse();
        }

        public void stop() {
            this.target = null;
            this.seeTime = 0;
            this.attackTime = -1;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
            boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
            if (flag) {
                ++this.seeTime;
            } else {
                this.seeTime = 0;
            }

            if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 5) {
                this.mob.getNavigation().stop();
            } else {
                this.mob.getNavigation().moveTo(this.target, this.speedModifier);
            }

            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
            if (--this.attackTime == 0) {
                if (!flag) {
                    return;
                }

                float f = (float)Math.sqrt(d0) / this.nutcracker.getTargetingRadius();
                float f1 = Mth.clamp(f, 0.1F, 1.0F);
                this.rangedAttackMob.performRangedAttack(this.target, f1);
                this.attackTime = Mth.floor((float)this.nutcracker.getFiringDelay());
            } else if (this.attackTime < 0) {
                this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / (double)this.nutcracker.getTargetingRadius(), (double)this.nutcracker.getFiringDelay(), (double)this.nutcracker.getFiringDelay()));
            }
        }
    }

    private static class LookAndFollowInteractingPlayerGoal extends Goal {
        private final NutcrackerEntity nutcracker;

        private Player interactingPlayer;
        private boolean isInteractingPreviousTick;

        public LookAndFollowInteractingPlayerGoal(NutcrackerEntity nutcracker) {
            this.nutcracker = nutcracker;
        }

        @Override
        public boolean canUse() {
            boolean isInteracting = this.nutcracker.isInteractingWithPlayer();

            // Check if the player has finished interacting with the Nutcracker
            if (isInteractingPreviousTick && !isInteracting) {
                nutcracker.onFinishPlayerInteraction(interactingPlayer);
                interactingPlayer = null;
            }

            if (isInteracting && interactingPlayer == null) {
                interactingPlayer = nutcracker.getInteractingPlayer();
            }

            isInteractingPreviousTick = isInteracting;

            return isInteracting;
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

    private static class NearestNuttableTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        private final NutcrackerEntity nutcracker;
        public NearestNuttableTargetGoal(NutcrackerEntity nutcracker, Class p_26054_, int p_26055_, boolean p_26056_,
                                         boolean p_26057_, @Nullable Predicate p_26058_) {
            super(nutcracker, p_26054_, p_26055_, p_26056_, p_26057_, p_26058_);
            this.nutcracker = nutcracker;
        }

        @Override
        protected double getFollowDistance() {
            return this.nutcracker == null ? super.getFollowDistance() : this.nutcracker.getTargetingRadius();
        }
    }

    public enum Damage {
        NONE, LOW, MEDIUM, HIGH;

        static Damage getDamageLevel(float fraction) {
            if (fraction == 1.0f) return NONE;
            else if (fraction > 0.66f) return LOW;
            else if (fraction > 0.33f) return MEDIUM;
            else return HIGH;
        }
    }
}
