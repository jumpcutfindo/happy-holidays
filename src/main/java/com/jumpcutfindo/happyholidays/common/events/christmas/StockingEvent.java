package com.jumpcutfindo.happyholidays.common.events.christmas;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraftforge.eventbus.api.Event;

public class StockingEvent extends Event {
    private final BlockState blockState;
    private final BlockPos blockPos;
    private final Player playerEntity;

    public StockingEvent(BlockState blockState, BlockPos blockPos, Player playerEntity) {
        this.blockState = blockState;
        this.blockPos = blockPos;
        this.playerEntity = playerEntity;
    }

    public Player getPlayerEntity() {
        return playerEntity;
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
}
