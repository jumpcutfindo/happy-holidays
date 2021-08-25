package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.happy;

import net.minecraft.entity.ai.goal.Goal;

public class SummonGiftsGoal extends Goal {
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
            summonTimer =
                    santaEntity.getRandom().nextInt(HappySantaEntity.GIFT_SPAWN_INTERVAL_MAX - HappySantaEntity.GIFT_SPAWN_INTERVAL_MIN) + HappySantaEntity.GIFT_SPAWN_INTERVAL_MIN;
        }

        if (santaEntity.getSummoningPos() != null) {
            santaEntity.getNavigation().moveTo(
                    santaEntity.getSummoningPos().getX(),
                    santaEntity.getSummoningPos().getY(),
                    santaEntity.getSummoningPos().getZ(),
                    1.0f
            );
        }
    }
}
