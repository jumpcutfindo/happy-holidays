package com.jumpcutfindo.happyholidays.common.guide.sections;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;

public class ItemSection implements ISection {
    private final List<ItemStack> items;

    public ItemSection(List<ItemStack> items) {
        this.items = items;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public static ItemSection from(String[] itemList) {
        List<ItemStack> items = Lists.newArrayList();
        for (String itemString : itemList) {
            Optional<Item> item = Registry.ITEM.getOptional(new ResourceLocation(itemString));

            item.ifPresent(value -> items.add(value.getDefaultInstance()));
        }

        return new ItemSection(items);
    }
}
