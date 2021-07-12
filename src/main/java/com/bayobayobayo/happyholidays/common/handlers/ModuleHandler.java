package com.bayobayobayo.happyholidays.common.handlers;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

public interface ModuleHandler {
    ChristmasHandler CHRISTMAS_HANDLER = new ChristmasHandler();

    static void registerModules() {
        CHRISTMAS_HANDLER.registerBlocks();
        CHRISTMAS_HANDLER.registerItems();
    }

    static void configureModules() {
        CHRISTMAS_HANDLER.configureBlocks();
    }

    void registerBlocks();
    void registerItems();
    void configureBlocks();

    RegistryObject<Block> getRegisteredBlock(String blockId);
}
