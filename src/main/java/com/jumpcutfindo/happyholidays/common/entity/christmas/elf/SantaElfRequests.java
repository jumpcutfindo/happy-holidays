package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import java.util.List;
import java.util.Random;

import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;
import com.jumpcutfindo.happyholidays.common.utils.TagUtils;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SantaElfRequests {
    public static final long DEFAULT_EXPIRY = 24000;

    public static final int TOTAL_NO_OF_ITEMS = 4;

    public static final int NO_OF_UNIQUE_BASIC_ITEMS = 2;
    public static final int MIN_BASIC_REQUESTABLE_ITEMS = 12;
    public static final int MAX_BASIC_REQUESTABLE_ITEMS = 48;

    public static final int NO_OF_UNIQUE_INTERMEDIATE_ITEMS = 1;
    public static final int MIN_INTERMEDIATE_REQUESTABLE_ITEMS = 6;
    public static final int MAX_INTERMEDIATE_REQUESTABLE_ITEMS = 24;
    public static final TagKey<Item> INTERMEDIATE_REQUESTABLE_ITEMS = ChristmasTags.Items.SANTA_ELF_INTERMEDIATE_REQUESTABLES;

    public static final int NO_OF_UNIQUE_ADVANCED_ITEMS = 1;
    public static final int MIN_ADVANCED_REQUESTABLE_ITEMS = 4;
    public static final int MAX_ADVANCED_REQUESTABLE_ITEMS = 12;
    public static final TagKey<Item> ADVANCED_REQUESTABLE_ITEMS = ChristmasTags.Items.SANTA_ELF_ADVANCED_REQUESTABLES;
    public static final TagKey<Item> ADVANCED_REQUESTABLE_ITEMS_ALTERNATE = ChristmasTags.Items.SANTA_ELF_ADVANCED_REQUESTABLES_ALTERNATE;


    public static SantaElfRequest createRandomRequest(long gameTime) {
        SantaElfRequest request = new SantaElfRequest();
        request.setExpiryTime(gameTime + DEFAULT_EXPIRY);

        Random random = request.random;

        // Basic items
        for (int i = 0; i < NO_OF_UNIQUE_BASIC_ITEMS; i++) {
            ItemStack randomItem = getRandomRequestable(ChristmasTags.Items.SANTA_ELF_BASIC_REQUESTABLES, MIN_BASIC_REQUESTABLE_ITEMS, MAX_BASIC_REQUESTABLE_ITEMS, random);
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

    public static ItemStack getRandomRequestable(TagKey<Item> tag, int min, int max, Random random) {
        List<Item> requestables = TagUtils.itemContents(tag);
        int randomIdx = random.nextInt(requestables.size());
        ItemStack itemStack = requestables.get(randomIdx).asItem().getDefaultInstance();
        int randomAmount = random.nextInt(max - min + 1) + min;

        itemStack.setCount(randomAmount);

        return itemStack;
    }

    public static ItemStack getRandomRequestable(TagKey<Item> tag, int amount, Random random) {
        List<Item> requestables = TagUtils.itemContents(tag);
        int randomIdx = random.nextInt(requestables.size());
        ItemStack itemStack = requestables.get(randomIdx).asItem().getDefaultInstance();
        itemStack.setCount(amount);

        return itemStack;
    }
}
