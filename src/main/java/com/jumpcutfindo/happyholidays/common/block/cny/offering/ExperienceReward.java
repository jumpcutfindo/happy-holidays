package com.jumpcutfindo.happyholidays.common.block.cny.offering;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ExperienceReward extends OfferingReward {
    private final int experience;

    private ExperienceReward(int experience) {
        this.experience = experience;
    }

    @Override
    public void execute(Level level, Player player, BlockPos blockPos) {
        player.giveExperiencePoints(experience);
    }

    public static ExperienceReward withAmount(int amount) {
        ExperienceReward reward = new ExperienceReward(amount);
        return reward;
    }
}
