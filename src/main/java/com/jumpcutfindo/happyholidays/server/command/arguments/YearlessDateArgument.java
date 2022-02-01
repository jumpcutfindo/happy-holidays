package com.jumpcutfindo.happyholidays.server.command.arguments;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.math.NumberUtils;

import com.jumpcutfindo.happyholidays.server.data.structs.YearlessDate;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.network.chat.TranslatableComponent;

public class YearlessDateArgument implements ArgumentType<YearlessDate> {
    private static final Collection<String> EXAMPLES = Arrays.asList("12/31", "02/28", "02/21");

    public static final SimpleCommandExceptionType ERROR_NOT_COMPLETE = new SimpleCommandExceptionType(new TranslatableComponent("argument.happyholidays.yearless_date.incomplete"));
    public static final SimpleCommandExceptionType ERROR_INVALID_MONTH = new SimpleCommandExceptionType(new TranslatableComponent("argument.happyholidays.yearless_date.invalid_month"));
    public static final SimpleCommandExceptionType ERROR_INVALID_DAY = new SimpleCommandExceptionType(new TranslatableComponent("argument.happyholidays.yearless_date.invalid_day"));

    public static YearlessDateArgument yearlessDate() {
        return new YearlessDateArgument();
    }

    @Override
    public YearlessDate parse(StringReader reader) throws CommandSyntaxException {
        if (!reader.canRead()) throw ERROR_NOT_COMPLETE.create();
        else {
            String s = reader.readString();
            String[] monthAndDay = s.split("-");

            if (monthAndDay.length != 2) throw ERROR_NOT_COMPLETE.create();

            if (!NumberUtils.isDigits(monthAndDay[0]) && !YearlessDate.isValidMonth(Integer.parseInt(monthAndDay[0]))) throw ERROR_INVALID_MONTH.create();
            if (!NumberUtils.isDigits(monthAndDay[1]) && !YearlessDate.isValidDay(Integer.parseInt(monthAndDay[1]))) throw ERROR_INVALID_DAY.create();

            return new YearlessDate(Integer.parseInt(monthAndDay[0]), Integer.parseInt(monthAndDay[1]));
        }
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return ArgumentType.super.listSuggestions(context, builder);
    }

    public static YearlessDate getYearlessDate(final CommandContext<?> context, final String name) {
        return context.getArgument(name, YearlessDate.class);
    }
}
