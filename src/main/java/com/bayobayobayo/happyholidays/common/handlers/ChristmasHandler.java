package com.bayobayobayo.happyholidays.common.handlers;

import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.WildPresentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.BlueBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.GoldBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.GreenBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.RedBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.SilverBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.YellowBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.RawGingerbreadBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.SoggyGingerbreadBlock;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;


public class ChristmasHandler implements ModuleHandler {
    private final ChristmasBlock[] christmasBlocks;

    public ChristmasHandler(){
        christmasBlocks = new ChristmasBlock[] {
                new WildPresentBlock(),
                new RedBallOrnamentBlock(),
                new BlueBallOrnamentBlock(),
                new YellowBallOrnamentBlock(),
                new GreenBallOrnamentBlock(),
                new SilverBallOrnamentBlock(),
                new GoldBallOrnamentBlock(),
                new RawGingerbreadBlock(),
                new GingerbreadBlock(),
                new SoggyGingerbreadBlock()
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
