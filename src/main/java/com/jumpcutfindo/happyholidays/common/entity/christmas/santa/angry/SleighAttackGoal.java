package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import net.minecraft.world.entity.ai.goal.Goal;

public class SleighAttackGoal extends Goal {
    private AngrySantaEntity santaEntity;

    private int sleighAttackTimer;
    private boolean isDiagonal;

    public SleighAttackGoal(AngrySantaEntity santaEntity) {
        this.santaEntity = santaEntity;
    }

    @Override
    public boolean canUse() {
        return santaEntity.getPhase() == Phase.SLEIGHS;
    }

    @Override
    public void start() {
        this.sleighAttackTimer = AngrySantaEntity.ATTACK_SLEIGH_INTERVAL;
    }

    @Override
    public void tick() {
        if (--sleighAttackTimer <= 0) {
            if (!isDiagonal) {
                this.santaEntity.fireHorizontalSleighs();
            } else {
                this.santaEntity.fireDiagonalSleighs();
            }

            isDiagonal = !isDiagonal;
            sleighAttackTimer =
                    (int) (AngrySantaEntity.ATTACK_SLEIGH_INTERVAL / santaEntity.getAttackIntervalMultiplier());
        }
    }
}
