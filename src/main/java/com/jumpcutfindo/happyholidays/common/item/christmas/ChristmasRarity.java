package com.jumpcutfindo.happyholidays.common.item.christmas;

import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;

public enum ChristmasRarity {
    COMMON(TextFormatting.WHITE),
    RARE(TextFormatting.AQUA),
    LEGENDARY(TextFormatting.GOLD),

    UNIQUE(TextFormatting.GREEN);

    public final TextFormatting color;

    private ChristmasRarity(TextFormatting formatting) {
        this.color = formatting;
    }
}
