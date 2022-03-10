package com.jumpcutfindo.happyholidays.common.item.christmas.dye;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

public class DyeBowlItem extends ChristmasItem {

    private static final Properties ITEM_PROPERTIES =
            new Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    public DyeBowlItem() {
        super(ITEM_PROPERTIES);
    }
}
