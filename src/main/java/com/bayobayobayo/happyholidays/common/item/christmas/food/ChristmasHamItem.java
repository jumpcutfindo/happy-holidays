package com.bayobayobayo.happyholidays.common.item.christmas.food;

import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;

import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ChristmasHamItem extends ChristmasFoodItem {
    public static final String ITEM_ID = "christmas_ham";

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP)
                    .food(new Food.Builder()
                            .nutrition(8)
                            .saturationMod(0.8f)
                            .build()
                    );

    public ChristmasHamItem() {
        super(ITEM_ID, ITEM_PROPERTIES);

        // TODO: Implement improvement in nutrition and saturation if Spirit of Christmas buff is applied
    }
}
