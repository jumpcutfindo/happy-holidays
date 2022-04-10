package com.jumpcutfindo.happyholidays.common.block.cny.offering;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ExperienceReward extends OfferingReward {
    private final int experience;

    private ExperienceReward(int experience) {
        this.experience = experience;
    }

    @Override
    public void execute(Level level, Player player, BlockPos blockPos) {
        ExperienceOrb orb = new ExperienceOrb(level, blockPos.getX() + 0.5D, blockPos.getY() + 1.0D, blockPos.getZ() + 0.5D, experience);
        level.addFreshEntity(orb);
    }

    public static ExperienceReward withAmount(int amount) {
        ExperienceReward reward = new ExperienceReward(amount);
        return reward;
    }
}
