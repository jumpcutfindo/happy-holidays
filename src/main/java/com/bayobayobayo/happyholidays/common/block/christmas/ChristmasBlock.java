package com.bayobayobayo.happyholidays.common.block.christmas;

import com.bayobayobayo.happyholidays.common.RegistryHandler;
import com.bayobayobayo.happyholidays.common.block.HappyHolidaysBlock;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ChristmasBlock extends Block implements HappyHolidaysBlock {
    RegistryObject<Block> blockRegistryObject;
    RegistryObject<Item> blockItemRegistryObject;

    final String blockId;
    final Properties properties;
    final Item.Properties itemProperties;

    public ChristmasBlock(String blockId, Properties properties, Item.Properties itemProperties) {
        super(properties);

        this.blockId = blockId;
        this.properties = properties;
        this.itemProperties = itemProperties;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getBlockId() {
        return blockId;
    }

    @Override
    public void registerBlock() {
        blockRegistryObject = RegistryHandler.BLOCKS.register(blockId, () -> this);
        blockItemRegistryObject = RegistryHandler.ITEMS.register(
                blockId,
                () -> new BlockItem(blockRegistryObject.get(), itemProperties)
        );
    }

    @Override
    public void configureBlock() {
        // Default left empty since we don't want the block to do anything
    }
}
