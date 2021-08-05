package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.handlers.modules.ModuleHandler;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class BaseCandyCaneBlock extends ChristmasBlock {
    public static final Properties BLOCK_PROPERTIES = AbstractBlock.Properties
            .of(Material.STONE)
            .harvestLevel(-1)
            .strength(1.0f)
            .sound(SoundType.STONE)
            .harvestTool(ToolType.PICKAXE);

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public BaseCandyCaneBlock(String blockId) {
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERTIES);
    }
}
