package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.BaseCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.FestiveCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.alphabets.AlphabetOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.ChristmasWreathBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.SantaListBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc.StockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.BaubleOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common.BigBaubleOrnamentBlock;
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
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.GingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.RawGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread.SoggyGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.ChristmasStarBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.GiftWrapperBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.MusicBoxBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.AdultPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.BabyPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.ElderPresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.presents.PresentBlock;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ChristmasBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS,
            HappyHolidaysMod.MOD_ID
    );

    // Presents
    public static final RegistryObject<ChristmasBlock> BABY_PRESENT =
            BLOCKS.register(BabyPresentBlock.BLOCK_ID, BabyPresentBlock::new);
    public static final RegistryObject<ChristmasBlock> ADULT_PRESENT =
            BLOCKS.register(AdultPresentBlock.BLOCK_ID, AdultPresentBlock::new);
    public static final RegistryObject<ChristmasBlock> ELDER_PRESENT =
            BLOCKS.register(ElderPresentBlock.BLOCK_ID, ElderPresentBlock::new);

    // Baubles
    public static final RegistryObject<ChristmasBlock> RED_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.RED_BAUBLE_ID, BaubleOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BLUE_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.BLUE_BAUBLE_ID, BaubleOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> YELLOW_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.YELLOW_BAUBLE_ID, BaubleOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> GREEN_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.GREEN_BAUBLE_ID, BaubleOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> GOLD_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.GOLD_BAUBLE_ID, BaubleOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> SILVER_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.SILVER_BAUBLE_ID, BaubleOrnamentBlock::new);

    // Big Baubles
    public static final RegistryObject<ChristmasBlock> BIG_RED_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_RED_BAUBLE_ID, BigBaubleOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_BLUE_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_BLUE_BAUBLE_ID, BigBaubleOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_YELLOW_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_YELLOW_BAUBLE_ID, BigBaubleOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_GREEN_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_GREEN_BAUBLE_ID, BigBaubleOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_GOLD_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_GOLD_BAUBLE_ID, BigBaubleOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> BIG_SILVER_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_SILVER_BAUBLE_ID, BigBaubleOrnamentBlock::new);

    // Tinsels
    public static final RegistryObject<ChristmasBlock> RED_TINSEL =
            BLOCKS.register(TinselBlock.RED_TINSEL_ID, TinselBlock::new);
    public static final RegistryObject<ChristmasBlock> BLUE_TINSEL =
            BLOCKS.register(TinselBlock.BLUE_TINSEL_ID, TinselBlock::new);
    public static final RegistryObject<ChristmasBlock> YELLOW_TINSEL =
            BLOCKS.register(TinselBlock.YELLOW_TINSEL_ID, TinselBlock::new);
    public static final RegistryObject<ChristmasBlock> GREEN_TINSEL =
            BLOCKS.register(TinselBlock.GREEN_TINSEL_ID, TinselBlock::new);
    public static final RegistryObject<ChristmasBlock> GOLD_TINSEL =
            BLOCKS.register(TinselBlock.GOLD_TINSEL_ID, TinselBlock::new);
    public static final RegistryObject<ChristmasBlock> SILVER_TINSEL =
            BLOCKS.register(TinselBlock.SILVER_TINSEL_ID, TinselBlock::new);

    // Christmas Lights
    public static final RegistryObject<ChristmasBlock> RED_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.RED_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);
    public static final RegistryObject<ChristmasBlock> BLUE_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.BLUE_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);
    public static final RegistryObject<ChristmasBlock> YELLOW_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.YELLOW_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);
    public static final RegistryObject<ChristmasBlock> GREEN_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.GREEN_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);
    public static final RegistryObject<ChristmasBlock> GOLD_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.GOLD_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);
    public static final RegistryObject<ChristmasBlock> SILVER_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.SILVER_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);

    // Head Ornaments
    public static final RegistryObject<ChristmasBlock> CREEPER_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.CREEPER_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> SKELETON_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.SKELETON_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> WITHER_SKELETON_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.WITHER_SKELETON_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> ZOMBIE_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.ZOMBIE_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);

    // Stockings
    public static final RegistryObject<ChristmasBlock> RED_STOCKING =
            BLOCKS.register(StockingBlock.RED_STOCKING_ID, StockingBlock::new);
    public static final RegistryObject<ChristmasBlock> BLUE_STOCKING =
            BLOCKS.register(StockingBlock.BLUE_STOCKING_ID, StockingBlock::new);
    public static final RegistryObject<ChristmasBlock> YELLOW_STOCKING =
            BLOCKS.register(StockingBlock.YELLOW_STOCKING_ID, StockingBlock::new);
    public static final RegistryObject<ChristmasBlock> GREEN_STOCKING =
            BLOCKS.register(StockingBlock.GREEN_STOCKING_ID, StockingBlock::new);
    public static final RegistryObject<ChristmasBlock> GOLD_STOCKING =
            BLOCKS.register(StockingBlock.GOLD_STOCKING_ID, StockingBlock::new);
    public static final RegistryObject<ChristmasBlock> SILVER_STOCKING =
            BLOCKS.register(StockingBlock.SILVER_STOCKING_ID, StockingBlock::new);

    // Alphabets
    public static final RegistryObject<ChristmasBlock> ALPHABET_ORNAMENT_TEMPLATE =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_TEMPLATE_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> ALPHABET_ORNAMENT_A =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_A_ID, AlphabetOrnamentBlock::new);

    // Decoration blocks
    public static final RegistryObject<ChristmasBlock> CHRISTMAS_WREATH =
            BLOCKS.register(ChristmasWreathBlock.BLOCK_ID, ChristmasWreathBlock::new);
    public static final RegistryObject<ChristmasBlock> SANTA_LIST =
            BLOCKS.register(SantaListBlock.BLOCK_ID, SantaListBlock::new);

    // Legendary ornaments
    public static final RegistryObject<ChristmasBlock> BABY_PRESENT_ORNAMENT =
            BLOCKS.register(BabyPresentOrnamentBlock.BLOCK_ID, BabyPresentOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> ADULT_PRESENT_ORNAMENT =
            BLOCKS.register(AdultPresentOrnamentBlock.BLOCK_ID, AdultPresentOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> ELDER_PRESENT_ORNAMENT =
            BLOCKS.register(ElderPresentOrnamentBlock.BLOCK_ID, ElderPresentOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> CANDY_CANE_ORNAMENT =
            BLOCKS.register(CandyCaneOrnamentBlock.BLOCK_ID, CandyCaneOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> SANTA_ELF_ORNAMENT =
            BLOCKS.register(SantaElfOrnamentBlock.BLOCK_ID, SantaElfOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> GINGERBREAD_MAN_ORNAMENT =
            BLOCKS.register(GingerbreadManOrnamentBlock.BLOCK_ID, GingerbreadManOrnamentBlock::new);
    public static final RegistryObject<ChristmasBlock> GRINCH_ORNAMENT =
            BLOCKS.register(GrinchOrnamentBlock.BLOCK_ID, GrinchOrnamentBlock::new);

    // Gingerbread blocks
    public static final RegistryObject<ChristmasBlock> GINGERBREAD_DOUGH_BLOCK =
            BLOCKS.register(RawGingerbreadBlock.BLOCK_ID, RawGingerbreadBlock::new);
    public static final RegistryObject<ChristmasBlock> GINGERBREAD_BLOCK =
            BLOCKS.register(GingerbreadBlock.BLOCK_ID, GingerbreadBlock::new);
    public static final RegistryObject<ChristmasBlock> SOGGY_GINGERBREAD_BLOCK =
            BLOCKS.register(SoggyGingerbreadBlock.BLOCK_ID, SoggyGingerbreadBlock::new);

    // Candy cane blocks
    public static final RegistryObject<ChristmasBlock> CANDY_CANE_BLOCK =
            BLOCKS.register(CandyCaneBlock.BLOCK_ID, CandyCaneBlock::new);
    public static final RegistryObject<ChristmasBlock> FESTIVE_CANDY_CANE_BLOCK =
            BLOCKS.register(FestiveCandyCaneBlock.BLOCK_ID, FestiveCandyCaneBlock::new);

    // Miscellaneous blocks
    public static final RegistryObject<ChristmasBlock> CHRISTMAS_STAR =
            BLOCKS.register(ChristmasStarBlock.BLOCK_ID, ChristmasStarBlock::new);
    public static final RegistryObject<ChristmasBlock> MUSIC_BOX =
            BLOCKS.register(MusicBoxBlock.BLOCK_ID, MusicBoxBlock::new);
    public static final RegistryObject<ChristmasBlock> GIFT_WRAPPING_STATION =
            BLOCKS.register(GiftWrapperBlock.BLOCK_ID, GiftWrapperBlock::new);

    // Christmas foods
    public static final RegistryObject<ChristmasBlock> MILK_AND_COOKIES =
            BLOCKS.register(MilkAndCookiesBlock.BLOCK_ID, MilkAndCookiesBlock::new);
    public static final RegistryObject<ChristmasBlock> LOG_CAKE =
            BLOCKS.register(LogCakeBlock.BLOCK_ID, LogCakeBlock::new);
    public static final RegistryObject<ChristmasBlock> CHRISTMAS_HAM =
            BLOCKS.register(ChristmasHamBlock.BLOCK_ID, ChristmasHamBlock::new);
    public static final RegistryObject<ChristmasBlock> CHRISTMAS_PUDDING =
            BLOCKS.register(ChristmasPuddingBlock.BLOCK_ID, ChristmasPuddingBlock::new);

    public static boolean isInfluencedByStar(Block block) {
        return block instanceof PresentBlock || block instanceof BaseCandyCaneBlock;
    }
}
