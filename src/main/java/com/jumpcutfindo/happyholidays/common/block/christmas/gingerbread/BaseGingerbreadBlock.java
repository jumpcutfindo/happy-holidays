package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class BaseGingerbreadBlock extends ChristmasBlock {
    public static final AbstractBlock.Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.SNOW)
                    .strength(0.5f)
                    .sound(SoundType.FUNGUS)
                    .harvestTool(ToolType.HOE);

    public static final Item.Properties ITEM_PROPERITES =
            new Item.Properties().tab(ItemGroup.TAB_DECORATIONS);

    public BaseGingerbreadBlock(String blockId) {
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERITES);
    }
}
