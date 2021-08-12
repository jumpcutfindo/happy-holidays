package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

public class MistletoeAndHollyItem extends ChristmasItem {
    public static final String ITEM_ID = "mistletoe_and_holly";

    private static final Properties ITEM_PROPERTIES =
            new Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public MistletoeAndHollyItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
    }
}
