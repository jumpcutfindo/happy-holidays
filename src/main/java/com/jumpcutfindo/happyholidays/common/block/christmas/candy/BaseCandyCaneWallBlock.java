package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.level.block.WallBlock;

public class BaseCandyCaneWallBlock extends WallBlock implements ChristmasLike, ChristmasBlock {
    public BaseCandyCaneWallBlock() {
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
