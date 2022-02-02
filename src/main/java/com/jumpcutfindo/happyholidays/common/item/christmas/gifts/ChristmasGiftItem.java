package com.jumpcutfindo.happyholidays.common.item.christmas.gifts;

import java.util.List;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.NonNullList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

public class ChristmasGiftItem extends ChristmasItem {
    public static final String RED_GIFT_ID = "red_christmas_gift";
    public static final String BLUE_GIFT_ID = "blue_christmas_gift";
    public static final String YELLOW_GIFT_ID = "yellow_christmas_gift";
    public static final String GREEN_GIFT_ID = "green_christmas_gift";
    public static final String GOLD_GIFT_ID = "gold_christmas_gift";
    public static final String SILVER_GIFT_ID = "silver_christmas_gift";

    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP).stacksTo(1);

    public static final int ITEM_USE_DURATION = 30;

    public ChristmasGiftItem() {
        super(ITEM_PROPERTIES);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        player.playSound(ChristmasSounds.CHRISTMAS_GIFT_BOX_SHAKE.get(), 1.0F, 1.0F);
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), world.isClientSide());
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity livingEntity) {
        if (livingEntity instanceof Player) {
            Player playerEntity = (Player) livingEntity;

            CompoundTag nbt = stack.getTag();
            if (nbt != null) {
                CompoundTag giftsNBT = nbt.getCompound("Gifts");

                NonNullList<ItemStack> giftList = NonNullList.withSize(6, ItemStack.EMPTY);

                ContainerHelper.loadAllItems(giftsNBT, giftList);

                for (ItemStack giftItem : giftList) {
                    ItemEntity giftEntity = new ItemEntity(world, playerEntity.getX(), playerEntity.getY(),
                            playerEntity.getZ(), giftItem);
                    giftEntity.setDefaultPickUpDelay();
                    world.addFreshEntity(giftEntity);
                }

                playerEntity.playSound(SoundEvents.ITEM_PICKUP, 1.0f, 1.0f);
            }

            stack.shrink(1);
        }
        return stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return ITEM_USE_DURATION;
    }

    @Override
    public boolean isFoil(ItemStack itemStack) {
        CompoundTag nbt = itemStack.getTag();

        return nbt != null && nbt.contains("WrappedBy");
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        CompoundTag nbt = itemStack.getTag();

        if (nbt != null && nbt.contains("WrappedBy")) {
            MutableComponent wrappedByText = new TranslatableComponent("item.happyholidays.christmas_gift.desc", nbt.getString(
                    "WrappedBy"));
            wrappedByText.withStyle(ChatFormatting.GRAY);
            wrappedByText.withStyle(ChatFormatting.ITALIC);

            textComponents.add(wrappedByText);
        }
    }
}
