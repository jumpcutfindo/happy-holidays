package com.bayobayobayo.happyholidays.common.item.christmas.food;

import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;

import net.minecraft.item.Food;

public class LogCakeItem extends ChristmasFoodItem {
    public static final String ITEM_ID = "log_cake";

    public static final Properties ITEM_PROPERTIES =
            new Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP)
                    .food(new Food.Builder()
                            .nutrition(8)
                            .saturationMod(1.0f)
                            .build()
                    );

    public LogCakeItem() {
        super(ITEM_ID, ITEM_PROPERTIES);

        // TODO: Implement improvement in nutrition and saturation if Spirit of Christmas buff is applied
    }
}
