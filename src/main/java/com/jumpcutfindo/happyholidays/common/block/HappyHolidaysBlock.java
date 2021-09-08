package com.jumpcutfindo.happyholidays.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class HappyHolidaysBlock extends Block implements IHappyHolidaysBlock {
    public final String blockId;
    public final Item.Properties itemProperties;
    public final Properties properties;

    public HappyHolidaysBlock(String blockId, Properties properties, Item.Properties itemProperties) {
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
    public Item.Properties getItemProperties() {
        return itemProperties;
    }

    @Override
    public void configureBlock() {
    }
}
