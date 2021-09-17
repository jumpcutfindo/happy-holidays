package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.world.entity.player.Player;

public class ChristmasStarEvent extends ChristmasEvent {
    final ChristmasStarTileEntity tileEntity;

    public ChristmasStarEvent(ChristmasStarTileEntity tileEntity, Player playerEntity) {
        super(playerEntity);
        this.tileEntity = tileEntity;
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
