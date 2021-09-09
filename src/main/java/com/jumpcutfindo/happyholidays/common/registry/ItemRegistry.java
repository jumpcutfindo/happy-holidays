package com.jumpcutfindo.happyholidays.common.registry;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasContainerBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.FestiveCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.ChristmasWreathBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.SantaListBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.StockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.BaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.BigBaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.rare.HeadOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.AdultPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.BabyPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.CandyCaneOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.ElderPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.GingerbreadManOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.GrinchOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.SantaElfOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.ChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.TinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.ChristmasHamBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.ChristmasPuddingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.LogCakeBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.MilkAndCookiesBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.GiftWrapperBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.RawGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.SoggyGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.ChristmasStarBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.MusicBoxBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.AdultPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.BabyPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.ElderPresentBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.candy.CandyCaneItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.candy.EnchantedCandyCaneItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.candy.FestiveCandyCaneItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.EggnogItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.BlueChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.GoldChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.GreenChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.RedChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.SilverChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.YellowChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gingerbread.GingerbreadCookieItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gingerbread.RawGingerbreadItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.ChristmasGuideBookItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.EnchantedSantaHatItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.MistletoeAndHollyItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.PresentScrapItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.SantaElfBellItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.SantaHatItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.ToyPartsRequestItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.AngelsOnHighSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.CarolOfBellsSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.DeckTheHallsSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.FrostySnowmanSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.GodRestGentlemenSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.HereComesSantaSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.JingleBellRockSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.JingleBellsSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.JoyToTheWorldSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.RudolphSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SilentNightSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SleighRideSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.TheFirstNoelSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.WeThreeKingsSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.WeWishYouSheetMusicItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.WhiteChristmasSheetMusicItem;

import jdk.nashorn.internal.ir.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            HappyHolidaysMod.MOD_ID
    );

    public static Supplier<ChristmasBlockItem> itemOfBlock(RegistryObject<ChristmasBlock> block, Item.Properties properties) {
        return () -> new ChristmasBlockItem(block.get(), properties);
    }

    public static Supplier<ChristmasBlockItem> itemOfContainer(RegistryObject<ChristmasContainerBlock> block, Item.Properties properties) {
        return () -> new ChristmasBlockItem(block.get(), properties);
    }

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

    public static final RegistryObject<ChristmasItem> SANTA_HAT =
            ITEMS.register(SantaHatItem.ITEM_ID, SantaHatItem::new);
    public static final RegistryObject<ChristmasItem> ENCHANTED_SANTA_HAT =
            ITEMS.register(EnchantedSantaHatItem.ITEM_ID, EnchantedSantaHatItem::new);

    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_ANGELS_ON_HIGH =
            ITEMS.register(AngelsOnHighSheetMusicItem.ITEM_ID, AngelsOnHighSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_CAROL_OF_THE_BELLS =
            ITEMS.register(CarolOfBellsSheetMusicItem.ITEM_ID, CarolOfBellsSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_DECK_THE_HALLS =
            ITEMS.register(DeckTheHallsSheetMusicItem.ITEM_ID, DeckTheHallsSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_FROSTY_THE_SNOWMAN =
            ITEMS.register(FrostySnowmanSheetMusicItem.ITEM_ID, FrostySnowmanSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_GOD_REST_GENTLEMEN =
            ITEMS.register(GodRestGentlemenSheetMusicItem.ITEM_ID, GodRestGentlemenSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_HERE_COMES_SANTA =
            ITEMS.register(HereComesSantaSheetMusicItem.ITEM_ID, HereComesSantaSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_JINGLE_BELL_ROCK =
            ITEMS.register(JingleBellRockSheetMusicItem.ITEM_ID, JingleBellRockSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_JINGLE_BELLS =
            ITEMS.register(JingleBellsSheetMusicItem.ITEM_ID, JingleBellsSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_JOY_TO_THE_WORLD =
            ITEMS.register(JoyToTheWorldSheetMusicItem.ITEM_ID, JoyToTheWorldSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_RUDOLPH =
            ITEMS.register(RudolphSheetMusicItem.ITEM_ID, RudolphSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_SILENT_NIGHT =
            ITEMS.register(SilentNightSheetMusicItem.ITEM_ID, SilentNightSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_SLEIGH_RIDE =
            ITEMS.register(SleighRideSheetMusicItem.ITEM_ID, SleighRideSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_THE_FIRST_NOEL =
            ITEMS.register(TheFirstNoelSheetMusicItem.ITEM_ID, TheFirstNoelSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_WE_THREE_KINGS =
            ITEMS.register(WeThreeKingsSheetMusicItem.ITEM_ID, WeThreeKingsSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_WE_WISH_YOU =
            ITEMS.register(WeWishYouSheetMusicItem.ITEM_ID, WeWishYouSheetMusicItem::new);
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC_WHITE_CHRISTMAS =
            ITEMS.register(WhiteChristmasSheetMusicItem.ITEM_ID, WhiteChristmasSheetMusicItem::new);

    public static final RegistryObject<ChristmasFoodItem> CANDY_CANE =
            ITEMS.register(CandyCaneItem.ITEM_ID, CandyCaneItem::new);
    public static final RegistryObject<ChristmasFoodItem> FESTIVE_CANDY_CANE =
            ITEMS.register(FestiveCandyCaneItem.ITEM_ID, FestiveCandyCaneItem::new);
    public static final RegistryObject<ChristmasItem> ENCHANTED_CANDY_CANE =
            ITEMS.register(EnchantedCandyCaneItem.ITEM_ID, EnchantedCandyCaneItem::new);

    public static final RegistryObject<ChristmasFoodItem> EGGNOG =
            ITEMS.register(EggnogItem.ITEM_ID, EggnogItem::new);

    public static final RegistryObject<ChristmasItem> RED_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(RedChristmasGiftItem.ITEM_ID, RedChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> BLUE_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(BlueChristmasGiftItem.ITEM_ID, BlueChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> YELLOW_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(YellowChristmasGiftItem.ITEM_ID, YellowChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> GREEN_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(GreenChristmasGiftItem.ITEM_ID, GreenChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> GOLD_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(GoldChristmasGiftItem.ITEM_ID, GoldChristmasGiftItem::new);
    public static final RegistryObject<ChristmasItem> SILVER_CHRISTMAS_GIFT_ITEM =
            ITEMS.register(SilverChristmasGiftItem.ITEM_ID, SilverChristmasGiftItem::new);

    public static final RegistryObject<ChristmasBlockItem> BABY_PRESENT =
            ITEMS.register(BabyPresentBlock.BLOCK_ID, itemOfBlock(BlockRegistry.BABY_PRESENT, BabyPresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ADULT_PRESENT =
            ITEMS.register(AdultPresentBlock.BLOCK_ID,itemOfBlock(BlockRegistry.ADULT_PRESENT, AdultPresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ELDER_PRESENT =
            ITEMS.register(ElderPresentBlock.BLOCK_ID, itemOfBlock(BlockRegistry.ELDER_PRESENT, ElderPresentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.RED_BAUBLE_ID, itemOfBlock(BlockRegistry.RED_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.BLUE_BAUBLE_ID, itemOfBlock(BlockRegistry.BLUE_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.YELLOW_BAUBLE_ID, itemOfBlock(BlockRegistry.YELLOW_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.GREEN_BAUBLE_ID, itemOfBlock(BlockRegistry.GREEN_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.GOLD_BAUBLE_ID, itemOfBlock(BlockRegistry.GOLD_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_BAUBLE =
            ITEMS.register(BaubleOrnamentBlock.SILVER_BAUBLE_ID, itemOfBlock(BlockRegistry.SILVER_BAUBLE, BaubleOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> BIG_RED_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_RED_BAUBLE_ID, itemOfBlock(BlockRegistry.BIG_RED_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_BLUE_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_BLUE_BAUBLE_ID, itemOfBlock(BlockRegistry.BIG_BLUE_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_YELLOW_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_YELLOW_BAUBLE_ID, itemOfBlock(BlockRegistry.BIG_YELLOW_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_GREEN_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_GREEN_BAUBLE_ID, itemOfBlock(BlockRegistry.BIG_GREEN_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_GOLD_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_GOLD_BAUBLE_ID, itemOfBlock(BlockRegistry.BIG_GOLD_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_SILVER_BAUBLE =
            ITEMS.register(BigBaubleOrnamentBlock.BIG_SILVER_BAUBLE_ID, itemOfBlock(BlockRegistry.BIG_SILVER_BAUBLE, BigBaubleOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_TINSEL =
            ITEMS.register(TinselBlock.RED_TINSEL_ID, itemOfBlock(BlockRegistry.RED_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_TINSEL =
            ITEMS.register(TinselBlock.BLUE_TINSEL_ID, itemOfBlock(BlockRegistry.BLUE_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_TINSEL =
            ITEMS.register(TinselBlock.YELLOW_TINSEL_ID, itemOfBlock(BlockRegistry.YELLOW_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_TINSEL =
            ITEMS.register(TinselBlock.GREEN_TINSEL_ID, itemOfBlock(BlockRegistry.GREEN_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_TINSEL =
            ITEMS.register(TinselBlock.GOLD_TINSEL_ID, itemOfBlock(BlockRegistry.GOLD_TINSEL, TinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_TINSEL =
            ITEMS.register(TinselBlock.SILVER_TINSEL_ID, itemOfBlock(BlockRegistry.SILVER_TINSEL, TinselBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.RED_CHRISTMAS_LIGHTS_ID, itemOfBlock(BlockRegistry.RED_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.BLUE_CHRISTMAS_LIGHTS_ID, itemOfBlock(BlockRegistry.BLUE_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.YELLOW_CHRISTMAS_LIGHTS_ID, itemOfBlock(BlockRegistry.YELLOW_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.GREEN_CHRISTMAS_LIGHTS_ID, itemOfBlock(BlockRegistry.GREEN_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.GOLD_CHRISTMAS_LIGHTS_ID, itemOfBlock(BlockRegistry.GOLD_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_CHRISTMAS_LIGHTS =
            ITEMS.register(ChristmasLightBlock.SILVER_CHRISTMAS_LIGHTS_ID, itemOfBlock(BlockRegistry.SILVER_CHRISTMAS_LIGHTS, ChristmasLightBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CREEPER_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.CREEPER_HEAD_ORNAMENT_ID, itemOfBlock(BlockRegistry.CREEPER_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SKELETON_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.SKELETON_HEAD_ORNAMENT_ID, itemOfBlock(BlockRegistry.SKELETON_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> WITHER_SKELETON_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.WITHER_SKELETON_HEAD_ORNAMENT_ID, itemOfBlock(BlockRegistry.WITHER_SKELETON_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ZOMBIE_HEAD_ORNAMENT =
            ITEMS.register(HeadOrnamentBlock.ZOMBIE_HEAD_ORNAMENT_ID, itemOfBlock(BlockRegistry.ZOMBIE_HEAD_ORNAMENT, HeadOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_STOCKING =
            ITEMS.register(StockingBlock.RED_STOCKING_ID, itemOfBlock(BlockRegistry.RED_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_STOCKING =
            ITEMS.register(StockingBlock.BLUE_STOCKING_ID, itemOfBlock(BlockRegistry.BLUE_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_STOCKING =
            ITEMS.register(StockingBlock.YELLOW_STOCKING_ID, itemOfBlock(BlockRegistry.YELLOW_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_STOCKING =
            ITEMS.register(StockingBlock.GREEN_STOCKING_ID, itemOfBlock(BlockRegistry.GREEN_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_STOCKING =
            ITEMS.register(StockingBlock.GOLD_STOCKING_ID, itemOfBlock(BlockRegistry.GOLD_STOCKING, StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_STOCKING =
            ITEMS.register(StockingBlock.SILVER_STOCKING_ID, itemOfBlock(BlockRegistry.SILVER_STOCKING, StockingBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_WREATH =
            ITEMS.register(ChristmasWreathBlock.BLOCK_ID, itemOfBlock(BlockRegistry.CHRISTMAS_WREATH, ChristmasWreathBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SANTA_LIST =
            ITEMS.register(SantaListBlock.BLOCK_ID, itemOfBlock(BlockRegistry.SANTA_LIST, SantaListBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> BABY_PRESENT_ORNAMENT =
            ITEMS.register(BabyPresentOrnamentBlock.BLOCK_ID, itemOfBlock(BlockRegistry.BABY_PRESENT_ORNAMENT, BabyPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ADULT_PRESENT_ORNAMENT =
            ITEMS.register(AdultPresentOrnamentBlock.BLOCK_ID, itemOfBlock(BlockRegistry.ADULT_PRESENT_ORNAMENT, AdultPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ELDER_PRESENT_ORNAMENT =
            ITEMS.register(ElderPresentOrnamentBlock.BLOCK_ID, itemOfBlock(BlockRegistry.ELDER_PRESENT_ORNAMENT, ElderPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_ORNAMENT =
            ITEMS.register(CandyCaneOrnamentBlock.BLOCK_ID, itemOfBlock(BlockRegistry.CANDY_CANE_ORNAMENT, CandyCaneOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SANTA_ELF_ORNAMENT =
            ITEMS.register(SantaElfOrnamentBlock.BLOCK_ID, itemOfBlock(BlockRegistry.SANTA_ELF_ORNAMENT, SantaElfOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_MAN_ORNAMENT =
            ITEMS.register(GingerbreadManOrnamentBlock.BLOCK_ID, itemOfBlock(BlockRegistry.GINGERBREAD_MAN_ORNAMENT, GingerbreadManOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GRINCH_ORNAMENT =
            ITEMS.register(GrinchOrnamentBlock.BLOCK_ID, itemOfBlock(BlockRegistry.GRINCH_ORNAMENT, GrinchOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_DOUGH_BLOCK =
            ITEMS.register(RawGingerbreadBlock.BLOCK_ID, itemOfBlock(BlockRegistry.GINGERBREAD_DOUGH_BLOCK, RawGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BLOCK =
            ITEMS.register(GingerbreadBlock.BLOCK_ID, itemOfBlock(BlockRegistry.GINGERBREAD_BLOCK, GingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BLOCK =
            ITEMS.register(SoggyGingerbreadBlock.BLOCK_ID, itemOfBlock(BlockRegistry.SOGGY_GINGERBREAD_BLOCK, SoggyGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BLOCK =
            ITEMS.register(CandyCaneBlock.BLOCK_ID, itemOfBlock(BlockRegistry.CANDY_CANE_BLOCK, CandyCaneOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BLOCK =
            ITEMS.register(FestiveCandyCaneBlock.BLOCK_ID, itemOfBlock(BlockRegistry.FESTIVE_CANDY_CANE_BLOCK, FestiveCandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_STAR =
            ITEMS.register(ChristmasStarBlock.BLOCK_ID, itemOfBlock(BlockRegistry.CHRISTMAS_STAR, ChristmasStarBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> MUSIC_BOX =
            ITEMS.register(MusicBoxBlock.BLOCK_ID, itemOfContainer(BlockRegistry.MUSIC_BOX, MusicBoxBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GIFT_WRAPPING_STATION =
            ITEMS.register(GiftWrapperBlock.BLOCK_ID, itemOfBlock(BlockRegistry.GIFT_WRAPPING_STATION, GiftWrapperBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> MILK_AND_COOKIES =
            ITEMS.register(MilkAndCookiesBlock.BLOCK_ID, itemOfBlock(BlockRegistry.MILK_AND_COOKIES, MilkAndCookiesBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> LOG_CAKE =
            ITEMS.register(LogCakeBlock.BLOCK_ID, itemOfBlock(BlockRegistry.LOG_CAKE, LogCakeBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_HAM =
            ITEMS.register(ChristmasHamBlock.BLOCK_ID, itemOfBlock(BlockRegistry.CHRISTMAS_HAM, ChristmasHamBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_PUDDING =
            ITEMS.register(ChristmasPuddingBlock.BLOCK_ID, itemOfBlock(BlockRegistry.CHRISTMAS_PUDDING, ChristmasPuddingBlock.ITEM_PROPERTIES));
}
