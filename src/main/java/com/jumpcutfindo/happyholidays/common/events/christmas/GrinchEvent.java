package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class GrinchEvent extends Event {
    private final GrinchEntity grinchEntity;
    private final PlayerEntity playerEntity;

    public GrinchEvent(GrinchEntity grinchEntity, PlayerEntity playerEntity) {
        this.grinchEntity = grinchEntity;
        this.playerEntity = playerEntity;
    }

    public static class Encounter extends GrinchEvent {
        public Encounter(GrinchEntity grinchEntity, PlayerEntity playerEntity) {
            super(grinchEntity, playerEntity);
        }
    }

    public static class Appease extends GrinchEvent {
        public Appease(GrinchEntity grinchEntity, PlayerEntity playerEntity) {
            super(grinchEntity, playerEntity);
        }
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }
}
