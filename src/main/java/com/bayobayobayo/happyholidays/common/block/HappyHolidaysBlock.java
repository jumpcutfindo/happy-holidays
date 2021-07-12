package com.bayobayobayo.happyholidays.common.block;


import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public interface HappyHolidaysBlock {

    Properties getProperties();
    String getBlockId();
    RegistryObject<Block> getRegisteredBlock();
    RegistryObject<Item> getRegisteredBlockItem();

    void registerBlock();
    void configureBlock();
}
