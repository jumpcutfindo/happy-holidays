package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SantaElfRequests {
    public static final long DEFAULT_EXPIRY = 24000;

    public static final int TOTAL_NO_OF_ITEMS = 4;

    public static final int NO_OF_UNIQUE_BASIC_ITEMS = 2;
    public static final int MIN_BASIC_REQUESTABLE_ITEMS = 12;
    public static final int MAX_BASIC_REQUESTABLE_ITEMS = 48;
    public static final Item[] BASIC_REQUESTABLE_ITEMS = new Item[]{
            Items.DANDELION, Items.POPPY, Items.BLUE_ORCHID, Items.ALLIUM, Items.AZURE_BLUET, Items.RED_TULIP,
            Items.ORANGE_TULIP, Items.WHITE_TULIP, Items.PINK_TULIP, Items.OXEYE_DAISY, Items.BROWN_MUSHROOM,
            Items.RED_MUSHROOM, Items.WHEAT, Items.WHEAT_SEEDS, Items.BEETROOT, Items.BEETROOT_SEEDS, Items.CARROT,
            Items.POTATO, Items.ACACIA_SAPLING, Items.BIRCH_SAPLING, Items.SPRUCE_SAPLING, Items.OAK_SAPLING,
            Items.DARK_OAK_SAPLING, Items.JUNGLE_SAPLING, Items.SUGAR_CANE, Items.SUGAR, Items.CACTUS, Items.LILAC,
            Items.LILY_OF_THE_VALLEY, Items.LILY_PAD, Items.ROSE_BUSH, Items.APPLE, Items.SWEET_BERRIES, Items.FLINT,
            Items.ARROW, Items.COAL, Items.CHARCOAL, Items.STRING, Items.FEATHER, Items.STICK, Items.GUNPOWDER,
            Items.BONE,
            Items.REDSTONE, Items.CLAY_BALL, Items.BRICK, Items.PAPER, Items.SLIME_BALL, Items.BLACK_DYE,
            Items.BLUE_DYE,
            Items.BROWN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE, Items.CYAN_DYE, Items.GRAY_DYE,
            Items.GREEN_DYE,
            Items.LIME_DYE, Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.PINK_DYE, Items.PURPLE_DYE, Items.RED_DYE,
            Items.WHITE_DYE, Items.YELLOW_DYE, Items.MELON_SEEDS, Items.MELON_SLICE, Items.PUMPKIN, Items.PUMPKIN_SEEDS,
            Items.IRON_NUGGET, Items.GOLD_NUGGET, Items.ROTTEN_FLESH, Items.SAND, Items.ACACIA_PLANKS,
            Items.BIRCH_PLANKS, Items.OAK_PLANKS, Items.JUNGLE_PLANKS, Items.SPRUCE_PLANKS, Items.GRAVEL, Items.STONE,
            Items.COBBLESTONE, Items.WHITE_WOOL, Items.BLACK_WOOL, Items.BLUE_WOOL, Items.BROWN_WOOL, Items.CYAN_WOOL,
            Items.GRAY_WOOL, Items.GREEN_WOOL, Items.LIGHT_BLUE_WOOL, Items.LIGHT_GRAY_WOOL, Items.LIME_WOOL,
            Items.MAGENTA_WOOL, Items.ORANGE_WOOL, Items.PINK_WOOL, Items.PURPLE_WOOL, Items.RED_WOOL,
            Items.YELLOW_WOOL, Items.ACACIA_LEAVES, Items.BIRCH_LEAVES, Items.OAK_LEAVES, Items.DARK_OAK_LEAVES,
            Items.JUNGLE_LEAVES, Items.SPRUCE_LEAVES, Items.SPIDER_EYE, Items.BAMBOO, Items.KELP, Items.DRIED_KELP,
            Items.CORNFLOWER
    };

    public static final int NO_OF_UNIQUE_INTERMEDIATE_ITEMS = 1;
    public static final int MIN_INTERMEDIATE_REQUESTABLE_ITEMS = 6;
    public static final int MAX_INTERMEDIATE_REQUESTABLE_ITEMS = 24;
    public static final Item[] INTERMEDIATE_REQUESTABLE_ITEMS = new Item[]{
            Items.NETHER_WART, Items.NETHER_BRICK, Items.BLAZE_POWDER, Items.GLOWSTONE_DUST, Items.QUARTZ,
            Items.NETHERRACK, Items.SOUL_SAND, Items.CRIMSON_PLANKS, Items.WARPED_PLANKS,
            Items.WARPED_WART_BLOCK, Items.NETHER_WART_BLOCK, Items.WEEPING_VINES, Items.TWISTING_VINES,
            Items.SHROOMLIGHT, Items.BLACKSTONE, Items.END_STONE, Items.END_ROD, Items.CHORUS_FRUIT,
            Items.POPPED_CHORUS_FRUIT, Items.PURPUR_BLOCK, Items.PRISMARINE_SHARD, Items.PRISMARINE_CRYSTALS
    };

    public static final int NO_OF_UNIQUE_ADVANCED_ITEMS = 1;
    public static final int MIN_ADVANCED_REQUESTABLE_ITEMS = 1;
    public static final int MAX_ADVANCED_REQUESTABLE_ITEMS = 1;
    public static final Item[] ADVANCED_REQUESTABLE_ITEMS = new Item[] {
            Items.SPONGE, Items.DIAMOND, Items.NETHERITE_SCRAP, Items.ENCHANTED_GOLDEN_APPLE, Items.MUSIC_DISC_11,
            Items.MUSIC_DISC_13, Items.MUSIC_DISC_BLOCKS, Items.MUSIC_DISC_CAT, Items.MUSIC_DISC_CHIRP,
            Items.MUSIC_DISC_FAR, Items.MUSIC_DISC_MALL, Items.MUSIC_DISC_MELLOHI, Items.MUSIC_DISC_PIGSTEP,
            Items.MUSIC_DISC_STAL, Items.MUSIC_DISC_STRAD, Items.MUSIC_DISC_WAIT, Items.MUSIC_DISC_WARD,
            Items.END_CRYSTAL, Items.BELL, Items.SHULKER_SHELL
    };

    public static SantaElfRequest createRandomRequest(long gameTime) {
        SantaElfRequest request = new SantaElfRequest();
        request.setExpiryTime(gameTime + DEFAULT_EXPIRY);

        // Basic items
        for (int i = 0; i < NO_OF_UNIQUE_BASIC_ITEMS; i++) {
            Item randomItem =
                    BASIC_REQUESTABLE_ITEMS[request.random.nextInt(BASIC_REQUESTABLE_ITEMS.length)];
            int randomAmount =
                    request.random.nextInt((MAX_BASIC_REQUESTABLE_ITEMS - MIN_BASIC_REQUESTABLE_ITEMS) + 1)
                            + MIN_BASIC_REQUESTABLE_ITEMS;

            ItemStack itemStack = randomItem.getDefaultInstance();
            itemStack.setCount(randomAmount);

            request.addEntry(itemStack, false);
        }

        // Intermediate items
        for (int i = 0; i < NO_OF_UNIQUE_INTERMEDIATE_ITEMS; i++) {
            Item randomItem =
                    INTERMEDIATE_REQUESTABLE_ITEMS[request.random.nextInt(INTERMEDIATE_REQUESTABLE_ITEMS.length)];
            int randomAmount =
                    request.random.nextInt((MAX_INTERMEDIATE_REQUESTABLE_ITEMS - MIN_INTERMEDIATE_REQUESTABLE_ITEMS) + 1)
                            + MIN_INTERMEDIATE_REQUESTABLE_ITEMS;

            ItemStack itemStack = randomItem.getDefaultInstance();
            itemStack.setCount(randomAmount);

            request.addEntry(itemStack, false);
        }

        // Advanced items
        for (int i = 0; i < NO_OF_UNIQUE_ADVANCED_ITEMS; i++) {
            Item randomItem =
                    ADVANCED_REQUESTABLE_ITEMS[request.random.nextInt(ADVANCED_REQUESTABLE_ITEMS.length)];
            int randomAmount =
                    request.random.nextInt((MAX_ADVANCED_REQUESTABLE_ITEMS - MIN_ADVANCED_REQUESTABLE_ITEMS) + 1)
                            + MIN_ADVANCED_REQUESTABLE_ITEMS;

            ItemStack itemStack = randomItem.getDefaultInstance();
            itemStack.setCount(randomAmount);

            request.addEntry(itemStack, false);
        }

        return request;
    }
}
