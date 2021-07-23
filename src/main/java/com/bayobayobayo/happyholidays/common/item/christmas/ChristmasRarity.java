package com.bayobayobayo.happyholidays.common.item.christmas;

import net.minecraft.item.Rarity;
import net.minecraft.util.text.TextFormatting;

public enum ChristmasRarity {
    COMMON(TextFormatting.WHITE),
    RARE(TextFormatting.AQUA),
    LEGENDARY(TextFormatting.GOLD);

    public final TextFormatting color;

    private ChristmasRarity(TextFormatting formatting) {
        this.color = formatting;
    }
}
