package com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.ChristmasStarBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GingerbreadPersonEntity extends ChristmasEntity implements IAnimatable {
    public static final float ENTITY_BOX_SIZE = 0.8f;
    public static final float ENTITY_BOX_HEIGHT = 2.0f;

    private static final ResourceLocation GINGERBREAD_CONVERSION_LOOT_TABLE = new ResourceLocation("happyholidays"
            + ":entities/gingerbread_conversion");
    private static final double CONVERSION_ORNAMENT_DROP_BASE_CHANCE = 0.01D;

    public static final Item[] HEAT_EMITTING_ITEMS = {
            Items.CAMPFIRE, Items.SOUL_CAMPFIRE, Items.MAGMA_BLOCK, Items.LAVA_BUCKET
    };

    private AnimationFactory factory = new AnimationFactory(this);

    private boolean isLeader;

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

    public boolean isSoggy() {
        return this instanceof SoggyGingerbreadManEntity;
    }

    public boolean fireImmune() {
        return false;
    }

    public void dropConversionLoot() {
        LootTable lootTable = this.level.getServer().getLootTables().get(GINGERBREAD_CONVERSION_LOOT_TABLE);
        LootContext ctx = this.createLootContext(true, DamageSource.GENERIC).create(LootContextParamSets.ENTITY);

        double modifier;
        ChristmasStarBlockEntity starBlockEntity = ChristmasStarBlockEntity.getStarInfluencingEntity(this.level,
                this.position());
        if (starBlockEntity != null) {
            if (starBlockEntity.isBonusActive()) {
                modifier = 2.0D;
            } else {
                modifier = 1.0D + (starBlockEntity.getCurrentTier() * 0.1D);
            }
        } else {
            modifier = 1.0D;
        }

        // Drop random items
        lootTable.getRandomItems(ctx).forEach(itemStack -> {
            if (ChristmasItems.isBasicOrnamentItem(itemStack)) {
                itemStack.setCount((this.random.nextInt(2 - 1) + 1) + 1);
            }

            itemStack.setCount((int) (itemStack.getCount() * modifier));

            this.spawnAtLocation(itemStack);
        });

        // Drop ornament block
        double ornamentDropChance = CONVERSION_ORNAMENT_DROP_BASE_CHANCE * modifier;
        if (ornamentDropChance > this.random.nextDouble()) {
            ItemStack gingerbreadOrnamentItem = ChristmasItems.GINGERBREAD_MAN_ORNAMENT.get().getDefaultInstance();
            this.spawnAtLocation(gingerbreadOrnamentItem);
        }
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
        return world.getRawBrightness(pos,0) > 8;
    }

    public static boolean isValidHeatItem(ItemStack itemStack) {
        return Arrays.stream(HEAT_EMITTING_ITEMS).anyMatch(item -> ItemStack.isSame(itemStack, item.getDefaultInstance()));
    }

    public static boolean isValidHeatSource(BlockState blockState) {
        return blockState.is(Blocks.FURNACE) && blockState.getValue(BlockStateProperties.LIT)
                || blockState.is(Blocks.BLAST_FURNACE) && blockState.getValue(BlockStateProperties.LIT)
                || blockState.is(Blocks.SMOKER) && blockState.getValue(BlockStateProperties.LIT)
                || blockState.is(Blocks.FIRE)
                || blockState.is(Blocks.SOUL_FIRE)
                || blockState.is(Blocks.CAMPFIRE)
                || blockState.is(Blocks.SOUL_CAMPFIRE)
                || blockState.is(Blocks.MAGMA_BLOCK)
                || blockState.is(Blocks.LAVA);
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
            if (!this.gingerbreadPerson.isSoggy()) return false;

            this.player = this.gingerbreadPerson.level.getNearestPlayer(TEMP_TARGETING, this.gingerbreadPerson);
            if (this.player == null) return false;
            else {
                ItemStack[] heldItems = new ItemStack[]{ player.getMainHandItem(), player.getOffhandItem() };
                return Arrays.stream(heldItems).anyMatch(GingerbreadPersonEntity::isValidHeatItem);
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