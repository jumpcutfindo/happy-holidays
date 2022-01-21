package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.world.item.Item;

public class SwaggerStickItem extends ChristmasItem {
    public static String ITEM_ID = "swagger_stick";
    public static Item.Properties ITEM_PROPERTIES = new Item.Properties()
            .stacksTo(1)
            .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public SwaggerStickItem() {
        super(ITEM_PROPERTIES);
    }
}
