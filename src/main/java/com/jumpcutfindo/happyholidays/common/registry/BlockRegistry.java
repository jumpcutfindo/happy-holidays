package com.jumpcutfindo.happyholidays.common.registry;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasContainerBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.FestiveCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.stockings.BlueStockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.stockings.GoldStockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.stockings.GreenStockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.stockings.RedStockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.stockings.SilverStockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.stockings.YellowStockingBlock;
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
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.ChristmasWreathBlock;
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
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.stockings.StockingBlock;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS,
            HappyHolidaysMod.MOD_ID
    );

    public static final RegistryObject<ChristmasBlock> BABY_PRESENT_BLOCK =
            BLOCKS.register(BabyPresentBlock.BLOCK_ID, BabyPresentBlock::new);
    public static final RegistryObject<ChristmasBlock> ADULT_PRESENT_BLOCK =
            BLOCKS.register(AdultPresentBlock.BLOCK_ID, AdultPresentBlock::new);
    public static final RegistryObject<ChristmasBlock> ELDER_PRESENT_BLOCK =
            BLOCKS.register(ElderPresentBlock.BLOCK_ID, ElderPresentBlock::new);

    public static final RegistryObject<ChristmasBlock> RED_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(RedBallOrnamentBlock.BLOCK_ID, RedBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BLUE_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BlueBallOrnamentBlock.BLOCK_ID, BlueBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> YELLOW_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(YellowBallOrnamentBlock.BLOCK_ID, YellowBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> GREEN_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(GreenBallOrnamentBlock.BLOCK_ID, GreenBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> GOLD_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(GoldBallOrnamentBlock.BLOCK_ID, GoldBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> SILVER_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(SilverBallOrnamentBlock.BLOCK_ID, SilverBallOrnamentBlock::new);

    public static final RegistryObject<ChristmasBlock> BIG_RED_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigRedBallOrnamentBlock.BLOCK_ID, BigRedBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_BLUE_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigBlueBallOrnamentBlock.BLOCK_ID, BigBlueBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_YELLOW_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigYellowBallOrnamentBlock.BLOCK_ID, BigYellowBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_GREEN_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigGreenBallOrnamentBlock.BLOCK_ID, BigGreenBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_GOLD_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigGoldBallOrnamentBlock.BLOCK_ID, BigGoldBallOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_SILVER_BALL_ORNAMENT_BLOCK =
            BLOCKS.register(BigSilverBallOrnamentBlock.BLOCK_ID, BigSilverBallOrnamentBlock::new);

    public static final RegistryObject<ChristmasBlock> CREEPER_HEAD_ORNAMENT_BLOCK =
            BLOCKS.register(CreeperHeadOrnamentBlock.BLOCK_ID, CreeperHeadOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> SKELETON_HEAD_ORNAMENT_BLOCK =
            BLOCKS.register(SkeletonHeadOrnamentBlock.BLOCK_ID, SkeletonHeadOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> WITHER_SKELETON_HEAD_ORNAMENT_BLOCK =
            BLOCKS.register(WitherSkeletonHeadOrnamentBlock.BLOCK_ID, WitherSkeletonHeadOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> ZOMBIE_HEAD_ORNAMENT_BLOCK =
            BLOCKS.register(ZombieHeadOrnamentBlock.BLOCK_ID, ZombieHeadOrnamentBlock::new);

    public static final RegistryObject<ChristmasBlock> RED_TINSEL_BLOCK =
            BLOCKS.register(RedTinselBlock.BLOCK_ID, RedTinselBlock::new);
    public static final RegistryObject<ChristmasBlock> BLUE_TINSEL_BLOCK =
            BLOCKS.register(BlueTinselBlock.BLOCK_ID, BlueTinselBlock::new);
    public static final RegistryObject<ChristmasBlock> YELLOW_TINSEL_BLOCK =
            BLOCKS.register(YellowTinselBlock.BLOCK_ID, YellowTinselBlock::new);
    public static final RegistryObject<ChristmasBlock> GREEN_TINSEL_BLOCK =
            BLOCKS.register(GreenTinselBlock.BLOCK_ID, GreenTinselBlock::new);
    public static final RegistryObject<ChristmasBlock> GOLD_TINSEL_BLOCK =
            BLOCKS.register(GoldTinselBlock.BLOCK_ID, GoldTinselBlock::new);
    public static final RegistryObject<ChristmasBlock> SILVER_TINSEL_BLOCK =
            BLOCKS.register(SilverTinselBlock.BLOCK_ID, SilverTinselBlock::new);

    public static final RegistryObject<ChristmasBlock> RED_CHRISTMAS_LIGHT_BLOCK =
            BLOCKS.register(RedChristmasLightBlock.BLOCK_ID, RedChristmasLightBlock::new);
    public static final RegistryObject<ChristmasBlock> BLUE_CHRISTMAS_LIGHT_BLOCK =
            BLOCKS.register(BlueChristmasLightBlock.BLOCK_ID, BlueChristmasLightBlock::new);
    public static final RegistryObject<ChristmasBlock> YELLOW_CHRISTMAS_LIGHT_BLOCK =
            BLOCKS.register(YellowChristmasLightBlock.BLOCK_ID, YellowChristmasLightBlock::new);
    public static final RegistryObject<ChristmasBlock> GREEN_CHRISTMAS_LIGHT_BLOCK =
            BLOCKS.register(GreenChristmasLightBlock.BLOCK_ID, GreenChristmasLightBlock::new);
    public static final RegistryObject<ChristmasBlock> GOLD_CHRISTMAS_LIGHT_BLOCK =
            BLOCKS.register(GoldChristmasLightBlock.BLOCK_ID, GoldChristmasLightBlock::new);
    public static final RegistryObject<ChristmasBlock> SILVER_CHRISTMAS_LIGHT_BLOCK =
            BLOCKS.register(SilverChristmasLightBlock.BLOCK_ID, SilverChristmasLightBlock::new);

    public static final RegistryObject<ChristmasBlock> RED_STOCKING_BLOCK =
            BLOCKS.register(RedStockingBlock.BLOCK_ID, RedStockingBlock::new);
    public static final RegistryObject<ChristmasBlock> BLUE_STOCKING_BLOCK =
            BLOCKS.register(BlueStockingBlock.BLOCK_ID, BlueStockingBlock::new);
    public static final RegistryObject<ChristmasBlock> YELLOW_STOCKING_BLOCK =
            BLOCKS.register(YellowStockingBlock.BLOCK_ID, YellowStockingBlock::new);
    public static final RegistryObject<ChristmasBlock> GREEN_STOCKING_BLOCK =
            BLOCKS.register(GreenStockingBlock.BLOCK_ID, GreenStockingBlock::new);
    public static final RegistryObject<ChristmasBlock> GOLD_STOCKING_BLOCK =
            BLOCKS.register(GoldStockingBlock.BLOCK_ID, GoldStockingBlock::new);
    public static final RegistryObject<ChristmasBlock> SILVER_STOCKING_BLOCK =
            BLOCKS.register(SilverStockingBlock.BLOCK_ID, SilverStockingBlock::new);

    public static final RegistryObject<ChristmasBlock> CHRISTMAS_WREATH_BLOCK =
            BLOCKS.register(ChristmasWreathBlock.BLOCK_ID, ChristmasWreathBlock::new);

    public static final RegistryObject<ChristmasBlock> BABY_PRESENT_ORNAMENT_BLOCK =
            BLOCKS.register(BabyPresentOrnamentBlock.BLOCK_ID, BabyPresentOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> PRESENT_ORNAMENT_BLOCK =
            BLOCKS.register(PresentOrnamentBlock.BLOCK_ID, PresentOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> ELDER_PRESENT_ORNAMENT_BLOCK =
            BLOCKS.register(ElderPresentOrnamentBlock.BLOCK_ID, ElderPresentOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> CANDY_CANE_ORNAMENT_BLOCK =
            BLOCKS.register(CandyCaneOrnamentBlock.BLOCK_ID, CandyCaneOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> SANTA_ELF_ORNAMENT_BLOCK =
            BLOCKS.register(SantaElfOrnamentBlock.BLOCK_ID, SantaElfOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> GINGERBREAD_MAN_ORNAMENT_BLOCK =
            BLOCKS.register(GingerbreadManOrnamentBlock.BLOCK_ID, GingerbreadManOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> GRINCH_ORNAMENT_BLOCK =
            BLOCKS.register(GrinchOrnamentBlock.BLOCK_ID, GrinchOrnamentBlock::new);

    public static final RegistryObject<ChristmasBlock> RAW_GINGERBREAD_BLOCK =
            BLOCKS.register(RawGingerbreadBlock.BLOCK_ID, RawGingerbreadBlock::new);
    public static final RegistryObject<ChristmasBlock> GINGERBREAD_BLOCK =
            BLOCKS.register(GingerbreadBlock.BLOCK_ID, GingerbreadBlock::new);
    public static final RegistryObject<ChristmasBlock> SOGGY_GINGERBREAD_BLOCK =
            BLOCKS.register(SoggyGingerbreadBlock.BLOCK_ID, SoggyGingerbreadBlock::new);

    public static final RegistryObject<ChristmasBlock> CANDY_CANE_BLOCK =
            BLOCKS.register(CandyCaneBlock.BLOCK_ID, CandyCaneBlock::new);
    public static final RegistryObject<ChristmasBlock> FESTIVE_CANDY_CANE_BLOCK =
            BLOCKS.register(FestiveCandyCaneBlock.BLOCK_ID, FestiveCandyCaneBlock::new);

    public static final RegistryObject<ChristmasBlock> CHRISTMAS_STAR_BLOCK =
            BLOCKS.register(ChristmasStarBlock.BLOCK_ID, ChristmasStarBlock::new);
    public static final RegistryObject<ChristmasContainerBlock> MUSIC_BOX_BLOCK =
            BLOCKS.register(MusicBoxBlock.BLOCK_ID, MusicBoxBlock::new);
    public static final RegistryObject<ChristmasBlock> GIFT_WRAPPER_BLOCK =
            BLOCKS.register(GiftWrapperBlock.BLOCK_ID, GiftWrapperBlock::new);

    public static final RegistryObject<ChristmasBlock> MILK_AND_COOKIES_BLOCK =
            BLOCKS.register(MilkAndCookiesBlock.BLOCK_ID, MilkAndCookiesBlock::new);
    public static final RegistryObject<ChristmasBlock> LOG_CAKE_BLOCK =
            BLOCKS.register(LogCakeBlock.BLOCK_ID, LogCakeBlock::new);
    public static final RegistryObject<ChristmasBlock> CHRISTMAS_HAM_BLOCK =
            BLOCKS.register(ChristmasHamBlock.BLOCK_ID, ChristmasHamBlock::new);
    public static final RegistryObject<ChristmasBlock> CHRISTMAS_PUDDING_BLOCK =
            BLOCKS.register(ChristmasPuddingBlock.BLOCK_ID, ChristmasPuddingBlock::new);
}
