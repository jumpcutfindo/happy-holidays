package com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread;

import java.util.List;
import java.util.Random;

import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasEntity;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
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

    private AnimationFactory factory = new AnimationFactory(this);

    private boolean isLeader;

    public GingerbreadPersonEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);

        isLeader = false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, PlayerEntity.class, 6.0F, 1.0D, 1.25D));
        this.goalSelector.addGoal(2, new FollowGingerbreadLeaderGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
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

    public boolean fireImmune() {
        return false;
    }

    public void dropConversionLoot() {
        LootTable lootTable = this.level.getServer().getLootTables().get(GINGERBREAD_CONVERSION_LOOT_TABLE);
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

    public static boolean checkGingerbreadSpawnRules(EntityType<? extends GingerbreadPersonEntity> entity, IWorld world,
                                                     SpawnReason spawnReason, BlockPos pos, Random rand) {
        return world.getRawBrightness(pos,0) > 8;
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