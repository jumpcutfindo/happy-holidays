package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import java.util.List;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class TeleportGoal extends Goal {
    private AngrySantaEntity santaEntity;

    private Vec3 targetPosition;
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
        if (targetPosition != null) santaEntity.getLookControl().setLookAt(targetPosition);

        if (chargingTimer <= 0 && --attackTimer <= 0) {
            AABB box = new AABB(santaEntity.blockPosition()).inflate(AngrySantaEntity.ATTACK_TELEPORT_CONSIDERATION_RADIUS);
            List<Player> playerEntities = santaEntity.level.getEntitiesOfClass(Player.class, box);

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
