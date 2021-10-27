package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BaseCandyCaneStairBlock extends StairBlock implements ChristmasLike, ChristmasBlock {
    public BaseCandyCaneStairBlock(Supplier<BlockState> blockStateSupplier) {
        super(blockStateSupplier, BaseCandyCaneBlock.BLOCK_PROPERTIES);
    }

    @Override
    public void configure() {
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.COMMON;
    }
}
