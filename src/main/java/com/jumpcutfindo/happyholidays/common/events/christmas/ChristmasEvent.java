package com.jumpcutfindo.happyholidays.common.events.christmas;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

public class ChristmasEvent extends Event {
    private final Player player;
    private final Level level;

    public ChristmasEvent(Player player) {
        this.player = player;
        this.level = player.level;
    }

    public ChristmasEvent(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }
}
