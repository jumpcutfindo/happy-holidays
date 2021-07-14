package com.bayobayobayo.happyholidays.common.handlers;

import java.util.List;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.block.HappyHolidaysBlock;
import com.bayobayobayo.happyholidays.common.entity.HappyHolidaysEntities;
import com.bayobayobayo.happyholidays.common.item.HappyHolidaysItem;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.RegistryObject;

public interface ModuleHandler {
    ItemGroup HAPPY_HOLIDAYS_GROUP = new HappyHolidaysMod.HappyHolidaysGroup("happyholidaystab");
    ChristmasHandler CHRISTMAS_HANDLER = new ChristmasHandler();

    static void registerModules() {
        CHRISTMAS_HANDLER.registerBlocks();
        CHRISTMAS_HANDLER.registerItems();
        CHRISTMAS_HANDLER.registerEntities();
    }

    static void configureModules() {
        CHRISTMAS_HANDLER.configureBlocks();
        CHRISTMAS_HANDLER.configureItems();
        CHRISTMAS_HANDLER.configureEntities();
    }

    static List<ModuleHandler> getHandlers() {
        return ImmutableList.of(CHRISTMAS_HANDLER);
    }

    void registerBlocks();
    void registerItems();
    void registerEntities();
    void configureBlocks();
    void configureItems();
    void configureEntities();

    HappyHolidaysBlock[] getBlocks();
    HappyHolidaysItem[] getItems();
    HappyHolidaysEntities[] getEntities();

    RegistryObject<Block> getRegisteredBlock(String blockId);
}
