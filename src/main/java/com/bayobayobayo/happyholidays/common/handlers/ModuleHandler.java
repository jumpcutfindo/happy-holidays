package com.bayobayobayo.happyholidays.common.handlers;

import com.bayobayobayo.happyholidays.common.handlers.christmas.ChristmasHandler;

public interface ModuleHandler {
    static void registerModules() {
        ChristmasHandler christmasHandler = new ChristmasHandler();
        christmasHandler.registerBlocks();
        christmasHandler.registerItems();
    }

    void registerBlocks();
    void registerItems();
}
