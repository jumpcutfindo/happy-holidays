package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.level.block.WallBlock;

public class CandyCaneWallBlock extends WallBlock implements ChristmasLike, ChristmasBlock {
    public static final String BLOCK_ID = "candy_cane_wall";
    public static final String BRICKS_ID = "candy_cane_brick_wall";
    public static final String TILES_ID = "candy_cane_tile_wall";

    public CandyCaneWallBlock() {
        super(CandyCaneBlock.BLOCK_PROPERTIES);
    }

    @Override
    public void configure() {
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.COMMON;
    }
}
