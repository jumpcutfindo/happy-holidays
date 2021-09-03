package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class SantaElfEvent extends Event {
    private final PlayerEntity playerEntity;
    private final SantaElfEntity santaElfEntity;

    public SantaElfEvent(PlayerEntity playerEntity, SantaElfEntity santaElfEntity) {
        this.playerEntity = playerEntity;
        this.santaElfEntity = santaElfEntity;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public SantaElfEntity getSantaElfEntity() {
        return santaElfEntity;
    }

    public static class Summon extends SantaElfEvent {
        public Summon(PlayerEntity playerEntity, SantaElfEntity santaElfEntity) {
            super(playerEntity, santaElfEntity);
        }
    }

    public static class Trade extends SantaElfEvent {
        public Trade(PlayerEntity playerEntity, SantaElfEntity santaElfEntity) {
            super(playerEntity, santaElfEntity);
        }
    }

    public static class CompleteRequest extends SantaElfEvent {
        private final int timeTaken;
        public CompleteRequest(PlayerEntity playerEntity, SantaElfEntity santaElfEntity, int timeTaken) {
            super(playerEntity, santaElfEntity);
            this.timeTaken = timeTaken;
        }

        public int getTimeTaken() {
            return timeTaken;
        }
    }
}
