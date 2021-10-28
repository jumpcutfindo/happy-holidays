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
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ChristmasBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS,
            HappyHolidaysMod.MOD_ID
    );

    public static Supplier<BlockState> supplierOf(RegistryObject<? extends Block> registryObject) {
        return () -> registryObject.get().defaultBlockState();
    }

    // Presents
    public static final RegistryObject<Block> BABY_PRESENT =
            BLOCKS.register(BabyPresentBlock.BLOCK_ID, BabyPresentBlock::new);
    public static final RegistryObject<Block> ADULT_PRESENT =
            BLOCKS.register(AdultPresentBlock.BLOCK_ID, AdultPresentBlock::new);
    public static final RegistryObject<Block> ELDER_PRESENT =
            BLOCKS.register(ElderPresentBlock.BLOCK_ID, ElderPresentBlock::new);

    // Baubles

    public static final RegistryObject<Block> RED_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.RED_BAUBLE_ID, BaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BLUE_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.BLUE_BAUBLE_ID, BaubleOrnamentBlock::new);
    public static final RegistryObject<Block> YELLOW_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.YELLOW_BAUBLE_ID, BaubleOrnamentBlock::new);
    public static final RegistryObject<Block> GREEN_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.GREEN_BAUBLE_ID, BaubleOrnamentBlock::new);
    public static final RegistryObject<Block> GOLD_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.GOLD_BAUBLE_ID, BaubleOrnamentBlock::new);
    public static final RegistryObject<Block> SILVER_BAUBLE =
            BLOCKS.register(BaubleOrnamentBlock.SILVER_BAUBLE_ID, BaubleOrnamentBlock::new);

    // Big Baubles
    public static final RegistryObject<Block> BIG_RED_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_RED_BAUBLE_ID, BigBaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BIG_BLUE_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_BLUE_BAUBLE_ID, BigBaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BIG_YELLOW_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_YELLOW_BAUBLE_ID, BigBaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BIG_GREEN_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_GREEN_BAUBLE_ID, BigBaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BIG_GOLD_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_GOLD_BAUBLE_ID, BigBaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BIG_SILVER_BAUBLE =
            BLOCKS.register(BigBaubleOrnamentBlock.BIG_SILVER_BAUBLE_ID, BigBaubleOrnamentBlock::new);

    // Tinsels
    public static final RegistryObject<Block> RED_TINSEL =
            BLOCKS.register(TinselBlock.RED_TINSEL_ID, TinselBlock::new);
    public static final RegistryObject<Block> BLUE_TINSEL =
            BLOCKS.register(TinselBlock.BLUE_TINSEL_ID, TinselBlock::new);
    public static final RegistryObject<Block> YELLOW_TINSEL =
            BLOCKS.register(TinselBlock.YELLOW_TINSEL_ID, TinselBlock::new);
    public static final RegistryObject<Block> GREEN_TINSEL =
            BLOCKS.register(TinselBlock.GREEN_TINSEL_ID, TinselBlock::new);
    public static final RegistryObject<Block> GOLD_TINSEL =
            BLOCKS.register(TinselBlock.GOLD_TINSEL_ID, TinselBlock::new);
    public static final RegistryObject<Block> SILVER_TINSEL =
            BLOCKS.register(TinselBlock.SILVER_TINSEL_ID, TinselBlock::new);

    // Christmas Lights
    public static final RegistryObject<Block> RED_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.RED_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);
    public static final RegistryObject<Block> BLUE_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.BLUE_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);
    public static final RegistryObject<Block> YELLOW_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.YELLOW_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);
    public static final RegistryObject<Block> GREEN_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.GREEN_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);
    public static final RegistryObject<Block> GOLD_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.GOLD_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);
    public static final RegistryObject<Block> SILVER_CHRISTMAS_LIGHTS =
            BLOCKS.register(ChristmasLightBlock.SILVER_CHRISTMAS_LIGHTS_ID, ChristmasLightBlock::new);

    // Bells
    public static final RegistryObject<Block> RED_CHRISTMAS_BELLS =
            BLOCKS.register(ChristmasBellBlock.RED_BELLS_ID, ChristmasBellBlock::new);
    public static final RegistryObject<Block> BLUE_CHRISTMAS_BELLS =
            BLOCKS.register(ChristmasBellBlock.BLUE_BELLS_ID, ChristmasBellBlock::new);
    public static final RegistryObject<Block> YELLOW_CHRISTMAS_BELLS =
            BLOCKS.register(ChristmasBellBlock.YELLOW_BELLS_ID, ChristmasBellBlock::new);
    public static final RegistryObject<Block> GREEN_CHRISTMAS_BELLS =
            BLOCKS.register(ChristmasBellBlock.GREEN_BELLS_ID, ChristmasBellBlock::new);
    public static final RegistryObject<Block> GOLD_CHRISTMAS_BELLS =
            BLOCKS.register(ChristmasBellBlock.GOLD_BELLS_ID, ChristmasBellBlock::new);
    public static final RegistryObject<Block> SILVER_CHRISTMAS_BELLS =
            BLOCKS.register(ChristmasBellBlock.SILVER_BELLS_ID, ChristmasBellBlock::new);

    // Head Ornaments
    public static final RegistryObject<Block> CREEPER_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.CREEPER_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> SKELETON_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.SKELETON_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> WITHER_SKELETON_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.WITHER_SKELETON_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> ZOMBIE_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.ZOMBIE_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> DROWNED_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.DROWNED_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> BLAZE_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.BLAZE_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> GHAST_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.GHAST_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> PHANTOM_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.PHANTOM_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> PIG_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.PIG_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> COW_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.COW_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> CHICKEN_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.CHICKEN_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);
    public static final RegistryObject<Block> SHEEP_HEAD_ORNAMENT =
            BLOCKS.register(HeadOrnamentBlock.SHEEP_HEAD_ORNAMENT_ID, HeadOrnamentBlock::new);

    // Stockings
    public static final RegistryObject<Block> RED_STOCKING =
            BLOCKS.register(StockingBlock.RED_STOCKING_ID, StockingBlock::new);
    public static final RegistryObject<Block> BLUE_STOCKING =
            BLOCKS.register(StockingBlock.BLUE_STOCKING_ID, StockingBlock::new);
    public static final RegistryObject<Block> YELLOW_STOCKING =
            BLOCKS.register(StockingBlock.YELLOW_STOCKING_ID, StockingBlock::new);
    public static final RegistryObject<Block> GREEN_STOCKING =
            BLOCKS.register(StockingBlock.GREEN_STOCKING_ID, StockingBlock::new);
    public static final RegistryObject<Block> GOLD_STOCKING =
            BLOCKS.register(StockingBlock.GOLD_STOCKING_ID, StockingBlock::new);
    public static final RegistryObject<Block> SILVER_STOCKING =
            BLOCKS.register(StockingBlock.SILVER_STOCKING_ID, StockingBlock::new);

    // Alphabets
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_TEMPLATE =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_TEMPLATE_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_A =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_A_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_B =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_B_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_C =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_C_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_D =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_D_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_E =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_E_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_F =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_F_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_G =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_G_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_H =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_H_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_I =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_I_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_J =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_J_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_K =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_K_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_L =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_L_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_M =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_M_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_N =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_N_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_O =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_O_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_P =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_P_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_Q =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_Q_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_R =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_R_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_S =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_S_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_T =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_T_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_U =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_U_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_V =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_V_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_W =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_W_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_X =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_X_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_Y =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_Y_ID, AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_Z =
            BLOCKS.register(AlphabetOrnamentBlock.ALPHABET_Z_ID, AlphabetOrnamentBlock::new);

    // Decoration blocks
    public static final RegistryObject<Block> CHRISTMAS_WREATH =
            BLOCKS.register(ChristmasWreathBlock.BLOCK_ID, ChristmasWreathBlock::new);
    public static final RegistryObject<Block> FROST =
            BLOCKS.register(FrostBlock.BLOCK_ID, FrostBlock::new);
    public static final RegistryObject<Block> SANTA_LIST =
            BLOCKS.register(SantaListBlock.BLOCK_ID, SantaListBlock::new);

    // Legendary ornaments
    public static final RegistryObject<Block> BABY_PRESENT_ORNAMENT =
            BLOCKS.register(BabyPresentOrnamentBlock.BLOCK_ID, BabyPresentOrnamentBlock::new);
    public static final RegistryObject<Block> ADULT_PRESENT_ORNAMENT =
            BLOCKS.register(AdultPresentOrnamentBlock.BLOCK_ID, AdultPresentOrnamentBlock::new);
    public static final RegistryObject<Block> ELDER_PRESENT_ORNAMENT =
            BLOCKS.register(ElderPresentOrnamentBlock.BLOCK_ID, ElderPresentOrnamentBlock::new);
    public static final RegistryObject<Block> CANDY_CANE_ORNAMENT =
            BLOCKS.register(CandyCaneOrnamentBlock.BLOCK_ID, CandyCaneOrnamentBlock::new);
    public static final RegistryObject<Block> SANTA_ELF_ORNAMENT =
            BLOCKS.register(SantaElfOrnamentBlock.BLOCK_ID, SantaElfOrnamentBlock::new);
    public static final RegistryObject<Block> GINGERBREAD_MAN_ORNAMENT =
            BLOCKS.register(GingerbreadManOrnamentBlock.BLOCK_ID, GingerbreadManOrnamentBlock::new);
    public static final RegistryObject<Block> GRINCH_ORNAMENT =
            BLOCKS.register(GrinchOrnamentBlock.BLOCK_ID, GrinchOrnamentBlock::new);

    // Gingerbread blocks
    public static final RegistryObject<Block> GINGERBREAD_DOUGH_BLOCK =
            BLOCKS.register(BaseGingerbreadBlock.DOUGH_BLOCK_ID, BaseGingerbreadBlock.Builder.create().dough().soggyResult(BaseGingerbreadBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_DOUGH_STAIRS =
            BLOCKS.register(BaseGingerbreadStairBlock.DOUGH_BLOCK_ID, BaseGingerbreadStairBlock.Builder.create().dough().soggyResult(BaseGingerbreadStairBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_DOUGH_SLAB =
            BLOCKS.register(BaseGingerbreadSlabBlock.DOUGH_BLOCK_ID, BaseGingerbreadSlabBlock.Builder.create().dough().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_DOUGH_WALL =
            BLOCKS.register(BaseGingerbreadWall.DOUGH_BLOCK_ID, BaseGingerbreadWall.Builder.create().dough().soggyResult(BaseGingerbreadWall.SoggyResult.BLOCK).build());

    public static final RegistryObject<Block> GINGERBREAD_DOUGH_BRICKS =
            BLOCKS.register(BaseGingerbreadBlock.DOUGH_BRICKS_ID, BaseGingerbreadBlock.Builder.create().dough().soggyResult(BaseGingerbreadBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_DOUGH_BRICK_STAIRS =
            BLOCKS.register(BaseGingerbreadStairBlock.DOUGH_BRICKS_ID, BaseGingerbreadStairBlock.Builder.create().dough().soggyResult(BaseGingerbreadStairBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_DOUGH_BRICK_SLAB =
            BLOCKS.register(BaseGingerbreadSlabBlock.DOUGH_BRICKS_ID, BaseGingerbreadSlabBlock.Builder.create().dough().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_DOUGH_BRICK_WALL =
            BLOCKS.register(BaseGingerbreadWall.DOUGH_BRICKS_ID, BaseGingerbreadWall.Builder.create().dough().soggyResult(BaseGingerbreadWall.SoggyResult.BRICKS).build());

    public static final RegistryObject<Block> GINGERBREAD_DOUGH_TILES =
            BLOCKS.register(BaseGingerbreadBlock.DOUGH_TILES_ID, BaseGingerbreadBlock.Builder.create().dough().soggyResult(BaseGingerbreadBlock.SoggyResult.TILES).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_DOUGH_TILE_STAIRS =
            BLOCKS.register(BaseGingerbreadStairBlock.DOUGH_TILES_ID, BaseGingerbreadStairBlock.Builder.create().dough().soggyResult(BaseGingerbreadStairBlock.SoggyResult.TILES).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_DOUGH_TILE_SLAB =
            BLOCKS.register(BaseGingerbreadSlabBlock.DOUGH_TILES_ID, BaseGingerbreadSlabBlock.Builder.create().dough().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.TILES).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_DOUGH_TILE_WALL =
            BLOCKS.register(BaseGingerbreadWall.DOUGH_TILES_ID, BaseGingerbreadWall.Builder.create().dough().soggyResult(BaseGingerbreadWall.SoggyResult.TILES).build());

    public static final RegistryObject<Block> GINGERBREAD_BLOCK =
            BLOCKS.register(BaseGingerbreadBlock.COOKED_BLOCK_ID, BaseGingerbreadBlock.Builder.create().cooked().soggyResult(BaseGingerbreadBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_STAIRS =
            BLOCKS.register(BaseGingerbreadStairBlock.COOKED_BLOCK_ID, BaseGingerbreadStairBlock.Builder.create().cooked().soggyResult(BaseGingerbreadStairBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_SLAB =
            BLOCKS.register(BaseGingerbreadSlabBlock.COOKED_BLOCK_ID, BaseGingerbreadSlabBlock.Builder.create().cooked().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_WALL =
            BLOCKS.register(BaseGingerbreadWall.COOKED_BLOCK_ID, BaseGingerbreadWall.Builder.create().cooked().soggyResult(BaseGingerbreadWall.SoggyResult.BLOCK).build());

    public static final RegistryObject<Block> GINGERBREAD_BRICKS =
            BLOCKS.register(BaseGingerbreadBlock.COOKED_BRICKS_ID, BaseGingerbreadBlock.Builder.create().cooked().soggyResult(BaseGingerbreadBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_BRICK_STAIRS =
            BLOCKS.register(BaseGingerbreadStairBlock.COOKED_BRICKS_ID, BaseGingerbreadStairBlock.Builder.create().cooked().soggyResult(BaseGingerbreadStairBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_BRICK_SLAB =
            BLOCKS.register(BaseGingerbreadSlabBlock.COOKED_BRICKS_ID, BaseGingerbreadSlabBlock.Builder.create().cooked().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_BRICK_WALL =
            BLOCKS.register(BaseGingerbreadWall.COOKED_BRICKS_ID, BaseGingerbreadWall.Builder.create().cooked().soggyResult(BaseGingerbreadWall.SoggyResult.BRICKS).build());

    public static final RegistryObject<Block> GINGERBREAD_TILES =
            BLOCKS.register(BaseGingerbreadBlock.COOKED_TILES_ID, BaseGingerbreadBlock.Builder.create().cooked().soggyResult(BaseGingerbreadBlock.SoggyResult.TILES).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_TILE_STAIRS =
            BLOCKS.register(BaseGingerbreadStairBlock.COOKED_TILES_ID, BaseGingerbreadStairBlock.Builder.create().cooked().soggyResult(BaseGingerbreadStairBlock.SoggyResult.TILES).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_TILE_SLAB =
            BLOCKS.register(BaseGingerbreadSlabBlock.COOKED_TILES_ID, BaseGingerbreadSlabBlock.Builder.create().cooked().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.TILES).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_TILE_WALL =
            BLOCKS.register(BaseGingerbreadWall.COOKED_TILES_ID, BaseGingerbreadWall.Builder.create().cooked().soggyResult(BaseGingerbreadWall.SoggyResult.TILES).build());

    public static final RegistryObject<Block> SOGGY_GINGERBREAD_BLOCK =
            BLOCKS.register(BaseGingerbreadBlock.SOGGY_BLOCK_ID, BaseGingerbreadBlock.Builder.create().soggy().build());
    public static final RegistryObject<StairBlock> SOGGY_GINGERBREAD_STAIRS =
            BLOCKS.register(BaseGingerbreadStairBlock.SOGGY_BLOCK_ID, BaseGingerbreadStairBlock.Builder.create().soggy().build());
    public static final RegistryObject<SlabBlock> SOGGY_GINGERBREAD_SLAB =
            BLOCKS.register(BaseGingerbreadSlabBlock.SOGGY_BLOCK_ID, BaseGingerbreadSlabBlock.Builder.create().soggy().build());
    public static final RegistryObject<WallBlock> SOGGY_GINGERBREAD_WALL =
            BLOCKS.register(BaseGingerbreadWall.SOGGY_BLOCK_ID, BaseGingerbreadWall.Builder.create().soggy().build());

    public static final RegistryObject<Block> SOGGY_GINGERBREAD_BRICKS =
            BLOCKS.register(BaseGingerbreadBlock.SOGGY_BRICKS_ID, BaseGingerbreadBlock.Builder.create().soggy().build());
    public static final RegistryObject<StairBlock> SOGGY_GINGERBREAD_BRICK_STAIRS =
            BLOCKS.register(BaseGingerbreadStairBlock.SOGGY_BRICKS_ID, BaseGingerbreadStairBlock.Builder.create().soggy().build());
    public static final RegistryObject<SlabBlock> SOGGY_GINGERBREAD_BRICK_SLAB =
            BLOCKS.register(BaseGingerbreadSlabBlock.SOGGY_BRICKS_ID, BaseGingerbreadSlabBlock.Builder.create().soggy().build());
    public static final RegistryObject<WallBlock> SOGGY_GINGERBREAD_BRICK_WALL =
            BLOCKS.register(BaseGingerbreadWall.SOGGY_BRICKS_ID, BaseGingerbreadWall.Builder.create().soggy().build());

    public static final RegistryObject<Block> SOGGY_GINGERBREAD_TILES =
            BLOCKS.register(BaseGingerbreadBlock.SOGGY_TILES_ID, BaseGingerbreadBlock.Builder.create().soggy().build());
    public static final RegistryObject<StairBlock> SOGGY_GINGERBREAD_TILE_STAIRS =
            BLOCKS.register(BaseGingerbreadStairBlock.SOGGY_TILES_ID, BaseGingerbreadStairBlock.Builder.create().soggy().build());
    public static final RegistryObject<SlabBlock> SOGGY_GINGERBREAD_TILE_SLAB =
            BLOCKS.register(BaseGingerbreadSlabBlock.SOGGY_TILES_ID, BaseGingerbreadSlabBlock.Builder.create().soggy().build());
    public static final RegistryObject<WallBlock> SOGGY_GINGERBREAD_TILE_WALL =
            BLOCKS.register(BaseGingerbreadWall.SOGGY_TILES_ID, BaseGingerbreadWall.Builder.create().soggy().build());

    // Candy cane blocks
    public static final RegistryObject<Block> CANDY_CANE_BLOCK =
            BLOCKS.register(CandyCaneBlock.BLOCK_ID, CandyCaneBlock::new);
    public static final RegistryObject<StairBlock> CANDY_CANE_STAIRS =
            BLOCKS.register(CandyCaneStairBlock.BLOCK_ID, () -> new CandyCaneStairBlock(supplierOf(CANDY_CANE_BLOCK)));
    public static final RegistryObject<SlabBlock> CANDY_CANE_SLAB =
            BLOCKS.register(CandyCaneSlabBlock.BLOCK_ID, CandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> CANDY_CANE_WALL =
            BLOCKS.register(CandyCaneWallBlock.BLOCK_ID, CandyCaneWallBlock::new);

    public static final RegistryObject<Block> CANDY_CANE_BRICKS =
            BLOCKS.register(CandyCaneBlock.BRICKS_ID, CandyCaneBlock::new);
    public static final RegistryObject<StairBlock> CANDY_CANE_BRICK_STAIRS =
            BLOCKS.register(CandyCaneStairBlock.BRICKS_ID, () -> new CandyCaneStairBlock(supplierOf(CANDY_CANE_BRICKS)));
    public static final RegistryObject<SlabBlock> CANDY_CANE_BRICK_SLAB =
            BLOCKS.register(CandyCaneSlabBlock.BRICKS_ID, CandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> CANDY_CANE_BRICK_WALL =
            BLOCKS.register(CandyCaneWallBlock.BRICKS_ID, CandyCaneWallBlock::new);

    public static final RegistryObject<Block> CANDY_CANE_TILES =
            BLOCKS.register(CandyCaneBlock.TILES_ID, CandyCaneBlock::new);
    public static final RegistryObject<StairBlock> CANDY_CANE_TILE_STAIRS =
            BLOCKS.register(CandyCaneStairBlock.TILES_ID, () -> new CandyCaneStairBlock(supplierOf(CANDY_CANE_TILES)));
    public static final RegistryObject<SlabBlock> CANDY_CANE_TILE_SLAB =
            BLOCKS.register(CandyCaneSlabBlock.TILES_ID, CandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> CANDY_CANE_TILE_WALL =
            BLOCKS.register(CandyCaneWallBlock.TILES_ID, CandyCaneWallBlock::new);

    public static final RegistryObject<Block> FESTIVE_CANDY_CANE_BLOCK =
            BLOCKS.register(FestiveCandyCaneBlock.BLOCK_ID, FestiveCandyCaneBlock::new);
    public static final RegistryObject<StairBlock> FESTIVE_CANDY_CANE_STAIRS =
            BLOCKS.register(FestiveCandyCaneStairBlock.BLOCK_ID, () -> new FestiveCandyCaneStairBlock(supplierOf(FESTIVE_CANDY_CANE_BLOCK)));
    public static final RegistryObject<SlabBlock> FESTIVE_CANDY_CANE_SLAB =
            BLOCKS.register(FestiveCandyCaneSlabBlock.BLOCK_ID, FestiveCandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> FESTIVE_CANDY_CANE_WALL =
            BLOCKS.register(FestiveCandyCaneWallBlock.BLOCK_ID, FestiveCandyCaneWallBlock::new);

    public static final RegistryObject<Block> FESTIVE_CANDY_CANE_BRICKS =
            BLOCKS.register(FestiveCandyCaneBlock.BRICKS_ID, FestiveCandyCaneBlock::new);
    public static final RegistryObject<StairBlock> FESTIVE_CANDY_CANE_BRICK_STAIRS =
            BLOCKS.register(FestiveCandyCaneStairBlock.BRICKS_ID, () -> new FestiveCandyCaneStairBlock(supplierOf(FESTIVE_CANDY_CANE_BRICKS)));
    public static final RegistryObject<SlabBlock> FESTIVE_CANDY_CANE_BRICK_SLAB =
            BLOCKS.register(FestiveCandyCaneSlabBlock.BRICKS_ID, FestiveCandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> FESTIVE_CANDY_CANE_BRICK_WALL =
            BLOCKS.register(FestiveCandyCaneWallBlock.BRICKS_ID, FestiveCandyCaneWallBlock::new);

    public static final RegistryObject<Block> FESTIVE_CANDY_CANE_TILES =
            BLOCKS.register(FestiveCandyCaneBlock.TILES_ID, FestiveCandyCaneBlock::new);
    public static final RegistryObject<StairBlock> FESTIVE_CANDY_CANE_TILE_STAIRS =
            BLOCKS.register(FestiveCandyCaneStairBlock.TILES_ID, () -> new FestiveCandyCaneStairBlock(supplierOf(FESTIVE_CANDY_CANE_TILES)));
    public static final RegistryObject<SlabBlock> FESTIVE_CANDY_CANE_TILE_SLAB =
            BLOCKS.register(FestiveCandyCaneSlabBlock.TILES_ID, FestiveCandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> FESTIVE_CANDY_CANE_TILE_WALL =
            BLOCKS.register(FestiveCandyCaneWallBlock.TILES_ID, FestiveCandyCaneWallBlock::new);

    // Miscellaneous blocks
    public static final RegistryObject<Block> CHRISTMAS_STAR =
            BLOCKS.register(ChristmasStarBlock.BLOCK_ID, ChristmasStarBlock::new);
    public static final RegistryObject<Block> MUSIC_BOX =
            BLOCKS.register(MusicBoxBlock.BLOCK_ID, MusicBoxBlock::new);
    public static final RegistryObject<Block> GIFT_WRAPPING_STATION =
            BLOCKS.register(GiftWrapperBlock.BLOCK_ID, GiftWrapperBlock::new);

    // Christmas foods
    public static final RegistryObject<Block> MILK_AND_COOKIES =
            BLOCKS.register(MilkAndCookiesBlock.BLOCK_ID, MilkAndCookiesBlock::new);
    public static final RegistryObject<Block> LOG_CAKE =
            BLOCKS.register(LogCakeBlock.BLOCK_ID, LogCakeBlock::new);
    public static final RegistryObject<Block> CHRISTMAS_HAM =
            BLOCKS.register(ChristmasHamBlock.BLOCK_ID, ChristmasHamBlock::new);
    public static final RegistryObject<Block> CHRISTMAS_PUDDING =
            BLOCKS.register(ChristmasPuddingBlock.BLOCK_ID, ChristmasPuddingBlock::new);

    public static boolean isInfluencedByStar(Block block) {
        return ChristmasTags.Blocks.STAR_AFFECTED_BLOCKS.contains(block);
    }
}
