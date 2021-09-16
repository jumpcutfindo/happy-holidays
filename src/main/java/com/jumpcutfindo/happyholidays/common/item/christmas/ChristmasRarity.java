package com.jumpcutfindo.happyholidays.common.item.christmas;

import net.minecraft.ChatFormatting;

public enum ChristmasRarity {
    COMMON(ChatFormatting.WHITE),
    RARE(ChatFormatting.AQUA),
    LEGENDARY(ChatFormatting.GOLD),

    UNIQUE(ChatFormatting.GREEN);

    public final ChatFormatting color;

    private ChristmasRarity(ChatFormatting formatting) {
        this.color = formatting;
    }
}
