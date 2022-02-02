package com.jumpcutfindo.happyholidays.common.item.christmas.misc;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnchantedSantaHatItem extends ChristmasItem {
    public static final String ITEM_ID = "enchanted_santa_hat";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.HAPPY_HOLIDAYS_GROUP);

    public EnchantedSantaHatItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.LEGENDARY;
    }

    @Override
    public boolean isFoil(ItemStack p_77636_1_) {
        return true;
    }
}
