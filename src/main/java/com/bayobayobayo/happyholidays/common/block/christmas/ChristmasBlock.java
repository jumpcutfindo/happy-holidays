package com.bayobayobayo.happyholidays.common.block.christmas;

import com.bayobayobayo.happyholidays.common.RegistryHandler;
import com.bayobayobayo.happyholidays.common.block.HappyHolidaysBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.OrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.TinselBlock;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ChristmasBlock extends Block implements HappyHolidaysBlock {
    public RegistryObject<Block> blockRegistryObject;
    public RegistryObject<Item> blockItemRegistryObject;
    public final String blockId;

    private final Properties properties;
    private final Item.Properties itemProperties;

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
    public RegistryObject<Block> getRegisteredBlock() {
        return blockRegistryObject;
    }

    @Override
    public RegistryObject<Item> getRegisteredBlockItem() {
        return blockItemRegistryObject;
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

    public static boolean isDecorationBlock(Block block) {
        return (block instanceof TinselBlock || block instanceof OrnamentBlock);
    }
}
