package com.jumpcutfindo.happyholidays.server.command.arguments;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.server.data.structs.Availability;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.TranslatableComponent;

public class AvailabilityArgument implements ArgumentType<Availability> {
    public static final SimpleCommandExceptionType ERROR_NAME_NOT_FOUND = new SimpleCommandExceptionType(new TranslatableComponent("argument.happyholidays.availability.not_found"));

    public static AvailabilityArgument availability() {
        return new AvailabilityArgument();
    }

    @Override
    public Availability parse(StringReader reader) throws CommandSyntaxException {
        String s = reader.readString();
        if (!isValidAvailabilityString(s)) throw ERROR_NAME_NOT_FOUND.create();

        return Availability.fromString(s);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        List<String> suggestions = Lists.newArrayList();

        for (Availability a : Availability.values()) {
            suggestions.add(a.toString());
        }

        return SharedSuggestionProvider.suggest(suggestions, builder);
    }

    public static boolean isValidAvailabilityString(String s) {
        for (Availability a : Availability.values()) {
            if (s.equals(a.toString())) return true;
        }

        return false;
    }

    public static Availability getAvailability(final CommandContext<?> context, final String name) {
        return context.getArgument(name, Availability.class);
    }
}
