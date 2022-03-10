package com.jumpcutfindo.happyholidays.common.item.christmas.thread;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.item.ItemStack;

public class EnchantedThreadItem extends ChristmasItem {

    private static final Properties ITEM_PROPERTIES =
            new Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(64);

    public EnchantedThreadItem() {
        super(ITEM_PROPERTIES, ChristmasRarity.RARE);
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }
}
