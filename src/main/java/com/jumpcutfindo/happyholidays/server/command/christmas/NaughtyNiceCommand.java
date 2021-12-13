package com.jumpcutfindo.happyholidays.server.command.christmas;

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

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("naughtyNice").requires((player) -> player.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("query")
                                .then(Commands.argument("target", EntityArgument.player()).executes(command -> queryNaughtyNice(command.getSource(), EntityArgument.getPlayer(command, "target"))))
                )
                .then(Commands.literal("set")
                        .then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("value", IntegerArgumentType.integer()).executes(command -> setNaughtyNice(command.getSource(), EntityArgument.getPlayer(command, "target"), IntegerArgumentType.getInteger(command, "value"))))
                )
        );
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

    private static int setNaughtyNice(CommandSourceStack commandSourceStack, ServerPlayer serverPlayer, int value) {
        int finalValue = value;
        if (value <  NaughtyNiceMeter.VALUE_NAUGHTY_MAX) finalValue = NaughtyNiceMeter.VALUE_NAUGHTY_MAX;
        else if (value > NaughtyNiceMeter.VALUE_NICE_MAX) finalValue = NaughtyNiceMeter.VALUE_NICE_MAX;

        Optional<INaughtyNiceHandler> naughtyNiceCapability =
                serverPlayer.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

        if (naughtyNiceCapability.isPresent()) {
            NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) naughtyNiceCapability.get();
            naughtyNiceMeter.setValue(finalValue);

            Component playerName = serverPlayer.getDisplayName();
            Component naughtyNiceState = retrieveTextState(naughtyNiceMeter);

            commandSourceStack.sendSuccess(new TranslatableComponent(NAUGHTY_NICE_SET, playerName, finalValue, naughtyNiceState), true);
            return finalValue;
        } else {
            handleFailure(commandSourceStack, serverPlayer);
        }

        return 0;
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
