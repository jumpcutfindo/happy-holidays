package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.happy;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.SantaGiftType;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.SantaGifts;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ParticleRegistry;
import com.jumpcutfindo.happyholidays.common.registry.SoundRegistry;
import com.jumpcutfindo.happyholidays.common.sound.christmas.SantaSummonSound;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class HappySantaEntity extends BaseSantaEntity {
    public static final DataParameter<Boolean> IS_SUMMONING = EntityDataManager.defineId(HappySantaEntity.class,
            DataSerializers.BOOLEAN);

    public static final String ENTITY_ID = "happy_santa";

    public static final int DEFAULT_DESPAWN_DELAY = 12000;

    public static final int NUM_GIFTS_TO_SUMMON = 150;
    public static final int GIFT_SUMMON_RADIUS = 20;
    public static final int GIFT_SUMMON_HEIGHT = 5;
    public static final int GIFT_LIFESPAN = 160;
    public static final int GIFT_SPAWN_INTERVAL_MIN = 5;
    public static final int GIFT_SPAWN_INTERVAL_MAX = 10;

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

    private SantaSummonSound summonSound;

    private AxisAlignedBB areaOfEffect;
    private final ServerBossInfo bossEvent = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(),
            BossInfo.Color.RED, BossInfo.Overlay.PROGRESS));

    public HappySantaEntity(EntityType<? extends CreatureEntity> entityType, World world) {
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
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
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

        // Play summoning sounds
        this.summonSound = new SantaSummonSound(this.blockPosition());
        Minecraft.getInstance().getSoundManager().play(this.summonSound);

        // Show boss bar to all players around the area
        if (!this.level.isClientSide()) {
            this.bossEvent.setPercent((float) this.giftsRemaining / (float) (NUM_GIFTS_TO_SUMMON));
            this.areaOfEffect = new AxisAlignedBB(this.summoningPos).inflate(NAUGHTY_NICE_CONSIDERATION_RADIUS);

            List<PlayerEntity> playerList = this.level.getEntitiesOfClass(PlayerEntity.class, this.areaOfEffect);
            for (PlayerEntity playerEntity : playerList) this.bossEvent.addPlayer((ServerPlayerEntity) playerEntity);
        }
    }

    public void stopDropParty() {
        this.isSummoning = false;
        this.entityData.set(IS_SUMMONING, false);

        this.hasSummonedGifts = true;

        this.summoningPos = null;

        if (this.summonSound != null) this.summonSound.stopTrack();

        this.bossEvent.removeAllPlayers();

        this.despawnDelay = DEFAULT_DESPAWN_DELAY;

        this.spawnAtLocation(ItemRegistry.ENCHANTED_SANTA_HAT.get().getDefaultInstance());
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
        LootContext ctx = this.createLootContext(true, DamageSource.GENERIC).create(LootParameterSets.ENTITY);

        BasicParticleType particleType = null;

        double giftChance = this.random.nextDouble();
        if (giftChance < LEGENDARY_GIFT_SPAWN_CHANCE_THRESHOLD) {
            giftItem = SantaGifts.generateGift(SantaGiftType.LEGENDARY, this, (ServerWorld) this.level, ctx);
            particleType = ParticleRegistry.CHRISTMAS_MEDIUM_GOLD_PARTICLE.get();
        } else if (giftChance < RARE_GIFT_SPAWN_CHANCE_THRESHOLD) {
            giftItem = SantaGifts.generateGift(SantaGiftType.RARE, this, (ServerWorld) this.level, ctx);
            particleType = ParticleRegistry.CHRISTMAS_MEDIUM_GREEN_PARTICLE.get();
        } else {
            giftItem = SantaGifts.generateGift(SantaGiftType.BASIC, this, (ServerWorld) this.level, ctx);
            particleType = ParticleRegistry.CHRISTMAS_MEDIUM_BLUE_PARTICLE.get();
        }

        ItemEntity giftEntity = new ItemEntity(this.level, randomPos.getX(), randomPos.getY(), randomPos.getZ(), giftItem);
        giftEntity.lifespan = GIFT_LIFESPAN;

        for (int i = 0; i < 3; i++) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            ((ServerWorld) this.level).sendParticles(particleType,
                    randomPos.getX() + this.random.nextDouble(),
                    randomPos.getY() + this.random.nextDouble(),
                    randomPos.getZ() + this.random.nextDouble(),
                    1, d0, d1, d2,
                    0.0D
            );
        }

        this.level.addFreshEntity(giftEntity);
        this.level.playSound(null, randomPos.getX(), randomPos.getY(),
                randomPos.getZ(), SoundRegistry.SANTA_ITEM_APPEAR.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);

        this.giftsRemaining--;

        this.bossEvent.setPercent((float) this.giftsRemaining / (float) (NUM_GIFTS_TO_SUMMON));
    }

    public void createSummoningParticle() {
        double d0 = (double)(this.random.nextFloat() * 0.1F) + 0.25D;
        double d1 = (double)(this.random.nextFloat() * 0.1F) + 0.25D;
        double d2 = (double)(this.random.nextFloat() * 0.1F) + 0.25D;

        double d = this.random.nextDouble();
        BasicParticleType particleType = d < 0.25 ? ParticleRegistry.CHRISTMAS_SMALL_RED_PARTICLE.get()
                : d < 0.5 ? ParticleRegistry.CHRISTMAS_MEDIUM_RED_PARTICLE.get()
                : d < 0.75 ? ParticleRegistry.CHRISTMAS_SMALL_GREEN_PARTICLE.get()
                : ParticleRegistry.CHRISTMAS_MEDIUM_GREEN_PARTICLE.get();
        
        ((ServerWorld) this.level).sendParticles(particleType,
                this.getX(),
                this.getY() + d1 + 1.5D,
                this.getZ(),
                2, d0, d1, d2, 0.0D);
    }

    public BlockPos getSummoningPos() {
        return summoningPos;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putBoolean("HasSummonedGifts", this.hasSummonedGifts);
        nbt.putBoolean("HasStartedSummoningGifts", this.hasStartedSummoningGifts);
        nbt.putInt("GiftsRemaining", this.giftsRemaining);
        nbt.putInt("DespawnDelay", this.despawnDelay);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
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

        if (this.summonSound != null) this.summonSound.stopTrack();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level.isClientSide()) {
            this.isSummoning = this.entityData.get(IS_SUMMONING);
        } else {
            if (this.isSummoning) {
                createSummoningParticle();

                if (this.isAlive() && this.level.getGameTime() % 60L == 0) {
                    List<PlayerEntity> playerList = this.level.getEntitiesOfClass(PlayerEntity.class, this.areaOfEffect);

                    // Remove players outside the AOE
                    for (ServerPlayerEntity serverPlayerEntity : this.bossEvent.getPlayers()) {
                        if (!playerList.contains(serverPlayerEntity)) this.bossEvent.removePlayer(serverPlayerEntity);
                    }

                    // Add players inside the AOE
                    for (PlayerEntity playerEntity : playerList) {
                        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerEntity;
                        if (!this.bossEvent.getPlayers().contains(serverPlayerEntity)) this.bossEvent.addPlayer((ServerPlayerEntity) playerEntity);
                    }
                }
            } else {
                this.maybeDespawn();
            }
        }
    }

    private void maybeDespawn() {
        if (this.despawnDelay > 0 && --this.despawnDelay == 0) {
            this.remove();
        }
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.SANTA_PASSIVE.get();
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
