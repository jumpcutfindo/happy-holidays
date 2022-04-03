package com.jumpcutfindo.happyholidays.common.item.christmas;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ChristmasFoiledItem extends ChristmasItem {
    public ChristmasFoiledItem(Item.Properties properties) {
        super(properties);
    }

    public ChristmasFoiledItem(Item.Properties properties, ChristmasRarity rarity) {
        super(properties, rarity);
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }
}
