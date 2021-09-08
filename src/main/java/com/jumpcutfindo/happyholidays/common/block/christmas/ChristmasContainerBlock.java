package com.jumpcutfindo.happyholidays.common.block.christmas;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.IHappyHolidaysBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.ContainerBlock;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ChristmasContainerBlock extends ContainerBlock implements IHappyHolidaysBlock {
    protected ChristmasContainerBlock(Properties properties) {
        super(properties);
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
