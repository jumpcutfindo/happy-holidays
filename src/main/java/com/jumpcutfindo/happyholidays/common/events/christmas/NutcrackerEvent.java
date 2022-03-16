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

    public static class Encounter extends NutcrackerEvent {
        public Encounter(NutcrackerEntity nutcracker, Player player) {
            super(nutcracker, player);
        }
    }

    public static class Tame extends NutcrackerEvent {
        public Tame(NutcrackerEntity nutcracker, Player player) {
            super(nutcracker, player);
        }
    }

    public static class ReceiveCompleteOrders extends NutcrackerEvent {
        public ReceiveCompleteOrders(NutcrackerEntity nutcracker, Player player) {
            super(nutcracker, player);
        }
    }

    public static class ReceiveSpecialWalnuts extends NutcrackerEvent {
        public ReceiveSpecialWalnuts(NutcrackerEntity nutcracker, Player player) {
            super(nutcracker, player);
        }
    }

    public static class ReceiveArmor extends NutcrackerEvent {
        public ReceiveArmor(NutcrackerEntity nutcracker, Player player) {
            super(nutcracker, player);
        }
    }

    public static class FullOfExplosives extends NutcrackerEvent {
        public FullOfExplosives(NutcrackerEntity nutcracker, Player player) {
            super(nutcracker, player);
        }
    }
}
