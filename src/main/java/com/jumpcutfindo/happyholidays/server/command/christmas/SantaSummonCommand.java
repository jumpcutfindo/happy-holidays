package com.jumpcutfindo.happyholidays.server.command.christmas;

import com.jumpcutfindo.happyholidays.server.data.SantaSavedData;
import com.mojang.brigadier.builder.ArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;

public class SantaSummonCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("santaSummonTimer")
                .then(Commands.literal("query"))
                .then(Commands.literal("set"))
                .then(Commands.literal("reset"));
    }

    private static int queryTimer(CommandSourceStack commandSourceStack, ServerLevel serverLevel) {
        SantaSavedData santaData = SantaSavedData.retrieve(serverLevel);
    }
}
