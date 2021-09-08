package com.jumpcutfindo.happyholidays.common.block.christmas;

import com.jumpcutfindo.happyholidays.common.block.HappyHolidaysBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.BaseCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.PresentBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ChristmasBlock extends HappyHolidaysBlock {
    public ChristmasBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void configureBlock() {
        // Default left empty since we don't want the block to do anything
    }

    public static boolean isInfluencedByStar(Block block) {
        // TODO: Move to some central ChristmasBlocks class
        return block instanceof PresentBlock || block instanceof BaseCandyCaneBlock;
    }
}
