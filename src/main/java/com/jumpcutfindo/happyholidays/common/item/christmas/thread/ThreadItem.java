package com.jumpcutfindo.happyholidays.common.item.christmas.thread;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.world.item.Item;

public class ThreadItem extends ChristmasItem {
    public static final String ITEM_ID = "thread";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(64);

    public ThreadItem() {
        super(ITEM_PROPERTIES);
    }
}
