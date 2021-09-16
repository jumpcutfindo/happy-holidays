package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceAction;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaElfEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEffects;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SantaElfEntity extends ChristmasEntity implements IAnimatable, Merchant {
    public static final String ENTITY_ID = "santa_elf";
    public static final AttributeSupplier ENTITY_ATTRIBUTES =
            Mob.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 20.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.27D)
                    .build();

    public static final float ENTITY_BOX_SIZE = 0.8f;
    public static final float ENTITY_BOX_HEIGHT = 22.0f / 16.0f;

    public static final int DEFAULT_DESPAWN_DELAY = 24000;

    private static final ResourceLocation SANTA_ELF_REQUEST_LOOT_TABLE = new ResourceLocation("happyholidays:entities/santa_elf_request");
    private static final double REQUEST_ORNAMENT_DROP_BASE_CHANCE = 0.2d;

    private AnimationFactory factory = new AnimationFactory(this);

    @Nullable
    private Player tradingPlayer;
    @Nullable
    protected MerchantOffers offers;

    private int despawnDelay;
    private SantaElfRequest santaElfRequest;
    private int santaElfRequestOfferIndex = -1;

    private int requestItemCooldown = 0;
    private int requestPaperCooldown = 0;

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

                    ChristmasStarTileEntity starTileEntity =
                            ChristmasStarTileEntity.getStarInfluencingEntity(this.level, this.position());
                    if (starTileEntity != null && starTileEntity.isBonusActive()) {
                        discount = 0.8D;
                    } else {
                        discount = 0.1D * (double) (christmasDebuff.getAmplifier() + 1);
                    }

                    for (MerchantOffer merchantOffer : offers) {
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
            SantaElfEvent.Trade tradeEvent = new SantaElfEvent.Trade(this.getTradingPlayer(), this);
            MinecraftForge.EVENT_BUS.post(tradeEvent);
        }
    }

    @Override
    public void notifyTradeUpdated(ItemStack itemStack) {
        if (!this.level.isClientSide && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
            this.ambientSoundTime = -this.getAmbientSoundInterval();
            this.playSound(this.getTradeUpdatedSound(!itemStack.isEmpty()), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    public SoundEvent getNotifyTradeSound() {
        return ChristmasSounds.SANTA_ELF_YES.get();
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

        // Standard trades
        List<VillagerTrades.ItemListing[]> tradesList = ImmutableList.of(
                SantaElfTrades.SMALL_BALL_ORNAMENT_TRADES,
                SantaElfTrades.BIG_BALL_ORNAMENT_TRADES,
                SantaElfTrades.TINSEL_TRADES,
                SantaElfTrades.CHRISTMAS_LIGHT_TRADES
        );

        for (VillagerTrades.ItemListing[] trades : tradesList) {
            for (int i = 0; i < 1; i++) {
                int randInt = this.random.nextInt(trades.length);

                VillagerTrades.ItemListing randomTrade = trades[randInt];
                MerchantOffer merchantOffer = randomTrade.getOffer(this, this.random);

                if (merchantOffer != null) merchantOffers.add(merchantOffer);
            }
        }

        List<VillagerTrades.ItemListing[]> rareTradesList = ImmutableList.of(
                SantaElfTrades.HEAD_ORNAMENT_TRADES,
                SantaElfTrades.SHEET_MUSIC_TRADES
        );

        // Rare trades
        int rareTradeRandInt = this.random.nextInt(rareTradesList.size());
        if (rareTradeRandInt == 0) {
            int randInt = this.random.nextInt(rareTradesList.size());
            MerchantOffer merchantOffer = rareTradesList.get(0)[randInt].getOffer(this, this.random);

            if (merchantOffer != null) merchantOffers.add(merchantOffer);
        } else if (rareTradeRandInt == 1) {
            for (int i = 0; i < 2; i++) {
                int randInt = this.random.nextInt(rareTradesList.get(1).length);
                MerchantOffer merchantOffer = rareTradesList.get(1)[randInt].getOffer(this, this.random);

                if (merchantOffer != null) merchantOffers.add(merchantOffer);
            }
        }

        // Food trades
        for (int i = 0; i < 2; i ++) {
            int randInt = this.random.nextInt(SantaElfTrades.FOOD_TRADES.length);
            MerchantOffer merchantOffer = SantaElfTrades.FOOD_TRADES[randInt].getOffer(this, this.random);

            if (merchantOffer != null) merchantOffers.add(merchantOffer);
        }

        // Add Santa's Elf Request
        SantaElfEntity.ItemsForEmeraldsTrade santaElfRequestTrade =
                new SantaElfEntity.ItemsForEmeraldsTrade(this.santaElfRequest.getRequestPaper(), 1,1, 1, 2);
        MerchantOffer santaElfRequestOffer = santaElfRequestTrade.getOffer(this, this.random);
        if (santaElfRequestOffer != null) merchantOffers.add(santaElfRequestOffer);
        this.santaElfRequestOfferIndex = merchantOffers.indexOf(santaElfRequestOffer);

        // Sometimes appears trades
        if (this.random.nextBoolean()) {
            int randInt = this.random.nextInt(SantaElfTrades.SOMETIMES_APPEAR_TRADES.length);

            MerchantOffer merchantOffer = SantaElfTrades.SOMETIMES_APPEAR_TRADES[randInt].getOffer(this, this.random);
            if (merchantOffer != null) merchantOffers.add(merchantOffer);
        }

        // Always appears trades
        for (int i = 0; i < SantaElfTrades.ALWAYS_APPEAR_TRADES.length; i++) {
            MerchantOffer merchantOffer = SantaElfTrades.ALWAYS_APPEAR_TRADES[i].getOffer(this, this.random);
            if (merchantOffer != null) merchantOffers.add(merchantOffer);
        }
    }

    public SantaElfRequest getRequest() {
        return this.santaElfRequest;
    }

    public void handleRequestItemOnGround(ItemEntity itemEntity) {
        SantaElfRequest.SingleElfRequest completedRequest =
                this.getRequest().tryFulfilRequest(itemEntity.getItem());

        if (completedRequest != null) {
            this.pickUpSomeItems(itemEntity, completedRequest.getNumberOfItems());
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                    ChristmasSounds.SANTA_ELF_REQUEST_SINGLE_SUCCESS.get(), SoundSource.VOICE, 1.0f, 1.0f);

            this.requestItemCooldown = 40;
            this.isRequestOutdated = true;

            itemEntity.remove(RemovalReason.DISCARDED);
        }
    }

    public void handleRequestPaperOnGround(ItemEntity itemEntity) {
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

                SantaElfEvent.CompleteRequest completeRequestEvent = new SantaElfEvent.CompleteRequest(playerEntity,
                        this, this.timeToCompleteRequest);
                MinecraftForge.EVENT_BUS.post(completeRequestEvent);
            }

            this.isRewardThrown = true;
            itemEntity.remove(RemovalReason.DISCARDED);
        } else if (this.isRequestOutdated && !this.santaElfRequest.isCompleted()) {
            this.take(itemEntity, itemEntity.getItem().getCount());
            this.spawnAtLocation(this.getRequest().getRequestPaper());

            this.requestPaperCooldown = 40;
            this.isRequestOutdated = false;

            itemEntity.remove(RemovalReason.DISCARDED);
        }
    }

    public boolean isReadyToTakeRequestItem() {
        return this.requestItemCooldown <= 0;
    }

    public boolean isReadyToTakeRequestPaper() {
        return this.requestPaperCooldown <= 0;
    }

    public MerchantOffer getSantaElfRequestOffer() {
        return this.santaElfRequestOfferIndex == -1 ? null : this.offers.get(this.santaElfRequestOfferIndex);
    }

    public void throwRequestRewards() {
        LootTable lootTable = this.level.getServer().getLootTables().get(SANTA_ELF_REQUEST_LOOT_TABLE);
        LootContext ctx = this.createLootContext(true, DamageSource.GENERIC).create(LootContextParamSets.ENTITY);

        double modifier;
        ChristmasStarTileEntity starTileEntity = ChristmasStarTileEntity.getStarInfluencingEntity(this.level,
                this.position());
        if (starTileEntity != null) {
            if (starTileEntity.isBonusActive()) {
                modifier = 2.0D;
            } else {
                modifier = 1.0D + (starTileEntity.getCurrentTier() * 0.1D);
            }
        } else {
            modifier = 1.0D;
        }

        // Drop random items
        lootTable.getRandomItems(ctx).forEach(itemStack -> {
            if (ChristmasItems.isBasicOrnamentItem(itemStack)) {
                itemStack.setCount((this.random.nextInt(36 - 12) + 1) + 12);
            } else if (ChristmasItems.isRareOrnamentItem(itemStack)) {
                itemStack.setCount((this.random.nextInt(8 - 4) + 1) + 4);
            } else if (ItemStack.isSame(itemStack, ChristmasItems.PRESENT_SCRAPS.get().getDefaultInstance())) {
                itemStack.setCount((this.random.nextInt(18 - 12) + 1) + 12);
            }

            // Apply modifier
            itemStack.setCount((int) (itemStack.getCount() * modifier));

            this.spawnAtLocation(itemStack);
        });

        // Drop ornament block
        double ornamentDropChance = REQUEST_ORNAMENT_DROP_BASE_CHANCE * modifier;
        if (ornamentDropChance > this.random.nextDouble()) {
            ItemStack elfOrnamentItem = ChristmasItems.SANTA_ELF_ORNAMENT.get().getDefaultInstance();
            this.spawnAtLocation(elfOrnamentItem);
        }

        this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                ChristmasSounds.SANTA_ELF_REQUEST_COMPLETE.get(), SoundSource.VOICE, 1.0f, 1.0f);

        // Creating effects
        this.level.addFreshEntity(new FireworkRocketEntity(this.level, null, this.getX(), this.getY(), this.getZ(),
                this.createCelebratoryFireworks()));

        this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY(), this.getZ(), 36));
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
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            this.maybeDespawn();
        }

        if (--requestItemCooldown > 0);
        if (--requestPaperCooldown > 0);
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
            this.mob.setTradingPlayer((Player)null);
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
            if (targetedEntity != null || !targetedEntity.isAlive()) {
                this.santaElfEntity.getNavigation().moveTo(targetedEntity, 1.0f);

                if (this.santaElfEntity.isReadyToTakeRequestItem() && targetedEntity.distanceToSqr(this.santaElfEntity) < 2.0f) {
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
                    if (ItemStack.isSame(entity.getItem(), ChristmasItems.TOY_PARTS_REQUEST.get().getDefaultInstance())) {
                        targetedEntity = entity;
                        return true;
                    }
                }
            }

            return false;
        }

        @Override
        public void tick() {
            if (targetedEntity != null || !targetedEntity.isAlive()) {
                this.santaElfEntity.getNavigation().moveTo(targetedEntity, 1.0f);

                if (this.santaElfEntity.isReadyToTakeRequestPaper() && targetedEntity.distanceToSqr(this.santaElfEntity) < 2.0f) {
                    this.santaElfEntity.handleRequestPaperOnGround(targetedEntity);
                }
            }
        }
    }

    static class EmeraldForItemsTrade implements VillagerTrades.ItemListing {
        private final Item item;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForItemsTrade(ItemLike itemProvider, int cost, int maxUses, int xp) {
            this.item = itemProvider.asItem();
            this.cost = cost;
            this.maxUses = maxUses;
            this.villagerXp = xp;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
            ItemStack itemstack = new ItemStack(this.item, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
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

        public ItemsForEmeraldsTrade(ItemStack itemStack, int emeraldCost, int numberOfItems, int maxUses, int xp, float priceMultiplier) {
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
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), offerStack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
}
