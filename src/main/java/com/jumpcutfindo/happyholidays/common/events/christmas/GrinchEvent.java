package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;

public class GrinchEvent extends Event {
    private final GrinchEntity grinchEntity;
    private final Player playerEntity;

    public GrinchEvent(GrinchEntity grinchEntity, Player playerEntity) {
        this.grinchEntity = grinchEntity;
        this.playerEntity = playerEntity;
    }

    public static class Encounter extends GrinchEvent {
        public Encounter(GrinchEntity grinchEntity, Player playerEntity) {
            super(grinchEntity, playerEntity);
        }
    }

    public static class Appease extends GrinchEvent {
        public Appease(GrinchEntity grinchEntity, Player playerEntity) {
            super(grinchEntity, playerEntity);
        }
    }

    public Player getPlayerEntity() {
        return playerEntity;
    }
}
