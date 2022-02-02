package com.jumpcutfindo.happyholidays.common.item.christmas.elf;

import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfRequest;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.utils.StringUtils;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ToyPartsRequestItem extends ChristmasItem {
    public static final String ITEM_ID = "toy_parts_request";

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP)
                    .stacksTo(1);

    public ToyPartsRequestItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.UNIQUE;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        CompoundTag nbt = itemStack.getTag();

        if (nbt != null) {
            SantaElfRequest santaElfRequest = SantaElfRequest.fromTag(nbt.getCompound("SantaElfRequest"));

            for (SantaElfRequest.SingleElfRequest request : santaElfRequest.getRequestedItems()) {
                MutableComponent formattableTextComponent = new TextComponent(request.toString());
                if (request.isCompleted()) formattableTextComponent.withStyle(ChatFormatting.STRIKETHROUGH);

                textComponents.add(formattableTextComponent.withStyle(ChatFormatting.GRAY));
            }

            long timeRemaining = santaElfRequest.getExpiryTime() - world.getGameTime();

            if (timeRemaining > 0) {
                textComponents.add(new TextComponent(""));

                MutableComponent expiryTextComponent = new TranslatableComponent(
                        "item.happyholidays.toy_parts_request.expires_in",
                        StringUtils.convertTicksToString(timeRemaining)
                );
                expiryTextComponent.withStyle(ChatFormatting.GRAY);

                textComponents.add(expiryTextComponent);
            }
        }
    }
}
