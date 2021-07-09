package com.bayobayobayo.happyholidays.common.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public interface HappyHolidaysItem {
    Item.Properties getProperties();
    String getItemId();
    RegistryObject<? extends Item> getRegisteredItem();

    RegistryObject<? extends Item> registerItem();
}
