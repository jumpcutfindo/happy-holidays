package com.bayobayobayo.happyholidays.common.item.christmas;

import com.bayobayobayo.happyholidays.common.item.HappyHolidaysItem;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ChristmasItem extends Item implements HappyHolidaysItem {
    private RegistryObject<Item> registeredItem;

    private final String itemId;
    private final Item.Properties properties;

    public ChristmasItem(String itemId, Item.Properties properties) {
        super(properties);

        this.itemId = itemId;
        this.properties = properties;
    }

    @Override
    public Item.Properties getProperties() {
        return properties;
    }

    @Override
    public void configureItem() {
    }
}
