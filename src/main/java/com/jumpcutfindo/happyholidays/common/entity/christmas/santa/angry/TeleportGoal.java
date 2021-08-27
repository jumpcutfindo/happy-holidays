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
        return santaEntity.getPhase() == Phase.TELEPORT;
    }

    @Override
    public void tick() {
        if (--attackTimer <= 0) {
            AxisAlignedBB box = new AxisAlignedBB(santaEntity.blockPosition()).inflate(AngrySantaEntity.ATTACK_TELEPORT_CONSIDERATION_RADIUS);
            List<PlayerEntity> playerEntities = santaEntity.level.getEntitiesOfClass(PlayerEntity.class, box);

            if (playerEntities.size() > 0) {
                this.targetPosition = playerEntities.get(santaEntity.getRandom().nextInt(playerEntities.size())).position();
            }
        }

        if (targetPosition != null && --chargingTimer <= 0) {
            santaEntity.teleportAttack(targetPosition);

            targetPosition = null;
            chargingTimer = AngrySantaEntity.ATTACK_TELEPORT_CHARGE_TIME;
            attackTimer = AngrySantaEntity.ATTACK_TELEPORT_INTERVAL;
        }
    }
}
