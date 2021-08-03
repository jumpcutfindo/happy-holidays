package com.bayobayobayo.happyholidays.common.item.christmas.block;

import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasBlockItem;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.block.Block;

public class LegendaryOrnamentBlockItem extends ChristmasBlockItem {
    public LegendaryOrnamentBlockItem(Block block, Properties properties) {
        super(block, properties);

        this.christmasRarity = ChristmasRarity.LEGENDARY;
    }
}
