package com.bayobayobayo.happyholidays.common.handlers;

import java.util.HashMap;
import java.util.Map;

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
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.item.christmas.gingerbread.GingerbreadCookieItem;
import com.bayobayobayo.happyholidays.common.item.christmas.gingerbread.RawGingerbreadItem;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;


public class ChristmasHandler implements ModuleHandler {
    private final ChristmasBlock[] christmasBlocks;
    private final ChristmasItem[] christmasItems;

    HashMap<String, ChristmasBlock> christmasBlockMap;
    HashMap<String, ChristmasItem> christmasItemMap;

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

        christmasItems = new ChristmasItem[] {
                new RawGingerbreadItem(),
                new GingerbreadCookieItem()
        };

        christmasBlockMap = new HashMap<>();
        christmasItemMap = new HashMap<>();
    }

    @Override
    public void registerBlocks() {
        for (ChristmasBlock block : christmasBlocks) {
            block.registerBlock();
            christmasBlockMap.put(block.getBlockId(), block);
        }
    }

    @Override
    public void registerItems() {
        for (ChristmasItem item : christmasItems) {
            item.registerItem();
            christmasItemMap.put(item.getItemId(), item);
        }
    }

    @Override
    public void configureBlocks() {
        for (ChristmasBlock block : christmasBlocks) {
            block.configureBlock();
        }
    }

    @Override
    public RegistryObject<Block> getRegisteredBlock(String blockId) {
        return christmasBlockMap.get(blockId).getRegisteredBlock();
    }
}
