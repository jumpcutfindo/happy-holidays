package com.jumpcutfindo.happyholidays.server.command.common;

import com.jumpcutfindo.happyholidays.server.command.arguments.YearlessDateArgument;
import com.jumpcutfindo.happyholidays.server.data.structs.Interval;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;

public class IntervalCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register(String holidayCode) {
        return LiteralArgumentBuilder.<CommandSourceStack>literal("interval").requires((player) -> player.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .then(Commands.literal("query"))
                    .then(Commands.literal("set")
                        .then(Commands.argument("start", YearlessDateArgument.yearlessDate())
                        .then(Commands.argument("end", YearlessDateArgument.yearlessDate()).executes(command -> adjustInterval(command.getSource(), holidayCode, new Interval(YearlessDateArgument.getYearlessDate(command, "start"), YearlessDateArgument.getYearlessDate(command, "end")))))));
    }

    public static int adjustInterval(CommandSourceStack commandSourceStack, String holidayCode, Interval interval) {
        ServerLevel level = (ServerLevel) commandSourceStack.getLevel();

        return 0;
    }
}
