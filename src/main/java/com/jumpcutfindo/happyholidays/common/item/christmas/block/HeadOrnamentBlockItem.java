package com.jumpcutfindo.happyholidays.common.item.christmas.block;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.block.Block;

public class HeadOrnamentBlockItem extends ChristmasBlockItem {
    public HeadOrnamentBlockItem(Block block, Properties properties) {
        super(block, properties);

        this.christmasRarity = ChristmasRarity.RARE;
    }
}
