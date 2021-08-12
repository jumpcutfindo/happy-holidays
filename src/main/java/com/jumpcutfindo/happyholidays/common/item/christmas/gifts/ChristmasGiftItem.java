package com.jumpcutfindo.happyholidays.common.item.christmas.gifts;

import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.SoundRegistry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ChristmasGiftItem extends ChristmasItem {
    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public static final int ITEM_USE_DURATION = 35;

    public ChristmasGiftItem(String itemId) {
        super(itemId, ITEM_PROPERTIES);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.startUsingItem(hand);
        player.playSound(SoundRegistry.CHRISTMAS_GIFT_BOX_SHAKE.get(), 1.0F, 1.0F);
        return ActionResult.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity) livingEntity;

            CompoundNBT nbt = stack.getTag();
            if (nbt != null) {
                CompoundNBT giftsNBT = nbt.getCompound("Gifts");

                NonNullList<ItemStack> giftList = NonNullList.withSize(6, ItemStack.EMPTY);

                ItemStackHelper.loadAllItems(giftsNBT, giftList);

                for (ItemStack giftItem : giftList) {
                    playerEntity.inventory.add(giftItem);
                }

                playerEntity.playSound(SoundEvents.ITEM_PICKUP, 1.0f, 1.0f);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return ITEM_USE_DURATION;
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        CompoundNBT nbt = itemStack.getTag();

        return nbt != null && nbt.contains("WrappedBy");
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
