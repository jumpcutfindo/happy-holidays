package com.jumpcutfindo.happyholidays.server.command.christmas;

import com.jumpcutfindo.happyholidays.server.command.common.IntervalCommand;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;

public class ChristmasCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return LiteralArgumentBuilder.<CommandSourceStack>literal("christmas")
                .then(NaughtyNiceCommand.register())
                .then(SantaSummonCommand.register())
                .then(IntervalCommand.register("christmas"));
    }
}
