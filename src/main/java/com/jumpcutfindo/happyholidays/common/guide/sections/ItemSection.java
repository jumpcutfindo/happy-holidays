package com.jumpcutfindo.happyholidays.common.guide.sections;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

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
