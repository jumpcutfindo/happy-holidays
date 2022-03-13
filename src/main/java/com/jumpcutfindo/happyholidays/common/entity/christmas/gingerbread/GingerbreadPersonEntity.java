package com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;
import com.jumpcutfindo.happyholidays.server.data.HolidayAvailabilityData;
import com.jumpcutfindo.happyholidays.server.data.structs.Availability;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GingerbreadPersonEntity extends PathfinderMob implements IAnimatable, ChristmasEntity {
    public static final float ENTITY_BOX_SIZE = 0.8f;
    public static final float ENTITY_BOX_HEIGHT = 1.95f;

    private AnimationFactory factory = new AnimationFactory(this);

    private boolean isLeader;
    private boolean isFollowingPlayer;

    public GingerbreadPersonEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);

        isLeader = false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FollowHeatSourceGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.0D, 1.25D,
                (entity) -> {
                    if (entity instanceof Player) {
                        Player playerEntity = (Player) entity;
                        ItemStack[] heldItems = new ItemStack[]{ playerEntity.getMainHandItem(), playerEntity.getOffhandItem() };

                        return Arrays.stream(heldItems).anyMatch(itemStack -> ItemStack.isSame(itemStack,
                                Items.WATER_BUCKET.getDefaultInstance()));
                    }

                    return false;
                }
        ));
        this.goalSelector.addGoal(3, new FollowGingerbreadLeaderGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChristmasSounds.GINGERBREAD_PERSON_PASSIVE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ChristmasSounds.GINGERBREAD_PERSON_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChristmasSounds.GINGERBREAD_PERSON_HURT.get();
    }

    public void setLeader() {
        isLeader = true;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setFollowingPlayer(boolean isFollowingPlayer) {
        this.isFollowingPlayer = isFollowingPlayer;
    }

    public boolean isFollowingPlayer() {
        return this.isFollowingPlayer;
    }

    public boolean isSoggy() {
        return this instanceof SoggyGingerbreadManEntity;
    }

    public boolean fireImmune() {
        return false;
    }

    public void dropConversionLoot() {
        // Drop loot
        LootContext ctx = this.createLootContext(true, DamageSource.GENERIC).create(LootContextParamSets.ENTITY);
        GingerbreadConversionRewards.generateRewards(this, ctx).forEach(this::spawnAtLocation);
    }

    @Override
    public boolean removeWhenFarAway(double p_213397_1_) {
        return false;
    }

    /*
        Animation related methods
     */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving()) event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gingerbread_man.walk", true));
        else event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gingerbread_man.idle", true));

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public static boolean checkGingerbreadSpawnRules(EntityType<? extends GingerbreadPersonEntity> entity, LevelAccessor world,
                                                     MobSpawnType spawnReason, BlockPos pos, Random rand) {
        if (world instanceof ServerLevel serverLevel) {
            return Availability.isAvailable(serverLevel, Holiday.CHRISTMAS, HolidayAvailabilityData.CHRISTMAS_GINGERBREAD_SPAWN);
        }

        return world.getRawBrightness(pos,0) > 8 && world.getBlockState(pos.below()).is(ChristmasTags.Blocks.GINGERBREAD_MEN_SPAWNABLE_ON);
    }

    public static boolean isValidHeatItem(ItemStack itemStack) {
        return itemStack.is(ChristmasTags.Items.HEAT_EMITTING_ITEMS);
    }

    public static boolean isValidHeatSource(BlockState blockState) {
        return blockState.is(Blocks.FURNACE) && blockState.getValue(BlockStateProperties.LIT)
                || blockState.is(Blocks.BLAST_FURNACE) && blockState.getValue(BlockStateProperties.LIT)
                || blockState.is(Blocks.SMOKER) && blockState.getValue(BlockStateProperties.LIT)
                || blockState.is(ChristmasTags.Blocks.HEAT_EMITTING_BLOCKS);
    }

    private static class FollowHeatSourceGoal extends Goal {
        private static final TargetingConditions TEMP_TARGETING = TargetingConditions.forNonCombat().range(10.0D);
        private final GingerbreadPersonEntity gingerbreadPerson;
        private Player player;

        public FollowHeatSourceGoal(GingerbreadPersonEntity gingerbreadPerson) {
            this.gingerbreadPerson = gingerbreadPerson;
        }

        @Override
        public boolean canUse() {
            if (!this.gingerbreadPerson.isSoggy()) {
                this.gingerbreadPerson.setFollowingPlayer(false);
                return false;
            }

            this.player = this.gingerbreadPerson.level.getNearestPlayer(TEMP_TARGETING, this.gingerbreadPerson);
            if (this.player == null) {
                this.gingerbreadPerson.setFollowingPlayer(false);
                return false;
            } else {
                ItemStack[] heldItems = new ItemStack[]{ player.getMainHandItem(), player.getOffhandItem() };

                if (Arrays.stream(heldItems).anyMatch(GingerbreadPersonEntity::isValidHeatItem)) {
                    this.gingerbreadPerson.setFollowingPlayer(true);
                    return true;
                } else {
                    this.gingerbreadPerson.setFollowingPlayer(false);
                    this.player = null;
                    return false;
                }
            }
        }

        @Override
        public void tick() {
            this.gingerbreadPerson.lookAt(player, (float)(this.gingerbreadPerson.getMaxHeadYRot() + 20), (float)this.gingerbreadPerson.getMaxHeadXRot());
            if (this.gingerbreadPerson.distanceToSqr(this.player) < 6.25D) {
                this.gingerbreadPerson.getNavigation().stop();
            } else {
                this.gingerbreadPerson.getNavigation().moveTo(this.player, 1.0D);
            }
        }
    }

    private static class FollowGingerbreadLeaderGoal extends Goal {
        private GingerbreadPersonEntity gingerbreadPerson;
        private GingerbreadPersonEntity leader;

        private int timeToRecalcPath;

        public FollowGingerbreadLeaderGoal(GingerbreadPersonEntity gingerbreadPerson) {
            this.gingerbreadPerson = gingerbreadPerson;
        }

        @Override
        public boolean canUse() {
            if (this.gingerbreadPerson.isFollowingPlayer()) return false;

            if (this.gingerbreadPerson.isLeader()) {
                return false;
            } else {
                List<GingerbreadPersonEntity> list = getSurroundingGingerbreadPeople();

                if (canBeLeader()) {
                    this.gingerbreadPerson.setLeader();
                    return false;
                }

                for (GingerbreadPersonEntity entity : list) {
                    if (entity.isLeader()) {
                        leader = entity;
                        return true;
                    }
                }
            }

            return false;
        }

        @Override
        public boolean canContinueToUse() {
            if (!this.leader.isAlive()) {
                if (canBeLeader()) {
                    this.gingerbreadPerson.setLeader();
                }
                return false;
            } else {
                double d0 = this.gingerbreadPerson.distanceToSqr(this.leader);
                return !(d0 < 9.0D) && !(d0 > 256.0D);
            }
        }

        private boolean canBeLeader() {
            List<GingerbreadPersonEntity> entitiesAround = getSurroundingGingerbreadPeople();
            if (entitiesAround.isEmpty()) return true;

            for (GingerbreadPersonEntity entity : entitiesAround) {
                if (entity.isLeader()) return false;
            }

            return true;
        }

        private List<GingerbreadPersonEntity> getSurroundingGingerbreadPeople() {
            return this.gingerbreadPerson.level.getEntitiesOfClass(GingerbreadPersonEntity.class,
                            this.gingerbreadPerson.getBoundingBox().inflate(8.0D, 4.0D, 8.0D));
        }

        @Override
        public void start() {
            timeToRecalcPath = 0;
        }

        @Override
        public void stop() {
            this.leader = null;
        }

        @Override
        public void tick() {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;
                this.gingerbreadPerson.getNavigation().moveTo(this.leader, 1.0D);
            }
        }
    }
}