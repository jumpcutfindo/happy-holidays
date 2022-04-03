package com.jumpcutfindo.happyholidays.server.command.arguments;

import java.util.concurrent.CompletableFuture;

import com.jumpcutfindo.happyholidays.server.data.structs.Interval;
import com.jumpcutfindo.happyholidays.server.data.structs.YearlessDate;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.network.chat.TranslatableComponent;

public class IntervalArgument implements ArgumentType<Interval> {
    public static final SimpleCommandExceptionType ERROR_NOT_COMPLETE = new SimpleCommandExceptionType(new TranslatableComponent("argument.happyholidays.interval.incomplete"));

    public static IntervalArgument interval() {
        return new IntervalArgument();
    }

    @Override
    public Interval parse(StringReader reader) throws CommandSyntaxException {
        if (!reader.canRead()) throw ERROR_NOT_COMPLETE.create();

        int i = reader.getCursor();
        YearlessDate start = YearlessDateArgument.parseYearlessDate(reader);
        if (reader.canRead() && reader.peek() == ' ') {
            reader.skip();
            YearlessDate end = YearlessDateArgument.parseYearlessDate(reader);
            return new Interval(start, end);
        }

        throw ERROR_NOT_COMPLETE.create();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return ArgumentType.super.listSuggestions(context, builder);
    }

    public static Interval getInterval(final CommandContext<?> context, final String name) {
        return context.getArgument(name, Interval.class);
    }
}
