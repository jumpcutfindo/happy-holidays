package com.bayobayobayo.happyholidays.common.handlers;

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
