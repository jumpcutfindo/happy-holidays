package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.world.entity.npc.VillagerTrades;

public class SantaElfTrades {
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
}
