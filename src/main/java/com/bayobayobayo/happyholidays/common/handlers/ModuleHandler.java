package com.bayobayobayo.happyholidays.common.handlers;

import com.bayobayobayo.happyholidays.common.handlers.christmas.ChristmasHandler;

public interface ModuleHandler {
    ChristmasHandler christmasHandler = new ChristmasHandler();

    static void registerModules() {
        christmasHandler.registerBlocks();
        christmasHandler.registerItems();
    }

    static void configureModules() {
        christmasHandler.configureBlocks();
    }

    void registerBlocks();
    void registerItems();
    void configureBlocks();
}
