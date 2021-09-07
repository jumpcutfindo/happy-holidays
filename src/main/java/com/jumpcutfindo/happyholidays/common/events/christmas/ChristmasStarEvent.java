package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.eventbus.api.Event;

public class ChristmasStarEvent extends Event {
    final ChristmasStarTileEntity tileEntity;
    final PlayerEntity playerEntity;

    public ChristmasStarEvent(ChristmasStarTileEntity tileEntity, PlayerEntity playerEntity) {
        this.tileEntity = tileEntity;
        this.playerEntity = playerEntity;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public static class PutOrnament extends ChristmasStarEvent {
        public PutOrnament(ChristmasStarTileEntity tileEntity, PlayerEntity playerEntity) {
            super(tileEntity, playerEntity);
        }
    }

    public static class IncreaseTier extends ChristmasStarEvent {
        public IncreaseTier(ChristmasStarTileEntity tileEntity, PlayerEntity playerEntity) {
            super(tileEntity, playerEntity);
        }

        public int getTier() {
            return tileEntity.getCurrentTier();
        }
    }

    public static class SummonSanta extends ChristmasStarEvent {
        public SummonSanta(ChristmasStarTileEntity tileEntity, PlayerEntity playerEntity) {
            super(tileEntity, playerEntity);
        }
    }

    public static class ReachBonus extends ChristmasStarEvent {
        public ReachBonus(ChristmasStarTileEntity tileEntity, PlayerEntity playerEntity) {
            super(tileEntity, playerEntity);
        }
    }
}
