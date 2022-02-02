package com.jumpcutfindo.happyholidays.common.item;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class HappyHolidaysTabs {
    public static final CreativeModeTab CHRISTMAS_GROUP = new CreativeModeTab("happyholidays.christmas") {
        @Override
        public ItemStack makeIcon() {
            return ChristmasBlocks.ELDER_PRESENT.get().asItem().getDefaultInstance();
        }
    }.setRecipeFolderName("christmas/recipes");
}
