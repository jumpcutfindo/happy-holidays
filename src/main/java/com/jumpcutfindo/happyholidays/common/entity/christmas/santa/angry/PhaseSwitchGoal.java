package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import net.minecraft.entity.ai.goal.Goal;

public class PhaseSwitchGoal extends Goal {
    private final AngrySantaEntity santaEntity;

    private int timer;

    public PhaseSwitchGoal(AngrySantaEntity santaEntity) {
        this.santaEntity = santaEntity;
    }

    @Override
    public boolean canUse() {
        return santaEntity.isAlive();
    }

    @Override
    public void tick() {
        if (--timer <= 0) {
            timer = (int) (santaEntity.getRandom().nextDouble()
                        * (AngrySantaEntity.ATTACK_PHASE_SWITCH_TIMER_MAX - AngrySantaEntity.ATTACK_PHASE_SWITCH_TIMER_MIN))
                    + AngrySantaEntity.ATTACK_PHASE_SWITCH_TIMER_MIN;

            this.santaEntity.setPhase(Phase.SLEIGHS);

            // TODO: Enable this once testing and implementation is done
            // this.santaEntity.changePhaseRandomly();
        }
    }
}
