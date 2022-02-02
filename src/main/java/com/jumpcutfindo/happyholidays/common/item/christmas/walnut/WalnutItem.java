package com.jumpcutfindo.happyholidays.common.item.christmas.walnut;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.world.item.Item;

public class WalnutItem extends ChristmasItem {
    public static final String BASIC_ITEM_ID = "walnut";
    public static final String EXPLOSIVE_ITEM_ID = "explosive_walnut";
    public static final String SUGARED_ITEM_ID = "sugared_walnut";
    public static final String METALLIC_ITEM_ID = "metallic_walnut";
    public static final String HALVED_ITEM_ID = "halved_walnut";


    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(64);

    public WalnutItem() {
        super(ITEM_PROPERTIES);
    }
}
