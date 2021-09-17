package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;

import net.minecraft.world.entity.player.Player;

public class GrinchEvent extends ChristmasEvent {
    private final GrinchEntity grinchEntity;

    public GrinchEvent(GrinchEntity grinchEntity, Player playerEntity) {
        super(playerEntity);
        this.grinchEntity = grinchEntity;
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
}
