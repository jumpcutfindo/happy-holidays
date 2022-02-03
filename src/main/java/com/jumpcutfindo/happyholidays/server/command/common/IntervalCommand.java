package com.jumpcutfindo.happyholidays.server.command.common;

import java.util.Map;

import com.jumpcutfindo.happyholidays.server.command.arguments.YearlessDateArgument;
import com.jumpcutfindo.happyholidays.server.data.HolidayIntervalData;
import com.jumpcutfindo.happyholidays.server.data.structs.Interval;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;

public class IntervalCommand {
    public static final String INTERVAL_QUERY = "commands.happyholidays.intervals.query";
    public static final String INTERVAL_SET = "commands.happyholidays.intervals.set";
    public static final String INTERVAL_SET_INCORRECT_DATE = "commands.happyholidays.intervals.set.fail";
    public static final String INTERVAL_FAIL = "commands.happyholidays.intervals.set";

    public static ArgumentBuilder<CommandSourceStack, ?> register(String holidayCode) {
        Map<String, Interval> presetIntervals = HolidayIntervalData.HOLIDAY_PRESETS.get(holidayCode);

        ArgumentBuilder<CommandSourceStack, ?> setSubCommand = Commands.literal("set")
                .then(Commands.argument("start", YearlessDateArgument.yearlessDate()).then(Commands.argument("end", YearlessDateArgument.yearlessDate()).executes(command -> adjustInterval(command.getSource(), holidayCode, new Interval(YearlessDateArgument.getYearlessDate(command, "start"), YearlessDateArgument.getYearlessDate(command, "end"))))));

        // Include presets for set
        if (presetIntervals != null) {
            for (String presetName : presetIntervals.keySet()) {
                setSubCommand.then(Commands.literal(presetName).executes(command -> adjustInterval(command.getSource(), holidayCode, presetIntervals.get(presetName))));
            }
        }

        return LiteralArgumentBuilder.<CommandSourceStack>literal("interval").requires((player) -> player.hasPermission(Commands.LEVEL_GAMEMASTERS))
                    .then(Commands.literal("query").executes(command -> query(command.getSource(), holidayCode)))
                    .then(setSubCommand);
    }

    public static int query(CommandSourceStack commandSourceStack, String holidayCode) {
        ServerLevel level = (ServerLevel) commandSourceStack.getLevel();
        HolidayIntervalData intervalData = HolidayIntervalData.retrieve(level);

        Interval interval = intervalData.get(holidayCode);

        if (interval != null) commandSourceStack.sendSuccess(new TranslatableComponent(INTERVAL_QUERY, holidayCode, interval.getStart().toString(), interval.getEnd().toString()), false);
        else handleFailure(commandSourceStack, holidayCode);
        return 0;
    }

    public static int adjustInterval(CommandSourceStack commandSourceStack, String holidayCode, Interval interval) {
        if (!interval.isValid()) {
            commandSourceStack.sendFailure(new TranslatableComponent(INTERVAL_SET_INCORRECT_DATE));
            return 0;
        }

        ServerLevel level = (ServerLevel) commandSourceStack.getLevel();
        HolidayIntervalData intervalData = HolidayIntervalData.retrieve(level);

        intervalData.put(holidayCode, interval);
        intervalData.setDirty();

        commandSourceStack.sendSuccess(new TranslatableComponent(INTERVAL_SET, holidayCode, interval.getStart().toString(), interval.getEnd().toString()), false);

        return 0;
    }

    private static void handleFailure(CommandSourceStack commandSourceStack, String holidayCode) {
        commandSourceStack.sendFailure(new TranslatableComponent(INTERVAL_FAIL, holidayCode));
    }
}
