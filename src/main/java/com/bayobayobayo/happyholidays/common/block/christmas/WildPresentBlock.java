package com.bayobayobayo.happyholidays.common.block.christmas;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class WildPresentBlock extends ChristmasBlock {
    private static final String BLOCK_ID = "wild_present_block";

    private static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.WOOL)
                    .harvestLevel(-1)
                    .strength(0.25f)
                    .sound(SoundType.WOOL);

    public WildPresentBlock() {
        super(BLOCK_ID, BLOCK_PROPERTIES);
    }
}
