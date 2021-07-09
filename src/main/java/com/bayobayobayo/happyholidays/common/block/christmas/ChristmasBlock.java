package com.bayobayobayo.happyholidays.common.block.christmas;

import com.bayobayobayo.happyholidays.common.RegistryHandler;
import com.bayobayobayo.happyholidays.common.block.HappyHolidaysBlock;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

public class ChristmasBlock extends Block implements HappyHolidaysBlock {
    private RegistryObject<Block> registeredBlock;

    private final String blockId;
    private final Properties properties;

    public ChristmasBlock(String blockId, Properties properties) {
        super(properties);

        this.blockId = blockId;
        this.properties = properties;
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
    public RegistryObject<Block> registerBlock() {
        if (registeredBlock == null) registeredBlock = RegistryHandler.BLOCKS.register(blockId, () -> this);
        return registeredBlock;
    }

    @Override
    public RegistryObject<Block> getRegisteredBlock() {
        return registeredBlock != null ? registeredBlock : registerBlock();
    }
}
