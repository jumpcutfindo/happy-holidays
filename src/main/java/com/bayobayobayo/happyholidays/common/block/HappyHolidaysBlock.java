package com.bayobayobayo.happyholidays.common.block;


import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

public interface HappyHolidaysBlock {

    Properties getProperties();
    String getBlockId();

    void registerBlock();
    void configureBlock();
}
