package com.jumpcutfindo.happyholidays.common.events.christmas;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.eventbus.api.Event;

public class StockingEvent extends Event {
    private final BlockState blockState;
    private final BlockPos blockPos;
    private final PlayerEntity playerEntity;

    public StockingEvent(BlockState blockState, BlockPos blockPos, PlayerEntity playerEntity) {
        this.blockState = blockState;
        this.blockPos = blockPos;
        this.playerEntity = playerEntity;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public static class Fill extends StockingEvent {
        public Fill(BlockState blockState, BlockPos blockPos, PlayerEntity playerEntity) {
            super(blockState, blockPos, playerEntity);
        }
    }

    public static class Empty extends StockingEvent {
        public Empty(BlockState blockState, BlockPos blockPos, PlayerEntity playerEntity) {
            super(blockState, blockPos, playerEntity);
        }
    }
}
