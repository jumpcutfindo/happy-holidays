package com.bayobayobayo.happyholidays.common.item.christmas.gifts;

import java.util.List;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ChristmasGiftItem extends ChristmasItem {
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    public ChristmasGiftItem(String itemId) {
        super(itemId, ITEM_PROPERTIES);
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        CompoundNBT nbt = itemStack.getTag();

        if (nbt != null && nbt.contains("WrappedBy")) {
            IFormattableTextComponent wrappedByText = new TranslationTextComponent("item.happyholidays.christmas_gift_item.desc", nbt.getString(
                    "WrappedBy"));
            wrappedByText.withStyle(TextFormatting.GRAY);
            wrappedByText.withStyle(TextFormatting.ITALIC);

            textComponents.add(wrappedByText);
        }
    }
}
