package com.jumpcutfindo.happyholidays.common.item.christmas.thread;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.item.ItemStack;

public class EnchantedThreadItem extends ChristmasItem {
    public static final String ITEM_ID = "enchanted_thread";

    private static final Properties ITEM_PROPERTIES =
            new Properties()
                    .tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(64);

    public EnchantedThreadItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.RARE;
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }
}
