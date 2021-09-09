package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.entity.merchant.villager.VillagerTrades;

public class SantaElfTrades {
    public static final VillagerTrades.ITrade[] SMALL_BALL_ORNAMENT_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.RED_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.BLUE_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.YELLOW_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.GREEN_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.GOLD_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.SILVER_BAUBLE.get().asItem(), 2, 4, 2)
            };

    public static final VillagerTrades.ITrade[] BIG_BALL_ORNAMENT_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.BIG_RED_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.BIG_BLUE_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.BIG_YELLOW_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.BIG_GREEN_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.BIG_GOLD_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.BIG_SILVER_BAUBLE.get().asItem(), 2, 4, 2)
            };

    public static final VillagerTrades.ITrade[] TINSEL_TRADES =
            new VillagerTrades.ITrade[]{
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.RED_TINSEL.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.BLUE_TINSEL.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.YELLOW_TINSEL.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.GREEN_TINSEL.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.GOLD_TINSEL.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.SILVER_TINSEL.get().asItem(), 2, 4, 2)
            };

    public static final VillagerTrades.ITrade[] CHRISTMAS_LIGHT_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.RED_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2)
            };

    public static final VillagerTrades.ITrade[] HEAD_ORNAMENT_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.CREEPER_HEAD_ORNAMENT.get().asItem(), 16, 1, 2, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.ZOMBIE_HEAD_ORNAMENT.get().asItem(), 16, 1, 2, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SKELETON_HEAD_ORNAMENT.get().asItem(), 16, 1, 2, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.WITHER_SKELETON_HEAD_ORNAMENT.get().asItem(), 16, 1, 2, 4),
            };

    public static final VillagerTrades.ITrade[] SHEET_MUSIC_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_ANGELS_ON_HIGH.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_CAROL_OF_THE_BELLS.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_DECK_THE_HALLS.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_FROSTY_THE_SNOWMAN.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_GOD_REST_GENTLEMEN.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_HERE_COMES_SANTA.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_JINGLE_BELL_ROCK.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_JINGLE_BELLS.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_JOY_TO_THE_WORLD.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_RUDOLPH.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_SILENT_NIGHT.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_SLEIGH_RIDE.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_THE_FIRST_NOEL.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_WE_THREE_KINGS.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_WE_WISH_YOU.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SHEET_MUSIC_WHITE_CHRISTMAS.get().asItem(), 24, 1, 1, 8)
            };

    public static final VillagerTrades.ITrade[] FOOD_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.CANDY_CANE.get().asItem(), 1,4, 24, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.FESTIVE_CANDY_CANE.get().asItem(), 1,4, 24, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.EGGNOG.get().asItem(), 1, 1, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.LOG_CAKE.get().asItem(), 4, 1, 4, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.CHRISTMAS_HAM.get().asItem(), 4, 1, 4, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.CHRISTMAS_PUDDING.get().asItem(), 4, 1, 4, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.MILK_AND_COOKIES.get().asItem(), 4, 1, 4, 4)
            };

    public static final VillagerTrades.ITrade[] SOMETIMES_APPEAR_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.PRESENT_SCRAPS.get().asItem(), 12, 1, 8, 2)
            };

    public static final VillagerTrades.ITrade[] ALWAYS_APPEAR_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasBlocks.SANTA_LIST.get().asItem(), 4, 1, 1, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ChristmasItems.SANTA_ELF_BELL.get().asItem(), 64, 1, 1, 8)
            };
}
