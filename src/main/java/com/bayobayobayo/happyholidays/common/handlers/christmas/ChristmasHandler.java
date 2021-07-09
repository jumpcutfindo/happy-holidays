package com.bayobayobayo.happyholidays.common.handlers.christmas;

import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.WildPresentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.RedBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;


public class ChristmasHandler implements ModuleHandler {
    private final ChristmasBlock[] christmasBlocks;

    public ChristmasHandler(){
        christmasBlocks = new ChristmasBlock[] {
                new WildPresentBlock(),
                new RedBallOrnamentBlock()
        };
    }

    @Override
    public void registerBlocks() {
        for (ChristmasBlock block : christmasBlocks) {
            block.registerBlock();
        }
    }

    @Override
    public void registerItems() {
    }

    @Override
    public void configureBlocks() {
        for (ChristmasBlock block : christmasBlocks) {
            block.configureBlock();
        }
    }
}
