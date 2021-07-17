package com.bayobayobayo.happyholidays.common.handlers;

import java.util.HashMap;

import com.bayobayobayo.happyholidays.common.block.HappyHolidaysBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.RedTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.presents.AdultPresentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.presents.BabyPresentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.presents.ElderPresentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.presents.PresentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.BigBlueBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.BigGoldBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.BigGreenBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.BigRedBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.BigSilverBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.BigYellowBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.BlueBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.GoldBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.GreenBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.RedBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.SilverBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.YellowBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.RawGingerbreadBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.SoggyGingerbreadBlock;
import com.bayobayobayo.happyholidays.common.entity.HappyHolidaysEntities;
import com.bayobayobayo.happyholidays.common.entity.christmas.ChristmasEntities;
import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadEntities;
import com.bayobayobayo.happyholidays.common.item.HappyHolidaysItem;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.item.christmas.gingerbread.GingerbreadCookieItem;
import com.bayobayobayo.happyholidays.common.item.christmas.gingerbread.RawGingerbreadItem;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;


public class ChristmasHandler implements ModuleHandler {
    private final ChristmasBlock[] christmasBlocks;
    private final ChristmasItem[] christmasItems;
    private final ChristmasEntities[] christmasEntities;

    HashMap<String, ChristmasBlock> christmasBlockMap;
    HashMap<String, ChristmasItem> christmasItemMap;
    HashMap<String, ChristmasEntities> christmasEntitiesMap;

    public ChristmasHandler(){
        christmasBlocks = new ChristmasBlock[] {
                new BabyPresentBlock(),
                new AdultPresentBlock(),
                new ElderPresentBlock(),

                new RedBallOrnamentBlock(),
                new BlueBallOrnamentBlock(),
                new YellowBallOrnamentBlock(),
                new GreenBallOrnamentBlock(),
                new SilverBallOrnamentBlock(),
                new GoldBallOrnamentBlock(),

                new BigRedBallOrnamentBlock(),
                new BigBlueBallOrnamentBlock(),
                new BigYellowBallOrnamentBlock(),
                new BigGreenBallOrnamentBlock(),
                new BigSilverBallOrnamentBlock(),
                new BigGoldBallOrnamentBlock(),

                new RedTinselBlock(),

                new RawGingerbreadBlock(),
                new GingerbreadBlock(),
                new SoggyGingerbreadBlock()
        };

        christmasItems = new ChristmasItem[] {
                new RawGingerbreadItem(),
                new GingerbreadCookieItem()
        };

        christmasEntities = new ChristmasEntities[] {
                new GingerbreadEntities()
        };

        christmasBlockMap = new HashMap<>();
        christmasItemMap = new HashMap<>();
        christmasEntitiesMap = new HashMap<>();
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
    public void registerEntities() {
        for (ChristmasEntities entities : christmasEntities) {
            entities.registerEntities();
            christmasEntitiesMap.put(entities.getId(), entities);
        }
    }

    @Override
    public void configureBlocks() {
        for (ChristmasBlock block : christmasBlocks) {
            block.configureBlock();
        }
    }

    @Override
    public void configureItems() {

    }

    @Override
    public void configureEntities() {
        for (ChristmasEntities entities : christmasEntities) {
            entities.configureEntities();
        }
    }

    @Override
    public HappyHolidaysBlock[] getBlocks() {
        return christmasBlocks;
    }

    @Override
    public HappyHolidaysItem[] getItems() {
        return christmasItems;
    }

    @Override
    public HappyHolidaysEntities[] getEntities() {
        return christmasEntities;
    }

    @Override
    public HappyHolidaysEntities getSpecificEntities(String entitiesId) {
        return christmasEntitiesMap.get(entitiesId);
    }

    @Override
    public RegistryObject<Block> getRegisteredBlock(String blockId) {
        return christmasBlockMap.get(blockId).getRegisteredBlock();
    }

    @Override
    public RegistryObject<Item> getRegisteredItem(String itemId) {
        return christmasItemMap.get(itemId).getRegisteredItem();
    }
}
