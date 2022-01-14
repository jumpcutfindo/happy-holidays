package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.item.christmas.walnut.WalnutAmmo;
import com.jumpcutfindo.happyholidays.common.item.christmas.walnut.WalnutItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

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

    public WalnutAmmo getCurrentAmmo() {
        ItemStack itemStack = this.getFirstStack();

        if (itemStack.is(ChristmasItems.EXPLOSIVE_WALNUT.get())) {
            return WalnutAmmo.EXPLOSIVE;
        } else if (itemStack.is(ChristmasItems.SUGARED_WALNUT.get())) {
            return WalnutAmmo.SUGARED;
        } else if (itemStack.is(ChristmasItems.METALLIC_WALNUT.get())) {
            return WalnutAmmo.METALLIC;
        } else if (itemStack.is(ChristmasItems.HALVED_WALNUT.get())) {
            return WalnutAmmo.HALVED;
        }

        return WalnutAmmo.PLAIN;
    }

    public ItemStack getFirstStack() {
        for (ItemStack stack : this.stacks) {
            if (!stack.isEmpty()) return stack;
        }

        return ItemStack.EMPTY;
    }

    public int getFirstFilledSlot() {
        for (int i = 0; i < this.stacks.size(); i++) {
            if (!this.stacks.get(i).isEmpty()) return i;
        }

        return -1;
    }

    public void useAmmo() {
        int slot = this.getFirstFilledSlot();
        if (slot == -1) return;

        this.extractItem(slot, 1, false);
    }

    public List<ItemStack> getAllItems() {
        return this.stacks;
    }
}
