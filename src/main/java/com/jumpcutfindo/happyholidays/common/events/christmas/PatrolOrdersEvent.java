package com.jumpcutfindo.happyholidays.common.events.christmas;

import net.minecraft.world.entity.player.Player;

public class PatrolOrdersEvent extends ChristmasEvent{
    public PatrolOrdersEvent(Player player) {
        super(player);
    }

    public static class Complete extends PatrolOrdersEvent {
        public Complete(Player player) {
            super(player);
        }
    }
}
