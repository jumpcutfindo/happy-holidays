package com.jumpcutfindo.happyholidays.common.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public interface HappyHolidaysItem {
    Item.Properties getProperties();

    void configureItem();
}
