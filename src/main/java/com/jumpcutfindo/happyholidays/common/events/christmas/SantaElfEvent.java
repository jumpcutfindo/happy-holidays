package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;

import net.minecraft.world.entity.player.Player;

public class SantaElfEvent extends ChristmasEvent {
    private final SantaElfEntity santaElfEntity;

    public SantaElfEvent(SantaElfEntity santaElfEntity, Player playerEntity) {
        super(playerEntity);
        this.santaElfEntity = santaElfEntity;
    }

    public SantaElfEntity getSantaElfEntity() {
        return santaElfEntity;
    }

    public static class Summon extends SantaElfEvent {
        public Summon(SantaElfEntity santaElfEntity, Player playerEntity) {
            super(santaElfEntity, playerEntity);
        }
    }

    public static class Trade extends SantaElfEvent {
        public Trade(SantaElfEntity santaElfEntity, Player playerEntity) {
            super(santaElfEntity, playerEntity);
        }
    }

    public static class CompleteRequest extends SantaElfEvent {
        private final int timeTaken;
        public CompleteRequest(SantaElfEntity santaElfEntity, Player playerEntity, int timeTaken) {
            super(santaElfEntity, playerEntity);
            this.timeTaken = timeTaken;
        }

        public int getTimeTaken() {
            return timeTaken;
        }
    }
}
