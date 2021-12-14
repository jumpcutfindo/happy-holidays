package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarHelper;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceAction;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.jumpcutfindo.happyholidays.common.entity.christmas.IChristmasEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaElfEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEffects;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.server.data.SantaSavedData;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.Tag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SantaElfEntity extends PathfinderMob implements IAnimatable, Merchant, IChristmasEntity {
    public static final String ENTITY_ID = "santa_elf";
    public static final AttributeSupplier ENTITY_ATTRIBUTES =
            Mob.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 20.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.27D)
                    .build();

    public static final float ENTITY_BOX_SIZE = 0.8f;
    public static final float ENTITY_BOX_HEIGHT = 22.0f / 16.0f;

    public static final int DEFAULT_DESPAWN_DELAY = 24000;

    private AnimationFactory factory = new AnimationFactory(this);

    @Nullable
    private Player tradingPlayer;
    @Nullable
    protected MerchantOffers offers;

    private int despawnDelay;
    private SantaElfRequest santaElfRequest;
    private int santaElfRequestOfferIndex = -1;

    private boolean isRewardThrown = false;
    private boolean isRequestOutdated = false;
    private int timeToCompleteRequest;

    public SantaElfEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);

        this.despawnDelay = DEFAULT_DESPAWN_DELAY;

        if (this.santaElfRequest == null) this.santaElfRequest =
                SantaElfRequests.createRandomRequest(this.level.getGameTime());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new PickupRequestedItemGoal(this));
        this.goalSelector.addGoal(1, new SwapToyPartsRequestGoal(this));
        this.goalSelector.addGoal(2, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(3, new FollowEmeraldsGoal(this));
        this.goalSelector.addGoal(3, new FloatGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChristmasSounds.SANTA_ELF_PASSIVE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ChristmasSounds.SANTA_ELF_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChristmasSounds.SANTA_ELF_HURT.get();
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.walk", true));
        else event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle", true));

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
    protected InteractionResult mobInteract(Player playerEntity, InteractionHand hand) {
        if (this.getOffers().isEmpty()) {
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        } else {
            if (!this.level.isClientSide) {
                // Set prices based on debuff placed on elf
                MobEffectInstance christmasDebuff = this.getEffect(ChristmasEffects.DEBUFF_OF_CHRISTMAS_EFFECT.get());
                if (christmasDebuff != null) {
                    double discount;

                    ChristmasStarBlockEntity starBlockEntity =
                            ChristmasStarHelper.getStarInfluencingEntity(this.level, this.position());
                    if (starBlockEntity != null && starBlockEntity.isBonusActive()) {
                        discount = 0.8D;
                    } else {
                        discount = 0.1D * (double) (christmasDebuff.getAmplifier() + 1);
                    }

                    for (MerchantOffer merchantOffer : offers) {
                        if (merchantOffer.getResult().is(Items.EMERALD)) continue;

                        merchantOffer.setSpecialPriceDiff(0);
                        int j = (int) Math.floor(discount * (double) merchantOffer.getBaseCostA().getCount());
                        merchantOffer.addToSpecialPriceDiff(-Math.max(j, 1));
                    }
                }

                this.setTradingPlayer(playerEntity);
                this.openTradingScreen(playerEntity, this.getDisplayName(), 1);
            }

            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
    }

    @Override
    public void setTradingPlayer(@Nullable Player playerEntity) {
        this.tradingPlayer = playerEntity;
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
            this.updateTrades();
        }

        return this.offers;
    }

    @Override
    public void overrideOffers(@Nullable MerchantOffers merchantOffers) {
    }

    @Override
    public void notifyTrade(MerchantOffer merchantOffer) {
        merchantOffer.increaseUses();
        this.ambientSoundTime = -this.getAmbientSoundInterval();
        this.rewardTradeXp(merchantOffer);

        if (this.getTradingPlayer() != null) {
            SantaElfEvent.Trade tradeEvent = new SantaElfEvent.Trade(this, this.getTradingPlayer());
            MinecraftForge.EVENT_BUS.post(tradeEvent);
        }
    }

    @Override
    public void notifyTradeUpdated(ItemStack itemStack) {
        if (!this.level.isClientSide && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
            this.ambientSoundTime = -this.getAmbientSoundInterval();
            this.playSound(this.getTradeUpdatedSound(!itemStack.isEmpty()), this.getSoundVolume(),
                    this.getVoicePitch());
        }
    }

    public SoundEvent getNotifyTradeSound() {
        return ChristmasSounds.SANTA_ELF_YES.get();
    }

    @Override
    public boolean isClientSide() {
        return this.level.isClientSide();
    }

    protected SoundEvent getTradeUpdatedSound(boolean b) {
        return b ? ChristmasSounds.SANTA_ELF_YES.get() : ChristmasSounds.SANTA_ELF_NO.get();
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int i) {
    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    protected void rewardTradeXp(MerchantOffer merchantOffer) {
        if (merchantOffer.shouldRewardExp()) {
            int i = 3 + this.random.nextInt(4);
            this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
    }

    public boolean isTrading() {
        return this.tradingPlayer != null;
    }

    protected void updateTrades() {
        MerchantOffers merchantOffers = this.getOffers();

        // Add buyback trades if Santa has been defeated before
        if (this.canBuyBack()) {
            Set<VillagerTrades.ItemListing> buybackTradesSet = SantaElfTrades.generateBuybackTrades(4);
            buybackTradesSet.forEach(listing -> {
                MerchantOffer merchantOffer = listing.getOffer(this, this.random);
                if (merchantOffer != null) merchantOffers.add(merchantOffer);
            });
        }

        // Basic ornaments
        Set<VillagerTrades.ItemListing> basicTradesSet = SantaElfTrades.generateBasicTrades(4);
        basicTradesSet.forEach(listing -> {
            MerchantOffer merchantOffer = listing.getOffer(this, this.random);
            if (merchantOffer != null) merchantOffers.add(merchantOffer);
        });

        // Rare trades
        Set<VillagerTrades.ItemListing> rareTradesSet = SantaElfTrades.generateRareTrades(2);
        rareTradesSet.forEach(listing -> {
            MerchantOffer merchantOffer = listing.getOffer(this, this.random);
            if (merchantOffer != null) merchantOffers.add(merchantOffer);
        });

        // Food trades
        Set<VillagerTrades.ItemListing> foodTradesSet = SantaElfTrades.generateFoodTrades(2);
        foodTradesSet.forEach(listing -> {
            MerchantOffer merchantOffer = listing.getOffer(this, this.random);
            if (merchantOffer != null) merchantOffers.add(merchantOffer);
        });

        // Add Santa's Elf Request
        VillagerTrades.ItemListing santaElfRequestTrade = SantaElfTrades.generateRequestTrade(santaElfRequest);
        MerchantOffer santaElfRequestOffer = santaElfRequestTrade.getOffer(this, this.random);
        if (santaElfRequestOffer != null) merchantOffers.add(santaElfRequestOffer);
        this.santaElfRequestOfferIndex = merchantOffers.indexOf(santaElfRequestOffer);

        // Sometimes appears trades
        if (this.random.nextBoolean()) {
            Set<VillagerTrades.ItemListing> sometimesAppearTradesSet = SantaElfTrades.generateSometimesAppearTrades(1);
            sometimesAppearTradesSet.forEach(listing -> {
                MerchantOffer merchantOffer = listing.getOffer(this, this.random);
                if (merchantOffer != null) merchantOffers.add(merchantOffer);
            });
        }

        // Always appears trades
        Set<VillagerTrades.ItemListing> alwaysAppearTradesSet = SantaElfTrades.generateAlwaysAppearTrades();
        alwaysAppearTradesSet.forEach(listing -> {
            MerchantOffer merchantOffer = listing.getOffer(this, this.random);
            if (merchantOffer != null) merchantOffers.add(merchantOffer);
        });
    }

    public SantaElfRequest getRequest() {
        return this.santaElfRequest;
    }

    public void handleRequestItemOnGround(ItemEntity itemEntity) {
        if (itemEntity.hasPickUpDelay()) return;

        SantaElfRequest.SingleElfRequest completedRequest =
                this.getRequest().tryFulfilRequest(itemEntity.getItem());

        if (completedRequest != null) {
            this.pickUpSomeItems(itemEntity, completedRequest.getNumberOfItems());
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                    ChristmasSounds.SANTA_ELF_REQUEST_SINGLE_SUCCESS.get(), SoundSource.VOICE, 1.0f, 1.0f);

            this.isRequestOutdated = true;

            itemEntity.remove(RemovalReason.DISCARDED);
        }
    }

    public void handleRequestPaperOnGround(ItemEntity itemEntity) {
        if (itemEntity.hasPickUpDelay()) return;

        if (!this.isRewardThrown && this.santaElfRequest.isCompleted()) {
            // Set time taken to complete
            this.timeToCompleteRequest = DEFAULT_DESPAWN_DELAY - this.despawnDelay;

            this.take(itemEntity, itemEntity.getItem().getCount());

            // Reward the player for completing request
            this.throwRequestRewards();

            // Add to naughty / nice meter
            if (itemEntity.getThrower() != null) {
                Player playerEntity = this.level.getPlayerByUUID(itemEntity.getThrower());
                NaughtyNiceMeter.evaluateAction(playerEntity, NaughtyNiceAction.HELP_SANTA_ELF_EVENT);

                SantaElfEvent.CompleteRequest completeRequestEvent = new SantaElfEvent.CompleteRequest(this,
                        playerEntity, this.timeToCompleteRequest);
                MinecraftForge.EVENT_BUS.post(completeRequestEvent);
            }

            this.isRewardThrown = true;
            itemEntity.remove(RemovalReason.DISCARDED);
        } else if (this.isRequestOutdated && !this.santaElfRequest.isCompleted()) {
            this.take(itemEntity, itemEntity.getItem().getCount());
            this.spawnAtLocation(this.getRequest().getRequestPaper());

            this.isRequestOutdated = false;

            itemEntity.remove(RemovalReason.DISCARDED);
        }
    }

    public MerchantOffer getSantaElfRequestOffer() {
        return this.santaElfRequestOfferIndex == -1 || this.offers == null ? null :
                this.offers.get(this.santaElfRequestOfferIndex);
    }

    public void throwRequestRewards() {
        // Create experience rewards
        this.level.addFreshEntity(SantaElfRewards.generateCompletionXP(this.level, this.position()));

        // Drop loot
        LootContext ctx = this.createLootContext(false, DamageSource.GENERIC).create(LootContextParamSets.ENTITY);
        SantaElfRewards.generateRewards(this, ctx).forEach(this::spawnAtLocation);

        // Creating effects
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                ChristmasSounds.SANTA_ELF_REQUEST_COMPLETE.get(), SoundSource.VOICE, 1.0f, 1.0f);

        this.level.addFreshEntity(new FireworkRocketEntity(this.level, null, this.getX(), this.getY(), this.getZ(),
                this.createCelebratoryFireworks()));
    }

    private ItemStack createCelebratoryFireworks() {
        ItemStack fireworkStack = Items.FIREWORK_ROCKET.getDefaultInstance();
        CompoundTag fireworkNBT = new CompoundTag();

        fireworkNBT.putInt("Flight", 2);

        CompoundTag explosionsNBT = new CompoundTag();
        explosionsNBT.putByte("Type", (byte) 0);

        List<Integer> colorList = Lists.newArrayList();
        colorList.add(((DyeItem) Items.GREEN_DYE).getDyeColor().getFireworkColor());
        colorList.add(((DyeItem) Items.RED_DYE).getDyeColor().getFireworkColor());
        explosionsNBT.putIntArray("Colors", colorList);

        ListTag listWrapper = new ListTag();
        listWrapper.add(explosionsNBT);
        fireworkNBT.put("Explosions", listWrapper);

        fireworkStack.getOrCreateTag().put("Fireworks", fireworkNBT);

        return fireworkStack;
    }

    public boolean canBuyBack() {
        if (this.level.isClientSide()) return false;

        ServerLevel serverLevel = (ServerLevel) this.level;

        SantaSavedData santaData = SantaSavedData.retrieve(serverLevel);

        return santaData.isDefeated();
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            this.maybeDespawn();
        }
    }

    private void maybeDespawn() {
        if (this.despawnDelay > 0 && !this.isTrading() && --this.despawnDelay == 0) {
            this.despawnElf();
        }
    }

    public void despawnElf() {
        if (!this.level.isClientSide()) {
            ServerLevel serverWorld = (ServerLevel) this.level;

            // Spawn poof particles on disappear
            SimpleParticleType particleType = ParticleTypes.POOF;
            for (int i = 0; i < 5; i++) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                serverWorld.sendParticles(particleType, this.getRandomX(1.0D),
                        this.getRandomY() + 0.5D,
                        this.getRandomZ(1.0D), 1, d0, d1, d2, 0.0D);
            }

            serverWorld.playSound(null, this.blockPosition(), ChristmasSounds.SANTA_ELF_DESPAWN.get(),
                    SoundSource.NEUTRAL, 1.0f, 1.0f);
        }

        this.remove(RemovalReason.DISCARDED);
    }

    public void pickUpSomeItems(ItemEntity itemEntity, int itemsToPickUpCount) {
        ItemStack itemStack = itemEntity.getItem();
        this.onItemPickup(itemEntity);
        this.take(itemEntity, itemStack.getCount());

        if (!(itemsToPickUpCount >= itemStack.getCount())) {
            ItemStack itemStackCopy = itemStack.copy();
            itemStackCopy.setCount(itemStack.getCount() - itemsToPickUpCount);
            this.spawnAtLocation(itemStackCopy);
        }
    }

    @Override
    public boolean removeWhenFarAway(double p_213397_1_) {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("DespawnDelay", this.despawnDelay);
        nbt.put("SantaElfRequest", this.santaElfRequest.createTag());
        nbt.put("MerchantOffers", this.getOffers().createTag());
        nbt.putBoolean("IsRequestOutdated", this.isRequestOutdated);
        nbt.putBoolean("IsRewardThrown", this.isRewardThrown);
        nbt.putInt("TimeToCompleteRequest", this.timeToCompleteRequest);
        nbt.putInt("RequestOfferIndex", this.santaElfRequestOfferIndex);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("DespawnDelay", 99)) {
            this.despawnDelay = nbt.getInt("DespawnDelay");
        }

        if (nbt.contains("SantaElfRequest", 10)) {
            this.santaElfRequest = SantaElfRequest.fromTag(nbt.getCompound("SantaElfRequest"));
        }

        if (nbt.contains("MerchantOffers", 10)) {
            this.offers = new MerchantOffers(nbt.getCompound("MerchantOffers"));
        }

        this.isRequestOutdated = nbt.getBoolean("IsRequestOutdated");
        this.isRewardThrown = nbt.getBoolean("IsRewardThrown");

        this.timeToCompleteRequest = nbt.getInt("TimeToCompleteRequest");

        this.santaElfRequestOfferIndex = nbt.getInt("RequestOfferIndex");
    }

    private static class FollowEmeraldsGoal extends Goal {
        private SantaElfEntity santaElfEntity;
        private ItemEntity targetedEntity;

        public FollowEmeraldsGoal(SantaElfEntity santaElfEntity) {
            this.santaElfEntity = santaElfEntity;
        }

        @Override
        public boolean canUse() {
            List<ItemEntity> nearbyEntities = this.santaElfEntity.level.getEntitiesOfClass(ItemEntity.class,
                    this.santaElfEntity.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));

            for (ItemEntity itemEntity : nearbyEntities) {
                if (itemEntity.getItem().is(Items.EMERALD)) {
                    targetedEntity = itemEntity;
                    return true;
                }
            }

            return false;
        }

        @Override
        public void tick() {
            if (targetedEntity != null && targetedEntity.isAlive()) {
                this.santaElfEntity.getNavigation().moveTo(targetedEntity.position().x, targetedEntity.position().y,
                        targetedEntity.position().z, 1.0f);

                if (targetedEntity.distanceToSqr(this.santaElfEntity) < 2.0f) {
                    if (!targetedEntity.hasPickUpDelay()) {
                        this.santaElfEntity.take(targetedEntity, targetedEntity.getItem().getCount());
                        targetedEntity.remove(RemovalReason.DISCARDED);
                        this.targetedEntity = null;
                    }
                }
            } else {
                targetedEntity = null;
            }
        }
    }

    private static class TradeWithPlayerGoal extends Goal {
        private final SantaElfEntity mob;

        public TradeWithPlayerGoal(SantaElfEntity santaElfEntity) {
            this.mob = santaElfEntity;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canUse() {
            if (!this.mob.isAlive()) {
                return false;
            } else if (this.mob.isInWater()) {
                return false;
            } else if (!this.mob.isOnGround()) {
                return false;
            } else if (this.mob.hurtMarked) {
                return false;
            } else {
                Player playerentity = this.mob.getTradingPlayer();
                if (playerentity == null) {
                    return false;
                } else if (this.mob.distanceToSqr(playerentity) > 16.0D) {
                    return false;
                } else {
                    return playerentity.containerMenu != null;
                }
            }
        }

        public void start() {
            this.mob.getNavigation().stop();
        }

        public void stop() {
            this.mob.setTradingPlayer((Player) null);
        }
    }

    private static class LookAtCustomerGoal extends LookAtPlayerGoal {
        private final SantaElfEntity villager;

        public LookAtCustomerGoal(SantaElfEntity elfEntity) {
            super(elfEntity, Player.class, 8.0F);
            this.villager = elfEntity;
        }

        public boolean canUse() {
            if (this.villager.isTrading()) {
                this.lookAt = this.villager.getTradingPlayer();
                return true;
            } else {
                return false;
            }
        }
    }

    private static class PickupRequestedItemGoal extends Goal {
        private SantaElfEntity santaElfEntity;
        private ItemEntity targetedEntity;

        public PickupRequestedItemGoal(SantaElfEntity santaElfEntity) {
            this.santaElfEntity = santaElfEntity;
        }

        @Override
        public boolean canUse() {
            MerchantOffer requestOffer = santaElfEntity.getSantaElfRequestOffer();
            if (requestOffer == null || !requestOffer.isOutOfStock()) return false;

            SantaElfRequest request = this.santaElfEntity.getRequest();
            if (request.isCompleted()) return false;

            List<ItemEntity> nearbyEntities = this.santaElfEntity.level.getEntitiesOfClass(ItemEntity.class,
                    this.santaElfEntity.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));

            if (nearbyEntities.size() != 0) {
                for (ItemEntity entity : nearbyEntities) {
                    if (this.santaElfEntity.getRequest().isInRequest(entity.getItem())) {
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
                this.santaElfEntity.getNavigation().moveTo(targetedEntity.position().x, targetedEntity.position().y,
                        targetedEntity.position().z, 1.0f);

                if (targetedEntity.distanceToSqr(this.santaElfEntity) < 2.0f) {
                    this.santaElfEntity.handleRequestItemOnGround(this.targetedEntity);
                }
            }
        }
    }

    private static class SwapToyPartsRequestGoal extends Goal {
        private SantaElfEntity santaElfEntity;
        private ItemEntity targetedEntity;

        public SwapToyPartsRequestGoal(SantaElfEntity santaElfEntity) {
            this.santaElfEntity = santaElfEntity;
        }

        @Override
        public boolean canUse() {
            if (this.santaElfEntity.isRewardThrown) return false;

            List<ItemEntity> nearbyEntities = this.santaElfEntity.level.getEntitiesOfClass(ItemEntity.class,
                    this.santaElfEntity.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));

            if (nearbyEntities.size() != 0) {
                for (ItemEntity entity : nearbyEntities) {
                    if (ItemStack.isSame(entity.getItem(),
                            ChristmasItems.TOY_PARTS_REQUEST.get().getDefaultInstance())) {
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
                this.santaElfEntity.getNavigation().moveTo(targetedEntity, 1.0f);

                if (targetedEntity.distanceToSqr(this.santaElfEntity) < 2.0f) {
                    this.santaElfEntity.handleRequestPaperOnGround(targetedEntity);
                }
            }
        }
    }

    static class EmeraldForItemsTrade implements VillagerTrades.ItemListing {
        private final Item item;
        private final int cost;
        private final int emeraldsExpected;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForItemsTrade(ItemLike itemProvider, int cost, int emeraldsExpected, int maxUses, int xp) {
            this.item = itemProvider.asItem();
            this.cost = cost;
            this.emeraldsExpected = emeraldsExpected;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
            ItemStack itemstack = new ItemStack(this.item, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD, emeraldsExpected), this.maxUses,
                    this.villagerXp, this.priceMultiplier);
        }

        public static VillagerTrades.ItemListing[] tradesFromTag(Tag<Item> itemTag, int cost, int emeraldsExpected,
                                                                 int maxUses, int xp) {
            VillagerTrades.ItemListing[] results = new VillagerTrades.ItemListing[itemTag.getValues().size()];

            for (int i = 0; i < results.length; i++) {
                results[i] = new EmeraldForItemsTrade(itemTag.getValues().get(i), cost, emeraldsExpected, maxUses, xp);
            }

            return results;
        }
    }

    static class ItemsForEmeraldsTrade implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeraldsTrade(Block blockItem, int emeraldCost, int numberOfItems, int maxUses, int xp) {
            this(new ItemStack(blockItem), emeraldCost, numberOfItems, maxUses, xp);
        }

        public ItemsForEmeraldsTrade(Item item, int emeraldCost, int numberOfItems, int xp) {
            this(new ItemStack(item), emeraldCost, numberOfItems, 6, xp);
        }

        public ItemsForEmeraldsTrade(Item item, int emeraldCost, int numberOfItems, int maxUses, int xp) {
            this(new ItemStack(item), emeraldCost, numberOfItems, maxUses, xp);
        }

        public ItemsForEmeraldsTrade(ItemStack itemStack, int emeraldCost, int numberOfItems, int maxUses, int xp) {
            this(itemStack, emeraldCost, numberOfItems, maxUses, xp, 0.05F);
        }

        public ItemsForEmeraldsTrade(ItemStack itemStack, int emeraldCost, int numberOfItems, int maxUses, int xp,
                                     float priceMultiplier) {
            this.itemStack = itemStack;
            this.emeraldCost = emeraldCost;
            this.numberOfItems = numberOfItems;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = priceMultiplier;
        }

        public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
            ItemStack offerStack = new ItemStack(this.itemStack.getItem(), this.numberOfItems);
            offerStack.setTag(this.itemStack.getTag());
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), offerStack, this.maxUses,
                    this.villagerXp, this.priceMultiplier);
        }

        public static VillagerTrades.ItemListing[] tradesFromTag(Tag<Item> itemTag, int emeraldCost,
                                                                 int numberOfItems, int maxUses, int xp) {
            VillagerTrades.ItemListing[] results = new VillagerTrades.ItemListing[itemTag.getValues().size()];

            for (int i = 0; i < results.length; i++) {
                results[i] = new ItemsForEmeraldsTrade(itemTag.getValues().get(i), emeraldCost, numberOfItems,
                        maxUses, xp);
            }

            return results;
        }
    }
}
