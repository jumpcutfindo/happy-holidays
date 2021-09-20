package com.jumpcutfindo.happyholidays.common.item.christmas;

import com.jumpcutfindo.happyholidays.common.item.IHappyHolidaysItem;

import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.Component;

public interface IChristmasItem extends IHappyHolidaysItem {
    IChristmasItem setChristmasRarity(ChristmasRarity rarity);

    static Component createStyledComponent(BaseComponent component, ChristmasRarity rarity) {
        return component.withStyle(rarity.color);
    }
}
