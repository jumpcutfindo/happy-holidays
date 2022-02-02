package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.world.item.Item;

public class SantaHatItem extends ChristmasItem {
    public static final String ITEM_ID = "santa_hat";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.HAPPY_HOLIDAYS_GROUP);

    public SantaHatItem() {
        super(ITEM_PROPERTIES);
    }
}
