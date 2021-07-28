package com.bayobayobayo.happyholidays.common.item.christmas.misc;

import java.util.List;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.entity.christmas.elf.SantaElfRequest;
import com.bayobayobayo.happyholidays.common.handlers.modules.ModuleHandler;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasRarity;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ToyPartsRequestItem extends ChristmasItem {
    public static final String ITEM_ID = "toy_parts_request";

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP)
                    .stacksTo(1);

    public ToyPartsRequestItem() {
        super(ITEM_ID, ITEM_PROPERTIES);

        this.christmasRarity = ChristmasRarity.UNIQUE;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
        CompoundNBT nbt = itemStack.getTag();

        if (nbt != null) {
            SantaElfRequest santaElfRequest = SantaElfRequest.fromTag(nbt.getCompound("SantaElfRequest"));

            for (SantaElfRequest.SingleElfRequest request : santaElfRequest.getRequestedItems()) {
                IFormattableTextComponent formattableTextComponent = new StringTextComponent(request.toString());
                if (request.isCompleted()) formattableTextComponent =
                        formattableTextComponent.withStyle(TextFormatting.STRIKETHROUGH);

                textComponents.add(formattableTextComponent.withStyle(TextFormatting.GRAY));
            }
        }
    }
}
