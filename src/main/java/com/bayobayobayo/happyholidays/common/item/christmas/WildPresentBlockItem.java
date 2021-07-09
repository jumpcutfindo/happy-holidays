package com.bayobayobayo.happyholidays.common.item.christmas;

import com.bayobayobayo.happyholidays.common.RegistryHandler;
import com.bayobayobayo.happyholidays.common.block.christmas.WildPresentBlock;
import com.bayobayobayo.happyholidays.common.item.HappyHolidaysItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;


public class WildPresentBlockItem extends ChristmasItem implements HappyHolidaysItem {
    private static final String ITEM_ID = "wild_present_block";
    private static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ItemGroup.TAB_DECORATIONS);

    private final WildPresentBlock block;

    public WildPresentBlockItem(WildPresentBlock block) {
        super(ITEM_ID, ITEM_PROPERTIES);

        this.block = block;
    }

    @Override
    public RegistryObject<? extends Item> registerItem() {
        return RegistryHandler.ITEMS.register(ITEM_ID, () -> new BlockItem(block, ITEM_PROPERTIES));
    }
}
