package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import java.util.List;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;

public class TeleportGoal extends Goal {
    private AngrySantaEntity santaEntity;

    private Vector3d targetPosition;
    private int attackTimer;
    private int chargingTimer;

    public TeleportGoal(AngrySantaEntity santaEntity) {
        this.santaEntity = santaEntity;
    }

    @Override
    public boolean canUse() {
        if (santaEntity.getPhase() != Phase.TELEPORT) {
            this.chargingTimer = 0;
            this.attackTimer = 0;
            santaEntity.stopTeleportCharging();
        }

        return santaEntity.getPhase() == Phase.TELEPORT;
    }

    @Override
    public void tick() {
        if (chargingTimer > 0) santaEntity.getNavigation().stop();

        if (chargingTimer <= 0 && --attackTimer <= 0) {
            AxisAlignedBB box = new AxisAlignedBB(santaEntity.blockPosition()).inflate(AngrySantaEntity.ATTACK_TELEPORT_CONSIDERATION_RADIUS);
            List<PlayerEntity> playerEntities = santaEntity.level.getEntitiesOfClass(PlayerEntity.class, box);

            if (playerEntities.size() > 0) {
                this.targetPosition = playerEntities.get(santaEntity.getRandom().nextInt(playerEntities.size())).position();
                chargingTimer =
                        (int) (AngrySantaEntity.ATTACK_TELEPORT_CHARGE_TIME / santaEntity.getAttackIntervalMultiplier());
                santaEntity.startTeleportAttack(this.targetPosition);
            }
        }

        if (targetPosition != null && --chargingTimer <= 0) {
            santaEntity.teleportAttack(targetPosition);

            targetPosition = null;
            attackTimer = (int) (AngrySantaEntity.ATTACK_TELEPORT_INTERVAL / santaEntity.getAttackIntervalMultiplier());
        }
    }
}
