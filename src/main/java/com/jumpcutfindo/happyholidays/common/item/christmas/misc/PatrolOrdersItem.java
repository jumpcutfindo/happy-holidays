package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.world.item.Item;

public class PatrolOrdersItem extends ChristmasItem {
    public static final String ITEM_ID = "patrol_orders";
    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(1);

    public PatrolOrdersItem() {
        super(ITEM_PROPERTIES);
    }
}
