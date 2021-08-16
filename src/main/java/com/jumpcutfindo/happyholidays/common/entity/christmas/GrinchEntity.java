package com.jumpcutfindo.happyholidays.common.entity.christmas;

import java.util.List;
import java.util.Random;

import com.jumpcutfindo.happyholidays.common.block.christmas.presents.PresentBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.ChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;
import com.jumpcutfindo.happyholidays.common.registry.EffectRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;
import com.jumpcutfindo.happyholidays.common.registry.SoundRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GrinchEntity extends ChristmasEntity implements IAnimatable {
    public static final DataParameter<Integer> BREAK_ANIM_PROGRESS = EntityDataManager.defineId(GrinchEntity.class,
            DataSerializers.INT);

    public static final String ENTITY_ID = "grinch";
    public static final AttributeModifierMap ENTITY_ATTRIBUTES =
            MobEntity.createMobAttributes()
                    .add(Attributes.MAX_HEALTH, 20.0f)
                    .add(Attributes.MOVEMENT_SPEED, 0.21D)
                    .build();

    private static final ResourceLocation GRINCH_APPEASEMENT_LOOT_TABLE = new ResourceLocation("happyholidays:entities"
            + "/grinch_appeasement");

    public static final int SPAWN_PROBABILITY = 600;

    private static final int BREAK_PRESENT_ANIM_DURATION = 80;
    private static final int BREAK_PRESENT_INTERVAL = 100;
    private static final float AVOID_PLAYER_RADIUS = 6.0f;
    private static final int GRINCH_TIME_TO_DESPAWN = 200;
    private static final int PRESENT_SEARCH_RADIUS = 8;

    private static final double APPEASEMENT_ORNAMENT_DROP_BASE_CHANCE = 0.2d;

    private AnimationFactory factory = new AnimationFactory(this);

    private int[] presentsBrokenCount = new int[] { 0, 0, 0 };

    private int presentBreakingProgress = -1;

    private boolean hasReceivedGift = false;
    private boolean isHappyWithGift = false;

    private int despawnTimer = GRINCH_TIME_TO_DESPAWN;
    private boolean isReadyToDespawn = false;

    public GrinchEntity(EntityType<? extends CreatureEntity> entityType, World world) {
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
                this, PlayerEntity.class, GrinchEntity.AVOID_PLAYER_RADIUS, 1.0D, 1.5D,
                livingEntity -> {
                    if (GrinchEntity.this.hasReceivedGift) return false;

                    if (livingEntity instanceof PlayerEntity) {
                        PlayerEntity playerEntity = (PlayerEntity) livingEntity;
                        if (playerEntity.getMainHandItem().getItem() instanceof ChristmasGiftItem
                                || playerEntity.getOffhandItem().getItem() instanceof  ChristmasGiftItem) {
                            return false;
                        }
                    }
                    return true;
                }
        ));
        this.goalSelector.addGoal(1, new BreakPresentsGoal(this));
        this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.GRINCH_PASSIVE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.GRINCH_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.GRINCH_HURT.get();
    }

    public boolean isPlayerAround() {
        List<PlayerEntity> playerEntityList = this.level.getEntitiesOfClass(PlayerEntity.class, this.getBoundingBox().inflate(AVOID_PLAYER_RADIUS,
                AVOID_PLAYER_RADIUS, AVOID_PLAYER_RADIUS), null);

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

        if (this.level.getBlockState(targetPresentBlock).getBlock().is(BlockRegistry.BABY_PRESENT_BLOCK.get())) {
            // Baby present
            this.presentsBrokenCount[0]++;
        } else if (this.level.getBlockState(targetPresentBlock).getBlock().is(BlockRegistry.ADULT_PRESENT_BLOCK.get())) {
            // Adult present
            this.presentsBrokenCount[1]++;
        } else {
            // Elder present
            this.presentsBrokenCount[2]++;
        }

        this.level.destroyBlock(targetPresentBlock, false);
        this.level.playSound(null, targetPresentBlock, SoundRegistry.GRINCH_BREAK_BOX.get(), SoundCategory.NEUTRAL,
                1.0f, 1.0f);
    }

    public void handleGiftOnGround(ItemEntity itemEntity) {
        if (!this.hasReceivedGift) {
            CompoundNBT itemTag = itemEntity.getItem().getTag();
            this.take(itemEntity, itemEntity.getItem().getCount());

            itemEntity.remove();

            if (itemTag != null && itemTag.contains("Gifts")) {
                // Grinch has happily received gift
                this.hasReceivedGift = true;
                this.isHappyWithGift = true;

                // Drop loot & items (including all the scraps gathered)
                this.throwAppeasementRewards();

                for (int i = 0; i < 5; i++) {
                    double d0 = this.random.nextGaussian() * 0.02D;
                    double d1 = this.random.nextGaussian() * 0.02D;
                    double d2 = this.random.nextGaussian() * 0.02D;
                    ((ServerWorld) this.level).sendParticles(ParticleTypes.HEART, this.getRandomX(1.0D), this.getRandomY() + 0.5D,
                            this.getRandomZ(1.0D), 1, d0, d1, d2, 0.0D);
                }

                this.navigation.moveTo(this.getX() + this.random.nextDouble() * 32.0D + 128.0D, this.getY(),
                        this.random.nextDouble() * 32.0D + 128.0D, 2.0f);
                this.isReadyToDespawn = true;
            } else {
                // Grinch got an empty gift >:(
                this.isHappyWithGift = false;

                for (int i = 0; i < 5; i++) {
                    double d0 = this.random.nextGaussian() * 0.02D;
                    double d1 = this.random.nextGaussian() * 0.02D;
                    double d2 = this.random.nextGaussian() * 0.02D;

                    ((ServerWorld) this.level).sendParticles(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D,
                            this.getRandomZ(1.0D), 1, d0, d1, d2, 0.0D);
                }

                this.navigation.moveTo(this.getX() + this.random.nextDouble() * 64.0D + 128.0D, this.getY(),
                        this.random.nextDouble() * 64.0D + 128.0D, 2.0f);
                this.isReadyToDespawn = true;
            }
        }
    }

    public void throwAppeasementRewards() {
        this.level.addFreshEntity(new ExperienceOrbEntity(this.level, this.getX(), this.getY(), this.getZ(), 50));
        ItemStack scraps = ItemRegistry.PRESENT_SCRAPS.get().getDefaultInstance();

        scraps.setCount(this.presentsBrokenCount[0] + this.presentsBrokenCount[1] * 2 + this.presentsBrokenCount[2] * 3);
        this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), scraps));

        LootTable lootTable = this.level.getServer().getLootTables().get(GRINCH_APPEASEMENT_LOOT_TABLE);
        LootContext ctx = this.createLootContext(true, DamageSource.GENERIC).create(LootParameterSets.ENTITY);

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
            if (ChristmasItem.isBasicOrnamentItem(itemStack)) {
                itemStack.setCount((this.random.nextInt(36 - 12) + 1) + 12);
            } else if (ChristmasItem.isRareOrnamentItem(itemStack)) {
                itemStack.setCount((this.random.nextInt(8 - 4) + 1) + 4);
            } else if (ItemStack.isSame(itemStack, ItemRegistry.PRESENT_SCRAPS.get().getDefaultInstance())) {
                itemStack.setCount((this.random.nextInt(18 - 12) + 1) + 12);
            } else if (ChristmasItem.isFoodItem(itemStack)) {
                if (ChristmasItem.isLargeFoodItem(itemStack)) {
                    itemStack.setCount((this.random.nextInt(4 - 2) + 1) + 2);
                } else {
                    itemStack.setCount((this.random.nextInt(16 - 12) + 1) + 12);
                }
            }

            // Apply modifier
            if (!(itemStack.getItem() instanceof SwordItem)
                    && !(itemStack.getItem() instanceof HoeItem)
                    && !(itemStack.getItem() instanceof AxeItem)
                    && !(itemStack.getItem() instanceof PickaxeItem)
                    && !(itemStack.getItem() instanceof ShovelItem)
            ) {
                itemStack.setCount((int) (itemStack.getCount() * modifier));
            }

            this.spawnAtLocation(itemStack);
        });

        // Drop ornament block
        double ornamentDropChance = APPEASEMENT_ORNAMENT_DROP_BASE_CHANCE * modifier;
        if (ornamentDropChance > this.random.nextDouble()) {
            ItemStack grinchOrnamentItem = ItemRegistry.GRINCH_ORNAMENT_BLOCK.get().getDefaultInstance();
            this.spawnAtLocation(grinchOrnamentItem);
        }
    }

    public void despawnGrinch() {
        if (!this.level.isClientSide()) {
            ServerWorld serverWorld = (ServerWorld) this.level;

            // Spawn poof particles on disappear
            BasicParticleType particleType = ParticleTypes.POOF;
            for (int i = 0; i < 5; i++) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                serverWorld.sendParticles(particleType, this.getRandomX(1.0D),
                        this.getRandomY() + 0.5D,
                        this.getRandomZ(1.0D), 1, d0, d1, d2, 0.0D);
            }

            serverWorld.playSound(null, this.blockPosition(), SoundRegistry.GRINCH_DESPAWN.get(),
                    SoundCategory.NEUTRAL, 1.0f, 1.0f);

        }

        this.remove();
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
            BasicParticleType particleType = isHappyWithGift ? ParticleTypes.HEART : ParticleTypes.ANGRY_VILLAGER;

            ((ServerWorld) this.level).sendParticles(particleType, this.getRandomX(1.0D),
                    this.getRandomY() + 0.5D,
                    this.getRandomZ(1.0D), 1, d0, d1, d2, 0.0D);
        }

        if (this.isReadyToDespawn && --despawnTimer <= 0) {
            this.despawnGrinch();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putIntArray("PresentsBroken", this.presentsBrokenCount);
        nbt.putBoolean("HasReceivedGift", this.hasReceivedGift);
        nbt.putBoolean("IsReadyToDespawn", this.isReadyToDespawn);
        nbt.putInt("DespawnTimer", this.despawnTimer);
        nbt.putBoolean("IsHappyWithGift", this.isHappyWithGift);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
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
    protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
        // Grinch drops number of scraps equivalent to number of presents broken on death
        ItemStack scraps = ItemRegistry.PRESENT_SCRAPS.get().getDefaultInstance();
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

    public static boolean checkGrinchSpawnRules(EntityType<? extends GrinchEntity> entity, IWorld world,
                                                SpawnReason spawnReason, BlockPos pos, Random rand) {
        if (spawnReason == SpawnReason.CHUNK_GENERATION) return false;
        try {
            return HappyHolidaysUtils.findBlockInRadius(world, pos, BlockRegistry.BABY_PRESENT_BLOCK.get(), PRESENT_SEARCH_RADIUS * 2) != null
                    || HappyHolidaysUtils.findBlockInRadius(world, pos, BlockRegistry.ADULT_PRESENT_BLOCK.get(), PRESENT_SEARCH_RADIUS * 2) != null
                    || HappyHolidaysUtils.findBlockInRadius(world, pos, BlockRegistry.ELDER_PRESENT_BLOCK.get(), PRESENT_SEARCH_RADIUS * 2) != null;
        } catch (Exception e) {
            return false;
        }
    }

    private static class BreakPresentsGoal extends Goal {
        private static final float MOVE_TO_PRESENT_SPEED = 0.8f;

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
            if (this.grinchEntity.getEffect(EffectRegistry.DEBUFF_OF_CHRISTMAS_EFFECT.get()) != null) {
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
            if (targetPresentBlockPos == null && timeLeftToNextSearch == 0) {
                BlockPos startPos = this.grinchEntity.blockPosition();
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
                                if (ChristmasStarTileEntity.getStarInfluencingEntity(this.grinchEntity.level,
                                        new Vector3d(currPos.getX(), currPos.getY(), currPos.getZ())) != null) {
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
            } else {
                if (this.targetPresentBlockPos != null) {
                    if (this.grinchEntity.level.isEmptyBlock(targetPresentBlockPos)) {
                        this.targetPresentBlockPos = null;
                        grinchEntity.updatePresentBreaking(-1);
                        this.timeLeftToNextSearch = 100;
                    } else {
                        int presentX = targetPresentBlockPos.getX(), presentY = targetPresentBlockPos.getY(), presentZ =
                                targetPresentBlockPos.getZ();

                        if (this.grinchEntity.distanceToSqr(presentX, presentY, presentZ) > 3.0f) {
                            // Since Grinch is not close enough, we move him to the location & make him look at it
                            this.grinchEntity.navigation.moveTo(presentX, presentY, presentZ, MOVE_TO_PRESENT_SPEED);
                            this.grinchEntity.lookControl.setLookAt(presentX, presentY, presentZ);

                            if (this.grinchEntity.navigation.isStuck()) this.grinchEntity.jumpControl.jump();
                        } else {
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
                    }
                }
            }

            if (timeLeftToNextSearch > 0) timeLeftToNextSearch--;
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
                this.grinchEntity.getNavigation().moveTo(targetedEntity, 1.0f);

                if (targetedEntity.distanceToSqr(this.grinchEntity) < 2.0f) {
                    this.grinchEntity.handleGiftOnGround(targetedEntity);
                }
            }
        }
    }
}
