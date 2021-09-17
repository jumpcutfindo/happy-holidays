package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import java.util.Random;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SantaElfRequests {
    public static final long DEFAULT_EXPIRY = 24000;

    public static final int TOTAL_NO_OF_ITEMS = 4;

    public static final int NO_OF_UNIQUE_BASIC_ITEMS = 2;
    public static final int MIN_BASIC_REQUESTABLE_ITEMS = 12;
    public static final int MAX_BASIC_REQUESTABLE_ITEMS = 48;
    public static final Tag<Item> BASIC_REQUESTABLE_ITEMS =
            ItemTags.getAllTags().getTag(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas_elf_basic_requestables"));

    public static final int NO_OF_UNIQUE_INTERMEDIATE_ITEMS = 1;
    public static final int MIN_INTERMEDIATE_REQUESTABLE_ITEMS = 6;
    public static final int MAX_INTERMEDIATE_REQUESTABLE_ITEMS = 24;
    public static final Tag<Item> INTERMEDIATE_REQUESTABLE_ITEMS =
            ItemTags.getAllTags().getTag(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas_elf_intermediate_requestables"));

    public static final int NO_OF_UNIQUE_ADVANCED_ITEMS = 1;
    public static final int MIN_ADVANCED_REQUESTABLE_ITEMS = 4;
    public static final int MAX_ADVANCED_REQUESTABLE_ITEMS = 12;
    public static final Tag<Item> ADVANCED_REQUESTABLE_ITEMS =
            ItemTags.getAllTags().getTag(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas_elf_advanced_requestables"));
    public static final Tag<Item> ADVANCED_REQUESTABLE_ITEMS_ALTERNATE =
            ItemTags.getAllTags().getTag(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas_elf_advanced_requestables_alternate"));


    public static SantaElfRequest createRandomRequest(long gameTime) {
        SantaElfRequest request = new SantaElfRequest();
        request.setExpiryTime(gameTime + DEFAULT_EXPIRY);

        Random random = request.random;

        // Basic items
        for (int i = 0; i < NO_OF_UNIQUE_BASIC_ITEMS; i++) {
            ItemStack randomItem = getRandomRequestable(BASIC_REQUESTABLE_ITEMS, MIN_BASIC_REQUESTABLE_ITEMS, MAX_BASIC_REQUESTABLE_ITEMS, random);
            request.addEntry(randomItem, false);
        }

        // Intermediate items
        for (int i = 0; i < NO_OF_UNIQUE_INTERMEDIATE_ITEMS; i++) {
            ItemStack randomItem = getRandomRequestable(INTERMEDIATE_REQUESTABLE_ITEMS, MIN_INTERMEDIATE_REQUESTABLE_ITEMS, MAX_INTERMEDIATE_REQUESTABLE_ITEMS, random);
            request.addEntry(randomItem, false);
        }

        // Advanced items
        for (int i = 0; i < NO_OF_UNIQUE_ADVANCED_ITEMS; i++) {
            // Choose between gathering a bunch of rarer items or 1 of a super rare item
            if (random.nextBoolean()) {
                ItemStack randomItem = getRandomRequestable(ADVANCED_REQUESTABLE_ITEMS, MIN_ADVANCED_REQUESTABLE_ITEMS, MAX_ADVANCED_REQUESTABLE_ITEMS, random);
                request.addEntry(randomItem, false);
            } else {
                ItemStack randomItem = getRandomRequestable(ADVANCED_REQUESTABLE_ITEMS_ALTERNATE, 1, random);
                request.addEntry(randomItem, false);
            }
        }

        return request;
    }

    public static ItemStack getRandomRequestable(Tag<Item> tag, int min, int max, Random random) {
        ItemStack itemStack = tag.getRandomElement(random).asItem().getDefaultInstance();
        int randomAmount = random.nextInt(max - min + 1) + min;

        itemStack.setCount(randomAmount);

        return itemStack;
    }

    public static ItemStack getRandomRequestable(Tag<Item> tag, int amount, Random random) {
        ItemStack itemStack = tag.getRandomElement(random).asItem().getDefaultInstance();
        itemStack.setCount(amount);

        return itemStack;
    }
}
