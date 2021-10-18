package com.jumpcutfindo.happyholidays.common.events.christmas;

import net.minecraft.world.entity.player.Player;

public class MusicBoxEvent extends ChristmasEvent{
    public MusicBoxEvent(Player player) {
        super(player);
    }

    public static class Play extends MusicBoxEvent{
        public Play(Player player) {
            super(player);
        }
    }
}
