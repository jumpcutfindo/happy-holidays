package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;
import com.jumpcutfindo.happyholidays.common.registry.SoundRegistry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SantaElfEntity extends CreatureEntity implements IAnimatable, IMerchant {
    public static final String ENTITY_ID = "santa_elf";
    public static final AttributeModifierMap ENTITY_ATTRIBUTES =
            MobEntity.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 20.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.27D)
                    .build();
    public static final int DEFAULT_DESPAWN_DELAY = 24000;

    private static final ResourceLocation SANTA_ELF_REQUEST_LOOT_TABLE = new ResourceLocation("happyholidays:entities/santa_elf_request");

    private AnimationFactory factory = new AnimationFactory(this);

    @Nullable
    private PlayerEntity tradingPlayer;
    @Nullable
    protected MerchantOffers offers;

    private int despawnDelay;
    private SantaElfRequest santaElfRequest;
    private int santaElfRequestOfferIndex = -1;

    private int requestItemCooldown = 0;
    private int requestPaperCooldown = 0;
    private boolean isRewardThrown = false;
    private boolean isRequestOutdated = false;

    public SantaElfEntity(EntityType<? extends CreatureEntity> entityType, World world) {
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
        this.goalSelector.addGoal(3, new SwimGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));

    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.GENERIC_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
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
    protected ActionResultType mobInteract(PlayerEntity playerEntity, Hand hand) {
        if (this.getOffers().isEmpty()) {
            return ActionResultType.sidedSuccess(this.level.isClientSide);
        } else {
            if (!this.level.isClientSide) {
                this.setTradingPlayer(playerEntity);
                this.openTradingScreen(playerEntity, this.getDisplayName(), 1);
            }

            return ActionResultType.sidedSuccess(this.level.isClientSide);
        }
    }

    @Override
    public void setTradingPlayer(@Nullable PlayerEntity playerEntity) {
        this.tradingPlayer = playerEntity;
    }

    @Nullable
    @Override
    public PlayerEntity getTradingPlayer() {
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
    }

    @Override
    public void notifyTradeUpdated(ItemStack itemStack) {
        if (!this.level.isClientSide && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
            this.ambientSoundTime = -this.getAmbientSoundInterval();
            this.playSound(this.getTradeUpdatedSound(!itemStack.isEmpty()), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    public SoundEvent getNotifyTradeSound() {
        return SoundRegistry.SANTA_ELF_YES.get();
    }

    protected SoundEvent getTradeUpdatedSound(boolean b) {
        return b ? SoundRegistry.SANTA_ELF_YES.get() : SoundRegistry.SANTA_ELF_NO.get();
    }

    @Override
    public World getLevel() {
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
            this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY() + 0.5D, this.getZ(), i));
        }
    }

    public boolean isTrading() {
        return this.tradingPlayer != null;
    }

    protected void updateTrades() {
        MerchantOffers merchantOffers = this.getOffers();

        // Standard trades
        List<VillagerTrades.ITrade[]> tradesList = ImmutableList.of(
                SantaElfTrades.SMALL_BALL_ORNAMENT_TRADES,
                SantaElfTrades.BIG_BALL_ORNAMENT_TRADES,
                SantaElfTrades.TINSEL_TRADES,
                SantaElfTrades.CHRISTMAS_LIGHT_TRADES
        );

        for (VillagerTrades.ITrade[] trades : tradesList) {
            for (int i = 0; i < 2; i++) {
                int randInt = this.random.nextInt(trades.length);

                VillagerTrades.ITrade randomTrade = trades[randInt];
                MerchantOffer merchantOffer = randomTrade.getOffer(this, this.random);

                if (merchantOffer != null) merchantOffers.add(merchantOffer);
            }
        }

        List<VillagerTrades.ITrade[]> rareTradesList = ImmutableList.of(
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
                    SoundRegistry.SANTA_ELF_REQUEST_SINGLE_SUCCESS.get(), SoundCategory.VOICE, 1.0f, 1.0f);

            this.requestItemCooldown = 40;
            this.isRequestOutdated = true;

            itemEntity.remove();
        }
    }

    public void handleRequestPaperOnGround(ItemEntity itemEntity) {
        if (!this.isRewardThrown && this.santaElfRequest.isCompleted()) {
            this.take(itemEntity, itemEntity.getItem().getCount());
            this.throwRequestRewards();

            this.isRewardThrown = true;
            itemEntity.remove();
        } else if (this.isRequestOutdated && !this.santaElfRequest.isCompleted()) {
            this.take(itemEntity, itemEntity.getItem().getCount());
            this.spawnAtLocation(this.getRequest().getRequestPaper());

            this.requestPaperCooldown = 40;
            this.isRequestOutdated = false;

            itemEntity.remove();
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
        LootContext ctx = this.createLootContext(true, DamageSource.GENERIC).create(LootParameterSets.ENTITY);

        lootTable.getRandomItems(ctx).forEach(itemStack -> {
            if (ChristmasItem.isBasicOrnamentItem(itemStack)) {
                itemStack.setCount((this.random.nextInt(36 - 12) + 1) + 12);
            } else if (ChristmasItem.isRareOrnamentItem(itemStack)) {
                itemStack.setCount((this.random.nextInt(8 - 4) + 1) + 4);
            } else if (ItemStack.isSame(itemStack, ItemRegistry.PRESENT_SCRAPS.get().getDefaultInstance())) {
                itemStack.setCount((this.random.nextInt(18 - 12) + 1) + 12);
            }

            this.spawnAtLocation(itemStack);
        });

        this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundRegistry.SANTA_ELF_REQUEST_COMPLETE.get(), SoundCategory.VOICE, 1.0f, 1.0f);

        // Creating effects
        this.level.addFreshEntity(new FireworkRocketEntity(this.level, null, this.getX(), this.getY(), this.getZ(),
                this.createCelebratoryFireworks()));

        this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY(), this.getZ(), 36));
    }

    private ItemStack createCelebratoryFireworks() {
        ItemStack fireworkStack = Items.FIREWORK_ROCKET.getDefaultInstance();
        CompoundNBT fireworkNBT = new CompoundNBT();

        fireworkNBT.putInt("Flight", 2);

        CompoundNBT explosionsNBT = new CompoundNBT();
        explosionsNBT.putByte("Type", (byte) 0);

        List<Integer> colorList = Lists.newArrayList();
        colorList.add(((DyeItem) Items.GREEN_DYE).getDyeColor().getFireworkColor());
        colorList.add(((DyeItem) Items.RED_DYE).getDyeColor().getFireworkColor());
        explosionsNBT.putIntArray("Colors", colorList);

        ListNBT listWrapper = new ListNBT();
        listWrapper.add(explosionsNBT);
        fireworkNBT.put("Explosions", listWrapper);

        fireworkStack.getOrCreateTag().put("Fireworks", fireworkNBT);

        return fireworkStack;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("DespawnDelay", this.despawnDelay);
        nbt.put("SantaElfRequest", this.santaElfRequest.createTag());
        nbt.put("MerchantOffers", this.getOffers().createTag());
        nbt.putBoolean("IsRequestOutdated", this.isRequestOutdated);
        nbt.putBoolean("IsRewardThrown", this.isRewardThrown);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
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
            this.remove();
        }
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
                PlayerEntity playerentity = this.mob.getTradingPlayer();
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
            this.mob.setTradingPlayer((PlayerEntity)null);
        }
    }

    private static class LookAtCustomerGoal extends LookAtGoal {
        private final SantaElfEntity villager;

        public LookAtCustomerGoal(SantaElfEntity elfEntity) {
            super(elfEntity, PlayerEntity.class, 8.0F);
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
                    if (ItemStack.isSame(entity.getItem(), ItemRegistry.TOY_PARTS_REQUEST.get().getDefaultInstance())) {
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

    static class EmeraldForItemsTrade implements VillagerTrades.ITrade {
        private final Item item;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForItemsTrade(IItemProvider itemProvider, int cost, int maxUses, int xp) {
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

    static class ItemsForEmeraldsTrade implements VillagerTrades.ITrade {
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
