package com.jumpcutfindo.happyholidays.common.item.christmas;

import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.Component;

public interface ChristmasLike {
    ChristmasRarity getChristmasRarity();

    static Component createStyledComponent(BaseComponent component, ChristmasRarity rarity) {
        return component.withStyle(rarity.color);
    }
}
