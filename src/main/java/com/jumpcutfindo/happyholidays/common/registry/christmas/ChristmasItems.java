package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.BaseGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.CandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasStarBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.FestiveCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.GiftWrapperBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.MusicBoxBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.PresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.AdultPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.AlphabetOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.BabyPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.BasicOrnament;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.BaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.BigBaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.CandyCaneOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ChristmasBellBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ChristmasWreathBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ElderPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.FrostBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.GingerbreadManOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.GrinchOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.HeadOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.LegendaryOrnament;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.NutcrackerOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.RareOrnament;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.SantaElfOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.SantaListBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.StockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.TinselBlock;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.candy.EnchantedCandyCaneItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.dye.DyeBowlItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.elf.SantaElfBellItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.elf.ToyPartsRequestItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.EggnogItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.ChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gingerbread.RawGingerbreadItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.ChristmasGuideBookItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.EnchantedSantaHatItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.MistletoeAndHollyItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.PresentScrapItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.SnowGlobeItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.nutcracker.PatrolOrdersItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.nutcracker.SwaggerStickItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.ChristmasOutfits;
import com.jumpcutfindo.happyholidays.common.item.christmas.thread.EnchantedThreadItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.thread.ThreadItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.walnut.WalnutItem;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;
import com.jumpcutfindo.happyholidays.common.utils.ColourUtils;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            HappyHolidaysMod.MOD_ID
    );

    // Default item properties
    private static final Item.Properties BIG_FOOD_PROPS = new Item.Properties().stacksTo(16).tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    // ================== ITEMS ==================
    public static final RegistryObject<ChristmasItem> RAW_GINGERBREAD =
            ITEMS.register("gingerbread_dough", RawGingerbreadItem::new);
    public static final RegistryObject<Item> GINGERBREAD_COOKIE =
            registerFood("gingerbread_cookie", new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP).food(new FoodProperties.Builder().nutrition(2).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1), 0.5f).saturationMod(0.1f).alwaysEat().build()));
    public static final RegistryObject<ChristmasItem> PRESENT_SCRAPS =
            ITEMS.register("present_scraps", PresentScrapItem::new);
    public static final RegistryObject<ChristmasItem> HOLLY =
            ITEMS.register("mistletoe_and_holly", MistletoeAndHollyItem::new);
    public static final RegistryObject<ChristmasItem> SANTA_ELF_BELL =
            ITEMS.register("santa_elf_bell", SantaElfBellItem::new);
    public static final RegistryObject<ChristmasItem> TOY_PARTS_REQUEST =
            ITEMS.register("toy_parts_request", ToyPartsRequestItem::new);
    public static final RegistryObject<ChristmasItem> CHRISTMAS_GUIDE_BOOK =
            ITEMS.register("christmas_guide_book", ChristmasGuideBookItem::new);
    public static final RegistryObject<ChristmasItem> SNOW_GLOBE =
            ITEMS.register("snow_globe", SnowGlobeItem::new);

    public static final RegistryObject<ChristmasItem> THREAD =
            ITEMS.register("thread", ThreadItem::new);
    public static final RegistryObject<ChristmasItem> ENCHANTED_THREAD =
            ITEMS.register("enchanted_thread", EnchantedThreadItem::new);

    public static final RegistryObject<BlockItem> WALNUT =
            ITEMS.register("walnut", () -> new ItemNameBlockItem(ChristmasBlocks.WALNUT_PLANT.get(), WalnutItem.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasItem> EXPLOSIVE_WALNUT =
            ITEMS.register("explosive_walnut", WalnutItem::new);
    public static final RegistryObject<ChristmasItem> SUGARED_WALNUT =
            ITEMS.register("sugared_walnut", WalnutItem::new);
    public static final RegistryObject<ChristmasItem> METALLIC_WALNUT =
            ITEMS.register("metallic_walnut", WalnutItem::new);
    public static final RegistryObject<ChristmasItem> HALVED_WALNUT =
            ITEMS.register("halved_walnut", WalnutItem::new);

    public static final RegistryObject<Item> SWAGGER_STICK =
            ITEMS.register("swagger_stick", SwaggerStickItem::new);
    public static final RegistryObject<Item> PATROL_ORDERS =
            ITEMS.register("patrol_orders", PatrolOrdersItem::new);

    public static final RegistryObject<ChristmasItem> ENCHANTED_SANTA_HAT =
            ITEMS.register("enchanted_santa_hat", EnchantedSantaHatItem::new);
    public static final RegistryObject<Item> SANTA_HAT =
            ITEMS.register(ChristmasOutfits.SANTA_OUTFIT.getHeadpieceId(), ChristmasOutfits.SANTA_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> SANTA_TOP =
            ITEMS.register(ChristmasOutfits.SANTA_OUTFIT.getTopId(), ChristmasOutfits.SANTA_OUTFIT::getTop);
    public static final RegistryObject<Item> SANTA_BOTTOM =
            ITEMS.register(ChristmasOutfits.SANTA_OUTFIT.getBottomId(), ChristmasOutfits.SANTA_OUTFIT::getBottom);
    public static final RegistryObject<Item> SANTA_BOOTS =
            ITEMS.register(ChristmasOutfits.SANTA_OUTFIT.getFeetId(), ChristmasOutfits.SANTA_OUTFIT::getFeet);

    public static final RegistryObject<Item> SANTA_ELF_HAT =
            ITEMS.register(ChristmasOutfits.SANTA_ELF_OUTFIT.getHeadpieceId(), ChristmasOutfits.SANTA_ELF_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> SANTA_ELF_TOP =
            ITEMS.register(ChristmasOutfits.SANTA_ELF_OUTFIT.getTopId(), ChristmasOutfits.SANTA_ELF_OUTFIT::getTop);
    public static final RegistryObject<Item> SANTA_ELF_BOTTOM =
            ITEMS.register(ChristmasOutfits.SANTA_ELF_OUTFIT.getBottomId(), ChristmasOutfits.SANTA_ELF_OUTFIT::getBottom);
    public static final RegistryObject<Item> SANTA_ELF_BOOTS =
            ITEMS.register(ChristmasOutfits.SANTA_ELF_OUTFIT.getFeetId(), ChristmasOutfits.SANTA_ELF_OUTFIT::getFeet);

    public static final RegistryObject<Item> SNOWMAN_HEADPIECE =
            ITEMS.register(ChristmasOutfits.SNOWMAN_OUTFIT.getHeadpieceId(), ChristmasOutfits.SNOWMAN_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> SNOWMAN_TOP =
            ITEMS.register(ChristmasOutfits.SNOWMAN_OUTFIT.getTopId(), ChristmasOutfits.SNOWMAN_OUTFIT::getTop);
    public static final RegistryObject<Item> SNOWMAN_BOTTOM =
            ITEMS.register(ChristmasOutfits.SNOWMAN_OUTFIT.getBottomId(), ChristmasOutfits.SNOWMAN_OUTFIT::getBottom);

    public static final RegistryObject<Item> CANDY_CANE_HEADPIECE =
            ITEMS.register(ChristmasOutfits.CANDY_CANE_OUTFIT.getHeadpieceId(), ChristmasOutfits.CANDY_CANE_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> CANDY_CANE_TOP =
            ITEMS.register(ChristmasOutfits.CANDY_CANE_OUTFIT.getTopId(), ChristmasOutfits.CANDY_CANE_OUTFIT::getTop);
    public static final RegistryObject<Item> CANDY_CANE_BOTTOM =
            ITEMS.register(ChristmasOutfits.CANDY_CANE_OUTFIT.getBottomId(), ChristmasOutfits.CANDY_CANE_OUTFIT::getBottom);
    public static final RegistryObject<Item> CANDY_CANE_BOOTS =
            ITEMS.register(ChristmasOutfits.CANDY_CANE_OUTFIT.getFeetId(), ChristmasOutfits.CANDY_CANE_OUTFIT::getFeet);

    public static final RegistryObject<Item> REINDEER_HEADPIECE =
            ITEMS.register(ChristmasOutfits.REINDEER_OUTFIT.getHeadpieceId(), ChristmasOutfits.REINDEER_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> REINDEER_TOP =
            ITEMS.register(ChristmasOutfits.REINDEER_OUTFIT.getTopId(), ChristmasOutfits.REINDEER_OUTFIT::getTop);
    public static final RegistryObject<Item> REINDEER_BOTTOM =
            ITEMS.register(ChristmasOutfits.REINDEER_OUTFIT.getBottomId(), ChristmasOutfits.REINDEER_OUTFIT::getBottom);
    public static final RegistryObject<Item> REINDEER_BOOTS =
            ITEMS.register(ChristmasOutfits.REINDEER_OUTFIT.getFeetId(), ChristmasOutfits.REINDEER_OUTFIT::getFeet);

    public static final RegistryObject<Item> NUTCRACKER_HEADPIECE =
            ITEMS.register(ChristmasOutfits.NUTCRACKER_OUTFIT.getHeadpieceId(), ChristmasOutfits.NUTCRACKER_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> NUTCRACKER_TOP =
            ITEMS.register(ChristmasOutfits.NUTCRACKER_OUTFIT.getTopId(), ChristmasOutfits.NUTCRACKER_OUTFIT::getTop);
    public static final RegistryObject<Item> NUTCRACKER_BOTTOM =
            ITEMS.register(ChristmasOutfits.NUTCRACKER_OUTFIT.getBottomId(), ChristmasOutfits.NUTCRACKER_OUTFIT::getBottom);
    public static final RegistryObject<Item> NUTCRACKER_BOOTS =
            ITEMS.register(ChristmasOutfits.NUTCRACKER_OUTFIT.getFeetId(), ChristmasOutfits.NUTCRACKER_OUTFIT::getFeet);

    public static final RegistryObject<Item> GINGERBREAD_HEADPIECE =
            ITEMS.register(ChristmasOutfits.GINGERBREAD_OUTFIT.getHeadpieceId(), ChristmasOutfits.GINGERBREAD_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> GINGERBREAD_TOP =
            ITEMS.register(ChristmasOutfits.GINGERBREAD_OUTFIT.getTopId(), ChristmasOutfits.GINGERBREAD_OUTFIT::getTop);
    public static final RegistryObject<Item> GINGERBREAD_BOTTOM =
            ITEMS.register(ChristmasOutfits.GINGERBREAD_OUTFIT.getBottomId(), ChristmasOutfits.GINGERBREAD_OUTFIT::getBottom);
    public static final RegistryObject<Item> GINGERBREAD_BOOTS =
            ITEMS.register(ChristmasOutfits.GINGERBREAD_OUTFIT.getFeetId(), ChristmasOutfits.GINGERBREAD_OUTFIT::getFeet);

    public static final RegistryObject<ChristmasItem> DYE_BOWL =
            ITEMS.register("dye_bowl", DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> RED_CHRISTMAS_DYE =
            ITEMS.register("red_christmas_dye", DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> BLUE_CHRISTMAS_DYE =
            ITEMS.register("blue_christmas_dye", DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> YELLOW_CHRISTMAS_DYE =
            ITEMS.register("yellow_christmas_dye", DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> GREEN_CHRISTMAS_DYE =
            ITEMS.register("green_christmas_dye", DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> GOLD_CHRISTMAS_DYE =
            ITEMS.register("gold_christmas_dye", DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> SILVER_CHRISTMAS_DYE =
            ITEMS.register("silver_christmas_dye", DyeBowlItem::new);

    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_ANGELS_ON_HIGH =
            ITEMS.register("christmas_sheet_music_angels_on_high", () -> new SheetMusicItem().setMusic(ChristmasMusic.ANGELS_ON_HIGH));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_CAROL_OF_THE_BELLS =
            ITEMS.register("christmas_sheet_music_carol_of_the_bells", () -> new SheetMusicItem().setMusic(ChristmasMusic.CAROL_OF_THE_BELLS));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_DECK_THE_HALLS =
            ITEMS.register("christmas_sheet_music_deck_the_halls", () -> new SheetMusicItem().setMusic(ChristmasMusic.DECK_THE_HALLS));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_FROSTY_THE_SNOWMAN =
            ITEMS.register("christmas_sheet_music_frosty_the_snowman", () -> new SheetMusicItem().setMusic(ChristmasMusic.FROSTY_THE_SNOWMAN));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_GOD_REST_GENTLEMEN =
            ITEMS.register("christmas_sheet_music_god_rest_gentlemen", () -> new SheetMusicItem().setMusic(ChristmasMusic.GOD_REST_GENTLEMEN));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_HERE_COMES_SANTA =
            ITEMS.register("christmas_sheet_music_here_comes_santa", () -> new SheetMusicItem().setMusic(ChristmasMusic.HERE_COMES_SANTA));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_JINGLE_BELL_ROCK =
            ITEMS.register("christmas_sheet_music_jingle_bell_rock", () -> new SheetMusicItem().setMusic(ChristmasMusic.JINGLE_BELL_ROCK));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_JINGLE_BELLS =
            ITEMS.register("christmas_sheet_music_jingle_bells", () -> new SheetMusicItem().setMusic(ChristmasMusic.JINGLE_BELLS));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_JOY_TO_THE_WORLD =
            ITEMS.register("christmas_sheet_music_joy_to_the_world", () -> new SheetMusicItem().setMusic(ChristmasMusic.JOY_TO_THE_WORLD));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_RUDOLPH =
            ITEMS.register("christmas_sheet_music_rudolph", () -> new SheetMusicItem().setMusic(ChristmasMusic.RUDOLPH));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_SILENT_NIGHT =
            ITEMS.register("christmas_sheet_music_silent_night", () -> new SheetMusicItem().setMusic(ChristmasMusic.SILENT_NIGHT));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_SLEIGH_RIDE =
            ITEMS.register("christmas_sheet_music_sleigh_ride", () -> new SheetMusicItem().setMusic(ChristmasMusic.SLEIGH_RIDE));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_THE_FIRST_NOEL =
            ITEMS.register("christmas_sheet_music_the_first_noel", () -> new SheetMusicItem().setMusic(ChristmasMusic.THE_FIRST_NOEL));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_WE_THREE_KINGS =
            ITEMS.register("christmas_sheet_music_we_three_kings", () -> new SheetMusicItem().setMusic(ChristmasMusic.WE_THREE_KINGS));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_WE_WISH_YOU =
            ITEMS.register("christmas_sheet_music_we_wish_you", () -> new SheetMusicItem().setMusic(ChristmasMusic.WE_WISH_YOU));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_WHITE_CHRISTMAS =
            ITEMS.register("christmas_sheet_music_white_christmas", () -> new SheetMusicItem().setMusic(ChristmasMusic.WHITE_CHRISTMAS));

    public static final RegistryObject<Item> CANDY_CANE =
            registerFood("candy_cane", new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP).food(new FoodProperties.Builder().nutrition(2).effect(() -> new MobEffectInstance(MobEffects.JUMP, 100, 1), 0.5f).saturationMod(0.1f).alwaysEat().build()));
    public static final RegistryObject<Item> FESTIVE_CANDY_CANE =
            registerFood("festive_candy_cane", new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP).food(new FoodProperties.Builder().nutrition(2).effect(() -> new MobEffectInstance(MobEffects.JUMP, 100, 1), 0.5f).saturationMod(0.1f).alwaysEat().build()));
    public static final RegistryObject<ChristmasItem> ENCHANTED_CANDY_CANE =
            ITEMS.register("enchanted_candy_cane", EnchantedCandyCaneItem::new);

    public static final RegistryObject<Item> EGGNOG =
            ITEMS.register("eggnog", EggnogItem::new);

    public static final RegistryObject<ChristmasItem> RED_CHRISTMAS_GIFT_ITEM =
            ITEMS.register("red_christmas_gift", ChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> BLUE_CHRISTMAS_GIFT_ITEM =
            ITEMS.register("blue_christmas_gift", ChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> YELLOW_CHRISTMAS_GIFT_ITEM =
            ITEMS.register("yellow_christmas_gift", ChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> GREEN_CHRISTMAS_GIFT_ITEM =
            ITEMS.register("green_christmas_gift", ChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> GOLD_CHRISTMAS_GIFT_ITEM =
            ITEMS.register("gold_christmas_gift", ChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> SILVER_CHRISTMAS_GIFT_ITEM =
            ITEMS.register("silver_christmas_gift", ChristmasGiftItem::new);

    public static final RegistryObject<Item> SANTA_ELF_SPAWN_EGG =
            ITEMS.register("santa_elf_spawn_egg", spawnEggOf(ChristmasEntities.SANTA_ELF::get, ColourUtils.rgbaToInt(2, 126, 16, 255), ColourUtils.rgbaToInt(255, 2, 2, 255)));
    public static final RegistryObject<Item> GINGERBREAD_MAN_SPAWN_EGG =
            ITEMS.register("gingerbread_man_spawn_egg", spawnEggOf(ChristmasEntities.GINGERBREAD_MAN::get, ColourUtils.rgbaToInt(123, 76, 22, 255), ColourUtils.rgbaToInt(255, 255, 255, 255)));
    public static final RegistryObject<Item> GRINCH_SPAWN_EGG =
            ITEMS.register("grinch_spawn_egg", spawnEggOf(ChristmasEntities.GRINCH::get, ColourUtils.rgbaToInt(43, 178, 0, 255), ColourUtils.rgbaToInt(255, 216, 0, 255)));
    public static final RegistryObject<Item> NUTCRACKER_SPAWN_EGG =
            ITEMS.register("nutcracker_spawn_egg", spawnEggOf(ChristmasEntities.NUTCRACKER::get, ColourUtils.rgbaToInt(206, 0, 0, 255), ColourUtils.rgbaToInt(0, 0, 0, 255)));
    public static final RegistryObject<Item> HAPPY_SANTA_SPAWN_EGG =
            ITEMS.register("happy_santa_spawn_egg", spawnEggOf(ChristmasEntities.HAPPY_SANTA::get, ColourUtils.rgbaToInt(242, 0, 0, 255), ColourUtils.rgbaToInt(255, 255, 255, 255)));
    public static final RegistryObject<Item> ANGRY_SANTA_SPAWN_EGG =
            ITEMS.register("angry_santa_spawn_egg", spawnEggOf(ChristmasEntities.ANGRY_SANTA::get, ColourUtils.rgbaToInt(112, 24, 31, 255), ColourUtils.rgbaToInt(255, 255, 255, 255)));

    // ================== BLOCK ITEMS ==================
    public static final RegistryObject<ChristmasBlockItem> BABY_PRESENT =
            ITEMS.register("baby_present", blockItemOf(ChristmasBlocks.BABY_PRESENT, PresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ADULT_PRESENT =
            ITEMS.register("adult_present", blockItemOf(ChristmasBlocks.ADULT_PRESENT, PresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ELDER_PRESENT =
            ITEMS.register("elder_present", blockItemOf(ChristmasBlocks.ELDER_PRESENT, PresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> EXPLOSIVE_PRESENT =
            ITEMS.register("explosive_present", blockItemOf(ChristmasBlocks.EXPLOSIVE_PRESENT, PresentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_BAUBLE =
            ITEMS.register("red_bauble", blockItemOf(ChristmasBlocks.RED_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_BAUBLE =
            ITEMS.register("blue_bauble", blockItemOf(ChristmasBlocks.BLUE_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_BAUBLE =
            ITEMS.register("yellow_bauble", blockItemOf(ChristmasBlocks.YELLOW_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_BAUBLE =
            ITEMS.register("green_bauble", blockItemOf(ChristmasBlocks.GREEN_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_BAUBLE =
            ITEMS.register("gold_bauble", blockItemOf(ChristmasBlocks.GOLD_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_BAUBLE =
            ITEMS.register("silver_bauble", blockItemOf(ChristmasBlocks.SILVER_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> BIG_RED_BAUBLE =
            ITEMS.register("big_red_bauble", blockItemOf(ChristmasBlocks.BIG_RED_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_BLUE_BAUBLE =
            ITEMS.register("big_blue_bauble", blockItemOf(ChristmasBlocks.BIG_BLUE_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_YELLOW_BAUBLE =
            ITEMS.register("big_yellow_bauble", blockItemOf(ChristmasBlocks.BIG_YELLOW_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_GREEN_BAUBLE =
            ITEMS.register("big_green_bauble", blockItemOf(ChristmasBlocks.BIG_GREEN_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_GOLD_BAUBLE =
            ITEMS.register("big_gold_bauble", blockItemOf(ChristmasBlocks.BIG_GOLD_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_SILVER_BAUBLE =
            ITEMS.register("big_silver_bauble", blockItemOf(ChristmasBlocks.BIG_SILVER_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_TINSEL =
            ITEMS.register("red_tinsel", blockItemOf(ChristmasBlocks.RED_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_TINSEL =
            ITEMS.register("blue_tinsel", blockItemOf(ChristmasBlocks.BLUE_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_TINSEL =
            ITEMS.register("yellow_tinsel", blockItemOf(ChristmasBlocks.YELLOW_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_TINSEL =
            ITEMS.register("green_tinsel", blockItemOf(ChristmasBlocks.GREEN_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_TINSEL =
            ITEMS.register("gold_tinsel", blockItemOf(ChristmasBlocks.GOLD_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_TINSEL =
            ITEMS.register("silver_tinsel", blockItemOf(ChristmasBlocks.SILVER_TINSEL, TinselBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_CHRISTMAS_LIGHTS =
            ITEMS.register("red_christmas_lights", blockItemOf(ChristmasBlocks.RED_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_CHRISTMAS_LIGHTS =
            ITEMS.register("blue_christmas_lights", blockItemOf(ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_CHRISTMAS_LIGHTS =
            ITEMS.register("yellow_christmas_lights", blockItemOf(ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_CHRISTMAS_LIGHTS =
            ITEMS.register("green_christmas_lights", blockItemOf(ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_CHRISTMAS_LIGHTS =
            ITEMS.register("gold_christmas_lights", blockItemOf(ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_CHRISTMAS_LIGHTS =
            ITEMS.register("silver_christmas_lights", blockItemOf(ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_CHRISTMAS_BELLS =
            ITEMS.register("red_christmas_bells", blockItemOf(ChristmasBlocks.RED_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_CHRISTMAS_BELLS =
            ITEMS.register("blue_christmas_bells", blockItemOf(ChristmasBlocks.BLUE_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_CHRISTMAS_BELLS =
            ITEMS.register("yellow_christmas_bells", blockItemOf(ChristmasBlocks.YELLOW_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_CHRISTMAS_BELLS =
            ITEMS.register("green_christmas_bells", blockItemOf(ChristmasBlocks.GREEN_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_CHRISTMAS_BELLS =
            ITEMS.register("gold_christmas_bells", blockItemOf(ChristmasBlocks.GOLD_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_CHRISTMAS_BELLS =
            ITEMS.register("silver_christmas_bells", blockItemOf(ChristmasBlocks.SILVER_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CREEPER_HEAD_ORNAMENT =
            ITEMS.register("creeper_head_ornament", blockItemOf(ChristmasBlocks.CREEPER_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SKELETON_HEAD_ORNAMENT =
            ITEMS.register("skeleton_head_ornament", blockItemOf(ChristmasBlocks.SKELETON_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> WITHER_SKELETON_HEAD_ORNAMENT =
            ITEMS.register("wither_skeleton_head_ornament", blockItemOf(ChristmasBlocks.WITHER_SKELETON_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ZOMBIE_HEAD_ORNAMENT =
            ITEMS.register("zombie_head_ornament", blockItemOf(ChristmasBlocks.ZOMBIE_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> DROWNED_HEAD_ORNAMENT =
            ITEMS.register("drowned_head_ornament", blockItemOf(ChristmasBlocks.DROWNED_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLAZE_HEAD_ORNAMENT =
            ITEMS.register("blaze_head_ornament", blockItemOf(ChristmasBlocks.BLAZE_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GHAST_HEAD_ORNAMENT =
            ITEMS.register("ghast_head_ornament", blockItemOf(ChristmasBlocks.GHAST_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> PHANTOM_HEAD_ORNAMENT =
            ITEMS.register("phantom_head_ornament", blockItemOf(ChristmasBlocks.PHANTOM_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> PIG_HEAD_ORNAMENT =
            ITEMS.register("pig_head_ornament", blockItemOf(ChristmasBlocks.PIG_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> COW_HEAD_ORNAMENT =
            ITEMS.register("cow_head_ornament", blockItemOf(ChristmasBlocks.COW_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CHICKEN_HEAD_ORNAMENT =
            ITEMS.register("chicken_head_ornament", blockItemOf(ChristmasBlocks.CHICKEN_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SHEEP_HEAD_ORNAMENT =
            ITEMS.register("sheep_head_ornament", blockItemOf(ChristmasBlocks.SHEEP_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_STOCKING =
            ITEMS.register("red_stocking", blockItemOf(ChristmasBlocks.RED_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_STOCKING =
            ITEMS.register("blue_stocking", blockItemOf(ChristmasBlocks.BLUE_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_STOCKING =
            ITEMS.register("yellow_stocking", blockItemOf(ChristmasBlocks.YELLOW_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_STOCKING =
            ITEMS.register("green_stocking", blockItemOf(ChristmasBlocks.GREEN_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_STOCKING =
            ITEMS.register("gold_stocking", blockItemOf(ChristmasBlocks.GOLD_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_STOCKING =
            ITEMS.register("silver_stocking", blockItemOf(ChristmasBlocks.SILVER_STOCKING, StockingBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_TEMPLATE =
            ITEMS.register("alphabet_ornament_template", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_A =
            ITEMS.register("alphabet_ornament_a", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_A, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_B =
            ITEMS.register("alphabet_ornament_b", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_B, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_C =
            ITEMS.register("alphabet_ornament_c", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_C, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_D =
            ITEMS.register("alphabet_ornament_d", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_D, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_E =
            ITEMS.register("alphabet_ornament_e", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_E, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_F =
            ITEMS.register("alphabet_ornament_f", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_F, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_G =
            ITEMS.register("alphabet_ornament_g", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_G, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_H =
            ITEMS.register("alphabet_ornament_h", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_H, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_I =
            ITEMS.register("alphabet_ornament_i", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_I, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_J =
            ITEMS.register("alphabet_ornament_j", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_J, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_K =
            ITEMS.register("alphabet_ornament_k", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_K, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_L =
            ITEMS.register("alphabet_ornament_l", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_L, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_M =
            ITEMS.register("alphabet_ornament_m", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_M, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_N =
            ITEMS.register("alphabet_ornament_n", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_N, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_O =
            ITEMS.register("alphabet_ornament_o", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_O, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_P =
            ITEMS.register("alphabet_ornament_p", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_P, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_Q =
            ITEMS.register("alphabet_ornament_q", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_Q, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_R =
            ITEMS.register("alphabet_ornament_r", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_R, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_S =
            ITEMS.register("alphabet_ornament_s", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_S, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_T =
            ITEMS.register("alphabet_ornament_t", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_T, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_U =
            ITEMS.register("alphabet_ornament_u", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_U, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_V =
            ITEMS.register("alphabet_ornament_v", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_V, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_W =
            ITEMS.register("alphabet_ornament_w", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_W, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_X =
            ITEMS.register("alphabet_ornament_x", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_X, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_Y =
            ITEMS.register("alphabet_ornament_y", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_Y, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_Z =
            ITEMS.register("alphabet_ornament_z", blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_Z, AlphabetOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_WREATH =
            ITEMS.register("christmas_wreath", blockItemOf(ChristmasBlocks.CHRISTMAS_WREATH, ChristmasWreathBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FROST =
            ITEMS.register("frost", blockItemOf(ChristmasBlocks.FROST, FrostBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SANTA_LIST =
            ITEMS.register("santa_list", blockItemOf(ChristmasBlocks.SANTA_LIST, SantaListBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> BABY_PRESENT_ORNAMENT =
            ITEMS.register("baby_present_ornament", blockItemOf(ChristmasBlocks.BABY_PRESENT_ORNAMENT, BabyPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ADULT_PRESENT_ORNAMENT =
            ITEMS.register("adult_present_ornament", blockItemOf(ChristmasBlocks.ADULT_PRESENT_ORNAMENT, AdultPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ELDER_PRESENT_ORNAMENT =
            ITEMS.register("elder_present_ornament", blockItemOf(ChristmasBlocks.ELDER_PRESENT_ORNAMENT, ElderPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_ORNAMENT =
            ITEMS.register("candy_cane_ornament", blockItemOf(ChristmasBlocks.CANDY_CANE_ORNAMENT, CandyCaneOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SANTA_ELF_ORNAMENT =
            ITEMS.register("santa_elf_ornament", blockItemOf(ChristmasBlocks.SANTA_ELF_ORNAMENT, SantaElfOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_MAN_ORNAMENT =
            ITEMS.register("gingerbread_man_ornament", blockItemOf(ChristmasBlocks.GINGERBREAD_MAN_ORNAMENT, GingerbreadManOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GRINCH_ORNAMENT =
            ITEMS.register("grinch_ornament", blockItemOf(ChristmasBlocks.GRINCH_ORNAMENT, GrinchOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> NUTCRACKER_ORNAMENT =
            ITEMS.register("nutcracker_ornament", blockItemOf(ChristmasBlocks.NUTCRACKER_ORNAMENT, NutcrackerOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BLOCK =
            ITEMS.register("gingerbread_dough_block", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_STAIRS =
            ITEMS.register("gingerbread_dough_stairs", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_SLAB =
            ITEMS.register("gingerbread_dough_slab", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_WALL =
            ITEMS.register("gingerbread_dough_wall", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BRICKS =
            ITEMS.register("gingerbread_dough_bricks", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_BRICKS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BRICK_STAIRS =
            ITEMS.register("gingerbread_dough_brick_stairs", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BRICK_SLAB =
            ITEMS.register("gingerbread_dough_brick_slab", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BRICK_WALL =
            ITEMS.register("gingerbread_dough_brick_wall", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_TILES =
            ITEMS.register("gingerbread_dough_tiles", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_TILES, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_TILE_STAIRS =
            ITEMS.register("gingerbread_dough_tile_stairs", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_TILE_SLAB =
            ITEMS.register("gingerbread_dough_tile_slab", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_TILE_WALL =
            ITEMS.register("gingerbread_dough_tile_wall", blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BLOCK =
            ITEMS.register("gingerbread_block", blockItemOf(ChristmasBlocks.GINGERBREAD_BLOCK, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_STAIRS =
            ITEMS.register("gingerbread_stairs", blockItemOf(ChristmasBlocks.GINGERBREAD_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_SLAB =
            ITEMS.register("gingerbread_slab", blockItemOf(ChristmasBlocks.GINGERBREAD_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_WALL =
            ITEMS.register("gingerbread_wall", blockItemOf(ChristmasBlocks.GINGERBREAD_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BRICKS =
            ITEMS.register("gingerbread_bricks", blockItemOf(ChristmasBlocks.GINGERBREAD_BRICKS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BRICK_STAIRS =
            ITEMS.register("gingerbread_brick_stairs", blockItemOf(ChristmasBlocks.GINGERBREAD_BRICK_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BRICK_SLAB =
            ITEMS.register("gingerbread_brick_slab", blockItemOf(ChristmasBlocks.GINGERBREAD_BRICK_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BRICK_WALL =
            ITEMS.register("gingerbread_brick_wall", blockItemOf(ChristmasBlocks.GINGERBREAD_BRICK_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_TILES =
            ITEMS.register("gingerbread_tiles", blockItemOf(ChristmasBlocks.GINGERBREAD_TILES, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_TILE_STAIRS =
            ITEMS.register("gingerbread_tile_stairs", blockItemOf(ChristmasBlocks.GINGERBREAD_TILE_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_TILE_SLAB =
            ITEMS.register("gingerbread_tile_slab", blockItemOf(ChristmasBlocks.GINGERBREAD_TILE_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_TILE_WALL =
            ITEMS.register("gingerbread_tile_wall", blockItemOf(ChristmasBlocks.GINGERBREAD_TILE_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BLOCK =
            ITEMS.register("soggy_gingerbread_block", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_STAIRS =
            ITEMS.register("soggy_gingerbread_stairs", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_SLAB =
            ITEMS.register("soggy_gingerbread_slab", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_WALL =
            ITEMS.register("soggy_gingerbread_wall", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BRICKS =
            ITEMS.register("soggy_gingerbread_bricks", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_BRICKS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BRICK_STAIRS =
            ITEMS.register("soggy_gingerbread_brick_stairs", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BRICK_SLAB =
            ITEMS.register("soggy_gingerbread_brick_slab", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BRICK_WALL =
            ITEMS.register("soggy_gingerbread_brick_wall", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_TILES =
            ITEMS.register("soggy_gingerbread_tiles", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_TILES, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_TILE_STAIRS =
            ITEMS.register("soggy_gingerbread_tile_stairs", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_TILE_SLAB =
            ITEMS.register("soggy_gingerbread_tile_slab", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_TILE_WALL =
            ITEMS.register("soggy_gingerbread_tile_wall", blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BLOCK =
            ITEMS.register("candy_cane_block", blockItemOf(ChristmasBlocks.CANDY_CANE_BLOCK, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_STAIRS =
            ITEMS.register("candy_cane_stairs", blockItemOf(ChristmasBlocks.CANDY_CANE_STAIRS, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_SLAB =
            ITEMS.register("candy_cane_slab", blockItemOf(ChristmasBlocks.CANDY_CANE_SLAB, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_WALL =
            ITEMS.register("candy_cane_wall", blockItemOf(ChristmasBlocks.CANDY_CANE_WALL, CandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BRICKS =
            ITEMS.register("candy_cane_bricks", blockItemOf(ChristmasBlocks.CANDY_CANE_BRICKS, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BRICK_STAIRS =
            ITEMS.register("candy_cane_brick_stairs", blockItemOf(ChristmasBlocks.CANDY_CANE_BRICK_STAIRS, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BRICK_SLAB =
            ITEMS.register("candy_cane_brick_slab", blockItemOf(ChristmasBlocks.CANDY_CANE_BRICK_SLAB, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BRICK_WALL =
            ITEMS.register("candy_cane_brick_wall", blockItemOf(ChristmasBlocks.CANDY_CANE_BRICK_WALL, CandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_TILES =
            ITEMS.register("candy_cane_tiles", blockItemOf(ChristmasBlocks.CANDY_CANE_TILES, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_TILE_STAIRS =
            ITEMS.register("candy_cane_tile_stairs", blockItemOf(ChristmasBlocks.CANDY_CANE_TILE_STAIRS, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_TILE_SLAB =
            ITEMS.register("candy_cane_tile_slab", blockItemOf(ChristmasBlocks.CANDY_CANE_TILE_SLAB, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_TILE_WALL =
            ITEMS.register("candy_cane_tile_wall", blockItemOf(ChristmasBlocks.CANDY_CANE_TILE_WALL, CandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BLOCK =
            ITEMS.register("festive_candy_cane_block", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_STAIRS =
            ITEMS.register("festive_candy_cane_stairs", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_STAIRS, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_SLAB =
            ITEMS.register("festive_candy_cane_slab", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_SLAB, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_WALL =
            ITEMS.register("festive_candy_cane_wall", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_WALL, FestiveCandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BRICKS =
            ITEMS.register("festive_candy_cane_bricks", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICKS, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BRICK_STAIRS =
            ITEMS.register("festive_candy_cane_brick_stairs", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_STAIRS, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BRICK_SLAB =
            ITEMS.register("festive_candy_cane_brick_slab", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_SLAB, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BRICK_WALL =
            ITEMS.register("festive_candy_cane_brick_wall", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_WALL, FestiveCandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_TILES =
            ITEMS.register("festive_candy_cane_tiles", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_TILES, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_TILE_STAIRS =
            ITEMS.register("festive_candy_cane_tile_stairs", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_STAIRS, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_TILE_SLAB =
            ITEMS.register("festive_candy_cane_tile_slab", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_SLAB, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_TILE_WALL =
            ITEMS.register("festive_candy_cane_tile_wall", blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_WALL, FestiveCandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_STAR =
            ITEMS.register("christmas_star", blockItemOf(ChristmasBlocks.CHRISTMAS_STAR, ChristmasStarBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> MUSIC_BOX =
            ITEMS.register("music_box", blockItemOf(ChristmasBlocks.MUSIC_BOX, MusicBoxBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GIFT_WRAPPING_STATION =
            ITEMS.register("gift_wrapping_station", blockItemOf(ChristmasBlocks.GIFT_WRAPPING_STATION, GiftWrapperBlock.ITEM_PROPERTIES));

    public static final RegistryObject<BlockItem> MILK_AND_COOKIES =
            registerFood("milk_and_cookies", ChristmasBlocks.MILK_AND_COOKIES.get(), BIG_FOOD_PROPS);
    public static final RegistryObject<BlockItem> LOG_CAKE =
            registerFood("log_cake", ChristmasBlocks.LOG_CAKE.get(), BIG_FOOD_PROPS);
    public static final RegistryObject<BlockItem> CHRISTMAS_HAM =
            registerFood("christmas_ham", ChristmasBlocks.CHRISTMAS_HAM.get(), BIG_FOOD_PROPS);
    public static final RegistryObject<BlockItem> CHRISTMAS_PUDDING =
            registerFood("christmas_pudding", ChristmasBlocks.CHRISTMAS_PUDDING.get(), BIG_FOOD_PROPS);

    private static RegistryObject<Item> registerFood(String foodId, Item.Properties itemProps) {
        return ITEMS.register(foodId, () -> new ChristmasItem(itemProps));
    }

    private static RegistryObject<BlockItem> registerFood(String foodId, Block block, Item.Properties itemProps) {
        return ITEMS.register(foodId, () -> new ChristmasBlockItem(block, itemProps));
    }

    public static Supplier<ChristmasBlockItem> blockItemOf(RegistryObject<? extends Block> block, Item.Properties properties) {
        return () -> new ChristmasBlockItem(block.get(), properties);
    }

    public static Supplier<Item> spawnEggOf(Supplier<EntityType<? extends Mob>> entityType, int backgroundColor, int highlightColor) {
        return () -> new ForgeSpawnEggItem(entityType::get, backgroundColor, highlightColor, new Item.Properties().tab(CreativeModeTab.TAB_MISC));
    }

    public static boolean isSheetMusicItem(ItemStack itemStack) {
        return itemStack.getItem() instanceof SheetMusicItem;
    }

    public static boolean isFoodItem(ItemStack itemStack) {
        return itemStack.is(ChristmasTags.Items.FOODS);
    }

    public static boolean isLargeFoodItem(ItemStack itemStack) {
        return itemStack.is(ChristmasTags.Items.LARGE_FOODS);
    }

    public static boolean isOrnamentItem(ItemStack item) {
        return isBasicOrnamentItem(item) || isRareOrnamentItem(item) || isLegendaryOrnamentItem(item);
    }

    public static boolean isBasicOrnamentItem(ItemStack item) {
        if (item.getItem() instanceof ChristmasBlockItem) {
            ChristmasBlockItem blockItem = (ChristmasBlockItem) item.getItem();

            return blockItem.getBlock() instanceof BasicOrnament;
        }

        return false;
    }

    public static boolean isRareOrnamentItem(ItemStack item) {
        if (item.getItem() instanceof ChristmasBlockItem) {
            ChristmasBlockItem blockItem = (ChristmasBlockItem) item.getItem();

            return blockItem.getBlock() instanceof RareOrnament;
        }

        return false;
    }

    public static boolean isLegendaryOrnamentItem(ItemStack item) {
        if (item.getItem() instanceof ChristmasBlockItem) {
            ChristmasBlockItem blockItem = (ChristmasBlockItem) item.getItem();

            return blockItem.getBlock() instanceof LegendaryOrnament;
        }

        return false;
    }

    public static boolean isPresentItem(ItemStack item) {
        return item.getItem() instanceof BlockItem && ((BlockItem) item.getItem()).getBlock() instanceof PresentBlock;
    }
}
