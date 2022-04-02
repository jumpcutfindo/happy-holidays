package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.BaseGingerbreadBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.BaseGingerbreadSlabBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.BaseGingerbreadStairBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.BaseGingerbreadWall;
import com.jumpcutfindo.happyholidays.common.block.christmas.CandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.CandyCaneSlabBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.CandyCaneStairBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.CandyCaneWallBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasStarBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ExplosivePresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.FestiveCandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.FestiveCandyCaneSlabBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.FestiveCandyCaneStairBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.FestiveCandyCaneWallBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.GiftWrapperBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.MusicBoxBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.PresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.WalnutPlantBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.AdultPresentOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.AlphabetOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.BabyPresentOrnamentBlock;
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
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.NutcrackerOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.SantaElfOrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.SantaListBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.StockingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.TinselBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.ChristmasHamBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.ChristmasPuddingBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.LogCakeBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.food.MilkAndCookiesBlock;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
            BLOCKS.register("baby_present", () -> new PresentBlock(PresentBlock.BABY_SHAPE));
    public static final RegistryObject<Block> ADULT_PRESENT =
            BLOCKS.register("adult_present", () -> new PresentBlock(PresentBlock.ADULT_SHAPE));
    public static final RegistryObject<Block> ELDER_PRESENT =
            BLOCKS.register("elder_present", () -> new PresentBlock(PresentBlock.ELDER_SHAPE));

    public static final RegistryObject<Block> EXPLOSIVE_PRESENT =
            BLOCKS.register("explosive_present", ExplosivePresentBlock::new);

    // Baubles
    public static final RegistryObject<Block> RED_BAUBLE =
            BLOCKS.register("red_bauble", BaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BLUE_BAUBLE =
            BLOCKS.register("blue_bauble", BaubleOrnamentBlock::new);
    public static final RegistryObject<Block> YELLOW_BAUBLE =
            BLOCKS.register("yellow_bauble", BaubleOrnamentBlock::new);
    public static final RegistryObject<Block> GREEN_BAUBLE =
            BLOCKS.register("green_bauble", BaubleOrnamentBlock::new);
    public static final RegistryObject<Block> GOLD_BAUBLE =
            BLOCKS.register("gold_bauble", BaubleOrnamentBlock::new);
    public static final RegistryObject<Block> SILVER_BAUBLE =
            BLOCKS.register("silver_bauble", BaubleOrnamentBlock::new);

    // Big Baubles
    public static final RegistryObject<Block> BIG_RED_BAUBLE =
            BLOCKS.register("big_red_bauble", BigBaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BIG_BLUE_BAUBLE =
            BLOCKS.register("big_blue_bauble", BigBaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BIG_YELLOW_BAUBLE =
            BLOCKS.register("big_yellow_bauble", BigBaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BIG_GREEN_BAUBLE =
            BLOCKS.register("big_green_bauble", BigBaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BIG_GOLD_BAUBLE =
            BLOCKS.register("big_gold_bauble", BigBaubleOrnamentBlock::new);
    public static final RegistryObject<Block> BIG_SILVER_BAUBLE =
            BLOCKS.register("big_silver_bauble", BigBaubleOrnamentBlock::new);

    // Tinsels
    public static final RegistryObject<Block> RED_TINSEL =
            BLOCKS.register("red_tinsel", TinselBlock::new);
    public static final RegistryObject<Block> BLUE_TINSEL =
            BLOCKS.register("blue_tinsel", TinselBlock::new);
    public static final RegistryObject<Block> YELLOW_TINSEL =
            BLOCKS.register("yellow_tinsel", TinselBlock::new);
    public static final RegistryObject<Block> GREEN_TINSEL =
            BLOCKS.register("green_tinsel", TinselBlock::new);
    public static final RegistryObject<Block> GOLD_TINSEL =
            BLOCKS.register("gold_tinsel", TinselBlock::new);
    public static final RegistryObject<Block> SILVER_TINSEL =
            BLOCKS.register("silver_tinsel", TinselBlock::new);

    // Christmas Lights
    public static final RegistryObject<Block> RED_CHRISTMAS_LIGHTS =
            BLOCKS.register("red_christmas_lights", ChristmasLightBlock::new);
    public static final RegistryObject<Block> BLUE_CHRISTMAS_LIGHTS =
            BLOCKS.register("blue_christmas_lights", ChristmasLightBlock::new);
    public static final RegistryObject<Block> YELLOW_CHRISTMAS_LIGHTS =
            BLOCKS.register("yellow_christmas_lights", ChristmasLightBlock::new);
    public static final RegistryObject<Block> GREEN_CHRISTMAS_LIGHTS =
            BLOCKS.register("green_christmas_lights", ChristmasLightBlock::new);
    public static final RegistryObject<Block> GOLD_CHRISTMAS_LIGHTS =
            BLOCKS.register("gold_christmas_lights", ChristmasLightBlock::new);
    public static final RegistryObject<Block> SILVER_CHRISTMAS_LIGHTS =
            BLOCKS.register("silver_christmas_lights", ChristmasLightBlock::new);

    // Bells
    public static final RegistryObject<Block> RED_CHRISTMAS_BELLS =
            BLOCKS.register("red_christmas_bells", ChristmasBellBlock::new);
    public static final RegistryObject<Block> BLUE_CHRISTMAS_BELLS =
            BLOCKS.register("blue_christmas_bells", ChristmasBellBlock::new);
    public static final RegistryObject<Block> YELLOW_CHRISTMAS_BELLS =
            BLOCKS.register("yellow_christmas_bells", ChristmasBellBlock::new);
    public static final RegistryObject<Block> GREEN_CHRISTMAS_BELLS =
            BLOCKS.register("green_christmas_bells", ChristmasBellBlock::new);
    public static final RegistryObject<Block> GOLD_CHRISTMAS_BELLS =
            BLOCKS.register("gold_christmas_bells", ChristmasBellBlock::new);
    public static final RegistryObject<Block> SILVER_CHRISTMAS_BELLS =
            BLOCKS.register("silver_christmas_bells", ChristmasBellBlock::new);

    // Head Ornaments
    public static final RegistryObject<Block> CREEPER_HEAD_ORNAMENT =
            BLOCKS.register("creeper_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> SKELETON_HEAD_ORNAMENT =
            BLOCKS.register("skeleton_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> WITHER_SKELETON_HEAD_ORNAMENT =
            BLOCKS.register("wither_skeleton_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> ZOMBIE_HEAD_ORNAMENT =
            BLOCKS.register("zombie_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> DROWNED_HEAD_ORNAMENT =
            BLOCKS.register("drowned_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> BLAZE_HEAD_ORNAMENT =
            BLOCKS.register("blaze_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> GHAST_HEAD_ORNAMENT =
            BLOCKS.register("ghast_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> PHANTOM_HEAD_ORNAMENT =
            BLOCKS.register("phantom_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> PIG_HEAD_ORNAMENT =
            BLOCKS.register("pig_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> COW_HEAD_ORNAMENT =
            BLOCKS.register("cow_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> CHICKEN_HEAD_ORNAMENT =
            BLOCKS.register("chicken_head_ornament", HeadOrnamentBlock::new);
    public static final RegistryObject<Block> SHEEP_HEAD_ORNAMENT =
            BLOCKS.register("sheep_head_ornament", HeadOrnamentBlock::new);

    // Stockings
    public static final RegistryObject<Block> RED_STOCKING =
            BLOCKS.register("red_stocking", StockingBlock::new);
    public static final RegistryObject<Block> BLUE_STOCKING =
            BLOCKS.register("blue_stocking", StockingBlock::new);
    public static final RegistryObject<Block> YELLOW_STOCKING =
            BLOCKS.register("yellow_stocking", StockingBlock::new);
    public static final RegistryObject<Block> GREEN_STOCKING =
            BLOCKS.register("green_stocking", StockingBlock::new);
    public static final RegistryObject<Block> GOLD_STOCKING =
            BLOCKS.register("gold_stocking", StockingBlock::new);
    public static final RegistryObject<Block> SILVER_STOCKING =
            BLOCKS.register("silver_stocking", StockingBlock::new);

    // Alphabets
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_TEMPLATE =
            BLOCKS.register("alphabet_ornament_template", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_A =
            BLOCKS.register("alphabet_ornament_a", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_B =
            BLOCKS.register("alphabet_ornament_b", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_C =
            BLOCKS.register("alphabet_ornament_c", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_D =
            BLOCKS.register("alphabet_ornament_d", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_E =
            BLOCKS.register("alphabet_ornament_e", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_F =
            BLOCKS.register("alphabet_ornament_f", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_G =
            BLOCKS.register("alphabet_ornament_g", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_H =
            BLOCKS.register("alphabet_ornament_h", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_I =
            BLOCKS.register("alphabet_ornament_i", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_J =
            BLOCKS.register("alphabet_ornament_j", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_K =
            BLOCKS.register("alphabet_ornament_k", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_L =
            BLOCKS.register("alphabet_ornament_l", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_M =
            BLOCKS.register("alphabet_ornament_m", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_N =
            BLOCKS.register("alphabet_ornament_n", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_O =
            BLOCKS.register("alphabet_ornament_o", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_P =
            BLOCKS.register("alphabet_ornament_p", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_Q =
            BLOCKS.register("alphabet_ornament_q", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_R =
            BLOCKS.register("alphabet_ornament_r", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_S =
            BLOCKS.register("alphabet_ornament_s", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_T =
            BLOCKS.register("alphabet_ornament_t", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_U =
            BLOCKS.register("alphabet_ornament_u", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_V =
            BLOCKS.register("alphabet_ornament_v", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_W =
            BLOCKS.register("alphabet_ornament_w", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_X =
            BLOCKS.register("alphabet_ornament_x", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_Y =
            BLOCKS.register("alphabet_ornament_y", AlphabetOrnamentBlock::new);
    public static final RegistryObject<Block> ALPHABET_ORNAMENT_Z =
            BLOCKS.register("alphabet_ornament_z", AlphabetOrnamentBlock::new);

    // Decoration blocks
    public static final RegistryObject<Block> CHRISTMAS_WREATH =
            BLOCKS.register("christmas_wreath", ChristmasWreathBlock::new);
    public static final RegistryObject<Block> FROST =
            BLOCKS.register("frost", FrostBlock::new);
    public static final RegistryObject<Block> SANTA_LIST =
            BLOCKS.register("santa_list", SantaListBlock::new);

    // Legendary ornaments
    public static final RegistryObject<Block> BABY_PRESENT_ORNAMENT =
            BLOCKS.register("baby_present_ornament", BabyPresentOrnamentBlock::new);
    public static final RegistryObject<Block> ADULT_PRESENT_ORNAMENT =
            BLOCKS.register("adult_present_ornament", AdultPresentOrnamentBlock::new);
    public static final RegistryObject<Block> ELDER_PRESENT_ORNAMENT =
            BLOCKS.register("elder_present_ornament", ElderPresentOrnamentBlock::new);
    public static final RegistryObject<Block> CANDY_CANE_ORNAMENT =
            BLOCKS.register("candy_cane_ornament", CandyCaneOrnamentBlock::new);
    public static final RegistryObject<Block> SANTA_ELF_ORNAMENT =
            BLOCKS.register("santa_elf_ornament", SantaElfOrnamentBlock::new);
    public static final RegistryObject<Block> GINGERBREAD_MAN_ORNAMENT =
            BLOCKS.register("gingerbread_man_ornament", GingerbreadManOrnamentBlock::new);
    public static final RegistryObject<Block> GRINCH_ORNAMENT =
            BLOCKS.register("grinch_ornament", GrinchOrnamentBlock::new);
    public static final RegistryObject<Block> NUTCRACKER_ORNAMENT =
            BLOCKS.register("nutcracker_ornament", NutcrackerOrnamentBlock::new);

    // Gingerbread blocks
    public static final RegistryObject<Block> GINGERBREAD_DOUGH_BLOCK =
            BLOCKS.register("gingerbread_dough_block", () -> BaseGingerbreadBlock.Builder.create().dough().soggyResult(BaseGingerbreadBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_DOUGH_STAIRS =
            BLOCKS.register("gingerbread_dough_stairs", () -> BaseGingerbreadStairBlock.Builder.create().dough().soggyResult(BaseGingerbreadStairBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_DOUGH_SLAB =
            BLOCKS.register("gingerbread_dough_slab", () -> BaseGingerbreadSlabBlock.Builder.create().dough().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_DOUGH_WALL =
            BLOCKS.register("gingerbread_dough_wall", () -> BaseGingerbreadWall.Builder.create().dough().soggyResult(BaseGingerbreadWall.SoggyResult.BLOCK).build());

    public static final RegistryObject<Block> GINGERBREAD_DOUGH_BRICKS =
            BLOCKS.register("gingerbread_dough_bricks", () -> BaseGingerbreadBlock.Builder.create().dough().soggyResult(BaseGingerbreadBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_DOUGH_BRICK_STAIRS =
            BLOCKS.register("gingerbread_dough_brick_stairs", () -> BaseGingerbreadStairBlock.Builder.create().dough().soggyResult(BaseGingerbreadStairBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_DOUGH_BRICK_SLAB =
            BLOCKS.register("gingerbread_dough_brick_slab", () -> BaseGingerbreadSlabBlock.Builder.create().dough().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_DOUGH_BRICK_WALL =
            BLOCKS.register("gingerbread_dough_brick_wall", () -> BaseGingerbreadWall.Builder.create().dough().soggyResult(BaseGingerbreadWall.SoggyResult.BRICKS).build());

    public static final RegistryObject<Block> GINGERBREAD_DOUGH_TILES =
            BLOCKS.register("gingerbread_dough_tiles", () -> BaseGingerbreadBlock.Builder.create().dough().soggyResult(BaseGingerbreadBlock.SoggyResult.TILES).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_DOUGH_TILE_STAIRS =
            BLOCKS.register("gingerbread_dough_tile_stairs", () -> BaseGingerbreadStairBlock.Builder.create().dough().soggyResult(BaseGingerbreadStairBlock.SoggyResult.TILES).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_DOUGH_TILE_SLAB =
            BLOCKS.register("gingerbread_dough_tile_slab", () -> BaseGingerbreadSlabBlock.Builder.create().dough().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.TILES).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_DOUGH_TILE_WALL =
            BLOCKS.register("gingerbread_dough_tile_wall", () -> BaseGingerbreadWall.Builder.create().dough().soggyResult(BaseGingerbreadWall.SoggyResult.TILES).build());

    public static final RegistryObject<Block> GINGERBREAD_BLOCK =
            BLOCKS.register("gingerbread_block", () -> BaseGingerbreadBlock.Builder.create().cooked().soggyResult(BaseGingerbreadBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_STAIRS =
            BLOCKS.register("gingerbread_stairs", () -> BaseGingerbreadStairBlock.Builder.create().cooked().soggyResult(BaseGingerbreadStairBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_SLAB =
            BLOCKS.register("gingerbread_slab", () -> BaseGingerbreadSlabBlock.Builder.create().cooked().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.BLOCK).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_WALL =
            BLOCKS.register("gingerbread_wall", () -> BaseGingerbreadWall.Builder.create().cooked().soggyResult(BaseGingerbreadWall.SoggyResult.BLOCK).build());

    public static final RegistryObject<Block> GINGERBREAD_BRICKS =
            BLOCKS.register("gingerbread_bricks", () -> BaseGingerbreadBlock.Builder.create().cooked().soggyResult(BaseGingerbreadBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_BRICK_STAIRS =
            BLOCKS.register("gingerbread_brick_stairs", () -> BaseGingerbreadStairBlock.Builder.create().cooked().soggyResult(BaseGingerbreadStairBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_BRICK_SLAB =
            BLOCKS.register("gingerbread_brick_slab", () -> BaseGingerbreadSlabBlock.Builder.create().cooked().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.BRICKS).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_BRICK_WALL =
            BLOCKS.register("gingerbread_brick_wall", () -> BaseGingerbreadWall.Builder.create().cooked().soggyResult(BaseGingerbreadWall.SoggyResult.BRICKS).build());

    public static final RegistryObject<Block> GINGERBREAD_TILES =
            BLOCKS.register("gingerbread_tiles", () -> BaseGingerbreadBlock.Builder.create().cooked().soggyResult(BaseGingerbreadBlock.SoggyResult.TILES).build());
    public static final RegistryObject<StairBlock> GINGERBREAD_TILE_STAIRS =
            BLOCKS.register("gingerbread_tile_stairs", () -> BaseGingerbreadStairBlock.Builder.create().cooked().soggyResult(BaseGingerbreadStairBlock.SoggyResult.TILES).build());
    public static final RegistryObject<SlabBlock> GINGERBREAD_TILE_SLAB =
            BLOCKS.register("gingerbread_tile_slab", () -> BaseGingerbreadSlabBlock.Builder.create().cooked().soggyResult(BaseGingerbreadSlabBlock.SoggyResult.TILES).build());
    public static final RegistryObject<WallBlock> GINGERBREAD_TILE_WALL =
            BLOCKS.register("gingerbread_tile_wall", () -> BaseGingerbreadWall.Builder.create().cooked().soggyResult(BaseGingerbreadWall.SoggyResult.TILES).build());

    public static final RegistryObject<Block> SOGGY_GINGERBREAD_BLOCK =
            BLOCKS.register("soggy_gingerbread_block", () -> BaseGingerbreadBlock.Builder.create().soggy().build());
    public static final RegistryObject<StairBlock> SOGGY_GINGERBREAD_STAIRS =
            BLOCKS.register("soggy_gingerbread_stairs", () -> BaseGingerbreadStairBlock.Builder.create().soggy().build());
    public static final RegistryObject<SlabBlock> SOGGY_GINGERBREAD_SLAB =
            BLOCKS.register("soggy_gingerbread_slab", () -> BaseGingerbreadSlabBlock.Builder.create().soggy().build());
    public static final RegistryObject<WallBlock> SOGGY_GINGERBREAD_WALL =
            BLOCKS.register("soggy_gingerbread_wall", () -> BaseGingerbreadWall.Builder.create().soggy().build());

    public static final RegistryObject<Block> SOGGY_GINGERBREAD_BRICKS =
            BLOCKS.register("soggy_gingerbread_bricks", () -> BaseGingerbreadBlock.Builder.create().soggy().build());
    public static final RegistryObject<StairBlock> SOGGY_GINGERBREAD_BRICK_STAIRS =
            BLOCKS.register("soggy_gingerbread_brick_stairs", () -> BaseGingerbreadStairBlock.Builder.create().soggy().build());
    public static final RegistryObject<SlabBlock> SOGGY_GINGERBREAD_BRICK_SLAB =
            BLOCKS.register("soggy_gingerbread_brick_slab", () -> BaseGingerbreadSlabBlock.Builder.create().soggy().build());
    public static final RegistryObject<WallBlock> SOGGY_GINGERBREAD_BRICK_WALL =
            BLOCKS.register("soggy_gingerbread_brick_wall", () -> BaseGingerbreadWall.Builder.create().soggy().build());

    public static final RegistryObject<Block> SOGGY_GINGERBREAD_TILES =
            BLOCKS.register("soggy_gingerbread_tiles", () -> BaseGingerbreadBlock.Builder.create().soggy().build());
    public static final RegistryObject<StairBlock> SOGGY_GINGERBREAD_TILE_STAIRS =
            BLOCKS.register("soggy_gingerbread_tile_stairs", () -> BaseGingerbreadStairBlock.Builder.create().soggy().build());
    public static final RegistryObject<SlabBlock> SOGGY_GINGERBREAD_TILE_SLAB =
            BLOCKS.register("soggy_gingerbread_tile_slab", () -> BaseGingerbreadSlabBlock.Builder.create().soggy().build());
    public static final RegistryObject<WallBlock> SOGGY_GINGERBREAD_TILE_WALL =
            BLOCKS.register("soggy_gingerbread_tile_wall", () -> BaseGingerbreadWall.Builder.create().soggy().build());

    // Candy cane blocks
    public static final RegistryObject<Block> CANDY_CANE_BLOCK =
            BLOCKS.register("candy_cane_block", CandyCaneBlock::new);
    public static final RegistryObject<StairBlock> CANDY_CANE_STAIRS =
            BLOCKS.register("candy_cane_stairs", () -> new CandyCaneStairBlock(supplierOf(CANDY_CANE_BLOCK)));
    public static final RegistryObject<SlabBlock> CANDY_CANE_SLAB =
            BLOCKS.register("candy_cane_slab", CandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> CANDY_CANE_WALL =
            BLOCKS.register("candy_cane_wall", CandyCaneWallBlock::new);

    public static final RegistryObject<Block> CANDY_CANE_BRICKS =
            BLOCKS.register("candy_cane_bricks", CandyCaneBlock::new);
    public static final RegistryObject<StairBlock> CANDY_CANE_BRICK_STAIRS =
            BLOCKS.register("candy_cane_brick_stairs", () -> new CandyCaneStairBlock(supplierOf(CANDY_CANE_BRICKS)));
    public static final RegistryObject<SlabBlock> CANDY_CANE_BRICK_SLAB =
            BLOCKS.register("candy_cane_brick_slab", CandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> CANDY_CANE_BRICK_WALL =
            BLOCKS.register("candy_cane_brick_wall", CandyCaneWallBlock::new);

    public static final RegistryObject<Block> CANDY_CANE_TILES =
            BLOCKS.register("candy_cane_tiles", CandyCaneBlock::new);
    public static final RegistryObject<StairBlock> CANDY_CANE_TILE_STAIRS =
            BLOCKS.register("candy_cane_tile_stairs", () -> new CandyCaneStairBlock(supplierOf(CANDY_CANE_TILES)));
    public static final RegistryObject<SlabBlock> CANDY_CANE_TILE_SLAB =
            BLOCKS.register("candy_cane_tile_slab", CandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> CANDY_CANE_TILE_WALL =
            BLOCKS.register("candy_cane_tile_wall", CandyCaneWallBlock::new);

    public static final RegistryObject<Block> FESTIVE_CANDY_CANE_BLOCK =
            BLOCKS.register("festive_candy_cane_block", FestiveCandyCaneBlock::new);
    public static final RegistryObject<StairBlock> FESTIVE_CANDY_CANE_STAIRS =
            BLOCKS.register("festive_candy_cane_stairs", () -> new FestiveCandyCaneStairBlock(supplierOf(FESTIVE_CANDY_CANE_BLOCK)));
    public static final RegistryObject<SlabBlock> FESTIVE_CANDY_CANE_SLAB =
            BLOCKS.register("festive_candy_cane_slab", FestiveCandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> FESTIVE_CANDY_CANE_WALL =
            BLOCKS.register("festive_candy_cane_wall", FestiveCandyCaneWallBlock::new);

    public static final RegistryObject<Block> FESTIVE_CANDY_CANE_BRICKS =
            BLOCKS.register("festive_candy_cane_bricks", FestiveCandyCaneBlock::new);
    public static final RegistryObject<StairBlock> FESTIVE_CANDY_CANE_BRICK_STAIRS =
            BLOCKS.register("festive_candy_cane_brick_stairs", () -> new FestiveCandyCaneStairBlock(supplierOf(FESTIVE_CANDY_CANE_BRICKS)));
    public static final RegistryObject<SlabBlock> FESTIVE_CANDY_CANE_BRICK_SLAB =
            BLOCKS.register("festive_candy_cane_brick_slab", FestiveCandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> FESTIVE_CANDY_CANE_BRICK_WALL =
            BLOCKS.register("festive_candy_cane_brick_wall", FestiveCandyCaneWallBlock::new);

    public static final RegistryObject<Block> FESTIVE_CANDY_CANE_TILES =
            BLOCKS.register("festive_candy_cane_tiles", FestiveCandyCaneBlock::new);
    public static final RegistryObject<StairBlock> FESTIVE_CANDY_CANE_TILE_STAIRS =
            BLOCKS.register("festive_candy_cane_tile_stairs", () -> new FestiveCandyCaneStairBlock(supplierOf(FESTIVE_CANDY_CANE_TILES)));
    public static final RegistryObject<SlabBlock> FESTIVE_CANDY_CANE_TILE_SLAB =
            BLOCKS.register("festive_candy_cane_tile_slab", FestiveCandyCaneSlabBlock::new);
    public static final RegistryObject<WallBlock> FESTIVE_CANDY_CANE_TILE_WALL =
            BLOCKS.register("festive_candy_cane_tile_wall", FestiveCandyCaneWallBlock::new);

    // Miscellaneous blocks
    public static final RegistryObject<Block> CHRISTMAS_STAR =
            BLOCKS.register("christmas_star", ChristmasStarBlock::new);
    public static final RegistryObject<Block> MUSIC_BOX =
            BLOCKS.register("music_box", MusicBoxBlock::new);
    public static final RegistryObject<Block> GIFT_WRAPPING_STATION =
            BLOCKS.register("gift_wrapping_station", GiftWrapperBlock::new);
    public static final RegistryObject<Block> WALNUT_PLANT =
            BLOCKS.register("walnut_plant", WalnutPlantBlock::new);

    // Christmas foods
    public static final RegistryObject<Block> MILK_AND_COOKIES =
            BLOCKS.register("milk_and_cookies", MilkAndCookiesBlock::new);
    public static final RegistryObject<Block> LOG_CAKE =
            BLOCKS.register("log_cake", LogCakeBlock::new);
    public static final RegistryObject<Block> CHRISTMAS_HAM =
            BLOCKS.register("christmas_ham", ChristmasHamBlock::new);
    public static final RegistryObject<Block> CHRISTMAS_PUDDING =
            BLOCKS.register("christmas_pudding", ChristmasPuddingBlock::new);

    public static boolean isInfluencedByStar(BlockState blockState) {
        return blockState.is(ChristmasTags.Blocks.STAR_AFFECTED_BLOCKS);
    }
}
