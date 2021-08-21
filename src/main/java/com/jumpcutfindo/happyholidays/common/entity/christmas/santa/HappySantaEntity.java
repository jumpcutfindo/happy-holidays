package com.jumpcutfindo.happyholidays.common.entity.christmas.santa;

import com.jumpcutfindo.happyholidays.common.entity.christmas.GingerbreadPersonEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class HappySantaEntity extends BaseSantaEntity {
    public static final DataParameter<Boolean> IS_SUMMONING = EntityDataManager.defineId(HappySantaEntity.class,
            DataSerializers.BOOLEAN);

    public static final String ENTITY_ID = "happy_santa";

    public static final int NUM_GIFTS_TO_SUMMON = 200;
    public static final int GIFT_SUMMON_RADIUS = 20;
    public static final int GIFT_SUMMON_HEIGHT = 5;
    public static final int GIFT_LIFESPAN = 160;
    public static final int GIFT_SPAWN_INTERVAL_MIN = 5;
    public static final int GIFT_SPAWN_INTERVAL_MAX = 10;

    private BlockPos summoningPos = null;
    private boolean hasSummonedGifts = false;
    private boolean isSummoning = false;
    private int giftsRemaining = -1;

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
        if (this.giftsRemaining != -1) this.giftsRemaining = NUM_GIFTS_TO_SUMMON;
        this.isSummoning = true;
        this.entityData.set(IS_SUMMONING, true);

        if (this.summoningPos == null) this.summoningPos = this.blockPosition();
    }

    public void stopDropParty() {
        this.isSummoning = false;
        this.entityData.set(IS_SUMMONING, false);

        this.hasSummonedGifts = true;

        this.summoningPos = null;
    }

    public void summonGift() {
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

        // TODO: Add loot table retrieval and wrapping of gift
        // TODO: Add particle effects and playing of sound where gift spawns
        ItemEntity giftEntity = new ItemEntity(this.level, randomPos.getX(), randomPos.getY(), randomPos.getZ(), Items.DIRT.getDefaultInstance());
        giftEntity.lifespan = GIFT_LIFESPAN;

        this.level.addFreshEntity(giftEntity);

        this.giftsRemaining--;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);

        nbt.putBoolean("HasSummonedGifts", this.hasSummonedGifts);
        nbt.putInt("GiftsRemaining", this.giftsRemaining);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);

        this.hasSummonedGifts = nbt.getBoolean("HasSummonedGifts");
        this.giftsRemaining = nbt.getInt("GiftsRemaining");
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.level.isClientSide()) {
            this.isSummoning = this.entityData.get(IS_SUMMONING);
        }
    }

    @Override
    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isSummoning) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.summon", true));

            return PlayState.CONTINUE;
        }
        else return super.predicate(event);
    }

    public static class SummonGiftsGoal extends Goal {
        private HappySantaEntity santaEntity;

        private int summonTimer = 0;

        public SummonGiftsGoal(HappySantaEntity santaEntity) {
            this.santaEntity = santaEntity;
        }

        @Override
        public boolean canUse() {
            return !santaEntity.hasSummonedGifts();
        }

        @Override
        public boolean canContinueToUse() {
            return !santaEntity.hasSummonedGifts();
        }

        @Override
        public void tick() {
            if (!santaEntity.isSummoning()) {
                santaEntity.startDropParty();
            }

            if (--summonTimer <= 0) {
                if (santaEntity.getGiftsRemaining() <= 0) {
                    santaEntity.stopDropParty();
                } else {
                    santaEntity.summonGift();
                }

                // Reset timer
                summonTimer = santaEntity.random.nextInt(HappySantaEntity.GIFT_SPAWN_INTERVAL_MAX - HappySantaEntity.GIFT_SPAWN_INTERVAL_MIN) + HappySantaEntity.GIFT_SPAWN_INTERVAL_MIN;
            }

            if (santaEntity.summoningPos != null) {
                santaEntity.navigation.moveTo(
                        santaEntity.summoningPos.getX(),
                        santaEntity.summoningPos.getY(),
                        santaEntity.summoningPos.getZ(),
                        1.0f
                );
            }
        }
    }

}
