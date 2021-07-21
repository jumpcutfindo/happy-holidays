package com.bayobayobayo.happyholidays.common.item.christmas.gingerbread;

import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.item.Item;

public class RawGingerbreadItem extends ChristmasItem {
    public static final String ITEM_ID = "raw_gingerbread";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public RawGingerbreadItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
    }
}
