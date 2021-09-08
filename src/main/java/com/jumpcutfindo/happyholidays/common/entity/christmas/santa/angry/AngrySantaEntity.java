package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.SantaGiftType;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.SantaGifts;
import com.jumpcutfindo.happyholidays.common.events.christmas.SantaEvent;
import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ParticleRegistry;
import com.jumpcutfindo.happyholidays.common.registry.SoundRegistry;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
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
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class AngrySantaEntity extends BaseSantaEntity {
    public static final DataParameter<Integer> SANTA_PHASE = EntityDataManager.defineId(AngrySantaEntity.class,
            DataSerializers.INT);

    public static final DataParameter<Integer> ATTACK_HIT_ANIM_TIMER = EntityDataManager.defineId(AngrySantaEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> ATTACK_SLEIGHS_ANIM_TIMER = EntityDataManager.defineId(AngrySantaEntity.class, DataSerializers.INT);
    public static final DataParameter<Integer> ATTACK_TELEPORT_ANIM_TIMER = EntityDataManager.defineId(AngrySantaEntity.class, DataSerializers.INT);

    public static final String ENTITY_ID = "angry_santa";

    // Angry Santa attack variables
    public static final int ATTACK_PHASE_SWITCH_TIMER_MAX = 200;
    public static final int ATTACK_PHASE_SWITCH_TIMER_MIN = 80;

    public static final int ATTACK_SLEIGH_CHARGE_TIME = 10;
    public static final int ATTACK_SLEIGH_INTERVAL = 40;
    public static final int ATTACK_SLEIGH_LIFETIME = 80;

    public static final int ATTACK_PRESENTS_CONSIDERATION_RADIUS = 30;
    public static final int ATTACK_PRESENTS_INTERVAL = 30;

    public static final int ATTACK_TELEPORT_CHARGE_TIME = 40;
    public static final int ATTACK_TELEPORT_INTERVAL = 60;
    public static final int ATTACK_TELEPORT_CONSIDERATION_RADIUS = 30;
    public static final float ATTACK_TELEPORT_DAMAGE = 8.0f;
    public static final int ATTACK_TELEPORT_DAMAGE_RADIUS = 4;

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


    private static final Vector3d[] HORIZONTALS = {
            new Vector3d(1.0, 0.0, 0.0),
            new Vector3d(-1.0, 0.0, 0.0),
            new Vector3d(0.0, 0.0, 1.0),
            new Vector3d(0.0, 0.0, -1.0)
    };

    private static final Vector3d[] DIAGONALS = {
            new Vector3d(1.0, 0.0, 1.0),
            new Vector3d(1.0, 0.0, -1.0),
            new Vector3d(-1.0, 0.0, -1.0),
            new Vector3d(-1.0, 0.0, 1.0)
    };

    private Phase currentPhase = Phase.SLEIGHS;

    private boolean isTeleportCharging;
    private Vector3d teleportTarget;

    private final ServerBossInfo bossEvent = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(),
            BossInfo.Color.RED, BossInfo.Overlay.PROGRESS));

    private int hitAnimTimer, sleighAnimTimer, teleportAnimTimer;

    private boolean isDamagedByPlayer;

    public AngrySantaEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1.0f, 4));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

        this.goalSelector.addGoal(0, new PhaseSwitchGoal(this));
        this.goalSelector.addGoal(1, new SleighAttackGoal(this));
        this.goalSelector.addGoal(1, new ExplosivePresentsAttackGoal(this));
        this.goalSelector.addGoal(1, new TeleportGoal(this));
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
        this.bossEvent.setPercent((float) this.getHealth() / (float) (MAX_HEALTH));
        AxisAlignedBB areaOfEffect = new AxisAlignedBB(this.blockPosition()).inflate(NAUGHTY_NICE_CONSIDERATION_RADIUS);

        List<PlayerEntity> playerList = this.level.getEntitiesOfClass(PlayerEntity.class, areaOfEffect);
        for (PlayerEntity playerEntity : playerList) this.bossEvent.addPlayer((ServerPlayerEntity) playerEntity);
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

        return currentHealth >= 150 ? 1.0f
                : currentHealth >= 100 ? 1.5f
                : currentHealth >= 50 ? 2.0f
                : 2.5f;
    }

    public void fireHorizontalSleighs() {
        for (Vector3d horizontal : HORIZONTALS) {
            this.fireSleigh(horizontal);
        }
    }

    public void fireDiagonalSleighs() {
        for (Vector3d diagonal : DIAGONALS) {
            this.fireSleigh(diagonal);
        }
    }

    public void fireSleigh(Vector3d vector) {
        this.entityData.set(ATTACK_SLEIGHS_ANIM_TIMER, 20);
        this.sleighAnimTimer = 20;

        SleighEntity sleighEntity = EntityRegistry.SLEIGH.get().create(this.level).setRotation(vector);
        sleighEntity.moveTo(this.position().add(sleighEntity.getForward().multiply(3.0d, 3.0d, 3.0d)));

        this.level.playSound(null, this.blockPosition(), SoundRegistry.SANTA_SUMMON_SLEIGHS.get(), SoundCategory.HOSTILE, 1.0f,
                1.0f);
        this.level.addFreshEntity(sleighEntity);

        for (int i = 0; i < 5; i ++) {
            ((ServerWorld) this.level).sendParticles(ParticleTypes.SMOKE,
                    sleighEntity.position().x + random.nextDouble(),
                    sleighEntity.position().y + random.nextDouble(),
                    sleighEntity.position().z + random.nextDouble(),
                    1, 0D, 0D, 0D, 0D);
        }
    }

    public void spawnExplosives(List<PlayerEntity> playerEntities) {
        for (PlayerEntity playerEntity : playerEntities) {
            if (playerEntity.isAlive()) {
                Vector3d playerPos = playerEntity.position();
                ExplosivePresentEntity explosive = EntityRegistry.EXPLOSIVE_PRESENT.get().create(this.level);
                Vector3d explosivePos = playerPos.add(new Vector3d(0.0d, 2.0d, 0.0d));

                explosive.moveTo(explosivePos);
                for (int i = 0; i < 5; i++) this.summonPresentsParticles(explosivePos);

                this.level.addFreshEntity(explosive);

            }
        }
    }

    public void startTeleportAttack(Vector3d target) {
        this.teleportTarget = target;

        this.entityData.set(ATTACK_TELEPORT_ANIM_TIMER, ATTACK_TELEPORT_CHARGE_TIME);
        this.teleportAnimTimer = ATTACK_TELEPORT_CHARGE_TIME;

        this.isTeleportCharging = true;

        this.level.playSound(null, this.blockPosition(), SoundRegistry.SANTA_PREPARE_TELEPORT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
        this.level.playSound(null, new BlockPos(target), SoundRegistry.SANTA_PREPARE_TELEPORT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
    }

    public void stopTeleportCharging() {
        this.isTeleportCharging = false;
    }

    public void teleportAttack(Vector3d position) {
        this.isTeleportCharging = false;

        // Teleport to location and stomp the ground
        this.level.playSound(null, this.blockPosition(), SoundRegistry.SANTA_TELEPORT.get(), SoundCategory.HOSTILE, 1.0f,
                1.0f);
        this.teleportTo(position.x, position.y + 2.0D, position.z);
        this.level.playSound(null, this.blockPosition(), SoundRegistry.SANTA_TELEPORT.get(), SoundCategory.HOSTILE, 1.0f,
                1.0f);

        List<PlayerEntity> playerEntities = this.level.getEntitiesOfClass(PlayerEntity.class,
                this.getBoundingBox().inflate(ATTACK_TELEPORT_DAMAGE_RADIUS / this.getAttackIntervalMultiplier()));
        this.summonStompParticles(position);

        for (PlayerEntity playerEntity : playerEntities) {
            playerEntity.hurt(DamageSource.mobAttack(this), ATTACK_TELEPORT_DAMAGE * this.getAttackIntervalMultiplier());
        }

    }

    @Override
    public void playerTouch(PlayerEntity playerEntity) {
        if (this.getBoundingBox().inflate(1.0D).intersects(playerEntity.getBoundingBox())) {
            playerEntity.hurt(DamageSource.GENERIC, 12.0f);
            playerEntity.setDeltaMovement(
                    this.random.nextDouble() * 2.0d,
                    this.random.nextDouble(),
                    this.random.nextDouble() * 2.0d
            );

            this.level.playSound(null, this.blockPosition(), SoundRegistry.SANTA_FLICK.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);

            this.hitAnimTimer = 10;
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

                AxisAlignedBB areaOfEffect = new AxisAlignedBB(this.blockPosition()).inflate(NAUGHTY_NICE_CONSIDERATION_RADIUS);
                List<PlayerEntity> playerList = this.level.getEntitiesOfClass(PlayerEntity.class, areaOfEffect);

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
        }
    }

    private void summonPresentsParticles(Vector3d pos) {
        double d0 = (Math.random() * 0.1D) + 0.25D;
        double d1 = (Math.random() * 0.1D) + 0.25D;
        double d2 = (Math.random() * 0.1D) + 0.25D;

        BasicParticleType particleType = ParticleTypes.SMOKE;

        ((ServerWorld) this.level).sendParticles(particleType,
                pos.x,
                pos.y + d1,
                pos.z,
                2, d0, d1, d2, 0.0D);
    }

    private void summonSantaPresentsParticles() {
        Vector3d pos = this.position();

        double d0 = (Math.random() * 0.1D) + 0.25D;
        double d1 = (Math.random() * 0.1D) + 0.25D;
        double d2 = (Math.random() * 0.1D) + 0.25D;

        BasicParticleType particleType = ParticleTypes.SMOKE;

        ((ServerWorld) this.level).sendParticles(particleType,
                pos.x + Math.random() * 2.0D * (this.random.nextBoolean() ? 1 : -1),
                pos.y + d1,
                pos.z + Math.random() * 2.0D * (this.random.nextBoolean() ? 1 : -1),
                2, d0, d1, d2, 0.0D);
    }

    private void summonTeleportationParticles(Vector3d pos) {
        double d0 = (Math.random() * 0.1D) + 0.25D;
        double d1 = (Math.random() * 0.1D) + 0.25D;
        double d2 = (Math.random() * 0.1D) + 0.25D;

        double d = Math.random();
        BasicParticleType particleType = d < 0.5 ? ParticleRegistry.CHRISTMAS_SANTA_GREEN_SPAWN_PARTICLE.get() :
                ParticleRegistry.CHRISTMAS_SANTA_RED_SPAWN_PARTICLE.get();

        ((ServerWorld) this.level).sendParticles(particleType,
                pos.x,
                pos.y + d1,
                pos.z,
                2, d0, d1, d2, 0.0D);
    }

    private void summonStompParticles(Vector3d pos) {
        BasicParticleType particleType = ParticleTypes.CLOUD;

        for (int i = 0; i < 25; i++) {
            double d0 = (Math.random() * ATTACK_TELEPORT_DAMAGE_RADIUS) * (this.random.nextBoolean() ? 1 : -1);
            double d1 = Math.random() * 1.5D;
            double d2 = (Math.random() * ATTACK_TELEPORT_DAMAGE_RADIUS) * (this.random.nextBoolean() ? 1 : -1);

            double dv0 = (Math.random() * 0.1D) + 0.25D;
            double dv1 = (Math.random() * 0.1D) + 0.25D;
            double dv2 = (Math.random() * 0.1D) + 0.25D;

            ((ServerWorld) this.level).sendParticles(particleType,
                    pos.x + d0,
                    pos.y + d1,
                    pos.z + d2,
                    2, dv0, dv1, dv2, 0.0D);
        }
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
        super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);

        // Spawn gifts at this location
        for (ItemStack gift : this.generateDrops()) this.spawnAtLocation(gift);

        // Spawn XP orbs
        int numOrbs = this.random.nextInt(NUM_XP_ORBS_MAX - NUM_XP_ORBS_MIN) + NUM_XP_ORBS_MIN;

        for (int i = 0; i < numOrbs; i++) {
            int xpDrop = ExperienceOrbEntity.getExperienceValue((int) ((1 / (double) numOrbs) * AMT_XP_DROP));
            this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY(), this.getZ(), xpDrop));
        }
    }

    private List<ItemStack> generateDrops() {
        // Create present drops
        int numLegendaryPresents =
                this.random.nextInt(NUM_LEGENDARY_PRESENTS_MAX - NUM_LEGENDARY_PRESENTS_MIN) + NUM_LEGENDARY_PRESENTS_MIN;
        int numRarePresents =
                this.random.nextInt(NUM_RARE_PRESENTS_MAX - NUM_RARE_PRESENTS_MIN) + NUM_RARE_PRESENTS_MIN;
        int numBasicPresents =
                this.random.nextInt(NUM_BASIC_PRESENTS_MAX - NUM_BASIC_PRESENTS_MIN) + NUM_BASIC_PRESENTS_MIN;

        LootContext ctx = this.createLootContext(true, DamageSource.GENERIC).create(LootParameterSets.ENTITY);

        List<ItemStack> gifts = Lists.newArrayList();

        for (int i = 0; i < numLegendaryPresents; i++) {
            ItemStack legendaryGift = SantaGifts.generateGift(SantaGiftType.LEGENDARY, this, (ServerWorld) this.level, ctx);
            gifts.add(legendaryGift);
        }

        for (int i = 0; i < numRarePresents; i++) {
            ItemStack rareGift = SantaGifts.generateGift(SantaGiftType.RARE, this, (ServerWorld) this.level, ctx);
            gifts.add(rareGift);
        }

        for (int i = 0; i < numBasicPresents; i++) {
            ItemStack basicGift = SantaGifts.generateGift(SantaGiftType.BASIC, this, (ServerWorld) this.level, ctx);
            gifts.add(basicGift);
        }

        gifts.add(ItemRegistry.ENCHANTED_SANTA_HAT.get().getDefaultInstance());

        return gifts;
    }

    @Override
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        boolean flag = super.hurt(p_70097_1_, p_70097_2_);

        if (p_70097_1_.getEntity() instanceof PlayerEntity) {
            this.isDamagedByPlayer = true;
        }

        this.bossEvent.setPercent(this.getHealth() / MAX_HEALTH);

        return flag;
    }

    @Override
    public void die(DamageSource p_70645_1_) {
        super.die(p_70645_1_);

        if (!this.isDamagedByPlayer) {
            AxisAlignedBB searchBox =
                    new AxisAlignedBB(this.blockPosition()).inflate(NAUGHTY_NICE_CONSIDERATION_RADIUS);
            List<PlayerEntity> playerEntities = this.level.getEntitiesOfClass(PlayerEntity.class, searchBox);

            for (PlayerEntity playerEntity : playerEntities) {
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
        return SoundRegistry.SANTA_ANGRY_PASSIVE.get();
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
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putBoolean("IsDamagedByPlayer", this.isDamagedByPlayer);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);

        this.isDamagedByPlayer = nbt.getBoolean("IsDamagedByPlayer");
    }
}