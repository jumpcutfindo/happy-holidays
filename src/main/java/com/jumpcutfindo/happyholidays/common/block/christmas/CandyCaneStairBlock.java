package com.jumpcutfindo.happyholidays.common.block.christmas;

import java.util.function.Supplier;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CandyCaneStairBlock extends StairBlock implements ChristmasBlock {
    public CandyCaneStairBlock(Supplier<BlockState> blockStateSupplier) {
        super(blockStateSupplier, CandyCaneBlock.BLOCK_PROPERTIES);
    }

    @Override
    public void configure() {
    }
}
