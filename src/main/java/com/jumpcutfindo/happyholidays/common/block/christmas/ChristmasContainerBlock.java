package com.jumpcutfindo.happyholidays.common.block.christmas;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.IHappyHolidaysBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.ContainerBlock;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ChristmasContainerBlock extends ContainerBlock implements IHappyHolidaysBlock {

    public final String blockId;

    private Properties properties;
    private Item.Properties itemProperties;

    protected ChristmasContainerBlock(String blockId, Properties properties, Item.Properties itemProperties) {
        super(properties);

        this.blockId = blockId;
        this.properties = properties;
        this.itemProperties = itemProperties;
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
    public AbstractBlock.Properties getProperties() {
        return properties;
    }

    @Override
    public void configureBlock() {
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return null;
    }
}
