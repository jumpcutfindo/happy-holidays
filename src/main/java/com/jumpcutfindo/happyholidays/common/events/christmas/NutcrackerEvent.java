package com.jumpcutfindo.happyholidays.common.events.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;

import net.minecraft.world.entity.player.Player;

public class NutcrackerEvent extends ChristmasEvent{
    private final NutcrackerEntity nutcracker;

    public NutcrackerEvent(NutcrackerEntity nutcracker, Player player) {
        super(player);
        this.nutcracker = nutcracker;
    }

    public NutcrackerEntity getNutcracker() {
        return nutcracker;
    }

    public static class Tame extends NutcrackerEvent {
        public Tame(NutcrackerEntity nutcracker, Player player) {
            super(nutcracker, player);
        }
    }
}
