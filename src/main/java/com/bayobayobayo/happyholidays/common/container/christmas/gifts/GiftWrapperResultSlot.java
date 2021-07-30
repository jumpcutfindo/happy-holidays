package com.bayobayobayo.happyholidays.common.container.christmas.gifts;

import com.bayobayobayo.happyholidays.common.item.christmas.gifts.ChristmasGiftItem;
import com.bayobayobayo.happyholidays.common.tileentity.christmas.GiftWrapperTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class GiftWrapperResultSlot extends Slot {
    public GiftWrapperResultSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return false;
    }

    @Override
    public ItemStack onTake(PlayerEntity p_190901_1_, ItemStack p_190901_2_) {
        IInventory giftWrapperInv = this.container;

        ItemStack stringItems = giftWrapperInv.getItem(GiftWrapperTileEntity.STRING_SLOT_INDEX);
        ItemStack paperItems = giftWrapperInv.getItem(GiftWrapperTileEntity.PAPER_SLOT_INDEX);
        ItemStack dyeItems = giftWrapperInv.getItem(GiftWrapperTileEntity.DYE_SLOT_INDEX);

        stringItems.shrink(2);
        paperItems.shrink(4);
        dyeItems.shrink(1);

        for (int i = GiftWrapperTileEntity.GIFT_ITEMS_SLOT_INDEX_START; i <= GiftWrapperTileEntity.GIFT_ITEMS_SLOT_INDEX_END; i++) {
            giftWrapperInv.removeItemNoUpdate(i);
        }

        giftWrapperInv.removeItem(GiftWrapperTileEntity.RESULT_SLOT_INDEX, 1);

        return super.onTake(p_190901_1_, p_190901_2_);
    }
}
