package com.jumpcutfindo.happyholidays.server.command;

import com.jumpcutfindo.happyholidays.server.command.christmas.ChristmasCommand;
import com.jumpcutfindo.happyholidays.server.command.common.IntervalCommand;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;

public class HappyHolidaysCommand {
    public HappyHolidaysCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSourceStack>literal("happyholidays")
                .then(ChristmasCommand.register())
                .then(IntervalCommand.register())
        );
    }
}
