package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.world.entity.npc.VillagerTrades;

public class SantaElfTrades {
    private static final Random RANDOM = new Random();

    public static final List<VillagerTrades.ItemListing> BASIC_ORNAMENT_TRADES =
            SantaElfEntity.ItemsForEmeraldsTrade.tradesFromTag(ChristmasTags.Items.BASIC_ORNAMENTS, 1, 4, 6, 2);

    public static final List<VillagerTrades.ItemListing> RARE_ORNAMENT_TRADES =
            SantaElfEntity.ItemsForEmeraldsTrade.tradesFromTag(ChristmasTags.Items.RARE_ORNAMENTS, 8, 1, 4, 4);

    public static final List<VillagerTrades.ItemListing> SHEET_MUSIC_TRADES =
            SantaElfEntity.ItemsForEmeraldsTrade.tradesFromTag(ChristmasTags.Items.SHEET_MUSIC, 16, 1, 1, 4);

    public static final List<VillagerTrades.ItemListing> FOOD_TRADES =
            SantaElfEntity.ItemsForEmeraldsTrade.tradesFromTag(ChristmasTags.Items.FOODS, 2, 4, 4, 2);

    public static final List<VillagerTrades.ItemListing> SOMETIMES_APPEAR_TRADES = Lists.newArrayList(
            new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.PRESENT_SCRAPS.get().asItem(), 12, 1, 8, 2)
    );

    public static final List<VillagerTrades.ItemListing> ALWAYS_APPEAR_TRADES = Lists.newArrayList(
            new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.SANTA_LIST.get().asItem(), 4, 1, 1, 4),
            new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SANTA_ELF_BELL.get().asItem(), 64, 1, 1, 8)
    );

    public static final List<VillagerTrades.ItemListing> BUYBACK_BASIC_ORNAMENT_TRADES =
            SantaElfEntity.EmeraldForItemsTrade.tradesFromTag(ChristmasTags.Items.BASIC_ORNAMENTS, 2, 1, 8, 2);

    public static final List<VillagerTrades.ItemListing> BUYBACK_RARE_ORNAMENT_TRADES =
            SantaElfEntity.EmeraldForItemsTrade.tradesFromTag(ChristmasTags.Items.RARE_ORNAMENTS, 1, 2, 8, 2);

    public static final List<VillagerTrades.ItemListing> BUYBACK_DECORATIONS_TRADES =
            SantaElfEntity.EmeraldForItemsTrade.tradesFromTag(ChristmasTags.Items.DECORATIONS, 2, 1, 8, 2);

    public static Set<VillagerTrades.ItemListing> generateBasicTrades(int numTrades) {
        return generateTradesFromListings(BASIC_ORNAMENT_TRADES, numTrades);
    }

    public static Set<VillagerTrades.ItemListing> generateRareTrades(int numTrades) {
        List<VillagerTrades.ItemListing> rareTradeListings = Lists.newArrayList();
        rareTradeListings.addAll(SantaElfTrades.RARE_ORNAMENT_TRADES);
        rareTradeListings.addAll(SantaElfTrades.SHEET_MUSIC_TRADES);

        return generateTradesFromListings(rareTradeListings, numTrades);
    }

    public static Set<VillagerTrades.ItemListing> generateFoodTrades(int numTrades) {
        return generateTradesFromListings(FOOD_TRADES, numTrades);
    }

    public static VillagerTrades.ItemListing generateRequestTrade(SantaElfRequest request) {
        return new SantaElfEntity.ItemsForEmeraldsTrade(request.getRequestPaper(), 1, 1, 1, 2);
    }

    public static Set<VillagerTrades.ItemListing> generateSometimesAppearTrades(int numTrades) {
        return generateTradesFromListings(SOMETIMES_APPEAR_TRADES, numTrades);
    }

    public static Set<VillagerTrades.ItemListing> generateAlwaysAppearTrades() {
        return Sets.newHashSet(ALWAYS_APPEAR_TRADES);
    }

    public static Set<VillagerTrades.ItemListing> generateBuybackTrades(int numTrades) {
        List<VillagerTrades.ItemListing> buybackTrades = Lists.newArrayList();
        buybackTrades.addAll(SantaElfTrades.BUYBACK_BASIC_ORNAMENT_TRADES);
        buybackTrades.addAll(SantaElfTrades.BUYBACK_DECORATIONS_TRADES);
        buybackTrades.addAll(SantaElfTrades.BUYBACK_RARE_ORNAMENT_TRADES);

        return generateTradesFromListings(buybackTrades, numTrades);
    }

    private static Set<VillagerTrades.ItemListing> generateTradesFromListings(List<VillagerTrades.ItemListing> listings, int numTrades) {
        Set<VillagerTrades.ItemListing> set = Sets.newHashSet();
        while (set.size() < numTrades) {
            int randInt = RANDOM.nextInt(listings.size());
            set.add(listings.get(randInt));
        }

        return set;
    }
}
