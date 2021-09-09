package com.jumpcutfindo.happyholidays.common.entity.christmas.elf;

import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;

import net.minecraft.entity.merchant.villager.VillagerTrades;

public class SantaElfTrades {
    public static final VillagerTrades.ITrade[] SMALL_BALL_ORNAMENT_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.RED_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.BLUE_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.YELLOW_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.GREEN_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.GOLD_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.SILVER_BAUBLE.get().asItem(), 2, 4, 2)
            };

    public static final VillagerTrades.ITrade[] BIG_BALL_ORNAMENT_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.BIG_RED_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.BIG_BLUE_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.BIG_YELLOW_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.BIG_GREEN_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.BIG_GOLD_BAUBLE.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.BIG_SILVER_BAUBLE.get().asItem(), 2, 4, 2)
            };

    public static final VillagerTrades.ITrade[] TINSEL_TRADES =
            new VillagerTrades.ITrade[]{
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.RED_TINSEL.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.BLUE_TINSEL.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.YELLOW_TINSEL.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.GREEN_TINSEL.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.GOLD_TINSEL.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.SILVER_TINSEL.get().asItem(), 2, 4, 2)
            };

    public static final VillagerTrades.ITrade[] CHRISTMAS_LIGHT_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.RED_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.BLUE_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.YELLOW_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.GREEN_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.GOLD_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.SILVER_CHRISTMAS_LIGHTS.get().asItem(), 2, 4, 2)
            };

    public static final VillagerTrades.ITrade[] HEAD_ORNAMENT_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.CREEPER_HEAD_ORNAMENT_BLOCK_ITEM.get().asItem(), 16, 1, 2, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.ZOMBIE_HEAD_ORNAMENT_BLOCK_ITEM.get().asItem(), 16, 1, 2, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SKELETON_HEAD_ORNAMENT_BLOCK_ITEM.get().asItem(), 16, 1, 2, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.WITHER_SKELETON_HEAD_ORNAMENT_BLOCK_ITEM.get().asItem(), 16, 1, 2, 4),
            };

    public static final VillagerTrades.ITrade[] SHEET_MUSIC_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_ANGELS_ON_HIGH.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_CAROL_OF_THE_BELLS.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_DECK_THE_HALLS.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_FROSTY_THE_SNOWMAN.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_GOD_REST_GENTLEMEN.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_HERE_COMES_SANTA.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_JINGLE_BELL_ROCK.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_JINGLE_BELLS.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_JOY_TO_THE_WORLD.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_RUDOLPH.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_SILENT_NIGHT.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_SLEIGH_RIDE.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_THE_FIRST_NOEL.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_WE_THREE_KINGS.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_WE_WISH_YOU.get().asItem(), 24, 1, 1, 8),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SHEET_MUSIC_WHITE_CHRISTMAS.get().asItem(), 24, 1, 1, 8)
            };

    public static final VillagerTrades.ITrade[] FOOD_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.CANDY_CANE.get().asItem(), 1,4, 24, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.FESTIVE_CANDY_CANE.get().asItem(), 1,4, 24, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.EGGNOG.get().asItem(), 1, 1, 4, 2),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.LOG_CAKE_BLOCK.get().asItem(), 4, 1, 4, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.CHRISTMAS_HAM_BLOCK.get().asItem(), 4, 1, 4, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.CHRISTMAS_PUDDING_BLOCK.get().asItem(), 4, 1, 4, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.MILK_AND_COOKIES_BLOCK.get().asItem(), 4, 1, 4, 4)
            };

    public static final VillagerTrades.ITrade[] SOMETIMES_APPEAR_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.PRESENT_SCRAPS.get().asItem(), 12, 1, 8, 2)
            };

    public static final VillagerTrades.ITrade[] ALWAYS_APPEAR_TRADES =
            new VillagerTrades.ITrade[] {
                    new SantaElfEntity.ItemsForEmeraldsTrade(BlockRegistry.SANTA_LIST_BLOCK.get().asItem(), 4, 1, 1, 4),
                    new SantaElfEntity.ItemsForEmeraldsTrade(ItemRegistry.SANTA_ELF_BELL.get().asItem(), 64, 1, 1, 8)
            };
}
