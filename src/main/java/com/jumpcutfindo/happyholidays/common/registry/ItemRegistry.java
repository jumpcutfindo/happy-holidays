package com.jumpcutfindo.happyholidays.common.registry;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.FestiveCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigBlueBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigGoldBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigGreenBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigRedBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigSilverBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigYellowBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.BlueBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.GoldBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.GreenBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.RedBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.SilverBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.ball.YellowBallOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.head.CreeperHeadOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.head.SkeletonHeadOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.head.WitherSkeletonHeadOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.head.ZombieHeadOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.BabyPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.CandyCaneOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.ElderPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.GingerbreadManOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.GrinchOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.PresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary.SantaElfOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.lights.BlueChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.lights.GoldChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.lights.GreenChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.lights.RedChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.lights.SilverChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.lights.YellowChristmasLightBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.BlueTinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.GoldTinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.GreenTinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.RedTinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.SilverTinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.YellowTinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.wall.ChristmasWreathBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.ChristmasHamBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.ChristmasPuddingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.LogCakeBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.MilkAndCookiesBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gifts.GiftWrapperBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.RawGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.SoggyGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.ChristmasStarBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.MusicBoxBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.AdultPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.BabyPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.ElderPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.wall.StockingBlock;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.block.LegendaryOrnamentBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.ChristmasFoodItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.BlueChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.GoldChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.GreenChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.RedChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.SilverChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.YellowChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.HollyItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.PresentScrapItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.block.HeadOrnamentBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.candy.CandyCaneItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.candy.EnchantedCandyCaneItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.candy.FestiveCandyCaneItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.food.EggnogItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gingerbread.GingerbreadCookieItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.gingerbread.RawGingerbreadItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.misc.SantaElfBellItem;
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

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            HappyHolidaysMod.MOD_ID
    );

    public static final RegistryObject<ChristmasItem> RAW_GINGERBREAD =
            ITEMS.register(RawGingerbreadItem.ITEM_ID, RawGingerbreadItem::new);
    public static final RegistryObject<ChristmasItem> GINGERBREAD_COOKIE =
            ITEMS.register(GingerbreadCookieItem.ITEM_ID, GingerbreadCookieItem::new);
    public static final RegistryObject<ChristmasItem> PRESENT_SCRAPS =
            ITEMS.register(PresentScrapItem.ITEM_ID, PresentScrapItem::new);
    public static final RegistryObject<ChristmasItem> HOLLY =
            ITEMS.register(HollyItem.ITEM_ID, HollyItem::new);
    public static final RegistryObject<ChristmasItem> SANTA_ELF_BELL =
            ITEMS.register(SantaElfBellItem.ITEM_ID, SantaElfBellItem::new);
    public static final RegistryObject<ChristmasItem> TOY_PARTS_REQUEST =
            ITEMS.register(ToyPartsRequestItem.ITEM_ID, ToyPartsRequestItem::new);

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

    public static final RegistryObject<ChristmasBlockItem> BABY_PRESENT_BLOCK_ITEM =
            ITEMS.register(BabyPresentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.BABY_PRESENT_BLOCK.get(),
                    BabyPresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ADULT_PRESENT_BLOCK_ITEM =
            ITEMS.register(AdultPresentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.ADULT_PRESENT_BLOCK.get(),
                    AdultPresentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ELDER_PRESENT_BLOCK_ITEM =
            ITEMS.register(ElderPresentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.ELDER_PRESENT_BLOCK.get(),
                    ElderPresentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(RedBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.RED_BALL_ORNAMENT_BLOCK.get(),
                    RedBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BlueBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.BLUE_BALL_ORNAMENT_BLOCK.get(),
                    BlueBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(YellowBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.YELLOW_BALL_ORNAMENT_BLOCK.get(),
                    YellowBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(GreenBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.GREEN_BALL_ORNAMENT_BLOCK.get(),
                    GreenBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(GoldBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.GOLD_BALL_ORNAMENT_BLOCK.get(),
                    GoldBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(SilverBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.SILVER_BALL_ORNAMENT_BLOCK.get(),
                    SilverBallOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> BIG_RED_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigRedBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.BIG_RED_BALL_ORNAMENT_BLOCK.get(),
                            BigRedBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_BLUE_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigBlueBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.BIG_BLUE_BALL_ORNAMENT_BLOCK.get(),
                            BigBlueBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_YELLOW_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigYellowBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.BIG_YELLOW_BALL_ORNAMENT_BLOCK.get(),
                            BigYellowBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_GREEN_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigGreenBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.BIG_GREEN_BALL_ORNAMENT_BLOCK.get(),
                            BigGreenBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_GOLD_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigGoldBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.BIG_GOLD_BALL_ORNAMENT_BLOCK.get(),
                            BigGoldBallOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BIG_SILVER_BALL_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BigSilverBallOrnamentBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.BIG_SILVER_BALL_ORNAMENT_BLOCK.get(),
                            BigSilverBallOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CREEPER_HEAD_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(CreeperHeadOrnamentBlock.BLOCK_ID,
                    () -> new HeadOrnamentBlockItem(BlockRegistry.CREEPER_HEAD_ORNAMENT_BLOCK.get(),
                    CreeperHeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SKELETON_HEAD_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(SkeletonHeadOrnamentBlock.BLOCK_ID,
                    () -> new HeadOrnamentBlockItem(BlockRegistry.SKELETON_HEAD_ORNAMENT_BLOCK.get(),
                    SkeletonHeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> WITHER_SKELETON_HEAD_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(WitherSkeletonHeadOrnamentBlock.BLOCK_ID,
                    () -> new HeadOrnamentBlockItem(BlockRegistry.WITHER_SKELETON_HEAD_ORNAMENT_BLOCK.get(),
                    WitherSkeletonHeadOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ZOMBIE_HEAD_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(ZombieHeadOrnamentBlock.BLOCK_ID,
                    () -> new HeadOrnamentBlockItem(BlockRegistry.ZOMBIE_HEAD_ORNAMENT_BLOCK.get(),
                    ZombieHeadOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_TINSEL_BLOCK_ITEM =
            ITEMS.register(RedTinselBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.RED_TINSEL_BLOCK.get(),
                            RedTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_TINSEL_BLOCK_ITEM =
            ITEMS.register(BlueTinselBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.BLUE_TINSEL_BLOCK.get(),
                            BlueTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_TINSEL_BLOCK_ITEM =
            ITEMS.register(YellowTinselBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.YELLOW_TINSEL_BLOCK.get(),
                            YellowTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_TINSEL_BLOCK_ITEM =
            ITEMS.register(GreenTinselBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.GREEN_TINSEL_BLOCK.get(),
                            GreenTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_TINSEL_BLOCK_ITEM =
            ITEMS.register(GoldTinselBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.GOLD_TINSEL_BLOCK.get(),
                            GoldTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_TINSEL_BLOCK_ITEM =
            ITEMS.register(SilverTinselBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.SILVER_TINSEL_BLOCK.get(),
                            SilverTinselBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RED_CHRISTMAS_LIGHT_BLOCK_ITEM =
            ITEMS.register(RedChristmasLightBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.RED_CHRISTMAS_LIGHT_BLOCK.get(),
                            RedChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_CHRISTMAS_LIGHT_BLOCK_ITEM =
            ITEMS.register(BlueChristmasLightBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.BLUE_CHRISTMAS_LIGHT_BLOCK.get(),
                            BlueChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> YELLOW_CHRISTMAS_LIGHT_BLOCK_ITEM =
            ITEMS.register(YellowChristmasLightBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.YELLOW_CHRISTMAS_LIGHT_BLOCK.get(),
                            YellowChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GREEN_CHRISTMAS_LIGHT_BLOCK_ITEM =
            ITEMS.register(GreenChristmasLightBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.GREEN_CHRISTMAS_LIGHT_BLOCK.get(),
                            GreenChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GOLD_CHRISTMAS_LIGHT_BLOCK_ITEM =
            ITEMS.register(GoldChristmasLightBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.GOLD_CHRISTMAS_LIGHT_BLOCK.get(),
                            GoldChristmasLightBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SILVER_CHRISTMAS_LIGHT_BLOCK_ITEM =
            ITEMS.register(SilverChristmasLightBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.SILVER_CHRISTMAS_LIGHT_BLOCK.get(),
                            SilverChristmasLightBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> BABY_PRESENT_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(BabyPresentOrnamentBlock.BLOCK_ID,
                    () -> new LegendaryOrnamentBlockItem(BlockRegistry.BABY_PRESENT_ORNAMENT_BLOCK.get(),
                            BabyPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> PRESENT_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(PresentOrnamentBlock.BLOCK_ID,
                    () -> new LegendaryOrnamentBlockItem(BlockRegistry.PRESENT_ORNAMENT_BLOCK.get(),
                            PresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> ELDER_PRESENT_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(ElderPresentOrnamentBlock.BLOCK_ID,
                    () -> new LegendaryOrnamentBlockItem(BlockRegistry.ELDER_PRESENT_ORNAMENT_BLOCK.get(),
                            ElderPresentOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_ORNAMENT_BLOCK_ITEM =
            ITEMS.register(CandyCaneOrnamentBlock.BLOCK_ID,
                    () -> new LegendaryOrnamentBlockItem(BlockRegistry.CANDY_CANE_ORNAMENT_BLOCK.get(),
                            CandyCaneOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> SANTA_ELF_ORNAMENT_BLOCK =
            ITEMS.register(SantaElfOrnamentBlock.BLOCK_ID,
                    () -> new LegendaryOrnamentBlockItem(BlockRegistry.SANTA_ELF_ORNAMENT_BLOCK.get(),
                            SantaElfOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_MAN_ORNAMENT_BLOCK =
            ITEMS.register(GingerbreadManOrnamentBlock.BLOCK_ID,
                    () -> new LegendaryOrnamentBlockItem(BlockRegistry.GINGERBREAD_MAN_ORNAMENT_BLOCK.get(),
                            GingerbreadManOrnamentBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GRINCH_ORNAMENT_BLOCK =
            ITEMS.register(GrinchOrnamentBlock.BLOCK_ID,
                    () -> new LegendaryOrnamentBlockItem(BlockRegistry.GRINCH_ORNAMENT_BLOCK.get(),
                            GrinchOrnamentBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> RAW_GINGERBREAD_BLOCK_ITEM =
            ITEMS.register(RawGingerbreadBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.RAW_GINGERBREAD_BLOCK.get(),
                            RawGingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> GINGERBREAD_BLOCK_ITEM =
            ITEMS.register(GingerbreadBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.GINGERBREAD_BLOCK.get(),
                            GingerbreadBlock.ITEM_PROPERITES));
    public static final RegistryObject<ChristmasBlockItem> SOGGY_GINGERBREAD_BLOCK_ITEM =
            ITEMS.register(SoggyGingerbreadBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.SOGGY_GINGERBREAD_BLOCK.get(),
                            SoggyGingerbreadBlock.ITEM_PROPERITES));

    public static final RegistryObject<ChristmasBlockItem> CANDY_CANE_BLOCK_ITEM =
            ITEMS.register(CandyCaneBlock.BLOCK_ID, () -> new ChristmasBlockItem(BlockRegistry.CANDY_CANE_BLOCK.get(),
                            CandyCaneBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> FESTIVE_CANDY_CANE_BLOCK_ITEM =
            ITEMS.register(FestiveCandyCaneBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.FESTIVE_CANDY_CANE_BLOCK.get(),
                            FestiveCandyCaneBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_STAR_BLOCK_ITEM =
            ITEMS.register(ChristmasStarBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.CHRISTMAS_STAR_BLOCK.get(),
                            ChristmasStarBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> MUSIC_BOX_BLOCK_ITEM =
            ITEMS.register(MusicBoxBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.MUSIC_BOX_BLOCK.get(),
                            MusicBoxBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> GIFT_WRAPPER_BLOCK_ITEM =
            ITEMS.register(GiftWrapperBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.GIFT_WRAPPER_BLOCK.get(),
                            GiftWrapperBlock.ITEM_PROPERTIES));

    public static final RegistryObject<ChristmasBlockItem> STOCKING_BLOCK_ITEM =
            ITEMS.register(StockingBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.STOCKING_BLOCK.get(),
                            StockingBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CHIRSTMAS_WREATH_BLOCK_ITEM =
            ITEMS.register(ChristmasWreathBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.CHRISTMAS_WREATH_BLOCK.get(),
                            ChristmasWreathBlock.ITEM_PROPERTIES));


    public static final RegistryObject<ChristmasFoodBlockItem> MILK_AND_COOKIES_BLOCK_ITEM =
            ITEMS.register(MilkAndCookiesBlock.BLOCK_ID,
                    () -> new ChristmasFoodBlockItem(BlockRegistry.MILK_AND_COOKIES_BLOCK.get(),
                            MilkAndCookiesBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasFoodBlockItem> LOG_CAKE_BLOCK_ITEM =
            ITEMS.register(LogCakeBlock.BLOCK_ID,
                    () -> new ChristmasFoodBlockItem(BlockRegistry.LOG_CAKE_BLOCK.get(),
                            LogCakeBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasFoodBlockItem> CHRISTMAS_HAM_BLOCK_ITEM =
            ITEMS.register(ChristmasHamBlock.BLOCK_ID,
                    () -> new ChristmasFoodBlockItem(BlockRegistry.CHRISTMAS_HAM_BLOCK.get(),
                            ChristmasHamBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasFoodBlockItem> CHRISTMAS_PUDDING_BLOCK_ITEM =
            ITEMS.register(ChristmasPuddingBlock.BLOCK_ID,
                () -> new ChristmasFoodBlockItem(BlockRegistry.CHRISTMAS_PUDDING_BLOCK.get(),
                    ChristmasPuddingBlock.ITEM_PROPERTIES));
}
