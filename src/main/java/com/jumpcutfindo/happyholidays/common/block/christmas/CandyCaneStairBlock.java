package com.jumpcutfindo.happyholidays.common.block.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CandyCaneStairBlock extends StairBlock implements ChristmasBlock {
    public static final String BLOCK_ID = "candy_cane_stairs";
    public static final String BRICKS_ID = "candy_cane_brick_stairs";
    public static final String TILES_ID = "candy_cane_tile_stairs";

    public CandyCaneStairBlock(Supplier<BlockState> blockStateSupplier) {
        super(blockStateSupplier, CandyCaneBlock.BLOCK_PROPERTIES);
    }

    @Override
    public void configure() {
    }
}