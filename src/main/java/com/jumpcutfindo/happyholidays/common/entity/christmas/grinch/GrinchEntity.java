package com.jumpcutfindo.happyholidays.common.entity.christmas.grinch;

import java.util.List;
import java.util.Random;

import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.common.block.christmas.PresentBlock;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarHelper;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceAction;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.GrinchEvent;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEffects;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.utils.EntityUtils;
import com.jumpcutfindo.happyholidays.server.data.HolidayAvailabilityData;
import com.jumpcutfindo.happyholidays.server.data.structs.Availability;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.item.ItemEntity;
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
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GrinchEntity extends PathfinderMob implements IAnimatable, ChristmasEntity {
    public static final EntityDataAccessor<Integer> BREAK_ANIM_PROGRESS = SynchedEntityData.defineId(GrinchEntity.class,
            EntityDataSerializers.INT);

    public static final AttributeSupplier ENTITY_ATTRIBUTES =
            Mob.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 20.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.21D)
                    .build();

    public static final float ENTITY_BOX_SIZE = 0.5f;
    public static final float ENTITY_BOX_HEIGHT = 34.0f / 16.0f;

    public static final double GRINCH_SPAWN_CHANCE = 0.01d;
    public static final int MAX_GRINCHES_IN_VICINITY = 3;

    private static final int BREAK_PRESENT_ANIM_DURATION = 40;
    private static final int BREAK_PRESENT_INTERVAL = 50;
    private static final float AVOID_PLAYER_RADIUS = 6.0f;
    private static final int GRINCH_TIME_TO_DESPAWN = 100;

    public static final int PRESENT_SEARCH_RADIUS = 8;
    public static final int PRESENT_SEARCH_INTERVAL = 100;

    private AnimationFactory factory = new AnimationFactory(this);

    private int[] presentsBrokenCount = new int[] { 0, 0, 0 };

    private int presentBreakingProgress = -1;

    private boolean hasReceivedGift = false;
    private boolean isHappyWithGift = false;

    private int despawnTimer = GRINCH_TIME_TO_DESPAWN;
    private boolean isReadyToDespawn = false;

    public GrinchEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(BREAK_ANIM_PROGRESS, -1);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new PickupGiftGoal(this));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(
                this, Player.class, GrinchEntity.AVOID_PLAYER_RADIUS, 1.0D, 1.5D,
                livingEntity -> {
                    if (GrinchEntity.this.hasReceivedGift) return false;

                    if (livingEntity instanceof Player) {
                        Player playerEntity = (Player) livingEntity;
                        if (playerEntity.getMainHandItem().getItem() instanceof ChristmasGiftItem
                                || playerEntity.getOffhandItem().getItem() instanceof  ChristmasGiftItem) {
                            return false;
                        }
                    }
                    return true;
                }
        ));
        this.goalSelector.addGoal(1, new BreakPresentsGoal(this));
        this.goalSelector.addGoal(2, new FloatGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChristmasSounds.GRINCH_PASSIVE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ChristmasSounds.GRINCH_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChristmasSounds.GRINCH_HURT.get();
    }

    public boolean isPlayerAround() {
        List<Player> playerEntityList = this.level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(AVOID_PLAYER_RADIUS,
                AVOID_PLAYER_RADIUS, AVOID_PLAYER_RADIUS), player -> true);

        return playerEntityList.size() > 0;
    }

    public void beginPresentBreaking() {
        this.entityData.set(BREAK_ANIM_PROGRESS, BREAK_PRESENT_ANIM_DURATION);
    }

    public void updatePresentBreaking(int progress) {
        this.entityData.set(BREAK_ANIM_PROGRESS, progress);
    }

    public void endPresentBreaking(BlockPos targetPresentBlock) {
        this.entityData.set(BREAK_ANIM_PROGRESS, -1);

        if (this.level.getBlockState(targetPresentBlock).is(ChristmasBlocks.BABY_PRESENT.get())) {
            // Baby present
            this.presentsBrokenCount[0]++;
        } else if (this.level.getBlockState(targetPresentBlock).is(ChristmasBlocks.ADULT_PRESENT.get())) {
            // Adult present
            this.presentsBrokenCount[1]++;
        } else {
            // Elder present
            this.presentsBrokenCount[2]++;
        }

        this.level.destroyBlock(targetPresentBlock, false);
        this.level.playSound(null, targetPresentBlock, ChristmasSounds.GRINCH_BREAK_BOX.get(), SoundSource.NEUTRAL,
                1.0f, 1.0f);
    }

    public void handleGiftOnGround(ItemEntity itemEntity) {
        if (!this.hasReceivedGift && !itemEntity.hasPickUpDelay()) {
            this.pickUpItem(itemEntity);

            // Grinch has happily received gift
            this.hasReceivedGift = true;
            this.isHappyWithGift = true;

            // Drop loot & items (including all the scraps gathered)
            this.throwAppeasementRewards();

            // Add to naughty / nice meter
            if (itemEntity.getThrower() != null) {
                Player thrower = this.level.getPlayerByUUID(itemEntity.getThrower());
                NaughtyNiceMeter.evaluateAction(thrower, NaughtyNiceAction.APPEASE_GRINCH_EVENT);

                // Post event for achievements
                MinecraftForge.EVENT_BUS.post(new GrinchEvent.Appease(this, thrower));
            }

            for (int i = 0; i < 5; i++) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                ((ServerLevel) this.level).sendParticles(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D,
                        this.getRandomZ(1.0D), 1, d0, d1, d2, 0.0D);
            }

            this.navigation.moveTo(this.getX() + this.random.nextDouble() * 32.0D + 128.0D, this.getY(),
                    this.random.nextDouble() * 32.0D + 128.0D, 2.0f);
            this.isReadyToDespawn = true;
        }
    }

    public void throwAppeasementRewards() {
        // Create experience rewards
        this.level.addFreshEntity(GrinchRewards.generateAppeasementXP(this.level, this.position()));

        // Drop loot
        LootContext ctx = this.createLootContext(false, DamageSource.GENERIC).create(LootContextParamSets.ENTITY);
        GrinchRewards.generateRewards(this, ctx).forEach(this::spawnAtLocation);
    }

    public void despawnGrinch() {
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

            serverWorld.playSound(null, this.blockPosition(), ChristmasSounds.GRINCH_DESPAWN.get(),
                    SoundSource.NEUTRAL, 1.0f, 1.0f);

        }

        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level.isClientSide()) {
            this.presentBreakingProgress = this.entityData.get(BREAK_ANIM_PROGRESS);
        }

        if (this.isReadyToDespawn && this.tickCount % 3 == 0) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            SimpleParticleType particleType = isHappyWithGift ? ParticleTypes.HEART : ParticleTypes.ANGRY_VILLAGER;

            ((ServerLevel) this.level).sendParticles(particleType, this.getRandomX(1.0D),
                    this.getRandomY() + 0.5D,
                    this.getRandomZ(1.0D), 1, d0, d1, d2, 0.0D);
        }

        if (this.isReadyToDespawn && --despawnTimer <= 0) {
            this.despawnGrinch();
        }

        if (!this.level.isClientSide() && this.level.getGameTime() % 60L == 0) {
            List<Player> playersAround = EntityUtils.findPlayersInRadius(this.level, this.position(),
                    AVOID_PLAYER_RADIUS);

            for (Player playerEntity : playersAround) MinecraftForge.EVENT_BUS.post(new GrinchEvent.Encounter(this, playerEntity));
        }
    }

    public int[] getPresentsBrokenCount() {
        return presentsBrokenCount;
    }

    public static boolean canSpawnInArea(BlockPos blockPos, ServerLevel serverWorld) {
        if (!Availability.isAvailable(serverWorld, Holiday.CHRISTMAS, HolidayAvailabilityData.CHRISTMAS_GRINCH_SPAWN)) return false;

        AABB searchBox = new AABB(blockPos).inflate(40.0D);
        boolean isPlayerInVicinity = serverWorld.getEntitiesOfClass(Player.class, searchBox).size() > 0;
        boolean isGrinchesAround = serverWorld.getEntitiesOfClass(GrinchEntity.class, searchBox).size() > MAX_GRINCHES_IN_VICINITY;

        return isPlayerInVicinity && !isGrinchesAround && serverWorld.isNight();
    }

    public static void spawnGrinchAround(BlockPos blockPos, ServerLevel serverWorld, Random random) {
        int randomX = random.nextInt(GrinchEntity.PRESENT_SEARCH_RADIUS * 2) * (random.nextBoolean() ? -1 : 1);
        int randomY = 0;
        int randomZ = random.nextInt(GrinchEntity.PRESENT_SEARCH_RADIUS * 2) * (random.nextBoolean() ? -1 : 1);

        GrinchEntity grinchEntity = ChristmasEntities.GRINCH.get().create(serverWorld);
        BlockPos spawnPos = new BlockPos(blockPos.getX() + randomX, blockPos.getY() + randomY, blockPos.getZ() + randomZ);

        // Make sure that the block doesn't suffocate our grinch
        int count = 0;
        while (serverWorld.getBlockState(spawnPos).isSuffocating(serverWorld, spawnPos) && ++count <= 10) spawnPos = spawnPos.above();

        // Last check to ensure it doesn't suffocate him
        if (!serverWorld.getBlockState(spawnPos).isSuffocating(serverWorld, spawnPos)) {
            grinchEntity.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
            serverWorld.addFreshEntity(grinchEntity);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putIntArray("PresentsBroken", this.presentsBrokenCount);
        nbt.putBoolean("HasReceivedGift", this.hasReceivedGift);
        nbt.putBoolean("IsReadyToDespawn", this.isReadyToDespawn);
        nbt.putInt("DespawnTimer", this.despawnTimer);
        nbt.putBoolean("IsHappyWithGift", this.isHappyWithGift);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);

        int[] tempArray = nbt.getIntArray("PresentsBroken");
        if (tempArray.length == 3) this.presentsBrokenCount = tempArray;
        else this.presentsBrokenCount = new int[] { 0, 0, 0 };

        this.hasReceivedGift = nbt.getBoolean("HasReceivedGift");

        this.isReadyToDespawn = nbt.getBoolean("IsReadyToDespawn");

        int tempDespawnTimer = nbt.getInt("DespawnTimer");
        if (tempDespawnTimer == 0) this.despawnTimer = GrinchEntity.GRINCH_TIME_TO_DESPAWN;
        else this.despawnTimer = tempDespawnTimer;

        this.isHappyWithGift = nbt.getBoolean("IsHappyWithGift");
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource, int p_213333_2_, boolean p_213333_3_) {
        // Grinch drops number of scraps equivalent to number of presents broken on death
        if (damageSource == DamageSource.OUT_OF_WORLD) return;

        ItemStack scraps = ChristmasItems.PRESENT_SCRAPS.get().getDefaultInstance();
        scraps.setCount(this.presentsBrokenCount[0] + this.presentsBrokenCount[1] + this.presentsBrokenCount[2]);

        this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), scraps));
    }

    private <E extends GrinchEntity> PlayState predicate(AnimationEvent<E> event) {
        if (this.presentBreakingProgress >= 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.break", false));
        } else if (event.isMoving()) {
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
    protected float getStandingEyeHeight(Pose p_21131_, EntityDimensions p_21132_) {
        return 25.0f / 16.0f;
    }

    private static class BreakPresentsGoal extends Goal {
        private static final float MOVE_TO_PRESENT_SPEED = 0.9f;

        private final GrinchEntity grinchEntity;

        private BlockPos targetPresentBlockPos = null;
        private int presentBreakingProgress = 80;
        private int timeLeftToNextSearch = 0;

        public BreakPresentsGoal(GrinchEntity grinchEntity) {
            this.grinchEntity = grinchEntity;
        }

        @Override
        public boolean canUse() {
            if (grinchEntity.hasReceivedGift) return false;
            if (this.grinchEntity.getEffect(ChristmasEffects.DEBUFF_OF_CHRISTMAS.get()) != null) {
                this.targetPresentBlockPos = null;
                this.resetPresentBreaking();

                return false;
            }

            if (!grinchEntity.isPlayerAround()) return true;
            else {
                this.targetPresentBlockPos = null;
                this.resetPresentBreaking();
                return false;
            }
        }

        @Override
        public void tick() {
            if (targetPresentBlockPos == null) {
                if (timeLeftToNextSearch > 0) timeLeftToNextSearch--;
                if (timeLeftToNextSearch <= 0) findNearestPresent();
            } else {
                if (this.grinchEntity.level.isEmptyBlock(targetPresentBlockPos)) {
                    // After breaking the present, we reset the searching
                    this.targetPresentBlockPos = null;
                    grinchEntity.updatePresentBreaking(-1);
                    this.timeLeftToNextSearch = PRESENT_SEARCH_INTERVAL;
                } else {
                    BlockPos targetPos = getMoveToTarget();
                    if (targetPos == null) return;

                    int presentX = targetPos.getX(), presentY = targetPos.getY(), presentZ = targetPos.getZ();

                    if (!targetPos.closerThan(this.grinchEntity.position(), 1.5D)) {
                        // Since Grinch is not close enough, we move him to the location & make him look at it
                        this.grinchEntity.navigation.moveTo(presentX + 0.5D, presentY, presentZ + 0.5D,
                                MOVE_TO_PRESENT_SPEED);
                        this.grinchEntity.lookControl.setLookAt(presentX, presentY, presentZ);
                    } else {
                        this.breakPresent();
                    }
                }
            }
        }

        private void findNearestPresent() {
            BlockPos startPos = grinchEntity.getOnPos();
            boolean isFound = false;

            // Do a spiral search from mob's current position
            // x and z represent the offset from mob's current position
            for (int y = (-PRESENT_SEARCH_RADIUS / 2); y < (PRESENT_SEARCH_RADIUS / 2); y++) {
                if (isFound) break;

                int X = PRESENT_SEARCH_RADIUS * 2, Z = PRESENT_SEARCH_RADIUS * 2;
                int x = 0, z = 0, dx = 0, dz = -1;
                int t = Math.max(X, Z);
                int maxI = t * t;

                for (int i = 0; i < maxI; i ++){
                    if ((-X/2 <= x) && (x <= X/2) && (-Z/2 <= z) && (z <= Z/2)) {
                        BlockPos currPos = startPos.offset(x, y, z);
                        if (this.grinchEntity.level.getBlockState(currPos).getBlock() instanceof PresentBlock) {
                            // Check for entity instead of block, since we consider the Grinch's AOE instead of
                            // the block's AOE for the block breaking functionality
                            if (ChristmasStarHelper.getStarInfluencingEntity(this.grinchEntity.level, new Vec3(currPos.getX(), currPos.getY(), currPos.getZ())) != null) {
                                continue;
                            } else {
                                this.targetPresentBlockPos = currPos;
                                this.presentBreakingProgress = GrinchEntity.BREAK_PRESENT_ANIM_DURATION;

                                isFound = true;
                                break;
                            }
                        }
                    }

                    if((x == z) || ((x < 0) && (x == -z)) || ((x > 0) && (x == 1 - z))) {
                        t = dx; dx = -dz; dz = t;
                    }
                    x += dx; z += dz;
                }
            }
        }

        private BlockPos getMoveToTarget() {
            return this.targetPresentBlockPos == null ? null : this.targetPresentBlockPos.above();
        }

        private void breakPresent() {
            // Grinch is now close enough, so we carry out the breaking of the block & execution of the
            // animation
            if (presentBreakingProgress == GrinchEntity.BREAK_PRESENT_ANIM_DURATION) {
                // Present breaking process just started
                grinchEntity.beginPresentBreaking();
            } else if (presentBreakingProgress == 0) {
                // Present breaking process should end now
                grinchEntity.endPresentBreaking(targetPresentBlockPos);
                this.targetPresentBlockPos = null;

                this.timeLeftToNextSearch = GrinchEntity.BREAK_PRESENT_INTERVAL;
            } else {
                grinchEntity.updatePresentBreaking(this.presentBreakingProgress);
            }

            this.presentBreakingProgress--;
        }

        private void resetPresentBreaking() {
            this.presentBreakingProgress = 80;
            this.grinchEntity.updatePresentBreaking(-1);
        }
    }

    private static class PickupGiftGoal extends Goal {
        private final GrinchEntity grinchEntity;
        private ItemEntity targetedEntity;

        public PickupGiftGoal(GrinchEntity grinchEntity) {
            this.grinchEntity = grinchEntity;
        }

        @Override
        public boolean canUse() {
            if (this.grinchEntity.hasReceivedGift) return false;

            List<ItemEntity> nearbyEntities = this.grinchEntity.level.getEntitiesOfClass(ItemEntity.class,
                    this.grinchEntity.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));

            if (nearbyEntities.size() != 0) {
                for (ItemEntity entity : nearbyEntities) {
                    if (entity.getItem().getItem() instanceof ChristmasGiftItem) {
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
                this.grinchEntity.getNavigation().moveTo(targetedEntity.position().x, targetedEntity.position().y, targetedEntity.position().z, 1.0f);

                this.grinchEntity.lookAt(targetedEntity, 45.0f, 45.0f);

                if (targetedEntity.distanceToSqr(this.grinchEntity) < 2.5f) {
                    this.grinchEntity.handleGiftOnGround(targetedEntity);
                }
            }
        }
    }
}
