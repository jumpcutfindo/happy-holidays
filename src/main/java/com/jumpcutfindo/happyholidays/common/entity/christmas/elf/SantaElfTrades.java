package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Sets;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.world.entity.npc.VillagerTrades;

public class SantaElfTrades {
    private static final Random RANDOM = new Random();

    public static final VillagerTrades.ItemListing[] BASIC_ORNAMENT_TRADES =
            SantaElfEntity.ItemsForEmeraldsTrade.tradesFromTag(ChristmasTags.Items.BASIC_ORNAMENTS, 1, 4, 6, 2);

    public static final VillagerTrades.ItemListing[] RARE_ORNAMENT_TRADES =
            SantaElfEntity.ItemsForEmeraldsTrade.tradesFromTag(ChristmasTags.Items.RARE_ORNAMENTS, 8, 1, 4, 4);

    public static final VillagerTrades.ItemListing[] SHEET_MUSIC_TRADES =
            SantaElfEntity.ItemsForEmeraldsTrade.tradesFromTag(ChristmasTags.Items.SHEET_MUSIC, 16, 1, 1, 4);

    public static final VillagerTrades.ItemListing[] FOOD_TRADES =
            SantaElfEntity.ItemsForEmeraldsTrade.tradesFromTag(ChristmasTags.Items.FOODS, 2, 4, 4, 2);

    public static final VillagerTrades.ItemListing[] SOMETIMES_APPEAR_TRADES =
            new VillagerTrades.ItemListing[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.PRESENT_SCRAPS.get().asItem(), 12, 1, 8, 2)
            };

    public static final VillagerTrades.ItemListing[] ALWAYS_APPEAR_TRADES =
            new VillagerTrades.ItemListing[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.SANTA_LIST.get().asItem(), 4, 1, 1, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SANTA_ELF_BELL.get().asItem(), 64, 1, 1, 8)
            };

    public static final VillagerTrades.ItemListing[] BUYBACK_TRADES =
            ArrayUtils.addAll(
                SantaElfEntity.EmeraldForItemsTrade.tradesFromTag(ChristmasTags.Items.BASIC_ORNAMENTS, 2, 1, 8, 2),
                SantaElfEntity.EmeraldForItemsTrade.tradesFromTag(ChristmasTags.Items.RARE_ORNAMENTS, 1, 2, 8, 2)
            );

    public static Set<VillagerTrades.ItemListing> generateBasicTrades(int numTrades) {
        return generateTradesFromListings(BASIC_ORNAMENT_TRADES, numTrades);
    }

    public static Set<VillagerTrades.ItemListing> generateRareTrades(int numTrades) {
        VillagerTrades.ItemListing[] rareTradeListings = ArrayUtils.addAll(SantaElfTrades.RARE_ORNAMENT_TRADES, SantaElfTrades.SHEET_MUSIC_TRADES);

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
        return generateTradesFromListings(BUYBACK_TRADES, numTrades);
    }

    private static Set<VillagerTrades.ItemListing> generateTradesFromListings(VillagerTrades.ItemListing[] listings, int numTrades) {
        Set<VillagerTrades.ItemListing> set = Sets.newHashSet();
        while (set.size() < numTrades) {
            int randInt = RANDOM.nextInt(listings.length);
            set.add(listings[randInt]);
        }

        return set;
    }
}
