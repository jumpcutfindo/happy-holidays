package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraftforge.common.ToolType;

public class BaseGingerbreadBlock extends ChristmasBlock {
    public static final AbstractBlock.Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.SNOW)
                    .strength(0.5f)
                    .sound(SoundType.FUNGUS)
                    .harvestTool(ToolType.HOE);

    public static final Item.Properties ITEM_PROPERITES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public BaseGingerbreadBlock() {
        super(BLOCK_PROPERTIES);
        FurnaceTileEntity f = new FurnaceTileEntity();
    }
}
