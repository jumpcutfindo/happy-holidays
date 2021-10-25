package com.jumpcutfindo.happyholidays.common.events.christmas;

import net.minecraft.world.entity.player.Player;

public class SnowGlobeEvent extends ChristmasEvent {
    public SnowGlobeEvent(Player player) {
        super(player);
    }

    public static class Use extends SnowGlobeEvent {
        public Use(Player player) {
            super(player);
        }
    }
}
