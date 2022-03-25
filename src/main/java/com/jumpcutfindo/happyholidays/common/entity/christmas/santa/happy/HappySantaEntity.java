package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.happy;

import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.SantaGiftType;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.SantaGifts;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class HappySantaEntity extends BaseSantaEntity {
    public static final EntityDataAccessor<Boolean> IS_SUMMONING = SynchedEntityData.defineId(HappySantaEntity.class,
            EntityDataSerializers.BOOLEAN);

    public static final int DEFAULT_DESPAWN_DELAY = 12000;

    public static final int NUM_GIFTS_TO_SUMMON = 150;
    public static final int GIFT_SUMMON_RADIUS = 20;
    public static final int GIFT_SUMMON_HEIGHT = 5;
    public static final int GIFT_LIFESPAN = 160;
    public static final int GIFT_SPAWN_INTERVAL_MIN = 3;
    public static final int GIFT_SPAWN_INTERVAL_MAX = 6;

    // Gift spawn thresholds. Basic = 75%, Rare = 20% and Legendary = 5%
    public static final double BASIC_GIFT_SPAWN_CHANCE_THRESHOLD = 1.0;
    public static final double RARE_GIFT_SPAWN_CHANCE_THRESHOLD = 0.25;
    public static final double LEGENDARY_GIFT_SPAWN_CHANCE_THRESHOLD = 0.05;

    private BlockPos summoningPos = null;

    private boolean hasStartedSummoningGifts = false;
    private boolean hasSummonedGifts = false;
    private boolean isSummoning = false;
    private int giftsRemaining = -1;

    private int despawnDelay;

    private AABB areaOfEffect;
    private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS));

    public HappySantaEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(IS_SUMMONING, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SummonGiftsGoal(this));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    public boolean isSummoning() {
        return isSummoning;
    }

    public boolean hasSummonedGifts() {
        return hasSummonedGifts;
    }

    public int getGiftsRemaining() {
        return giftsRemaining;
    }

    public void startDropParty() {
        if (!this.hasStartedSummoningGifts) this.giftsRemaining = NUM_GIFTS_TO_SUMMON;

        this.isSummoning = true;
        this.entityData.set(IS_SUMMONING, true);

        this.hasStartedSummoningGifts = true;

        if (this.summoningPos == null) this.summoningPos = this.blockPosition();

        if (this.level.isClientSide()) this.playDropPartySummonSound();

        // Show boss bar to all players around the area
        if (!this.level.isClientSide()) {
            this.bossEvent.setProgress((float) this.giftsRemaining / (float) (NUM_GIFTS_TO_SUMMON));
            this.areaOfEffect = new AABB(this.summoningPos).inflate(NAUGHTY_NICE_CONSIDERATION_RADIUS);

            List<Player> playerList = this.level.getEntitiesOfClass(Player.class, this.areaOfEffect);
            for (Player playerEntity : playerList) this.bossEvent.addPlayer((ServerPlayer) playerEntity);
        }
    }

    public void stopDropParty() {
        this.isSummoning = false;
        this.entityData.set(IS_SUMMONING, false);

        this.hasSummonedGifts = true;

        this.summoningPos = null;

        if (this.level.isClientSide()) this.stopDropPartySummonSound();

        this.bossEvent.removeAllPlayers();

        this.despawnDelay = DEFAULT_DESPAWN_DELAY;

        this.spawnAtLocation(ChristmasItems.ENCHANTED_SANTA_HAT.get().getDefaultInstance());

        if (!this.level.isClientSide()) {
            AABB searchBox =
                    new AABB(this.blockPosition()).inflate(NAUGHTY_NICE_CONSIDERATION_RADIUS);
            List<Player> playerEntities = this.level.getEntitiesOfClass(Player.class, searchBox);

            for (Player playerEntity : playerEntities) {
                SantaEvent event = new SantaEvent.CompleteDropParty(this, playerEntity);
                MinecraftForge.EVENT_BUS.post(event);
            }

            this.onDefeat((ServerLevel) this.level);
        }
    }

    public void playDropPartySummonSound() {
        MinecraftForge.EVENT_BUS.post(new SantaEvent.SoundChange(this, Minecraft.getInstance().player, true));
    }

    public void stopDropPartySummonSound() {
        MinecraftForge.EVENT_BUS.post(new SantaEvent.SoundChange(this, Minecraft.getInstance().player, false));
    }

    public void summonGift() {
        if (this.level.isClientSide()) return;

        int randomX = this.random.nextInt(GIFT_SUMMON_RADIUS) * (this.random.nextBoolean() ? 1 : -1);
        int randomY = this.random.nextInt(GIFT_SUMMON_HEIGHT);
        int randomZ = this.random.nextInt(GIFT_SUMMON_RADIUS) * (this.random.nextBoolean() ? 1 : -1);

        BlockPos randomPos = this.blockPosition().offset(randomX, randomY, randomZ);
        BlockState currState = this.level.getBlockState(randomPos);

        int counter = 0;
        while (!this.level.getBlockState(randomPos).is(Blocks.AIR)) {
            if (counter > GIFT_SUMMON_RADIUS) break;
            randomPos = randomPos.above();
            counter++;
        }

        // Unable to summon a gift at this position, so we just stop the process
        if (!this.level.getBlockState(randomPos).is(Blocks.AIR)) return;

        // Create a gift to be spawned
        ItemStack giftItem = ItemStack.EMPTY;
        LootContext ctx = this.createLootContext(true, DamageSource.GENERIC).create(LootContextParamSets.ENTITY);

        SimpleParticleType particleType = null;

        double giftChance = this.random.nextDouble();
        if (giftChance < LEGENDARY_GIFT_SPAWN_CHANCE_THRESHOLD) {
            giftItem = SantaGifts.generateGift(SantaGiftType.LEGENDARY, this, (ServerLevel) this.level, ctx);
            particleType = ChristmasParticles.CHRISTMAS_MEDIUM_GOLD.get();
        } else if (giftChance < RARE_GIFT_SPAWN_CHANCE_THRESHOLD) {
            giftItem = SantaGifts.generateGift(SantaGiftType.RARE, this, (ServerLevel) this.level, ctx);
            particleType = ChristmasParticles.CHRISTMAS_MEDIUM_GREEN.get();
        } else {
            giftItem = SantaGifts.generateGift(SantaGiftType.BASIC, this, (ServerLevel) this.level, ctx);
            particleType = ChristmasParticles.CHRISTMAS_MEDIUM_BLUE.get();
        }

        ItemEntity giftEntity = new ItemEntity(this.level, randomPos.getX(), randomPos.getY(), randomPos.getZ(), giftItem);
        giftEntity.lifespan = GIFT_LIFESPAN;

        for (int i = 0; i < 3; i++) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            ((ServerLevel) this.level).sendParticles(particleType,
                    randomPos.getX() + this.random.nextDouble(),
                    randomPos.getY() + this.random.nextDouble(),
                    randomPos.getZ() + this.random.nextDouble(),
                    1, d0, d1, d2,
                    0.0D
            );
        }

        this.level.addFreshEntity(giftEntity);
        this.level.playSound(null, randomPos.getX(), randomPos.getY(),
                randomPos.getZ(), ChristmasSounds.SANTA_ITEM_APPEAR.get(), SoundSource.NEUTRAL, 1.0f, 1.0f);

        this.giftsRemaining--;

        this.bossEvent.setProgress((float) this.giftsRemaining / (float) (NUM_GIFTS_TO_SUMMON));
    }

    public void createSummoningParticle() {
        double d0 = (double)(this.random.nextFloat() * 0.1F) + 0.25D;
        double d1 = (double)(this.random.nextFloat() * 0.1F) + 0.25D;
        double d2 = (double)(this.random.nextFloat() * 0.1F) + 0.25D;

        double d = this.random.nextDouble();
        SimpleParticleType particleType = d < 0.25 ? ChristmasParticles.CHRISTMAS_SMALL_RED.get()
                : d < 0.5 ? ChristmasParticles.CHRISTMAS_MEDIUM_RED.get()
                : d < 0.75 ? ChristmasParticles.CHRISTMAS_SMALL_GREEN.get()
                : ChristmasParticles.CHRISTMAS_MEDIUM_GREEN.get();
        
        ((ServerLevel) this.level).sendParticles(particleType,
                this.getX(),
                this.getY() + d1 + 1.5D,
                this.getZ(),
                2, d0, d1, d2, 0.0D);
    }

    public BlockPos getSummoningPos() {
        return summoningPos;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putBoolean("HasSummonedGifts", this.hasSummonedGifts);
        nbt.putBoolean("HasStartedSummoningGifts", this.hasStartedSummoningGifts);
        nbt.putInt("GiftsRemaining", this.giftsRemaining);
        nbt.putInt("DespawnDelay", this.despawnDelay);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        this.hasSummonedGifts = nbt.getBoolean("HasSummonedGifts");
        this.hasStartedSummoningGifts = nbt.getBoolean("HasStartedSummoningGifts");
        this.giftsRemaining = nbt.getInt("GiftsRemaining");
        this.despawnDelay = nbt.getInt("DespawnDelay");
    }

    @Override
    public void die(DamageSource p_70645_1_) {
        super.die(p_70645_1_);
        this.bossEvent.removeAllPlayers();
        this.isSummoning = false;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level.isClientSide()) {
            this.isSummoning = this.entityData.get(IS_SUMMONING);
            if (this.isAlive() && this.isSummoning) playDropPartySummonSound();
            else {
                this.stopDropPartySummonSound();
            }
        } else {
            if (this.isSummoning) {
                createSummoningParticle();

                if (this.isAlive() && this.level.getGameTime() % 60L == 0) {
                    List<Player> playerList = this.level.getEntitiesOfClass(Player.class, this.areaOfEffect);

                    // Remove players outside the AOE
                    for (ServerPlayer serverPlayerEntity : this.bossEvent.getPlayers()) {
                        if (!playerList.contains(serverPlayerEntity)) this.bossEvent.removePlayer(serverPlayerEntity);
                    }

                    // Add players inside the AOE
                    for (Player playerEntity : playerList) {
                        ServerPlayer serverPlayerEntity = (ServerPlayer) playerEntity;
                        if (!this.bossEvent.getPlayers().contains(serverPlayerEntity)) this.bossEvent.addPlayer((ServerPlayer) playerEntity);
                    }
                }
            } else {
                this.maybeDespawn();
            }
        }
    }

    private void maybeDespawn() {
        if (this.despawnDelay > 0 && --this.despawnDelay == 0) {
            this.remove(RemovalReason.KILLED);
        }
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ChristmasSounds.SANTA_PASSIVE.get();
    }

    @Override
    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isSummoning) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.summon", true));

            return PlayState.CONTINUE;
        }
        else return super.predicate(event);
    }



}
