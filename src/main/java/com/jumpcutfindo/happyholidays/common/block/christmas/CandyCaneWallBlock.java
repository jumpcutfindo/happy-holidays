package com.jumpcutfindo.happyholidays.common.block.christmas;

import net.minecraft.world.level.block.WallBlock;

public class CandyCaneWallBlock extends WallBlock implements ChristmasBlock {
    public CandyCaneWallBlock() {
        super(CandyCaneBlock.BLOCK_PROPERTIES);
    }

    @Override
    public void configure() {
    }
}
