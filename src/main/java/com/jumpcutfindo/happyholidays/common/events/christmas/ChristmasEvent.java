package com.jumpcutfindo.happyholidays.common.events.christmas;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

public class ChristmasEvent extends Event {
    private final Player player;
    private final Level level;

    public ChristmasEvent(@NotNull Player player) {
        this.player = player;
        this.level = player.level;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }
}
