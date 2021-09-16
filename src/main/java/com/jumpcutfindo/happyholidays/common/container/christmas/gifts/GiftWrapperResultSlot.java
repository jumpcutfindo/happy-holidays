package com.jumpcutfindo.happyholidays.common.container.christmas.gifts;

import com.jumpcutfindo.happyholidays.common.tileentity.christmas.GiftWrapperTileEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class GiftWrapperResultSlot extends Slot {
    public GiftWrapperResultSlot(Container p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return false;
    }

    @Override
    public void onTake(Player p_190901_1_, ItemStack p_190901_2_) {
        Container giftWrapperInv = this.container;

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
    }
}
