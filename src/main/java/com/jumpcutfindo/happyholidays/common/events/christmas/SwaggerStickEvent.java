package com.jumpcutfindo.happyholidays.common.events.christmas;

import net.minecraft.world.entity.player.Player;

public class SwaggerStickEvent extends ChristmasEvent{
    public SwaggerStickEvent(Player player) {
        super(player);
    }

    public static class Held extends SwaggerStickEvent {
        public Held(Player player) {
            super(player);
        }
    }
}
