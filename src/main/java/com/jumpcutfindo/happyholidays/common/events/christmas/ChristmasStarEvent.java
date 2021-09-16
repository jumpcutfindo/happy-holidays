package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;

public class ChristmasStarEvent extends Event {
    final ChristmasStarTileEntity tileEntity;
    final Player playerEntity;

    public ChristmasStarEvent(ChristmasStarTileEntity tileEntity, Player playerEntity) {
        this.tileEntity = tileEntity;
        this.playerEntity = playerEntity;
    }

    public Player getPlayerEntity() {
        return playerEntity;
    }

    public static class PutOrnament extends ChristmasStarEvent {
        public PutOrnament(ChristmasStarTileEntity tileEntity, Player playerEntity) {
            super(tileEntity, playerEntity);
        }
    }

    public static class IncreaseTier extends ChristmasStarEvent {
        public IncreaseTier(ChristmasStarTileEntity tileEntity, Player playerEntity) {
            super(tileEntity, playerEntity);
        }

        public int getTier() {
            return tileEntity.getCurrentTier();
        }
    }

    public static class SummonSanta extends ChristmasStarEvent {
        public SummonSanta(ChristmasStarTileEntity tileEntity, Player playerEntity) {
            super(tileEntity, playerEntity);
        }
    }

    public static class ReachBonus extends ChristmasStarEvent {
        public ReachBonus(ChristmasStarTileEntity tileEntity, Player playerEntity) {
            super(tileEntity, playerEntity);
        }
    }
}
