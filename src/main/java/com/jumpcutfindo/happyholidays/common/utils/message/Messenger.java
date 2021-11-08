package com.jumpcutfindo.happyholidays.common.utils.message;

import java.util.List;
import java.util.UUID;

import net.minecraft.world.entity.player.Player;

public class Messenger {
    public static void sendChatMessage(GameplayMessage message, List<? extends Player> players) {
        for (Player player : players) {
            player.sendMessage(message.getStyledMessage(), UUID.randomUUID());
        }
    }

    public static void sendChatMessage(GameplayMessage message, Player player) {
        sendChatMessage(message, List.of(player));
    }

    public static void sendClientMessage(GameplayMessage message, List<? extends Player> players) {
        for (Player player : players) {
            player.displayClientMessage(message.getStyledMessage(), true);
        }
    }

    public static void sendClientMessage(GameplayMessage message, Player player) {
        sendClientMessage(message, List.of(player));
    }
}
