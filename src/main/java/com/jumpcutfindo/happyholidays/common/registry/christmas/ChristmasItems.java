package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneSlabBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneStairBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneWallBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.festive.FestiveCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.festive.FestiveCandyCaneSlabBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.festive.FestiveCandyCaneStairBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.festive.FestiveCandyCaneWallBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.alphabets.AlphabetOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.ChristmasWreathBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.FrostBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.SantaListBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.StockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.BasicOrnament;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.LegendaryOrnament;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.RareOrnament;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.BaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.BigBaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.ChristmasBellBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.ChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.TinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.AdultPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.BabyPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.CandyCaneOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.ElderPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.GingerbreadManOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.GrinchOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.SantaElfOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.rare.HeadOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.ChristmasHamBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.ChristmasPuddingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.LogCakeBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.MilkAndCookiesBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.BaseGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.BaseGingerbreadSlabBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.BaseGingerbreadStairBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.BaseGingerbreadWall;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.ChristmasStarBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.GiftWrapperBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.MusicBoxBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.AdultPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.BabyPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.ElderPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.ExplosivePresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.PresentBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.candy.CandyCaneItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.candy.EnchantedCandyCaneItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.candy.FestiveCandyCaneItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.dye.DyeBowlItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.EggnogItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.ChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gingerbread.GingerbreadCookieItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gingerbread.RawGingerbreadItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.ChristmasGuideBookItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.EnchantedSantaHatItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.MistletoeAndHollyItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.nutcracker.PatrolOrdersItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.PresentScrapItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.elf.SantaElfBellItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.SnowGlobeItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.nutcracker.SwaggerStickItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.elf.ToyPartsRequestItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.walnut.WalnutItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.outfits.ChristmasOutfits;
import com.jumpcutfindo.happyholidays.common.item.christmas.thread.EnchantedThreadItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.thread.ThreadItem;
import com.jumpcutfindo.happyholidays.common.utils.ColourUtils;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ChristmasItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            HappyHolidaysMod.MOD_ID
    );

    // ================== ITEMS ==================
    public static final RegistryObject<ChristmasItem> RAW_GINGERBREAD =
            ITEMS.register(RawGingerbreadItem.ITEM_ID, RawGingerbreadItem::new);
    public static final RegistryObject<ChristmasItem> GINGERBREAD_COOKIE =
            ITEMS.register(GingerbreadCookieItem.ITEM_ID, GingerbreadCookieItem::new);
    public static final RegistryObject<ChristmasItem> PRESENT_SCRAPS =
            ITEMS.register(PresentScrapItem.ITEM_ID, PresentScrapItem::new);
    public static final RegistryObject<ChristmasItem> HOLLY =
            ITEMS.register(MistletoeAndHollyItem.ITEM_ID, MistletoeAndHollyItem::new);
    public static final RegistryObject<ChristmasItem> SANTA_ELF_BELL =
            ITEMS.register(SantaElfBellItem.ITEM_ID, SantaElfBellItem::new);
    public static final RegistryObject<ChristmasItem> TOY_PARTS_REQUEST =
            ITEMS.register(ToyPartsRequestItem.ITEM_ID, ToyPartsRequestItem::new);
    public static final RegistryObject<ChristmasItem> CHRISTMAS_GUIDE_BOOK =
            ITEMS.register(ChristmasGuideBookItem.ITEM_ID, ChristmasGuideBookItem::new);
    public static final RegistryObject<ChristmasItem> SNOW_GLOBE =
            ITEMS.register(SnowGlobeItem.ITEM_ID, SnowGlobeItem::new);

    public static final RegistryObject<ChristmasItem> THREAD =
            ITEMS.register(ThreadItem.ITEM_ID, ThreadItem::new);
    public static final RegistryObject<ChristmasItem> ENCHANTED_THREAD =
            ITEMS.register(EnchantedThreadItem.ITEM_ID, EnchantedThreadItem::new);

    public static final RegistryObject<BlockItem> WALNUT =
            ITEMS.register(WalnutItem.BASIC_ITEM_ID, () -> new ItemNameBlockItem(ChristmasBlocks.WALNUT_PLANT.get(), WalnutItem.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasItem> EXPLOSIVE_WALNUT =
            ITEMS.register(WalnutItem.EXPLOSIVE_ITEM_ID, WalnutItem::new);
    public static final RegistryObject<ChristmasItem> SUGARED_WALNUT =
            ITEMS.register(WalnutItem.SUGARED_ITEM_ID, WalnutItem::new);
    public static final RegistryObject<ChristmasItem> METALLIC_WALNUT =
            ITEMS.register(WalnutItem.METALLIC_ITEM_ID, WalnutItem::new);
    public static final RegistryObject<ChristmasItem> HALVED_WALNUT =
            ITEMS.register(WalnutItem.HALVED_ITEM_ID, WalnutItem::new);

    public static final RegistryObject<Item> SWAGGER_STICK =
            ITEMS.register(SwaggerStickItem.ITEM_ID, SwaggerStickItem::new);
    public static final RegistryObject<Item> PATROL_ORDERS =
            ITEMS.register(PatrolOrdersItem.ITEM_ID, PatrolOrdersItem::new);

    public static final RegistryObject<ChristmasItem> ENCHANTED_SANTA_HAT =
            ITEMS.register(EnchantedSantaHatItem.ITEM_ID, EnchantedSantaHatItem::new);
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

    public static final RegistryObject<ChristmasItem> DYE_BOWL =
            ITEMS.register(DyeBowlItem.DYE_BOWL_ID, DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> RED_CHRISTMAS_DYE =
            ITEMS.register(DyeBowlItem.RED_DYE_ID, DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> BLUE_CHRISTMAS_DYE =
            ITEMS.register(DyeBowlItem.BLUE_DYE_ID, DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> YELLOW_CHRISTMAS_DYE =
            ITEMS.register(DyeBowlItem.YELLOW_DYE_ID, DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> GREEN_CHRISTMAS_DYE =
            ITEMS.register(DyeBowlItem.GREEN_DYE_ID, DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> GOLD_CHRISTMAS_DYE =
            ITEMS.register(DyeBowlItem.GOLD_DYE_ID, DyeBowlItem::new);
    public static final RegistryObject<ChristmasItem> SILVER_CHRISTMAS_DYE =
            ITEMS.register(DyeBowlItem.SILVER_DYE_ID, DyeBowlItem::new);

    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_ANGELS_ON_HIGH =
            ITEMS.register(SheetMusicItem.ANGELS_ON_HIGH_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.ANGELS_ON_HIGH));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_CAROL_OF_THE_BELLS =
            ITEMS.register(SheetMusicItem.CAROL_OF_BELLS_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.CAROL_OF_THE_BELLS));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_DECK_THE_HALLS =
            ITEMS.register(SheetMusicItem.DECK_THE_HALLS_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.DECK_THE_HALLS));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_FROSTY_THE_SNOWMAN =
            ITEMS.register(SheetMusicItem.FROSTY_THE_SNOWMAN_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.FROSTY_THE_SNOWMAN));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_GOD_REST_GENTLEMEN =
            ITEMS.register(SheetMusicItem.GOD_REST_GENTLEMEN_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.GOD_REST_GENTLEMEN));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_HERE_COMES_SANTA =
            ITEMS.register(SheetMusicItem.HERE_COMES_SANTA_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.HERE_COMES_SANTA));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_JINGLE_BELL_ROCK =
            ITEMS.register(SheetMusicItem.JINGLE_BELL_ROCK_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.JINGLE_BELL_ROCK));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_JINGLE_BELLS =
            ITEMS.register(SheetMusicItem.JINGLE_BELLS_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.JINGLE_BELLS));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_JOY_TO_THE_WORLD =
            ITEMS.register(SheetMusicItem.JOY_TO_THE_WORLD_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.JOY_TO_THE_WORLD));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_RUDOLPH =
            ITEMS.register(SheetMusicItem.RUDOLPH_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.RUDOLPH));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_SILENT_NIGHT =
            ITEMS.register(SheetMusicItem.SILENT_NIGHT_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.SILENT_NIGHT));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_SLEIGH_RIDE =
            ITEMS.register(SheetMusicItem.SLEIGH_RIDE_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.SLEIGH_RIDE));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_THE_FIRST_NOEL =
            ITEMS.register(SheetMusicItem.THE_FIRST_NOEL_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.THE_FIRST_NOEL));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_WE_THREE_KINGS =
            ITEMS.register(SheetMusicItem.WE_THREE_KINGS_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.WE_THREE_KINGS));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_WE_WISH_YOU =
            ITEMS.register(SheetMusicItem.WE_WISH_YOU_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.WE_WISH_YOU));
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_WHITE_CHRISTMAS =
            ITEMS.register(SheetMusicItem.WHITE_CHRISTMAS_ID, () -> new SheetMusicItem().setMusic(ChristmasMusic.WHITE_CHRISTMAS));

    public static final RegistryObject<ChristmasFoodItem> CANDY_CANE =
            ITEMS.register(CandyCaneItem.ITEM_ID, CandyCaneItem::new);
    public static final RegistryObject<ChristmasFoodItem> FESTIVE_CANDY_CANE =
            ITEMS.register(FestiveCandyCaneItem.ITEM_ID, FestiveCandyCaneItem::new);
    public static final RegistryObject<ChristmasItem> ENCHANTED_CANDY_CANE =
            ITEMS.register(EnchantedCandyCaneItem.ITEM_ID, EnchantedCandyCaneItem::new);

    public static final RegistryObject<ChristmasFoodItem> EGGNOG =
            ITEMS.register(EggnogItem.ITEM_ID, EggnogItem::new);

    public static final RegistryObject<ChristmasItem> RED_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(ChristmasGiftItem.RED_GIFT_ID, ChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> BLUE_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(ChristmasGiftItem.BLUE_GIFT_ID, ChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> YELLOW_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(ChristmasGiftItem.YELLOW_GIFT_ID, ChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> GREEN_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(ChristmasGiftItem.GREEN_GIFT_ID, ChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> GOLD_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(ChristmasGiftItem.GOLD_GIFT_ID, ChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> SILVER_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(ChristmasGiftItem.SILVER_GIFT_ID, ChristmasGiftItem::new);

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
            ITEMS.register(BabyPresentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.BABY_PRESENT, BabyPresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ADULT_PRESENT =
            ITEMS.register(AdultPresentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.ADULT_PRESENT, AdultPresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ELDER_PRESENT =
            ITEMS.register(ElderPresentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.ELDER_PRESENT, ElderPresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<BlockItem> EXPLOSIVE_PRESENT =
            ITEMS.register(ExplosivePresentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.EXPLOSIVE_PRESENT, ElderPresentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.RED_BAUBLE_ID, blockItemOf(ChristmasBlocks.RED_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.BLUE_BAUBLE_ID, blockItemOf(ChristmasBlocks.BLUE_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.YELLOW_BAUBLE_ID, blockItemOf(ChristmasBlocks.YELLOW_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.GREEN_BAUBLE_ID, blockItemOf(ChristmasBlocks.GREEN_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.GOLD_BAUBLE_ID, blockItemOf(ChristmasBlocks.GOLD_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.SILVER_BAUBLE_ID, blockItemOf(ChristmasBlocks.SILVER_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> BIG_RED_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_RED_BAUBLE_ID, blockItemOf(ChristmasBlocks.BIG_RED_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_BLUE_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_BLUE_BAUBLE_ID, blockItemOf(ChristmasBlocks.BIG_BLUE_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_YELLOW_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_YELLOW_BAUBLE_ID, blockItemOf(ChristmasBlocks.BIG_YELLOW_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_GREEN_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_GREEN_BAUBLE_ID, blockItemOf(ChristmasBlocks.BIG_GREEN_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_GOLD_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_GOLD_BAUBLE_ID, blockItemOf(ChristmasBlocks.BIG_GOLD_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_SILVER_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_SILVER_BAUBLE_ID, blockItemOf(ChristmasBlocks.BIG_SILVER_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_TINSEL =
            ITEMS.register(TinselBlock.RED_TINSEL_ID, blockItemOf(ChristmasBlocks.RED_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_TINSEL =
            ITEMS.register(TinselBlock.BLUE_TINSEL_ID, blockItemOf(ChristmasBlocks.BLUE_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_TINSEL =
            ITEMS.register(TinselBlock.YELLOW_TINSEL_ID, blockItemOf(ChristmasBlocks.YELLOW_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_TINSEL =
            ITEMS.register(TinselBlock.GREEN_TINSEL_ID, blockItemOf(ChristmasBlocks.GREEN_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_TINSEL =
            ITEMS.register(TinselBlock.GOLD_TINSEL_ID, blockItemOf(ChristmasBlocks.GOLD_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_TINSEL =
            ITEMS.register(TinselBlock.SILVER_TINSEL_ID, blockItemOf(ChristmasBlocks.SILVER_TINSEL, TinselBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.RED_CHRISTMAS_LIGHTS_ID, blockItemOf(ChristmasBlocks.RED_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.BLUE_CHRISTMAS_LIGHTS_ID, blockItemOf(ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.YELLOW_CHRISTMAS_LIGHTS_ID, blockItemOf(ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.GREEN_CHRISTMAS_LIGHTS_ID, blockItemOf(ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.GOLD_CHRISTMAS_LIGHTS_ID, blockItemOf(ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.SILVER_CHRISTMAS_LIGHTS_ID, blockItemOf(ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_CHRISTMAS_BELLS =
            ITEMS.register(ChristmasBellBlock.RED_BELLS_ID, blockItemOf(ChristmasBlocks.RED_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_CHRISTMAS_BELLS =
            ITEMS.register(ChristmasBellBlock.BLUE_BELLS_ID, blockItemOf(ChristmasBlocks.BLUE_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_CHRISTMAS_BELLS =
            ITEMS.register(ChristmasBellBlock.YELLOW_BELLS_ID, blockItemOf(ChristmasBlocks.YELLOW_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_CHRISTMAS_BELLS =
            ITEMS.register(ChristmasBellBlock.GREEN_BELLS_ID, blockItemOf(ChristmasBlocks.GREEN_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_CHRISTMAS_BELLS =
            ITEMS.register(ChristmasBellBlock.GOLD_BELLS_ID, blockItemOf(ChristmasBlocks.GOLD_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_CHRISTMAS_BELLS =
            ITEMS.register(ChristmasBellBlock.SILVER_BELLS_ID, blockItemOf(ChristmasBlocks.SILVER_CHRISTMAS_BELLS, ChristmasBellBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CREEPER_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.CREEPER_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.CREEPER_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SKELETON_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.SKELETON_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.SKELETON_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> WITHER_SKELETON_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.WITHER_SKELETON_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.WITHER_SKELETON_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ZOMBIE_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.ZOMBIE_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.ZOMBIE_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> DROWNED_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.DROWNED_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.DROWNED_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLAZE_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.BLAZE_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.BLAZE_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GHAST_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.GHAST_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.GHAST_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> PHANTOM_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.PHANTOM_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.PHANTOM_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> PIG_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.PIG_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.PIG_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> COW_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.COW_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.COW_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CHICKEN_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.CHICKEN_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.CHICKEN_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SHEEP_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.SHEEP_HEAD_ORNAMENT_ID, blockItemOf(ChristmasBlocks.SHEEP_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_STOCKING =
            ITEMS.register(StockingBlock.RED_STOCKING_ID, blockItemOf(ChristmasBlocks.RED_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_STOCKING =
            ITEMS.register(StockingBlock.BLUE_STOCKING_ID, blockItemOf(ChristmasBlocks.BLUE_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_STOCKING =
            ITEMS.register(StockingBlock.YELLOW_STOCKING_ID, blockItemOf(ChristmasBlocks.YELLOW_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_STOCKING =
            ITEMS.register(StockingBlock.GREEN_STOCKING_ID, blockItemOf(ChristmasBlocks.GREEN_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_STOCKING =
            ITEMS.register(StockingBlock.GOLD_STOCKING_ID, blockItemOf(ChristmasBlocks.GOLD_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_STOCKING =
            ITEMS.register(StockingBlock.SILVER_STOCKING_ID, blockItemOf(ChristmasBlocks.SILVER_STOCKING, StockingBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_TEMPLATE =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_TEMPLATE_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_A =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_A_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_A, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_B =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_B_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_B, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_C =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_C_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_C, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_D =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_D_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_D, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_E =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_E_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_E, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_F =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_F_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_F, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_G =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_G_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_G, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_H =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_H_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_H, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_I =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_I_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_I, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_J =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_J_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_J, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_K =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_K_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_K, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_L =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_L_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_L, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_M =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_M_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_M, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_N =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_N_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_N, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_O =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_O_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_O, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_P =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_P_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_P, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_Q =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_Q_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_Q, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_R =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_R_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_R, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_S =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_S_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_S, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_T =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_T_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_T, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_U =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_U_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_U, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_V =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_V_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_V, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_W =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_W_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_W, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_X =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_X_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_X, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_Y =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_Y_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_Y, AlphabetOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ALPHABET_ORNAMENT_Z =
            ITEMS.register(AlphabetOrnamentBlock.ALPHABET_Z_ID, blockItemOf(ChristmasBlocks.ALPHABET_ORNAMENT_Z, AlphabetOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_WREATH =
            ITEMS.register(ChristmasWreathBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.CHRISTMAS_WREATH, ChristmasWreathBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FROST =
            ITEMS.register(FrostBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.FROST, FrostBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SANTA_LIST =
            ITEMS.register(SantaListBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.SANTA_LIST, SantaListBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> BABY_PRESENT_ORNAMENT =
            ITEMS.register(BabyPresentOrnamentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.BABY_PRESENT_ORNAMENT, BabyPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ADULT_PRESENT_ORNAMENT =
            ITEMS.register(AdultPresentOrnamentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.ADULT_PRESENT_ORNAMENT, AdultPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ELDER_PRESENT_ORNAMENT =
            ITEMS.register(ElderPresentOrnamentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.ELDER_PRESENT_ORNAMENT, ElderPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_ORNAMENT =
            ITEMS.register(CandyCaneOrnamentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_ORNAMENT, CandyCaneOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SANTA_ELF_ORNAMENT =
            ITEMS.register(SantaElfOrnamentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.SANTA_ELF_ORNAMENT, SantaElfOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_MAN_ORNAMENT =
            ITEMS.register(GingerbreadManOrnamentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_MAN_ORNAMENT, GingerbreadManOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GRINCH_ORNAMENT =
            ITEMS.register(GrinchOrnamentBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.GRINCH_ORNAMENT, GrinchOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BLOCK =
            ITEMS.register(BaseGingerbreadBlock.DOUGH_BLOCK_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_STAIRS =
            ITEMS.register(BaseGingerbreadStairBlock.DOUGH_BLOCK_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_SLAB =
            ITEMS.register(BaseGingerbreadSlabBlock.DOUGH_BLOCK_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_WALL =
            ITEMS.register(BaseGingerbreadWall.DOUGH_BLOCK_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BRICKS =
            ITEMS.register(BaseGingerbreadBlock.DOUGH_BRICKS_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_BRICKS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BRICK_STAIRS =
            ITEMS.register(BaseGingerbreadStairBlock.DOUGH_BRICKS_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BRICK_SLAB =
            ITEMS.register(BaseGingerbreadSlabBlock.DOUGH_BRICKS_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BRICK_WALL =
            ITEMS.register(BaseGingerbreadWall.DOUGH_BRICKS_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_TILES =
            ITEMS.register(BaseGingerbreadBlock.DOUGH_TILES_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_TILES, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_TILE_STAIRS =
            ITEMS.register(BaseGingerbreadStairBlock.DOUGH_TILES_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_TILE_SLAB =
            ITEMS.register(BaseGingerbreadSlabBlock.DOUGH_TILES_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_TILE_WALL =
            ITEMS.register(BaseGingerbreadWall.DOUGH_TILES_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_DOUGH_TILE_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BLOCK =
            ITEMS.register(BaseGingerbreadBlock.COOKED_BLOCK_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_BLOCK, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_STAIRS =
            ITEMS.register(BaseGingerbreadStairBlock.COOKED_BLOCK_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_SLAB =
            ITEMS.register(BaseGingerbreadSlabBlock.COOKED_BLOCK_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_WALL =
            ITEMS.register(BaseGingerbreadWall.COOKED_BLOCK_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BRICKS =
            ITEMS.register(BaseGingerbreadBlock.COOKED_BRICKS_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_BRICKS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BRICK_STAIRS =
            ITEMS.register(BaseGingerbreadStairBlock.COOKED_BRICKS_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_BRICK_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BRICK_SLAB =
            ITEMS.register(BaseGingerbreadSlabBlock.COOKED_BRICKS_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_BRICK_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BRICK_WALL =
            ITEMS.register(BaseGingerbreadWall.COOKED_BRICKS_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_BRICK_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_TILES =
            ITEMS.register(BaseGingerbreadBlock.COOKED_TILES_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_TILES, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_TILE_STAIRS =
            ITEMS.register(BaseGingerbreadStairBlock.COOKED_TILES_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_TILE_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_TILE_SLAB =
            ITEMS.register(BaseGingerbreadSlabBlock.COOKED_TILES_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_TILE_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_TILE_WALL =
            ITEMS.register(BaseGingerbreadWall.COOKED_TILES_ID, blockItemOf(ChristmasBlocks.GINGERBREAD_TILE_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BLOCK =
            ITEMS.register(BaseGingerbreadBlock.SOGGY_BLOCK_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_STAIRS =
            ITEMS.register(BaseGingerbreadStairBlock.SOGGY_BLOCK_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_SLAB =
            ITEMS.register(BaseGingerbreadSlabBlock.SOGGY_BLOCK_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_WALL =
            ITEMS.register(BaseGingerbreadWall.SOGGY_BLOCK_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BRICKS =
            ITEMS.register(BaseGingerbreadBlock.SOGGY_BRICKS_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_BRICKS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BRICK_STAIRS =
            ITEMS.register(BaseGingerbreadStairBlock.SOGGY_BRICKS_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BRICK_SLAB =
            ITEMS.register(BaseGingerbreadSlabBlock.SOGGY_BRICKS_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BRICK_WALL =
            ITEMS.register(BaseGingerbreadWall.SOGGY_BRICKS_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_TILES =
            ITEMS.register(BaseGingerbreadBlock.SOGGY_TILES_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_TILES, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_TILE_STAIRS =
            ITEMS.register(BaseGingerbreadStairBlock.SOGGY_TILES_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_STAIRS, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_TILE_SLAB =
            ITEMS.register(BaseGingerbreadSlabBlock.SOGGY_TILES_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_SLAB, BaseGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_TILE_WALL =
            ITEMS.register(BaseGingerbreadWall.SOGGY_TILES_ID, blockItemOf(ChristmasBlocks.SOGGY_GINGERBREAD_TILE_WALL, BaseGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BLOCK =
            ITEMS.register(CandyCaneBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_BLOCK, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_STAIRS =
            ITEMS.register(CandyCaneStairBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_STAIRS, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_SLAB =
            ITEMS.register(CandyCaneSlabBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_SLAB, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_WALL =
            ITEMS.register(CandyCaneWallBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_WALL, CandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BRICKS =
            ITEMS.register(CandyCaneBlock.BRICKS_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_BRICKS, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BRICK_STAIRS =
            ITEMS.register(CandyCaneStairBlock.BRICKS_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_BRICK_STAIRS, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BRICK_SLAB =
            ITEMS.register(CandyCaneSlabBlock.BRICKS_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_BRICK_SLAB, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BRICK_WALL =
            ITEMS.register(CandyCaneWallBlock.BRICKS_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_BRICK_WALL, CandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_TILES =
            ITEMS.register(CandyCaneBlock.TILES_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_TILES, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_TILE_STAIRS =
            ITEMS.register(CandyCaneStairBlock.TILES_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_TILE_STAIRS, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_TILE_SLAB =
            ITEMS.register(CandyCaneSlabBlock.TILES_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_TILE_SLAB, CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_TILE_WALL =
            ITEMS.register(CandyCaneWallBlock.TILES_ID, blockItemOf(ChristmasBlocks.CANDY_CANE_TILE_WALL, CandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BLOCK =
            ITEMS.register(FestiveCandyCaneBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_STAIRS =
            ITEMS.register(FestiveCandyCaneStairBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_STAIRS, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_SLAB =
            ITEMS.register(FestiveCandyCaneSlabBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_SLAB, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_WALL =
            ITEMS.register(FestiveCandyCaneWallBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_WALL, FestiveCandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BRICKS =
            ITEMS.register(FestiveCandyCaneBlock.BRICKS_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICKS, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BRICK_STAIRS =
            ITEMS.register(FestiveCandyCaneStairBlock.BRICKS_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_STAIRS, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BRICK_SLAB =
            ITEMS.register(FestiveCandyCaneSlabBlock.BRICKS_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_SLAB, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BRICK_WALL =
            ITEMS.register(FestiveCandyCaneWallBlock.BRICKS_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_WALL, FestiveCandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_TILES =
            ITEMS.register(FestiveCandyCaneBlock.TILES_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_TILES, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_TILE_STAIRS =
            ITEMS.register(FestiveCandyCaneStairBlock.TILES_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_STAIRS, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_TILE_SLAB =
            ITEMS.register(FestiveCandyCaneSlabBlock.TILES_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_SLAB, FestiveCandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_TILE_WALL =
            ITEMS.register(FestiveCandyCaneWallBlock.TILES_ID, blockItemOf(ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_WALL, FestiveCandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_STAR =
            ITEMS.register(ChristmasStarBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.CHRISTMAS_STAR, ChristmasStarBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> MUSIC_BOX =
            ITEMS.register(MusicBoxBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.MUSIC_BOX, MusicBoxBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GIFT_WRAPPING_STATION =
            ITEMS.register(GiftWrapperBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.GIFT_WRAPPING_STATION, GiftWrapperBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> MILK_AND_COOKIES =
            ITEMS.register(MilkAndCookiesBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.MILK_AND_COOKIES, MilkAndCookiesBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> LOG_CAKE =
            ITEMS.register(LogCakeBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.LOG_CAKE, LogCakeBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_HAM =
            ITEMS.register(ChristmasHamBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.CHRISTMAS_HAM, ChristmasHamBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_PUDDING =
            ITEMS.register(ChristmasPuddingBlock.BLOCK_ID, blockItemOf(ChristmasBlocks.CHRISTMAS_PUDDING, ChristmasPuddingBlock.ITEM_PROPERTIES));

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
        return itemStack.getItem() instanceof ChristmasFoodItem
                || itemStack.getItem() instanceof ChristmasFoodBlockItem;
    }

    public static boolean isLargeFoodItem(ItemStack itemStack) {
        return itemStack.getItem() instanceof ChristmasFoodBlockItem;
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
