package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;

public class SwatPlayerGoal extends Goal {
    private final AngrySantaEntity santaEntity;
    private Player targetedPlayer;

    public SwatPlayerGoal(AngrySantaEntity angrySantaEntity) {
        this.santaEntity = angrySantaEntity;
    }

    @Override
    public boolean canUse() {
        Player nearestPlayer = santaEntity.level.getNearestPlayer(this.santaEntity, 2.0D);

        if (nearestPlayer != null && this.santaEntity.getBoundingBox().inflate(1.0D).contains(nearestPlayer.position())) {
            this.targetedPlayer = nearestPlayer;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void tick() {
        if (this.targetedPlayer != null && this.santaEntity.hitAnimTimer <= 0) {
            targetedPlayer.hurt(DamageSource.GENERIC, 12.0f);
            targetedPlayer.setDeltaMovement(
                    santaEntity.getRandom().nextDouble() * 2.0d,
                    santaEntity.getRandom().nextDouble() * 0.75d + 0.75d,
                    santaEntity.getRandom().nextDouble() * 2.0d
            );

            this.santaEntity.level.playSound(null, this.santaEntity.blockPosition(), ChristmasSounds.SANTA_FLICK.get(), SoundSource.HOSTILE, 1.0f, 1.0f);

            this.santaEntity.hitAnimTimer = 10;

            targetedPlayer = null;
        }
    }
}
