package com.jumpcutfindo.happyholidays.common.block.cny.offering;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.world.item.ItemStack;

public class Offering {
    private final int size;
    private final List<ItemStack> offerings;

    public Offering(ItemStack firstStack, ItemStack secondStack, ItemStack thirdStack) {
        this.offerings = Lists.newArrayList(firstStack, secondStack, thirdStack);

        int size = 0;
        for (ItemStack itemStack : offerings) {
            if (!itemStack.isEmpty()) size ++;
        }
        this.size = size;
    }

    public List<ItemStack> getOfferings() {
        return offerings;
    }

    public int getSize() {
        return size;
    }
}
