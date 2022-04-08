package com.jumpcutfindo.happyholidays.common.registry.cny;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CNYItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            HappyHolidaysMod.MOD_ID
    );

    // Default item properties
    private static final Item.Properties DEFAULT_PROPS = new Item.Properties().tab(HappyHolidaysTabs.CNY_GROUP);
    public static final RegistryObject<Item> OFFERING_TABLE = ITEMS.register("offering_table", () -> new BlockItem(CNYBlocks.OFFERING_TABLE.get(), DEFAULT_PROPS));
}
