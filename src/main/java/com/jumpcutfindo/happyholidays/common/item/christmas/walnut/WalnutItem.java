package com.jumpcutfindo.happyholidays.common.item.christmas.walnut;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.world.item.Item;

public class WalnutItem extends ChristmasItem {


    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(64);

    public WalnutItem() {
        super(ITEM_PROPERTIES);
    }
}
