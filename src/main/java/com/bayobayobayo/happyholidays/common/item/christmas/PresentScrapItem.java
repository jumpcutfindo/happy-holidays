package com.bayobayobayo.happyholidays.common.item.christmas;

import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;

import net.minecraft.item.Item;

public class PresentScrapItem extends ChristmasItem {
    public static final String ITEM_ID = "present_scraps";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public PresentScrapItem() {
        super(ITEM_ID, ITEM_PROPERTIES);
    }
}
