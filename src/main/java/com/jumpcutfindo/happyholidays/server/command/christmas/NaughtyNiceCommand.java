package com.jumpcutfindo.happyholidays.server.command.christmas;

import java.util.Optional;

import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.mojang.brigadier.builder.ArgumentBuilder;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.RangeArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;

public class NaughtyNiceCommand {
    public static final String NAUGHTY_NICE_QUERY_MESSAGE = "commands.happyholidays.christmas.naughty_nice.query";
    public static final String NAUGHTY_NICE_FAIL_RETRIEVAL = "commands.happyholidays.christmas.naughty_nice.retrieval_fail";

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("naughtyNice").requires((player) -> player.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("query")
                        .then(Commands.argument("target", EntityArgument.player())
                                .executes(command -> queryNaughtyNice(command.getSource(), EntityArgument.getPlayer(command, "target")))
                ))
                .then(Commands.literal("set")
                        .then(Commands.argument("targets", EntityArgument.player()))
                            .then(Commands.argument("value", RangeArgument.intRange())
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
            Component naughtyNiceState = null;

            if (naughtyNiceMeter.isMaxNaughty()) {
                naughtyNiceState = new TextComponent("MAX_NAUGHTY").withStyle(ChatFormatting.RED);
            } else if (naughtyNiceMeter.isNaughty()) {
                naughtyNiceState = new TextComponent("NAUGHTY").withStyle(ChatFormatting.RED);
            } else if (naughtyNiceMeter.isNeutral()) {
                naughtyNiceState = new TextComponent("NEUTRAL").withStyle(ChatFormatting.GRAY);
            } else if (naughtyNiceMeter.isMaxNice()) {
                naughtyNiceState = new TextComponent("MAX_NICE").withStyle(ChatFormatting.AQUA);
            } else if (naughtyNiceMeter.isNice()) {
                naughtyNiceState = new TextComponent("NICE").withStyle(ChatFormatting.AQUA);
            }

            commandSourceStack.sendSuccess(new TranslatableComponent(NAUGHTY_NICE_QUERY_MESSAGE, playerName, value, naughtyNiceState), false);
            return value;
        } else {
            handleFailure(commandSourceStack, serverPlayer);
        }

        return 0;
    }

    private static void setNaughtyNice(CommandSourceStack commandSourceStack, ServerPlayer serverPlayer, int value) {
        Optional<INaughtyNiceHandler> naughtyNiceCapability =
                serverPlayer.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

        if (naughtyNiceCapability.isPresent()) {
            INaughtyNiceHandler naughtyNiceMeter = naughtyNiceCapability.get();
        } else {
            handleFailure(commandSourceStack, serverPlayer);
        }
    }

    private static void handleFailure(CommandSourceStack commandSourceStack, ServerPlayer serverPlayer) {
        commandSourceStack.sendFailure(new TranslatableComponent(NAUGHTY_NICE_FAIL_RETRIEVAL, serverPlayer.getDisplayName()));
    }
}
