package com.jumpcutfindo.happyholidays.common.utils.message;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;

public enum MessageType {
    STANDARD(ChatFormatting.WHITE),
    INFO(ChatFormatting.AQUA),
    ERROR(ChatFormatting.RED),
    WARNING(ChatFormatting.GOLD),
    SUCCESS(ChatFormatting.GREEN),

    NAUGHTY(ChatFormatting.RED),
    NEUTRAL(ChatFormatting.GRAY),
    NICE(ChatFormatting.AQUA);

    private final List<ChatFormatting> formatting;

    MessageType(ChatFormatting... formatting) {
        this.formatting = List.of(formatting);
    }

    public MutableComponent applyFormatting(MutableComponent component) {
        MutableComponent result = component;
        for (ChatFormatting format : formatting) {
            result = result.withStyle(format);
        }

        return result;
    }
}
