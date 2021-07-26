package com.bayobayobayo.happyholidays.common.registry;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.block.christmas.candy.CandyCaneBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.candy.FestiveCandyCaneBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigBlueBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigGoldBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigGreenBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigRedBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigSilverBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BigYellowBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.BlueBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.GoldBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.GreenBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.RedBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.SilverBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.ball.YellowBallOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.head.CreeperHeadOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.head.SkeletonHeadOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.head.WitherSkeletonHeadOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.head.ZombieHeadOrnamentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.lights.BlueChristmasLightBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.lights.GoldChristmasLightBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.lights.GreenChristmasLightBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.lights.RedChristmasLightBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.lights.SilverChristmasLightBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.lights.YellowChristmasLightBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.BlueTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.GoldTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.GreenTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.RedTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.SilverTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.decorations.ornaments.tinsel.YellowTinselBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.food.ChristmasHamBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.food.ChristmasPuddingBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.food.LogCakeBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.food.MilkAndCookiesBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.RawGingerbreadBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.gingerbread.SoggyGingerbreadBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.misc.ChristmasStarBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.misc.MusicBoxBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.presents.AdultPresentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.presents.BabyPresentBlock;
import com.bayobayobayo.happyholidays.common.block.christmas.presents.ElderPresentBlock;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasBlockItem;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.item.christmas.misc.HollyItem;
import com.bayobayobayo.happyholidays.common.item.christmas.misc.PresentScrapItem;
import com.bayobayobayo.happyholidays.common.item.christmas.block.HeadOrnamentBlockItem;
import com.bayobayobayo.happyholidays.common.item.christmas.candy.CandyCaneItem;
import com.bayobayobayo.happyholidays.common.item.christmas.candy.EnchantedCandyCaneItem;
import com.bayobayobayo.happyholidays.common.item.christmas.candy.FestiveCandyCaneItem;
import com.bayobayobayo.happyholidays.common.item.christmas.food.EggnogItem;
import com.bayobayobayo.happyholidays.common.item.christmas.gingerbread.GingerbreadCookieItem;
import com.bayobayobayo.happyholidays.common.item.christmas.gingerbread.RawGingerbreadItem;
import com.bayobayobayo.happyholidays.common.item.christmas.music.SheetMusicItem;

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
    public static final RegistryObject<ChristmasItem> SHEET_MUSIC =
            ITEMS.register(SheetMusicItem.ITEM_ID, SheetMusicItem::new);

    public static final RegistryObject<ChristmasItem> CANDY_CANE =
            ITEMS.register(CandyCaneItem.ITEM_ID, CandyCaneItem::new);
    public static final RegistryObject<ChristmasItem> FESTIVE_CANDY_CANE =
            ITEMS.register(FestiveCandyCaneItem.ITEM_ID, FestiveCandyCaneItem::new);
    public static final RegistryObject<ChristmasItem> ENCHANTED_CANDY_CANE =
            ITEMS.register(EnchantedCandyCaneItem.ITEM_ID, EnchantedCandyCaneItem::new);

    public static final RegistryObject<ChristmasItem> EGGNOG =
            ITEMS.register(EggnogItem.ITEM_ID, EggnogItem::new);

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
            ITEMS.register(RedTinselBlock.BLOCK_ID, () -> new ChristmasBlockItem(BlockRegistry.RED_TINSEL_BLOCK.get(),
                    RedTinselBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> BLUE_TINSEL_BLOCK_ITEM =
            ITEMS.register(BlueTinselBlock.BLOCK_ID, () -> new ChristmasBlockItem(BlockRegistry.BLUE_TINSEL_BLOCK.get(),
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
            ITEMS.register(GoldTinselBlock.BLOCK_ID, () -> new ChristmasBlockItem(BlockRegistry.GOLD_TINSEL_BLOCK.get(),
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

    public static final RegistryObject<ChristmasBlockItem> MILK_AND_COOKIES_BLOCK_ITEM =
            ITEMS.register(MilkAndCookiesBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.MILK_AND_COOKIES_BLOCK.get(),
                            MilkAndCookiesBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> LOG_CAKE_BLOCK_ITEM =
            ITEMS.register(LogCakeBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.LOG_CAKE_BLOCK.get(),
                            LogCakeBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_HAM_BLOCK_ITEM =
            ITEMS.register(ChristmasHamBlock.BLOCK_ID,
                    () -> new ChristmasBlockItem(BlockRegistry.CHRISTMAS_HAM_BLOCK.get(),
                            ChristmasHamBlock.ITEM_PROPERTIES));
    public static final RegistryObject<ChristmasBlockItem> CHRISTMAS_PUDDING_BLOCK_ITEM =
            ITEMS.register(ChristmasPuddingBlock.BLOCK_ID,
                () -> new ChristmasBlockItem(BlockRegistry.CHRISTMAS_PUDDING_BLOCK.get(),
                    ChristmasPuddingBlock.ITEM_PROPERTIES));
}
