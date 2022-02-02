package com.jumpcutfindo.happyholidays.common.item.christmas.gingerbread;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.world.item.Item;

public class RawGingerbreadItem extends ChristmasItem {
    public static final String ITEM_ID = "gingerbread_dough";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    public RawGingerbreadItem() {
        super(ITEM_PROPERTIES);
    }
}
