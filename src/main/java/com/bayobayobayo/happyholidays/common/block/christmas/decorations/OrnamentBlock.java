package com.bayobayobayo.happyholidays.common.block.christmas.decorations;

import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class OrnamentBlock extends ChristmasBlock {
    private static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.GLASS)
                    .harvestLevel(-1)
                    .strength(0.1f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .noCollission();

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ItemGroup.TAB_DECORATIONS);

    public OrnamentBlock(String blockId) {
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERTIES);
    }
}
