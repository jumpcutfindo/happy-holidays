package com.jumpcutfindo.happyholidays.server.command.common;

import java.util.Map;

import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.common.utils.ColourUtils;
import com.jumpcutfindo.happyholidays.server.command.arguments.AvailabilityArgument;
import com.jumpcutfindo.happyholidays.server.data.HolidayAvailabilityData;
import com.jumpcutfindo.happyholidays.server.data.structs.Availability;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class AvailabilityCommand {
    public static final String AVAILABILITY_SET = "commands.happyholidays.availability.set";
    public static final String AVAILABILITY_FAIL = "commands.happyholidays.availability.fail";
    public static final String AVAILABILITY_PRINT = "commands.happyholidays.availability.print";
    public static final String AVAILABILITY_RESET = "commands.happyholidays.availability.reset";

    public static ArgumentBuilder<CommandSourceStack, ?> register(Holiday holiday) {
        ArgumentBuilder<CommandSourceStack, ?> setSubCommand = Commands.literal("set");

        for (String key : HolidayAvailabilityData.HOLIDAY_KEYSETS.get(holiday)) {
            setSubCommand.then(Commands.literal(key).then(Commands.argument("when", AvailabilityArgument.availability()).executes(command -> changeAvailability(command.getSource(), holiday, key, AvailabilityArgument.getAvailability(command, "when")))));
        }

        return LiteralArgumentBuilder.<CommandSourceStack>literal("availability").requires((player) -> player.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("list").executes(command -> listAvailabilities(command.getSource(), holiday)))
                .then(Commands.literal("reset").executes(command -> reset(command.getSource(), holiday)))
                .then(setSubCommand);
    }

    public static int reset (CommandSourceStack commandSourceStack, Holiday holiday) {
        HolidayAvailabilityData availabilityData = HolidayAvailabilityData.retrieve(commandSourceStack.getLevel());
        availabilityData.reset(holiday);

        commandSourceStack.sendSuccess(new TranslatableComponent(AVAILABILITY_RESET, ColourUtils.formatHolidayCode(holiday.getCode())), true);

        return 0;
    }

    public static int changeAvailability(CommandSourceStack commandSourceStack, Holiday holiday, String key, Availability availability) {
        HolidayAvailabilityData availabilityData = HolidayAvailabilityData.retrieve(commandSourceStack.getLevel());
        availabilityData.setAvailability(holiday, key, availability);

        commandSourceStack.sendSuccess(new TranslatableComponent(AVAILABILITY_SET, ColourUtils.formatHolidayCode(holiday.getCode()), ColourUtils.formatHolidayCode(key), ColourUtils.formatAvailability(availability)), true);

        return 0;
    }

    public static int listAvailabilities(CommandSourceStack commandSourceStack, Holiday holiday) {
        HolidayAvailabilityData availabilityData = HolidayAvailabilityData.retrieve(commandSourceStack.getLevel());
        Map<String, Availability> holidayAvailabilities = availabilityData.getHolidayAvailabilities(holiday);

        for (String key : HolidayAvailabilityData.HOLIDAY_KEYSETS.get(holiday)) {
            commandSourceStack.sendSuccess(new TranslatableComponent(AVAILABILITY_PRINT, ColourUtils.formatHolidayCode(holiday.getCode()), ColourUtils.formatHolidayCode(key), ColourUtils.formatAvailability(holidayAvailabilities.get(key))), false);
        }

        return 0;
    }

    private static void handleFailure(CommandSourceStack commandSourceStack, Holiday holiday, String key) {
        commandSourceStack.sendFailure(new TranslatableComponent(AVAILABILITY_FAIL, holiday, key));
    }
}
