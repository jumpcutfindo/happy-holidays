package com.jumpcutfindo.happyholidays.common.item.christmas.candy;

import com.jumpcutfindo.happyholidays.common.handlers.modules.ModuleHandler;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantedCandyCaneItem extends ChristmasItem {
    public static final String ITEM_ID = "enchanted_candy_cane";

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties()
                    .tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public EnchantedCandyCaneItem() {
        super(ITEM_ID, ITEM_PROPERTIES);

        this.christmasRarity = ChristmasRarity.LEGENDARY;
    }

    @Override
    public boolean isFoil(ItemStack p_77636_1_) {
        return true;
    }
}