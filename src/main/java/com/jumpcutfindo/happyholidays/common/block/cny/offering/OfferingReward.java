package com.jumpcutfindo.happyholidays.common.block.cny.offering;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class OfferingReward {
    public abstract void execute(Level level, Player player, BlockPos blockPos);
}
