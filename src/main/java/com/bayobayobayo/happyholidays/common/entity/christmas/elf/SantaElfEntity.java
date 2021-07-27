package com.bayobayobayo.happyholidays.common.entity.christmas.elf;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.registry.BlockRegistry;
import com.bayobayobayo.happyholidays.common.registry.ItemRegistry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtCustomerGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
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

    private final Inventory inventory = new Inventory(8);

    private AnimationFactory factory = new AnimationFactory(this);

    @Nullable
    private PlayerEntity tradingPlayer;
    @Nullable
    protected MerchantOffers offers;

    public SantaElfEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(0, new LookAtCustomerGoal(this));
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
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
        return SoundEvents.VILLAGER_YES;
    }

    protected SoundEvent getTradeUpdatedSound(boolean b) {
        return b ? SoundEvents.VILLAGER_YES : SoundEvents.VILLAGER_NO;
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

    protected void addOffersFromItemListings(MerchantOffers merchantOffers, VillagerTrades.ITrade[] trades, int maxTrades) {
        Set<Integer> set = Sets.newHashSet();
        if (trades.length > maxTrades) {
            while (set.size() < maxTrades) {
                set.add(this.random.nextInt(trades.length));
            }
        } else {
            for (int i = 0; i < trades.length; ++i) {
                set.add(i);
            }
        }

        for (Integer integer : set) {
            VillagerTrades.ITrade villagertrades$itrade = trades[integer];
            MerchantOffer merchantoffer = villagertrades$itrade.getOffer(this, this.random);
            if (merchantoffer != null) {
                merchantOffers.add(merchantoffer);
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
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
}
