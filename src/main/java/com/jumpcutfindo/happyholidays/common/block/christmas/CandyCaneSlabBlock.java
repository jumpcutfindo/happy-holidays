package com.jumpcutfindo.happyholidays.common.block.christmas;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.level.block.SlabBlock;

public class CandyCaneSlabBlock extends SlabBlock implements ChristmasBlock {
    public static final String BLOCK_ID = "candy_cane_slab";
    public static final String BRICKS_ID = "candy_cane_brick_slab";
    public static final String TILES_ID = "candy_cane_tile_slab";

    public CandyCaneSlabBlock() {
        super(CandyCaneBlock.BLOCK_PROPERTIES);
    }

    @Override
    public void configure() {
    }
}
