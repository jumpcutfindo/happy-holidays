package com.bayobayobayo.happyholidays.common.item.christmas.candy;

import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.item.christmas.food.ChristmasFoodItem;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FestiveCandyCaneItem extends ChristmasFoodItem {
    public static final String ITEM_ID = "festive_candy_cane";

    private static final Properties ITEM_PROPERTIES =
            new Properties()
                    .food(new Food.Builder()
                            .nutrition(2)
                            .effect(() -> new EffectInstance(Effects.JUMP, 100, 1), 0.5f)
                            .saturationMod(0.1f)
                            .alwaysEat()
                            .build())
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public FestiveCandyCaneItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
    }
}
