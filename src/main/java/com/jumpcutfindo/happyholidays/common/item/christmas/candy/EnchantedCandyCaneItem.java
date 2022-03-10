package com.jumpcutfindo.happyholidays.common.item.christmas.candy;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnchantedCandyCaneItem extends ChristmasItem {

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    public EnchantedCandyCaneItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public boolean isFoil(ItemStack p_77636_1_) {
        return true;
    }
}
