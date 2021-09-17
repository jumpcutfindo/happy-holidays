package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.blockentity.christmas.ChristmasStarBlockEntity;

import net.minecraft.world.entity.player.Player;

public class ChristmasStarEvent extends ChristmasEvent {
    final ChristmasStarBlockEntity blockEntity;

    public ChristmasStarEvent(ChristmasStarBlockEntity blockEntity, Player playerEntity) {
        super(playerEntity);
        this.blockEntity = blockEntity;
    }

    public static class PutOrnament extends ChristmasStarEvent {
        public PutOrnament(ChristmasStarBlockEntity blockEntity, Player playerEntity) {
            super(blockEntity, playerEntity);
        }
    }

    public static class IncreaseTier extends ChristmasStarEvent {
        public IncreaseTier(ChristmasStarBlockEntity blockEntity, Player playerEntity) {
            super(blockEntity, playerEntity);
        }

        public int getTier() {
            return blockEntity.getCurrentTier();
        }
    }

    public static class SummonSanta extends ChristmasStarEvent {
        public SummonSanta(ChristmasStarBlockEntity blockEntity, Player playerEntity) {
            super(blockEntity, playerEntity);
        }
    }

    public static class ReachBonus extends ChristmasStarEvent {
        public ReachBonus(ChristmasStarBlockEntity blockEntity, Player playerEntity) {
            super(blockEntity, playerEntity);
        }
    }
}
