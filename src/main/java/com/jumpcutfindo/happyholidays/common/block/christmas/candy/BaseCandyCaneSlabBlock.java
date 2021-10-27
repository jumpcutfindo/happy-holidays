package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.level.block.SlabBlock;

public class BaseCandyCaneSlabBlock extends SlabBlock implements ChristmasLike, ChristmasBlock {
    public BaseCandyCaneSlabBlock() {
        super(BaseCandyCaneBlock.BLOCK_PROPERTIES);
    }

    @Override
    public void configure() {
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.COMMON;
    }
}
