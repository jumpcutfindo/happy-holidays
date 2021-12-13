package com.jumpcutfindo.happyholidays.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;

public class HappyHolidaysCommand {
    public HappyHolidaysCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                LiteralArgumentBuilder.literal("happyholidays")
        );
    }
}
