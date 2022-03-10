package com.jumpcutfindo.happyholidays.common.block.christmas;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.level.block.WallBlock;

public class CandyCaneWallBlock extends WallBlock implements ChristmasBlock {
    public static final String BLOCK_ID = "candy_cane_wall";
    public static final String BRICKS_ID = "candy_cane_brick_wall";
    public static final String TILES_ID = "candy_cane_tile_wall";

    public CandyCaneWallBlock() {
        super(CandyCaneBlock.BLOCK_PROPERTIES);
    }

    @Override
    public void configure() {
    }
}
