package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.common.handlers.modules.ModuleHandler;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

public class HollyItem extends ChristmasItem {
    public static final String ITEM_ID = "holly";

    private static final Properties ITEM_PROPERTIES =
            new Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public HollyItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
    }
}
