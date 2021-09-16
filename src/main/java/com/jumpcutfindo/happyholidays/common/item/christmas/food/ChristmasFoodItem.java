package com.jumpcutfindo.happyholidays.common.item.christmas.food;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.Item.Properties;

public class ChristmasFoodItem extends ChristmasItem {
    public ChristmasFoodItem(Properties properties) {
        super(properties);
    }

    public static boolean isChristmasFood(ItemStack itemStack) {
        return itemStack.getItem() instanceof ChristmasFoodItem;
    }
}
