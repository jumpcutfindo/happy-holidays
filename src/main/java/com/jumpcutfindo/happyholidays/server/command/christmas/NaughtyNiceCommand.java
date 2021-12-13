package com.jumpcutfindo.happyholidays.server.command.christmas;

import java.util.Collection;
import java.util.Optional;

import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;

public class NaughtyNiceCommand {
    public static final String NAUGHTY_NICE_QUERY_MESSAGE = "commands.happyholidays.christmas.naughty_nice.query";
    public static final String NAUGHTY_NICE_FAIL_RETRIEVAL = "commands.happyholidays.christmas.naughty_nice.retrieval_fail";
    public static final String NAUGHTY_NICE_SET = "commands.happyholidays.christmas.naughty_nice.set";
    public static final String NAUGHTY_NICE_AVERAGE = "commands.happyholidays.christmas.naughty_nice.average";

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("naughtyNice").requires((player) -> player.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("query")
                                .then(Commands.argument("target", EntityArgument.player()).executes(command -> queryNaughtyNice(command.getSource(), EntityArgument.getPlayer(command, "target"))))
                )
                .then(Commands.literal("set")
                        .then(Commands.argument("targets", EntityArgument.players()).then(Commands.argument("value", IntegerArgumentType.integer()).executes(command -> setNaughtyNice(command.getSource(), EntityArgument.getPlayers(command, "targets"), IntegerArgumentType.getInteger(command, "value")))))
                )
                .then(Commands.literal("average")
                        .then(Commands.argument("targets", EntityArgument.players()).executes(command -> averageNaughtyNice(command.getSource(), EntityArgument.getPlayers(command, "targets"))))
        );
    }

    private static int averageNaughtyNice(CommandSourceStack commandSourceStack, Collection<ServerPlayer> serverPlayers) {
        int total = 0;
        int playerCount = serverPlayers.size();

        for (ServerPlayer serverPlayer : serverPlayers) {

            Optional<INaughtyNiceHandler> naughtyNiceCapability =
                    serverPlayer.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

            if (naughtyNiceCapability.isPresent()) {
                NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) naughtyNiceCapability.get();
                total += naughtyNiceMeter.getValue();
            }
        }

        int average = total / playerCount;

        NaughtyNiceMeter tempMeter = new NaughtyNiceMeter();
        tempMeter.setValue(average);

        commandSourceStack.sendSuccess(new TranslatableComponent(NAUGHTY_NICE_AVERAGE, playerCount, average, retrieveTextState(tempMeter)), false);

        return average;
    }

    private static int queryNaughtyNice(CommandSourceStack commandSourceStack, ServerPlayer serverPlayer) {
        Optional<INaughtyNiceHandler> naughtyNiceCapability =
                serverPlayer.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

        if (naughtyNiceCapability.isPresent()) {
            NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) naughtyNiceCapability.get();

            int value = naughtyNiceMeter.getValue();
            Component playerName = serverPlayer.getDisplayName();
            Component naughtyNiceState = retrieveTextState(naughtyNiceMeter);

            commandSourceStack.sendSuccess(new TranslatableComponent(NAUGHTY_NICE_QUERY_MESSAGE, playerName, value, naughtyNiceState), false);
            return value;
        } else {
            handleFailure(commandSourceStack, serverPlayer);
        }

        return 0;
    }

    private static int setNaughtyNice(CommandSourceStack commandSourceStack, Collection<ServerPlayer> serverPlayers, int value) {
        int finalValue = value;

        // Clamp values so they don't exceed the expected amounts
        if (value <  NaughtyNiceMeter.VALUE_NAUGHTY_MAX) finalValue = NaughtyNiceMeter.VALUE_NAUGHTY_MAX;
        else if (value > NaughtyNiceMeter.VALUE_NICE_MAX) finalValue = NaughtyNiceMeter.VALUE_NICE_MAX;

        int playerCount = 0;
        Component naughtyNiceState = null;

        for (ServerPlayer serverPlayer : serverPlayers) {
            Optional<INaughtyNiceHandler> naughtyNiceCapability =
                    serverPlayer.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

            if (naughtyNiceCapability.isPresent()) {
                NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) naughtyNiceCapability.get();
                naughtyNiceMeter.setValue(finalValue);

                naughtyNiceState = retrieveTextState(naughtyNiceMeter);

                playerCount++;
            } else {
                handleFailure(commandSourceStack, serverPlayer);
            }
        }

        commandSourceStack.sendSuccess(new TranslatableComponent(NAUGHTY_NICE_SET, playerCount, finalValue, naughtyNiceState), true);

        return finalValue;
    }

    private static void handleFailure(CommandSourceStack commandSourceStack, ServerPlayer serverPlayer) {
        commandSourceStack.sendFailure(new TranslatableComponent(NAUGHTY_NICE_FAIL_RETRIEVAL, serverPlayer.getDisplayName()));
    }

    private static Component retrieveTextState(NaughtyNiceMeter naughtyNiceMeter) {
        NaughtyNiceMeter.State meterState = naughtyNiceMeter.getState();
        switch (meterState) {
        case MAX_NAUGHTY -> {
            return new TextComponent("MAX_NAUGHTY").withStyle(ChatFormatting.RED);
        }
        case NAUGHTY -> {
            return new TextComponent("NAUGHTY").withStyle(ChatFormatting.RED);
        }
        case NEUTRAL -> {
            return new TextComponent("NEUTRAL").withStyle(ChatFormatting.GRAY);
        }
        case MAX_NICE -> {
            return new TextComponent("MAX_NICE").withStyle(ChatFormatting.AQUA);
        }
        default -> {
            return new TextComponent("NICE").withStyle(ChatFormatting.AQUA);
        }
        }
    }
}
