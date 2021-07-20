package com.bayobayobayo.happyholidays.common.handlers;

import java.util.List;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.google.common.collect.ImmutableList;

import net.minecraft.item.ItemGroup;

public interface ModuleHandler {
    ItemGroup HAPPY_HOLIDAYS_GROUP = new HappyHolidaysMod.HappyHolidaysGroup("happyholidaystab");
    ChristmasHandler CHRISTMAS_HANDLER = new ChristmasHandler();

    static void preInit() {
        CHRISTMAS_HANDLER.initialise();

        CHRISTMAS_HANDLER.configureBlocks();
        CHRISTMAS_HANDLER.configureItems();
    }

    static void onClientSetup() {
        CHRISTMAS_HANDLER.configureEntities();
        CHRISTMAS_HANDLER.configureContainers();
    }

    void configureBlocks();
    void configureItems();
    void configureEntities();
    void configureContainers();
}
