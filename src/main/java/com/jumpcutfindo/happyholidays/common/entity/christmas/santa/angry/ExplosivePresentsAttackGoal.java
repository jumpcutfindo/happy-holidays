package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import java.util.List;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class ExplosivePresentsAttackGoal extends Goal {
    private final AngrySantaEntity santaEntity;

    private List<PlayerEntity> playerEntities;
    private int summonTimer;

    public ExplosivePresentsAttackGoal(AngrySantaEntity santaEntity) {
        this.santaEntity = santaEntity;
    }

    @Override
    public boolean canUse() {
        if (santaEntity.getPhase() == Phase.PRESENTS) {
            AxisAlignedBB box =
                    new AxisAlignedBB(this.santaEntity.blockPosition()).inflate(AngrySantaEntity.ATTACK_PRESENTS_CONSIDERATION_RADIUS);
            playerEntities = this.santaEntity.level.getEntitiesOfClass(PlayerEntity.class, box);

            return true;
        }

        return false;
    }

    @Override
    public void tick() {
        if (--summonTimer <= 0) {
            this.santaEntity.spawnExplosives(playerEntities);

            this.summonTimer =
                    (int) (AngrySantaEntity.ATTACK_PRESENTS_INTERVAL / santaEntity.getAttackIntervalMultiplier());
        }

        this.santaEntity.getNavigation().stop();
    }
}
