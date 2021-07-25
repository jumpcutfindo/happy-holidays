package com.bayobayobayo.happyholidays.common.item.christmas.food;

import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;

import net.minecraft.item.Food;

public class ChristmasPuddingItem extends ChristmasFoodItem {
    public static final String ITEM_ID = "christmas_pudding";

    public static final Properties ITEM_PROPERTIES =
            new Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP)
                    .food(new Food.Builder()
                            .nutrition(6)
                            .saturationMod(0.5f)
                            .build()
                    );

    public ChristmasPuddingItem() {
        super(ITEM_ID, ITEM_PROPERTIES);

        // TODO: Implement improvement in nutrition and saturation if Spirit of Christmas buff is applied
    }
}
