package com.jumpcutfindo.happyholidays.server.command.christmas;

import com.jumpcutfindo.happyholidays.server.data.SantaSavedData;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.TimeArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;

public class SantaSummonCommand {
    public static final String SANTA_SUMMON_QUERY_LAST_SUMMON = "commands.happyholidays.christmas.santa_summon.query.last_summon";
    public static final String SANTA_SUMMON_QUERY_NEXT_SUMMON = "commands.happyholidays.christmas.santa_summon.query.next_summon";
    public static final String SANTA_SUMMON_QUERY_COOLDOWN = "commands.happyholidays.christmas.santa_summon.query.cooldown";

    public static final String SANTA_SUMMON_SET_NEXT_SUMMON = "commands.happyholidays.christmas.santa_summon.set.next_summon";
    public static final String SANTA_SUMMON_SET_COOLDOWN = "commands.happyholidays.christmas.santa_summon.set.cooldown";

    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("santaSummonTimer")
                .then(Commands.literal("query")
                        .then(Commands.literal("lastSummon").executes(command -> queryLastSummon(command.getSource())))
                        .then(Commands.literal("nextSummon").executes(command -> queryNextSummon(command.getSource())))
                        .then(Commands.literal("cooldown").executes(command -> queryCooldown(command.getSource())))
                )
                .then(Commands.literal("set")
                        .then(Commands.literal("nextSummon").then(Commands.argument("ticks", TimeArgument.time()).executes(command -> setNextSummon(command.getSource(), IntegerArgumentType.getInteger(command, "ticks")))))
                        .then(Commands.literal("cooldown").then(Commands.argument("ticks", TimeArgument.time()).executes(command -> setCooldown(command.getSource(), IntegerArgumentType.getInteger(command, "ticks")))))
                )
                .then(Commands.literal("reset")
                        .then(Commands.literal("nextSummon").executes(command -> setNextSummon(command.getSource(), 0L)))
                        .then(Commands.literal("cooldown").executes(command -> setCooldown(command.getSource(), SantaSavedData.SUMMON_COOLDOWN)))
                );
    }

    private static int queryLastSummon(CommandSourceStack commandSourceStack) {
        ServerLevel serverLevel = commandSourceStack.getLevel();
        SantaSavedData santaData = SantaSavedData.retrieve(serverLevel);

        commandSourceStack.sendSuccess(new TranslatableComponent(SANTA_SUMMON_QUERY_LAST_SUMMON, santaData.getLastSummonTime(), serverLevel.getGameTime() - santaData.getLastSummonTime()), false);
        return 0;
    }

    private static int queryNextSummon(CommandSourceStack commandSourceStack) {
        ServerLevel serverLevel = commandSourceStack.getLevel();
        SantaSavedData santaData = SantaSavedData.retrieve(serverLevel);

        commandSourceStack.sendSuccess(new TranslatableComponent(SANTA_SUMMON_QUERY_NEXT_SUMMON, santaData.getNextSummonTime(), santaData.getNextSummonTime() - serverLevel.getGameTime()), false);
        return 0;
    }

    private static int queryCooldown(CommandSourceStack commandSourceStack) {
        ServerLevel serverLevel = commandSourceStack.getLevel();
        SantaSavedData santaData = SantaSavedData.retrieve(serverLevel);

        commandSourceStack.sendSuccess(new TranslatableComponent(SANTA_SUMMON_QUERY_COOLDOWN, santaData.getSummonCooldown()), false);
        return 0;
    }

    private static int setNextSummon(CommandSourceStack commandSourceStack, long ticks) {
        ServerLevel serverLevel = commandSourceStack.getLevel();
        SantaSavedData santaData = SantaSavedData.retrieve(serverLevel);

        santaData.setNextSummonTime(ticks);

        commandSourceStack.sendSuccess(new TranslatableComponent(SANTA_SUMMON_SET_NEXT_SUMMON, ticks), true);

        return 0;
    }

    private static int setCooldown(CommandSourceStack commandSourceStack, long ticks) {
        ServerLevel serverLevel = commandSourceStack.getLevel();
        SantaSavedData santaData = SantaSavedData.retrieve(serverLevel);

        santaData.setSummonCooldown(ticks);

        commandSourceStack.sendSuccess(new TranslatableComponent(SANTA_SUMMON_SET_COOLDOWN, ticks), true);

        return 0;
    }
}
