package com.jumpcutfindo.happyholidays.common.container.christmas.star;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class ChristmasStarBonusSlot extends Slot {
    public ChristmasStarBonusSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return ItemStack.isSame(itemStack, Blocks.DIRT.asItem().getDefaultInstance());
    }

    @Override
    public ItemStack onTake(PlayerEntity p_190901_1_, ItemStack p_190901_2_) {
        return super.onTake(p_190901_1_, p_190901_2_);
    }
}
