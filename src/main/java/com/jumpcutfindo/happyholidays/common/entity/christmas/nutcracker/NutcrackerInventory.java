package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.item.christmas.walnut.WalnutItem;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class NutcrackerInventory extends ItemStackHandler {
    public static final int SLOT_COUNT = 8;

    public NutcrackerInventory() {
        super(SLOT_COUNT);
    }

    @Override
    public int getSlots() {
        return SLOT_COUNT;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return stack.getItem() instanceof WalnutItem;
    }
}
