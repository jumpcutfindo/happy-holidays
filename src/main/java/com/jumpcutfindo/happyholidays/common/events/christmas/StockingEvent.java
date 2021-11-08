package com.jumpcutfindo.happyholidays.common.events.christmas;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class StockingEvent extends ChristmasEvent {
    private final BlockState blockState;
    private final BlockPos blockPos;

    public StockingEvent(BlockState blockState, BlockPos blockPos, Player playerEntity) {
        super(playerEntity);
        this.blockState = blockState;
        this.blockPos = blockPos;
    }

    public static class Fill extends StockingEvent {
        public Fill(BlockState blockState, BlockPos blockPos, Player playerEntity) {
            super(blockState, blockPos, playerEntity);
        }
    }

    public static class Empty extends StockingEvent {
        public Empty(BlockState blockState, BlockPos blockPos, Player playerEntity) {
            super(blockState, blockPos, playerEntity);
        }
    }

    public static class Upgrade extends StockingEvent {
        public Upgrade(BlockState blockState, BlockPos blockPos, Player playerEntity) {
            super(blockState, blockPos, playerEntity);
        }
    }
}
