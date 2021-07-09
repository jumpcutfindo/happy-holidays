package com.bayobayobayo.happyholidays.common.handlers.christmas;

import com.bayobayobayo.happyholidays.common.block.christmas.WildPresentBlock;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;
import com.bayobayobayo.happyholidays.common.item.christmas.WildPresentBlockItem;


public class ChristmasHandler implements ModuleHandler {

    public ChristmasHandler(){
    }

    @Override
    public void registerBlocks() {
        WildPresentBlock wildPresentBlock = new WildPresentBlock();
        wildPresentBlock.registerBlock();
        WildPresentBlockItem wildPresentBlockItem = new WildPresentBlockItem(wildPresentBlock);
        wildPresentBlockItem.registerItem();
    }

    @Override
    public void registerItems() {
    }
}
