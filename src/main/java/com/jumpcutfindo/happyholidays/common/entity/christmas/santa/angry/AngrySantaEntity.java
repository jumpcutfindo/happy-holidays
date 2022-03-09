package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.common.block.christmas.ExplosivePresentBlock;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.SantaGiftType;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.SantaGifts;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class AngrySantaEntity extends BaseSantaEntity implements Enemy {
    public static final EntityDataAccessor<Integer> SANTA_PHASE = SynchedEntityData.defineId(AngrySantaEntity.class,
            EntityDataSerializers.INT);

    public static final EntityDataAccessor<Integer> ATTACK_HIT_ANIM_TIMER = SynchedEntityData.defineId(AngrySantaEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ATTACK_SLEIGHS_ANIM_TIMER = SynchedEntityData.defineId(AngrySantaEntity.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<Integer> ATTACK_TELEPORT_ANIM_TIMER = SynchedEntityData.defineId(AngrySantaEntity.class, EntityDataSerializers.INT);

    public static final String ENTITY_ID = "angry_santa";

    // Angry Santa attack variables
    public static final int ATTACK_PHASE_SWITCH_TIMER_MAX = 100;
    public static final int ATTACK_PHASE_SWITCH_TIMER_MIN = 40;

    public static final int ATTACK_SLEIGH_CHARGE_TIME = 10;
    public static final int ATTACK_SLEIGH_INTERVAL = 40;
    public static final int ATTACK_SLEIGH_LIFETIME = 80;

    public static final int ATTACK_PRESENTS_CONSIDERATION_RADIUS = 30;
    public static final int ATTACK_PRESENTS_INTERVAL = 30;

    public static final int ATTACK_TELEPORT_CHARGE_TIME = 40;
    public static final int ATTACK_TELEPORT_INTERVAL = 60;
    public static final int ATTACK_TELEPORT_CONSIDERATION_RADIUS = 30;
    public static final float ATTACK_TELEPORT_DAMAGE = 8.0f;
    public static final int ATTACK_TELEPORT_DAMAGE_RADIUS = 8;

    // Rewards for defeating Angry Santa
    public static final int NUM_LEGENDARY_PRESENTS_MIN = 6;
    public static final int NUM_LEGENDARY_PRESENTS_MAX = 10;
    public static final int NUM_RARE_PRESENTS_MIN = 10;
    public static final int NUM_RARE_PRESENTS_MAX = 16;
    public static final int NUM_BASIC_PRESENTS_MIN = 12;
    public static final int NUM_BASIC_PRESENTS_MAX = 20;
    public static final int AMT_XP_DROP = 4800;
    public static final int NUM_XP_ORBS_MIN = 32;
    public static final int NUM_XP_ORBS_MAX = 48;


    private static final Vec3[] HORIZONTALS = {
            new Vec3(1.0, 0.0, 0.0),
            new Vec3(-1.0, 0.0, 0.0),
            new Vec3(0.0, 0.0, 1.0),
            new Vec3(0.0, 0.0, -1.0)
    };

    private static final Vec3[] DIAGONALS = {
            new Vec3(1.0, 0.0, 1.0),
            new Vec3(1.0, 0.0, -1.0),
            new Vec3(-1.0, 0.0, -1.0),
            new Vec3(-1.0, 0.0, 1.0)
    };

    private Phase currentPhase = Phase.SLEIGHS;

    private boolean isTeleportCharging;
    private Vec3 teleportTarget;

    private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(),
            BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS));

    public int hitAnimTimer, sleighAnimTimer, teleportAnimTimer;

    private boolean isDamagedByPlayer;

    public AngrySantaEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new PhaseSwitchGoal(this));
        this.goalSelector.addGoal(0, new SwatPlayerGoal(this));
        this.goalSelector.addGoal(1, new SleighAttackGoal(this));
        this.goalSelector.addGoal(1, new ExplosivePresentsAttackGoal(this));
        this.goalSelector.addGoal(1, new TeleportGoal(this));

        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.0f, 4));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(SANTA_PHASE, Phase.SLEIGHS.ordinal());
        this.entityData.define(ATTACK_SLEIGHS_ANIM_TIMER, 0);
        this.entityData.define(ATTACK_TELEPORT_ANIM_TIMER, 0);
        this.entityData.define(ATTACK_HIT_ANIM_TIMER, 0);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();

        this.currentPhase = Phase.SLEIGHS;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource dmgSource) {
        return dmgSource.isExplosion();
    }

    private void createBossBar() {
        this.bossEvent.setProgress((float) this.getHealth() / (float) (MAX_HEALTH));
        AABB areaOfEffect = new AABB(this.blockPosition()).inflate(NAUGHTY_NICE_CONSIDERATION_RADIUS);

        List<Player> playerList = this.level.getEntitiesOfClass(Player.class, areaOfEffect);
        for (Player playerEntity : playerList) this.bossEvent.addPlayer((ServerPlayer) playerEntity);
    }

    public void setPhase(Phase phase) {
        this.currentPhase = phase;
        this.entityData.set(SANTA_PHASE, phase.ordinal());
    }

    public void changePhaseRandomly() {
        Phase newPhase = Phase.values()[this.random.nextInt(Phase.values().length)];
        this.currentPhase = newPhase;
        this.entityData.set(SANTA_PHASE, newPhase.ordinal());
    }

    public float getAttackIntervalMultiplier() {
        int currentHealth = (int) this.getHealth();

        return currentHealth >= 150 ? 2.5f
                : currentHealth >= 100 ? 3.0f
                : currentHealth >= 50 ? 3.5f
                : 4.0f;
    }

    public void fireHorizontalSleighs() {
        for (Vec3 horizontal : HORIZONTALS) {
            this.fireSleigh(horizontal);
        }
    }

    public void fireDiagonalSleighs() {
        for (Vec3 diagonal : DIAGONALS) {
            this.fireSleigh(diagonal);
        }
    }

    public void fireSleigh(Vec3 vector) {
        this.entityData.set(ATTACK_SLEIGHS_ANIM_TIMER, 20);
        this.sleighAnimTimer = 20;

        SleighEntity sleighEntity = ChristmasEntities.SLEIGH.get().create(this.level).setRotation(vector);
        sleighEntity.moveTo(this.position().add(sleighEntity.getForward().multiply(3.0d, 3.0d, 3.0d)));

        this.level.playSound(null, this.blockPosition(), ChristmasSounds.SANTA_SUMMON_SLEIGHS.get(), SoundSource.HOSTILE, 1.0f,
                1.0f);
        this.level.addFreshEntity(sleighEntity);

        for (int i = 0; i < 5; i ++) {
            ((ServerLevel) this.level).sendParticles(ParticleTypes.SMOKE,
                    sleighEntity.position().x + random.nextDouble(),
                    sleighEntity.position().y + random.nextDouble(),
                    sleighEntity.position().z + random.nextDouble(),
                    1, 0D, 0D, 0D, 0D);
        }
    }

    public void spawnExplosives(List<Player> playerEntities) {
        for (Player playerEntity : playerEntities) {
            if (playerEntity.isAlive()) {
                Vec3 playerPos = playerEntity.position();
                Vec3 explosivePos = playerPos.add(new Vec3(0.0d, 2.0d, 0.0d));

                ExplosivePresentBlock.createExplosiveEntity(level, explosivePos, this);
            }
        }
    }

    public void startTeleportAttack(Vec3 target) {
        this.teleportTarget = target;

        this.entityData.set(ATTACK_TELEPORT_ANIM_TIMER, ATTACK_TELEPORT_CHARGE_TIME);
        this.teleportAnimTimer = ATTACK_TELEPORT_CHARGE_TIME;

        this.isTeleportCharging = true;

        this.level.playSound(null, this.blockPosition(), ChristmasSounds.SANTA_PREPARE_TELEPORT.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
        this.level.playSound(null, new BlockPos(target), ChristmasSounds.SANTA_PREPARE_TELEPORT.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
    }

    public void stopTeleportCharging() {
        this.isTeleportCharging = false;
    }

    public void teleportAttack(Vec3 position) {
        this.isTeleportCharging = false;

        // Teleport to location and stomp the ground
        this.level.playSound(null, this.blockPosition(), ChristmasSounds.SANTA_TELEPORT.get(), SoundSource.HOSTILE, 1.0f,
                1.0f);
        this.teleportTo(position.x, position.y + 2.0D, position.z);
        this.level.playSound(null, this.blockPosition(), ChristmasSounds.SANTA_TELEPORT.get(), SoundSource.HOSTILE, 1.0f,
                1.0f);

        List<Player> playerEntities = this.level.getEntitiesOfClass(Player.class,
                this.getBoundingBox().inflate(ATTACK_TELEPORT_DAMAGE_RADIUS / this.getAttackIntervalMultiplier()));
        this.summonStompParticles(position);

        for (Player playerEntity : playerEntities) {
            playerEntity.hurt(DamageSource.mobAttack(this), ATTACK_TELEPORT_DAMAGE * this.getAttackIntervalMultiplier());
        }

    }

    public Phase getPhase() {
        return this.currentPhase;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level.isClientSide()) {
            this.currentPhase = Phase.values()[this.entityData.get(SANTA_PHASE)];

            if (currentPhase == Phase.SLEIGHS) this.sleighAnimTimer = this.entityData.get(ATTACK_SLEIGHS_ANIM_TIMER);
            if (currentPhase == Phase.TELEPORT) this.teleportAnimTimer = this.entityData.get(ATTACK_TELEPORT_ANIM_TIMER);

            this.hitAnimTimer = this.entityData.get(ATTACK_HIT_ANIM_TIMER);
        } else {
            if (sleighAnimTimer > 0) {
                this.sleighAnimTimer--;
                this.entityData.set(ATTACK_SLEIGHS_ANIM_TIMER, this.sleighAnimTimer);
            }

            if (teleportAnimTimer > 0) {
                this.teleportAnimTimer--;
                this.entityData.set(ATTACK_TELEPORT_ANIM_TIMER, this.teleportAnimTimer);
            }

            if (hitAnimTimer > 0) {
                this.hitAnimTimer--;
                this.entityData.set(ATTACK_HIT_ANIM_TIMER, this.hitAnimTimer);
            }

            if (this.isTeleportCharging) {
                this.summonTeleportationParticles(this.position());
                this.summonTeleportationParticles(this.teleportTarget);
            }

            if (currentPhase == Phase.PRESENTS) {
                this.summonSantaPresentsParticles();
            }


            if (this.isAlive() && this.level.getGameTime() % 60L == 0) {
                if (this.bossEvent == null) this.createBossBar();

                AABB areaOfEffect = new AABB(this.blockPosition()).inflate(NAUGHTY_NICE_CONSIDERATION_RADIUS);
                List<Player> playerList = this.level.getEntitiesOfClass(Player.class, areaOfEffect);

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
        }
    }

    private void summonSantaPresentsParticles() {
        Vec3 pos = this.position();

        double d0 = (Math.random() * 0.1D) + 0.25D;
        double d1 = (Math.random() * 0.1D) + 0.25D;
        double d2 = (Math.random() * 0.1D) + 0.25D;

        SimpleParticleType particleType = ParticleTypes.SMOKE;

        ((ServerLevel) this.level).sendParticles(particleType,
                pos.x + Math.random() * 2.0D * (this.random.nextBoolean() ? 1 : -1),
                pos.y + d1,
                pos.z + Math.random() * 2.0D * (this.random.nextBoolean() ? 1 : -1),
                2, d0, d1, d2, 0.0D);
    }

    private void summonTeleportationParticles(Vec3 pos) {
        double d0 = (Math.random() * 0.1D) + 0.25D;
        double d1 = (Math.random() * 0.1D) + 0.25D;
        double d2 = (Math.random() * 0.1D) + 0.25D;

        double d = Math.random();
        SimpleParticleType particleType = d < 0.5 ? ChristmasParticles.CHRISTMAS_SANTA_GREEN_SPAWN_PARTICLE.get() :
                ChristmasParticles.CHRISTMAS_SANTA_RED_SPAWN_PARTICLE.get();

        ((ServerLevel) this.level).sendParticles(particleType,
                pos.x,
                pos.y + d1,
                pos.z,
                2, d0, d1, d2, 0.0D);
    }

    private void summonStompParticles(Vec3 pos) {
        SimpleParticleType particleType = ParticleTypes.CLOUD;

        for (int i = 0; i < 25; i++) {
            double d0 = (Math.random() * ATTACK_TELEPORT_DAMAGE_RADIUS / this.getAttackIntervalMultiplier()) * (this.random.nextBoolean() ? 1 : -1);
            double d1 = Math.random() * 1.5D;
            double d2 = (Math.random() * ATTACK_TELEPORT_DAMAGE_RADIUS / this.getAttackIntervalMultiplier()) * (this.random.nextBoolean() ? 1 : -1);

            double dv0 = (Math.random() * 0.1D) + 0.25D;
            double dv1 = (Math.random() * 0.1D) + 0.25D;
            double dv2 = (Math.random() * 0.1D) + 0.25D;

            ((ServerLevel) this.level).sendParticles(particleType,
                    pos.x + d0,
                    pos.y + d1,
                    pos.z + d2,
                    2, dv0, dv1, dv2, 0.0D);
        }
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource, int p_213333_2_, boolean p_213333_3_) {
        super.dropCustomDeathLoot(damageSource, p_213333_2_, p_213333_3_);
        if (damageSource == DamageSource.OUT_OF_WORLD) return;

        // Spawn gifts at this location
        for (ItemStack gift : this.generateDrops()) this.spawnAtLocation(gift);

        // Spawn XP orbs
        int numOrbs = this.random.nextInt(NUM_XP_ORBS_MAX - NUM_XP_ORBS_MIN) + NUM_XP_ORBS_MIN;

        for (int i = 0; i < numOrbs; i++) {
            int xpDrop = ExperienceOrb.getExperienceValue((int) ((1 / (double) numOrbs) * AMT_XP_DROP));
            this.level.addFreshEntity(new ExperienceOrb(this.level, this.getX(), this.getY(), this.getZ(), xpDrop));
        }
        
        // Update defeated before status
        if (!this.level.isClientSide()) this.onDefeat((ServerLevel) this.level);
    }

    private List<ItemStack> generateDrops() {
        // Create present drops
        int numLegendaryPresents =
                this.random.nextInt(NUM_LEGENDARY_PRESENTS_MAX - NUM_LEGENDARY_PRESENTS_MIN) + NUM_LEGENDARY_PRESENTS_MIN;
        int numRarePresents =
                this.random.nextInt(NUM_RARE_PRESENTS_MAX - NUM_RARE_PRESENTS_MIN) + NUM_RARE_PRESENTS_MIN;
        int numBasicPresents =
                this.random.nextInt(NUM_BASIC_PRESENTS_MAX - NUM_BASIC_PRESENTS_MIN) + NUM_BASIC_PRESENTS_MIN;

        LootContext ctx = this.createLootContext(true, DamageSource.GENERIC).create(LootContextParamSets.ENTITY);

        List<ItemStack> gifts = Lists.newArrayList();

        for (int i = 0; i < numLegendaryPresents; i++) {
            ItemStack legendaryGift = SantaGifts.generateGift(SantaGiftType.LEGENDARY, this, (ServerLevel) this.level, ctx);
            gifts.add(legendaryGift);
        }

        for (int i = 0; i < numRarePresents; i++) {
            ItemStack rareGift = SantaGifts.generateGift(SantaGiftType.RARE, this, (ServerLevel) this.level, ctx);
            gifts.add(rareGift);
        }

        for (int i = 0; i < numBasicPresents; i++) {
            ItemStack basicGift = SantaGifts.generateGift(SantaGiftType.BASIC, this, (ServerLevel) this.level, ctx);
            gifts.add(basicGift);
        }

        gifts.add(ChristmasItems.ENCHANTED_SANTA_HAT.get().getDefaultInstance());

        return gifts;
    }

    @Override
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        boolean flag = super.hurt(p_70097_1_, p_70097_2_);

        if (p_70097_1_.getEntity() instanceof Player) {
            this.isDamagedByPlayer = true;
        }

        this.bossEvent.setProgress(this.getHealth() / MAX_HEALTH);

        return flag;
    }

    @Override
    public void die(DamageSource p_70645_1_) {
        super.die(p_70645_1_);

        if (!this.isDamagedByPlayer) {
            AABB searchBox =
                    new AABB(this.blockPosition()).inflate(NAUGHTY_NICE_CONSIDERATION_RADIUS);
            List<Player> playerEntities = this.level.getEntitiesOfClass(Player.class, searchBox);

            for (Player playerEntity : playerEntities) {
                SantaEvent event = new SantaEvent.AngryDie(this, playerEntity);
                MinecraftForge.EVENT_BUS.post(event);
            }
        }

        this.bossEvent.removeAllPlayers();
    }

    public boolean isDamagedByPlayer() {
        return isDamagedByPlayer;
    }

    @Override
    public boolean removeWhenFarAway(double p_213397_1_) {
        return false;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ChristmasSounds.SANTA_ANGRY_PASSIVE.get();
    }

    @Override
    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.hitAnimTimer > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.attack", false));
        } else if (currentPhase == Phase.SLEIGHS && this.sleighAnimTimer > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.summon_sleighs", true));
        } else if (currentPhase == Phase.TELEPORT && this.teleportAnimTimer > 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.teleporting", false));
        } else if (currentPhase == Phase.PRESENTS) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.summon", true));
        } else if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.idle", true));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putBoolean("IsDamagedByPlayer", this.isDamagedByPlayer);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        this.isDamagedByPlayer = nbt.getBoolean("IsDamagedByPlayer");
    }
}
