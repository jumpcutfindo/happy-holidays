package com.bayobayobayo.happyholidays.common.item.christmas.gingerbread;

import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class RawGingerbreadItem extends ChristmasItem {
    private static final String ITEM_ID = "raw_gingerbread";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public RawGingerbreadItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
    }
}
