package com.bayobayobayo.happyholidays.common.item.christmas.candy;

import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.item.christmas.food.ChristmasFoodItem;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class CandyCaneItem extends ChristmasFoodItem {
    public static final String ITEM_ID = "candy_cane";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .food(new Food.Builder()
                            .nutrition(2)
                            .effect(() -> new EffectInstance(Effects.JUMP, 100, 1), 0.5f)
                            .saturationMod(0.1f)
                            .alwaysEat()
                            .build())
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public CandyCaneItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
    }
}
