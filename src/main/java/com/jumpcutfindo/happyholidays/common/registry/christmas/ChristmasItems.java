package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.PresentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.BasicOrnament;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.LegendaryOrnament;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.RareOrnament;
import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasBlockItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasFoiledItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasGuideBookItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.item.christmas.EggnogItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.PatrolOrdersItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.SantaElfBellItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.SnowGlobeItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.SwaggerStickItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.ToyPartsRequestItem;
import com.jumpcutfindo.happyholidays.common.item.christmas.crafting.PatrolOrdersCloningRecipe;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SheetMusicItem;
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
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
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
    private static final Item.Properties DEFAULT_PROPS = new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP);
    private static final Item.Properties ONE_IN_A_STACK_PROPS = new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP).stacksTo(1);

    private static final Item.Properties BIG_FOOD_PROPS = new Item.Properties().stacksTo(16).tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    // ================== ITEMS ==================
    public static final RegistryObject<Item> RAW_GINGERBREAD =
            registerItem("gingerbread_dough", DEFAULT_PROPS);
    public static final RegistryObject<Item> GINGERBREAD_COOKIE =
            registerFood("gingerbread_cookie", new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP).food(new FoodProperties.Builder().nutrition(2).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1), 0.5f).saturationMod(0.1f).alwaysEat().build()));
    public static final RegistryObject<Item> PRESENT_SCRAPS =
            registerItem("present_scraps", DEFAULT_PROPS);
    public static final RegistryObject<Item> HOLLY =
            registerItem("mistletoe_and_holly", DEFAULT_PROPS);
    public static final RegistryObject<Item> SANTA_ELF_BELL =
            registerCustomItem("santa_elf_bell", SantaElfBellItem::new);
    public static final RegistryObject<Item> TOY_PARTS_REQUEST =
            registerCustomItem("toy_parts_request", ToyPartsRequestItem::new);
    public static final RegistryObject<Item> CHRISTMAS_GUIDE_BOOK =
            registerCustomItem("christmas_guide_book", ChristmasGuideBookItem::new);
    public static final RegistryObject<Item> SNOW_GLOBE =
            registerCustomItem("snow_globe", SnowGlobeItem::new);

    public static final RegistryObject<Item> THREAD =
            registerItem("thread", DEFAULT_PROPS);
    public static final RegistryObject<Item> ENCHANTED_THREAD =
            registerCustomItem("enchanted_thread", () -> new ChristmasFoiledItem(DEFAULT_PROPS, ChristmasRarity.RARE));

    public static final RegistryObject<BlockItem> WALNUT =
            registerCustomBlockItem("walnut", () -> new ItemNameBlockItem(ChristmasBlocks.WALNUT_PLANT.get(), DEFAULT_PROPS));
    public static final RegistryObject<Item> EXPLOSIVE_WALNUT =
            registerItem("explosive_walnut", DEFAULT_PROPS);
    public static final RegistryObject<Item> SUGARED_WALNUT =
            registerItem("sugared_walnut", DEFAULT_PROPS);
    public static final RegistryObject<Item> METALLIC_WALNUT =
            registerItem("metallic_walnut", DEFAULT_PROPS);
    public static final RegistryObject<Item> HALVED_WALNUT =
            registerItem("halved_walnut", DEFAULT_PROPS);

    public static final RegistryObject<Item> SWAGGER_STICK =
            registerCustomItem("swagger_stick", SwaggerStickItem::new);
    public static final RegistryObject<Item> PATROL_ORDERS =
            registerCustomItem("patrol_orders", PatrolOrdersItem::new);

    public static final RegistryObject<Item> ENCHANTED_SANTA_HAT =
            registerCustomItem("enchanted_santa_hat", () -> new ChristmasFoiledItem(ONE_IN_A_STACK_PROPS, ChristmasRarity.LEGENDARY));
    public static final RegistryObject<Item> SANTA_HAT =
            registerCustomItem(ChristmasOutfits.SANTA_OUTFIT.getHeadpieceId(), ChristmasOutfits.SANTA_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> SANTA_TOP =
            registerCustomItem(ChristmasOutfits.SANTA_OUTFIT.getTopId(), ChristmasOutfits.SANTA_OUTFIT::getTop);
    public static final RegistryObject<Item> SANTA_BOTTOM =
            registerCustomItem(ChristmasOutfits.SANTA_OUTFIT.getBottomId(), ChristmasOutfits.SANTA_OUTFIT::getBottom);
    public static final RegistryObject<Item> SANTA_BOOTS =
            registerCustomItem(ChristmasOutfits.SANTA_OUTFIT.getFeetId(), ChristmasOutfits.SANTA_OUTFIT::getFeet);

    public static final RegistryObject<Item> SANTA_ELF_HAT =
            registerCustomItem(ChristmasOutfits.SANTA_ELF_OUTFIT.getHeadpieceId(), ChristmasOutfits.SANTA_ELF_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> SANTA_ELF_TOP =
            registerCustomItem(ChristmasOutfits.SANTA_ELF_OUTFIT.getTopId(), ChristmasOutfits.SANTA_ELF_OUTFIT::getTop);
    public static final RegistryObject<Item> SANTA_ELF_BOTTOM =
            registerCustomItem(ChristmasOutfits.SANTA_ELF_OUTFIT.getBottomId(), ChristmasOutfits.SANTA_ELF_OUTFIT::getBottom);
    public static final RegistryObject<Item> SANTA_ELF_BOOTS =
            registerCustomItem(ChristmasOutfits.SANTA_ELF_OUTFIT.getFeetId(), ChristmasOutfits.SANTA_ELF_OUTFIT::getFeet);

    public static final RegistryObject<Item> SNOWMAN_HEADPIECE =
            registerCustomItem(ChristmasOutfits.SNOWMAN_OUTFIT.getHeadpieceId(), ChristmasOutfits.SNOWMAN_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> SNOWMAN_TOP =
            registerCustomItem(ChristmasOutfits.SNOWMAN_OUTFIT.getTopId(), ChristmasOutfits.SNOWMAN_OUTFIT::getTop);
    public static final RegistryObject<Item> SNOWMAN_BOTTOM =
            registerCustomItem(ChristmasOutfits.SNOWMAN_OUTFIT.getBottomId(), ChristmasOutfits.SNOWMAN_OUTFIT::getBottom);

    public static final RegistryObject<Item> CANDY_CANE_HEADPIECE =
            registerCustomItem(ChristmasOutfits.CANDY_CANE_OUTFIT.getHeadpieceId(), ChristmasOutfits.CANDY_CANE_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> CANDY_CANE_TOP =
            registerCustomItem(ChristmasOutfits.CANDY_CANE_OUTFIT.getTopId(), ChristmasOutfits.CANDY_CANE_OUTFIT::getTop);
    public static final RegistryObject<Item> CANDY_CANE_BOTTOM =
            registerCustomItem(ChristmasOutfits.CANDY_CANE_OUTFIT.getBottomId(), ChristmasOutfits.CANDY_CANE_OUTFIT::getBottom);
    public static final RegistryObject<Item> CANDY_CANE_BOOTS =
            registerCustomItem(ChristmasOutfits.CANDY_CANE_OUTFIT.getFeetId(), ChristmasOutfits.CANDY_CANE_OUTFIT::getFeet);

    public static final RegistryObject<Item> REINDEER_HEADPIECE =
            registerCustomItem(ChristmasOutfits.REINDEER_OUTFIT.getHeadpieceId(), ChristmasOutfits.REINDEER_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> REINDEER_TOP =
            registerCustomItem(ChristmasOutfits.REINDEER_OUTFIT.getTopId(), ChristmasOutfits.REINDEER_OUTFIT::getTop);
    public static final RegistryObject<Item> REINDEER_BOTTOM =
            registerCustomItem(ChristmasOutfits.REINDEER_OUTFIT.getBottomId(), ChristmasOutfits.REINDEER_OUTFIT::getBottom);
    public static final RegistryObject<Item> REINDEER_BOOTS =
            registerCustomItem(ChristmasOutfits.REINDEER_OUTFIT.getFeetId(), ChristmasOutfits.REINDEER_OUTFIT::getFeet);

    public static final RegistryObject<Item> NUTCRACKER_HEADPIECE =
            registerCustomItem(ChristmasOutfits.NUTCRACKER_OUTFIT.getHeadpieceId(), ChristmasOutfits.NUTCRACKER_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> NUTCRACKER_TOP =
            registerCustomItem(ChristmasOutfits.NUTCRACKER_OUTFIT.getTopId(), ChristmasOutfits.NUTCRACKER_OUTFIT::getTop);
    public static final RegistryObject<Item> NUTCRACKER_BOTTOM =
            registerCustomItem(ChristmasOutfits.NUTCRACKER_OUTFIT.getBottomId(), ChristmasOutfits.NUTCRACKER_OUTFIT::getBottom);
    public static final RegistryObject<Item> NUTCRACKER_BOOTS =
            registerCustomItem(ChristmasOutfits.NUTCRACKER_OUTFIT.getFeetId(), ChristmasOutfits.NUTCRACKER_OUTFIT::getFeet);

    public static final RegistryObject<Item> GINGERBREAD_HEADPIECE =
            registerCustomItem(ChristmasOutfits.GINGERBREAD_OUTFIT.getHeadpieceId(), ChristmasOutfits.GINGERBREAD_OUTFIT::getHeadpiece);
    public static final RegistryObject<Item> GINGERBREAD_TOP =
            registerCustomItem(ChristmasOutfits.GINGERBREAD_OUTFIT.getTopId(), ChristmasOutfits.GINGERBREAD_OUTFIT::getTop);
    public static final RegistryObject<Item> GINGERBREAD_BOTTOM =
            registerCustomItem(ChristmasOutfits.GINGERBREAD_OUTFIT.getBottomId(), ChristmasOutfits.GINGERBREAD_OUTFIT::getBottom);
    public static final RegistryObject<Item> GINGERBREAD_BOOTS =
            registerCustomItem(ChristmasOutfits.GINGERBREAD_OUTFIT.getFeetId(), ChristmasOutfits.GINGERBREAD_OUTFIT::getFeet);

    public static final RegistryObject<Item> DYE_BOWL =
            registerItem("dye_bowl", DEFAULT_PROPS);
    public static final RegistryObject<Item> RED_CHRISTMAS_DYE =
            registerItem("red_christmas_dye", DEFAULT_PROPS);
    public static final RegistryObject<Item> BLUE_CHRISTMAS_DYE =
            registerItem("blue_christmas_dye", DEFAULT_PROPS);
    public static final RegistryObject<Item> YELLOW_CHRISTMAS_DYE =
            registerItem("yellow_christmas_dye", DEFAULT_PROPS);
    public static final RegistryObject<Item> GREEN_CHRISTMAS_DYE =
            registerItem("green_christmas_dye", DEFAULT_PROPS);
    public static final RegistryObject<Item> GOLD_CHRISTMAS_DYE =
            registerItem("gold_christmas_dye", DEFAULT_PROPS);
    public static final RegistryObject<Item> SILVER_CHRISTMAS_DYE =
            registerItem("silver_christmas_dye", DEFAULT_PROPS);

    public static final RegistryObject<Item> SHEET_MUSIC_ANGELS_ON_HIGH =
            registerCustomItem("christmas_sheet_music_angels_on_high", () -> new SheetMusicItem().setMusic(ChristmasMusic.ANGELS_ON_HIGH));
    public static final RegistryObject<Item> SHEET_MUSIC_CAROL_OF_THE_BELLS =
            registerCustomItem("christmas_sheet_music_carol_of_the_bells", () -> new SheetMusicItem().setMusic(ChristmasMusic.CAROL_OF_THE_BELLS));
    public static final RegistryObject<Item> SHEET_MUSIC_DECK_THE_HALLS =
            registerCustomItem("christmas_sheet_music_deck_the_halls", () -> new SheetMusicItem().setMusic(ChristmasMusic.DECK_THE_HALLS));
    public static final RegistryObject<Item> SHEET_MUSIC_FROSTY_THE_SNOWMAN =
            registerCustomItem("christmas_sheet_music_frosty_the_snowman", () -> new SheetMusicItem().setMusic(ChristmasMusic.FROSTY_THE_SNOWMAN));
    public static final RegistryObject<Item> SHEET_MUSIC_GOD_REST_GENTLEMEN =
            registerCustomItem("christmas_sheet_music_god_rest_gentlemen", () -> new SheetMusicItem().setMusic(ChristmasMusic.GOD_REST_GENTLEMEN));
    public static final RegistryObject<Item> SHEET_MUSIC_HERE_COMES_SANTA =
            registerCustomItem("christmas_sheet_music_here_comes_santa", () -> new SheetMusicItem().setMusic(ChristmasMusic.HERE_COMES_SANTA));
    public static final RegistryObject<Item> SHEET_MUSIC_JINGLE_BELL_ROCK =
            registerCustomItem("christmas_sheet_music_jingle_bell_rock", () -> new SheetMusicItem().setMusic(ChristmasMusic.JINGLE_BELL_ROCK));
    public static final RegistryObject<Item> SHEET_MUSIC_JINGLE_BELLS =
            registerCustomItem("christmas_sheet_music_jingle_bells", () -> new SheetMusicItem().setMusic(ChristmasMusic.JINGLE_BELLS));
    public static final RegistryObject<Item> SHEET_MUSIC_JOY_TO_THE_WORLD =
            registerCustomItem("christmas_sheet_music_joy_to_the_world", () -> new SheetMusicItem().setMusic(ChristmasMusic.JOY_TO_THE_WORLD));
    public static final RegistryObject<Item> SHEET_MUSIC_RUDOLPH =
            registerCustomItem("christmas_sheet_music_rudolph", () -> new SheetMusicItem().setMusic(ChristmasMusic.RUDOLPH));
    public static final RegistryObject<Item> SHEET_MUSIC_SILENT_NIGHT =
            registerCustomItem("christmas_sheet_music_silent_night", () -> new SheetMusicItem().setMusic(ChristmasMusic.SILENT_NIGHT));
    public static final RegistryObject<Item> SHEET_MUSIC_SLEIGH_RIDE =
            registerCustomItem("christmas_sheet_music_sleigh_ride", () -> new SheetMusicItem().setMusic(ChristmasMusic.SLEIGH_RIDE));
    public static final RegistryObject<Item> SHEET_MUSIC_THE_FIRST_NOEL =
            registerCustomItem("christmas_sheet_music_the_first_noel", () -> new SheetMusicItem().setMusic(ChristmasMusic.THE_FIRST_NOEL));
    public static final RegistryObject<Item> SHEET_MUSIC_WE_THREE_KINGS =
            registerCustomItem("christmas_sheet_music_we_three_kings", () -> new SheetMusicItem().setMusic(ChristmasMusic.WE_THREE_KINGS));
    public static final RegistryObject<Item> SHEET_MUSIC_WE_WISH_YOU =
            registerCustomItem("christmas_sheet_music_we_wish_you", () -> new SheetMusicItem().setMusic(ChristmasMusic.WE_WISH_YOU));
    public static final RegistryObject<Item> SHEET_MUSIC_WHITE_CHRISTMAS =
            registerCustomItem("christmas_sheet_music_white_christmas", () -> new SheetMusicItem().setMusic(ChristmasMusic.WHITE_CHRISTMAS));

    public static final RegistryObject<Item> CANDY_CANE =
            registerFood("candy_cane", new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP).food(new FoodProperties.Builder().nutrition(2).effect(() -> new MobEffectInstance(MobEffects.JUMP, 100, 1), 0.5f).saturationMod(0.1f).alwaysEat().build()));
    public static final RegistryObject<Item> FESTIVE_CANDY_CANE =
            registerFood("festive_candy_cane", new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP).food(new FoodProperties.Builder().nutrition(2).effect(() -> new MobEffectInstance(MobEffects.JUMP, 100, 1), 0.5f).saturationMod(0.1f).alwaysEat().build()));
    public static final RegistryObject<Item> ENCHANTED_CANDY_CANE =
            registerCustomItem("enchanted_candy_cane", () -> new ChristmasFoiledItem(DEFAULT_PROPS, ChristmasRarity.LEGENDARY));

    public static final RegistryObject<Item> EGGNOG =
            registerCustomItem("eggnog", EggnogItem::new);

    public static final RegistryObject<Item> RED_CHRISTMAS_GIFT =
            registerCustomItem("red_christmas_gift", ChristmasGiftItem::new);
    public static final RegistryObject<Item> BLUE_CHRISTMAS_GIFT =
            registerCustomItem("blue_christmas_gift", ChristmasGiftItem::new);
    public static final RegistryObject<Item> YELLOW_CHRISTMAS_GIFT =
            registerCustomItem("yellow_christmas_gift", ChristmasGiftItem::new);
    public static final RegistryObject<Item> GREEN_CHRISTMAS_GIFT =
            registerCustomItem("green_christmas_gift", ChristmasGiftItem::new);
    public static final RegistryObject<Item> GOLD_CHRISTMAS_GIFT =
            registerCustomItem("gold_christmas_gift", ChristmasGiftItem::new);
    public static final RegistryObject<Item> SILVER_CHRISTMAS_GIFT =
            registerCustomItem("silver_christmas_gift", ChristmasGiftItem::new);

    public static final RegistryObject<Item> SANTA_ELF_SPAWN_EGG =
            registerCustomItem("santa_elf_spawn_egg", spawnEggOf(ChristmasEntities.SANTA_ELF::get, ColourUtils.rgbaToInt(2, 126, 16, 255), ColourUtils.rgbaToInt(255, 2, 2, 255)));
    public static final RegistryObject<Item> GINGERBREAD_MAN_SPAWN_EGG =
            registerCustomItem("gingerbread_man_spawn_egg", spawnEggOf(ChristmasEntities.GINGERBREAD_MAN::get, ColourUtils.rgbaToInt(123, 76, 22, 255), ColourUtils.rgbaToInt(255, 255, 255, 255)));
    public static final RegistryObject<Item> GRINCH_SPAWN_EGG =
            registerCustomItem("grinch_spawn_egg", spawnEggOf(ChristmasEntities.GRINCH::get, ColourUtils.rgbaToInt(43, 178, 0, 255), ColourUtils.rgbaToInt(255, 216, 0, 255)));
    public static final RegistryObject<Item> NUTCRACKER_SPAWN_EGG =
            registerCustomItem("nutcracker_spawn_egg", spawnEggOf(ChristmasEntities.NUTCRACKER::get, ColourUtils.rgbaToInt(206, 0, 0, 255), ColourUtils.rgbaToInt(0, 0, 0, 255)));
    public static final RegistryObject<Item> HAPPY_SANTA_SPAWN_EGG =
            registerCustomItem("happy_santa_spawn_egg", spawnEggOf(ChristmasEntities.HAPPY_SANTA::get, ColourUtils.rgbaToInt(242, 0, 0, 255), ColourUtils.rgbaToInt(255, 255, 255, 255)));
    public static final RegistryObject<Item> ANGRY_SANTA_SPAWN_EGG =
            registerCustomItem("angry_santa_spawn_egg", spawnEggOf(ChristmasEntities.ANGRY_SANTA::get, ColourUtils.rgbaToInt(112, 24, 31, 255), ColourUtils.rgbaToInt(255, 255, 255, 255)));

    // ================== BLOCK ITEMS ==================
    public static final RegistryObject<BlockItem> BABY_PRESENT =
            registerItem("baby_present", ChristmasBlocks.BABY_PRESENT, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ADULT_PRESENT =
            registerItem("adult_present",ChristmasBlocks.ADULT_PRESENT, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ELDER_PRESENT =
            registerItem("elder_present", ChristmasBlocks.ELDER_PRESENT, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> EXPLOSIVE_PRESENT =
            registerItem("explosive_present", ChristmasBlocks.EXPLOSIVE_PRESENT, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> RED_BAUBLE =
            registerItem("red_bauble", ChristmasBlocks.RED_BAUBLE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> BLUE_BAUBLE =
            registerItem("blue_bauble", ChristmasBlocks.BLUE_BAUBLE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> YELLOW_BAUBLE =
            registerItem("yellow_bauble", ChristmasBlocks.YELLOW_BAUBLE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GREEN_BAUBLE =
            registerItem("green_bauble", ChristmasBlocks.GREEN_BAUBLE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GOLD_BAUBLE =
            registerItem("gold_bauble", ChristmasBlocks.GOLD_BAUBLE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SILVER_BAUBLE =
            registerItem("silver_bauble", ChristmasBlocks.SILVER_BAUBLE, DEFAULT_PROPS);


    public static final RegistryObject<BlockItem> BIG_RED_BAUBLE =
            registerItem("big_red_bauble", ChristmasBlocks.BIG_RED_BAUBLE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> BIG_BLUE_BAUBLE =
            registerItem("big_blue_bauble", ChristmasBlocks.BIG_BLUE_BAUBLE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> BIG_YELLOW_BAUBLE =
            registerItem("big_yellow_bauble", ChristmasBlocks.BIG_YELLOW_BAUBLE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> BIG_GREEN_BAUBLE =
            registerItem("big_green_bauble", ChristmasBlocks.BIG_GREEN_BAUBLE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> BIG_GOLD_BAUBLE =
            registerItem("big_gold_bauble", ChristmasBlocks.BIG_GOLD_BAUBLE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> BIG_SILVER_BAUBLE =
            registerItem("big_silver_bauble", ChristmasBlocks.BIG_SILVER_BAUBLE, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> RED_TINSEL =
            registerItem("red_tinsel", ChristmasBlocks.RED_TINSEL, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> BLUE_TINSEL =
            registerItem("blue_tinsel", ChristmasBlocks.BLUE_TINSEL, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> YELLOW_TINSEL =
            registerItem("yellow_tinsel", ChristmasBlocks.YELLOW_TINSEL, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GREEN_TINSEL =
            registerItem("green_tinsel", ChristmasBlocks.GREEN_TINSEL, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GOLD_TINSEL =
            registerItem("gold_tinsel", ChristmasBlocks.GOLD_TINSEL, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SILVER_TINSEL =
            registerItem("silver_tinsel", ChristmasBlocks.SILVER_TINSEL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> RED_CHRISTMAS_LIGHTS =
            registerItem("red_christmas_lights", ChristmasBlocks.RED_CHRISTMAS_LIGHTS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> BLUE_CHRISTMAS_LIGHTS =
            registerItem("blue_christmas_lights", ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> YELLOW_CHRISTMAS_LIGHTS =
            registerItem("yellow_christmas_lights", ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GREEN_CHRISTMAS_LIGHTS =
            registerItem("green_christmas_lights", ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GOLD_CHRISTMAS_LIGHTS =
            registerItem("gold_christmas_lights", ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SILVER_CHRISTMAS_LIGHTS =
            registerItem("silver_christmas_lights", ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> RED_CHRISTMAS_BELLS =
            registerItem("red_christmas_bells", ChristmasBlocks.RED_CHRISTMAS_BELLS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> BLUE_CHRISTMAS_BELLS =
            registerItem("blue_christmas_bells", ChristmasBlocks.BLUE_CHRISTMAS_BELLS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> YELLOW_CHRISTMAS_BELLS =
            registerItem("yellow_christmas_bells", ChristmasBlocks.YELLOW_CHRISTMAS_BELLS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GREEN_CHRISTMAS_BELLS =
            registerItem("green_christmas_bells", ChristmasBlocks.GREEN_CHRISTMAS_BELLS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GOLD_CHRISTMAS_BELLS =
            registerItem("gold_christmas_bells", ChristmasBlocks.GOLD_CHRISTMAS_BELLS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SILVER_CHRISTMAS_BELLS =
            registerItem("silver_christmas_bells", ChristmasBlocks.SILVER_CHRISTMAS_BELLS, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> CREEPER_HEAD_ORNAMENT =
            registerItem("creeper_head_ornament", ChristmasBlocks.CREEPER_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> SKELETON_HEAD_ORNAMENT =
            registerItem("skeleton_head_ornament", ChristmasBlocks.SKELETON_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> WITHER_SKELETON_HEAD_ORNAMENT =
            registerItem("wither_skeleton_head_ornament", ChristmasBlocks.WITHER_SKELETON_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> ZOMBIE_HEAD_ORNAMENT =
            registerItem("zombie_head_ornament", ChristmasBlocks.ZOMBIE_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> DROWNED_HEAD_ORNAMENT =
            registerItem("drowned_head_ornament", ChristmasBlocks.DROWNED_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> BLAZE_HEAD_ORNAMENT =
            registerItem("blaze_head_ornament", ChristmasBlocks.BLAZE_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> GHAST_HEAD_ORNAMENT =
            registerItem("ghast_head_ornament", ChristmasBlocks.GHAST_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> PHANTOM_HEAD_ORNAMENT =
            registerItem("phantom_head_ornament", ChristmasBlocks.PHANTOM_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> PIG_HEAD_ORNAMENT =
            registerItem("pig_head_ornament", ChristmasBlocks.PIG_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> COW_HEAD_ORNAMENT =
            registerItem("cow_head_ornament", ChristmasBlocks.COW_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> CHICKEN_HEAD_ORNAMENT =
            registerItem("chicken_head_ornament", ChristmasBlocks.CHICKEN_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);
    public static final RegistryObject<BlockItem> SHEEP_HEAD_ORNAMENT =
            registerItem("sheep_head_ornament", ChristmasBlocks.SHEEP_HEAD_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.RARE);

    public static final RegistryObject<BlockItem> RED_STOCKING =
            registerItem("red_stocking", ChristmasBlocks.RED_STOCKING, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> BLUE_STOCKING =
            registerItem("blue_stocking", ChristmasBlocks.BLUE_STOCKING, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> YELLOW_STOCKING =
            registerItem("yellow_stocking", ChristmasBlocks.YELLOW_STOCKING, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GREEN_STOCKING =
            registerItem("green_stocking", ChristmasBlocks.GREEN_STOCKING, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GOLD_STOCKING =
            registerItem("gold_stocking", ChristmasBlocks.GOLD_STOCKING, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SILVER_STOCKING =
            registerItem("silver_stocking", ChristmasBlocks.SILVER_STOCKING, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_TEMPLATE =
            registerItem("alphabet_ornament_template", ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_A =
            registerItem("alphabet_ornament_a", ChristmasBlocks.ALPHABET_ORNAMENT_A, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_B =
            registerItem("alphabet_ornament_b", ChristmasBlocks.ALPHABET_ORNAMENT_B, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_C =
            registerItem("alphabet_ornament_c", ChristmasBlocks.ALPHABET_ORNAMENT_C, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_D =
            registerItem("alphabet_ornament_d", ChristmasBlocks.ALPHABET_ORNAMENT_D, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_E =
            registerItem("alphabet_ornament_e", ChristmasBlocks.ALPHABET_ORNAMENT_E, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_F =
            registerItem("alphabet_ornament_f", ChristmasBlocks.ALPHABET_ORNAMENT_F, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_G =
            registerItem("alphabet_ornament_g", ChristmasBlocks.ALPHABET_ORNAMENT_G, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_H =
            registerItem("alphabet_ornament_h", ChristmasBlocks.ALPHABET_ORNAMENT_H, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_I =
            registerItem("alphabet_ornament_i", ChristmasBlocks.ALPHABET_ORNAMENT_I, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_J =
            registerItem("alphabet_ornament_j", ChristmasBlocks.ALPHABET_ORNAMENT_J, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_K =
            registerItem("alphabet_ornament_k", ChristmasBlocks.ALPHABET_ORNAMENT_K, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_L =
            registerItem("alphabet_ornament_l", ChristmasBlocks.ALPHABET_ORNAMENT_L, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_M =
            registerItem("alphabet_ornament_m", ChristmasBlocks.ALPHABET_ORNAMENT_M, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_N =
            registerItem("alphabet_ornament_n", ChristmasBlocks.ALPHABET_ORNAMENT_N, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_O =
            registerItem("alphabet_ornament_o", ChristmasBlocks.ALPHABET_ORNAMENT_O, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_P =
            registerItem("alphabet_ornament_p", ChristmasBlocks.ALPHABET_ORNAMENT_P, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_Q =
            registerItem("alphabet_ornament_q", ChristmasBlocks.ALPHABET_ORNAMENT_Q, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_R =
            registerItem("alphabet_ornament_r", ChristmasBlocks.ALPHABET_ORNAMENT_R, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_S =
            registerItem("alphabet_ornament_s", ChristmasBlocks.ALPHABET_ORNAMENT_S, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_T =
            registerItem("alphabet_ornament_t", ChristmasBlocks.ALPHABET_ORNAMENT_T, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_U =
            registerItem("alphabet_ornament_u", ChristmasBlocks.ALPHABET_ORNAMENT_U, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_V =
            registerItem("alphabet_ornament_v", ChristmasBlocks.ALPHABET_ORNAMENT_V, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_W =
            registerItem("alphabet_ornament_w", ChristmasBlocks.ALPHABET_ORNAMENT_W, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_X =
            registerItem("alphabet_ornament_x", ChristmasBlocks.ALPHABET_ORNAMENT_X, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_Y =
            registerItem("alphabet_ornament_y", ChristmasBlocks.ALPHABET_ORNAMENT_Y, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> ALPHABET_ORNAMENT_Z =
            registerItem("alphabet_ornament_z", ChristmasBlocks.ALPHABET_ORNAMENT_Z, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> CHRISTMAS_WREATH =
            registerItem("christmas_wreath", ChristmasBlocks.CHRISTMAS_WREATH, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> FROST =
            registerItem("frost", ChristmasBlocks.FROST, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SANTA_LIST =
            registerItem("santa_list", ChristmasBlocks.SANTA_LIST, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> BABY_PRESENT_ORNAMENT =
            registerItem("baby_present_ornament", ChristmasBlocks.BABY_PRESENT_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.LEGENDARY);
    public static final RegistryObject<BlockItem> ADULT_PRESENT_ORNAMENT =
            registerItem("adult_present_ornament", ChristmasBlocks.ADULT_PRESENT_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.LEGENDARY);
    public static final RegistryObject<BlockItem> ELDER_PRESENT_ORNAMENT =
            registerItem("elder_present_ornament", ChristmasBlocks.ELDER_PRESENT_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.LEGENDARY);
    public static final RegistryObject<BlockItem> CANDY_CANE_ORNAMENT =
            registerItem("candy_cane_ornament", ChristmasBlocks.CANDY_CANE_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.LEGENDARY);
    public static final RegistryObject<BlockItem> SANTA_ELF_ORNAMENT =
            registerItem("santa_elf_ornament", ChristmasBlocks.SANTA_ELF_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.LEGENDARY);
    public static final RegistryObject<BlockItem> GINGERBREAD_MAN_ORNAMENT =
            registerItem("gingerbread_man_ornament", ChristmasBlocks.GINGERBREAD_MAN_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.LEGENDARY);
    public static final RegistryObject<BlockItem> GRINCH_ORNAMENT =
            registerItem("grinch_ornament", ChristmasBlocks.GRINCH_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.LEGENDARY);
    public static final RegistryObject<BlockItem> NUTCRACKER_ORNAMENT =
            registerItem("nutcracker_ornament", ChristmasBlocks.NUTCRACKER_ORNAMENT, DEFAULT_PROPS, ChristmasRarity.LEGENDARY);

    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_BLOCK =
            registerItem("gingerbread_dough_block", ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_STAIRS =
            registerItem("gingerbread_dough_stairs", ChristmasBlocks.GINGERBREAD_DOUGH_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_SLAB =
            registerItem("gingerbread_dough_slab", ChristmasBlocks.GINGERBREAD_DOUGH_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_WALL =
            registerItem("gingerbread_dough_wall", ChristmasBlocks.GINGERBREAD_DOUGH_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_BRICKS =
            registerItem("gingerbread_dough_bricks", ChristmasBlocks.GINGERBREAD_DOUGH_BRICKS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_BRICK_STAIRS =
            registerItem("gingerbread_dough_brick_stairs", ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_BRICK_SLAB =
            registerItem("gingerbread_dough_brick_slab", ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_BRICK_WALL =
            registerItem("gingerbread_dough_brick_wall", ChristmasBlocks.GINGERBREAD_DOUGH_BRICK_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_TILES =
            registerItem("gingerbread_dough_tiles", ChristmasBlocks.GINGERBREAD_DOUGH_TILES, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_TILE_STAIRS =
            registerItem("gingerbread_dough_tile_stairs", ChristmasBlocks.GINGERBREAD_DOUGH_TILE_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_TILE_SLAB =
            registerItem("gingerbread_dough_tile_slab", ChristmasBlocks.GINGERBREAD_DOUGH_TILE_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_DOUGH_TILE_WALL =
            registerItem("gingerbread_dough_tile_wall", ChristmasBlocks.GINGERBREAD_DOUGH_TILE_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> GINGERBREAD_BLOCK =
            registerItem("gingerbread_block", ChristmasBlocks.GINGERBREAD_BLOCK, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_STAIRS =
            registerItem("gingerbread_stairs", ChristmasBlocks.GINGERBREAD_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_SLAB =
            registerItem("gingerbread_slab", ChristmasBlocks.GINGERBREAD_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_WALL =
            registerItem("gingerbread_wall", ChristmasBlocks.GINGERBREAD_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> GINGERBREAD_BRICKS =
            registerItem("gingerbread_bricks", ChristmasBlocks.GINGERBREAD_BRICKS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_BRICK_STAIRS =
            registerItem("gingerbread_brick_stairs", ChristmasBlocks.GINGERBREAD_BRICK_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_BRICK_SLAB =
            registerItem("gingerbread_brick_slab", ChristmasBlocks.GINGERBREAD_BRICK_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_BRICK_WALL =
            registerItem("gingerbread_brick_wall", ChristmasBlocks.GINGERBREAD_BRICK_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> GINGERBREAD_TILES =
            registerItem("gingerbread_tiles", ChristmasBlocks.GINGERBREAD_TILES, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_TILE_STAIRS =
            registerItem("gingerbread_tile_stairs", ChristmasBlocks.GINGERBREAD_TILE_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_TILE_SLAB =
            registerItem("gingerbread_tile_slab", ChristmasBlocks.GINGERBREAD_TILE_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GINGERBREAD_TILE_WALL =
            registerItem("gingerbread_tile_wall", ChristmasBlocks.GINGERBREAD_TILE_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_BLOCK =
            registerItem("soggy_gingerbread_block", ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_STAIRS =
            registerItem("soggy_gingerbread_stairs", ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_SLAB =
            registerItem("soggy_gingerbread_slab", ChristmasBlocks.SOGGY_GINGERBREAD_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_WALL =
            registerItem("soggy_gingerbread_wall", ChristmasBlocks.SOGGY_GINGERBREAD_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_BRICKS =
            registerItem("soggy_gingerbread_bricks", ChristmasBlocks.SOGGY_GINGERBREAD_BRICKS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_BRICK_STAIRS =
            registerItem("soggy_gingerbread_brick_stairs", ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_BRICK_SLAB =
            registerItem("soggy_gingerbread_brick_slab", ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_BRICK_WALL =
            registerItem("soggy_gingerbread_brick_wall", ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_TILES =
            registerItem("soggy_gingerbread_tiles", ChristmasBlocks.SOGGY_GINGERBREAD_TILES, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_TILE_STAIRS =
            registerItem("soggy_gingerbread_tile_stairs", ChristmasBlocks.SOGGY_GINGERBREAD_TILE_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_TILE_SLAB =
            registerItem("soggy_gingerbread_tile_slab", ChristmasBlocks.SOGGY_GINGERBREAD_TILE_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> SOGGY_GINGERBREAD_TILE_WALL =
            registerItem("soggy_gingerbread_tile_wall", ChristmasBlocks.SOGGY_GINGERBREAD_TILE_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> CANDY_CANE_BLOCK =
            registerItem("candy_cane_block", ChristmasBlocks.CANDY_CANE_BLOCK, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> CANDY_CANE_STAIRS =
            registerItem("candy_cane_stairs", ChristmasBlocks.CANDY_CANE_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> CANDY_CANE_SLAB =
            registerItem("candy_cane_slab", ChristmasBlocks.CANDY_CANE_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> CANDY_CANE_WALL =
            registerItem("candy_cane_wall", ChristmasBlocks.CANDY_CANE_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> CANDY_CANE_BRICKS =
            registerItem("candy_cane_bricks", ChristmasBlocks.CANDY_CANE_BRICKS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> CANDY_CANE_BRICK_STAIRS =
            registerItem("candy_cane_brick_stairs", ChristmasBlocks.CANDY_CANE_BRICK_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> CANDY_CANE_BRICK_SLAB =
            registerItem("candy_cane_brick_slab", ChristmasBlocks.CANDY_CANE_BRICK_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> CANDY_CANE_BRICK_WALL =
            registerItem("candy_cane_brick_wall", ChristmasBlocks.CANDY_CANE_BRICK_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> CANDY_CANE_TILES =
            registerItem("candy_cane_tiles", ChristmasBlocks.CANDY_CANE_TILES, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> CANDY_CANE_TILE_STAIRS =
            registerItem("candy_cane_tile_stairs", ChristmasBlocks.CANDY_CANE_TILE_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> CANDY_CANE_TILE_SLAB =
            registerItem("candy_cane_tile_slab", ChristmasBlocks.CANDY_CANE_TILE_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> CANDY_CANE_TILE_WALL =
            registerItem("candy_cane_tile_wall", ChristmasBlocks.CANDY_CANE_TILE_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_BLOCK =
            registerItem("festive_candy_cane_block", ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_STAIRS =
            registerItem("festive_candy_cane_stairs", ChristmasBlocks.FESTIVE_CANDY_CANE_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_SLAB =
            registerItem("festive_candy_cane_slab", ChristmasBlocks.FESTIVE_CANDY_CANE_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_WALL =
            registerItem("festive_candy_cane_wall", ChristmasBlocks.FESTIVE_CANDY_CANE_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_BRICKS =
            registerItem("festive_candy_cane_bricks", ChristmasBlocks.FESTIVE_CANDY_CANE_BRICKS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_BRICK_STAIRS =
            registerItem("festive_candy_cane_brick_stairs", ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_BRICK_SLAB =
            registerItem("festive_candy_cane_brick_slab", ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_BRICK_WALL =
            registerItem("festive_candy_cane_brick_wall", ChristmasBlocks.FESTIVE_CANDY_CANE_BRICK_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_TILES =
            registerItem("festive_candy_cane_tiles", ChristmasBlocks.FESTIVE_CANDY_CANE_TILES, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_TILE_STAIRS =
            registerItem("festive_candy_cane_tile_stairs", ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_STAIRS, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_TILE_SLAB =
            registerItem("festive_candy_cane_tile_slab", ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_SLAB, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> FESTIVE_CANDY_CANE_TILE_WALL =
            registerItem("festive_candy_cane_tile_wall", ChristmasBlocks.FESTIVE_CANDY_CANE_TILE_WALL, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> CHRISTMAS_STAR =
            registerItem("christmas_star", ChristmasBlocks.CHRISTMAS_STAR, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> MUSIC_BOX =
            registerItem("music_box", ChristmasBlocks.MUSIC_BOX, DEFAULT_PROPS);
    public static final RegistryObject<BlockItem> GIFT_WRAPPING_STATION =
            registerItem("gift_wrapping_station", ChristmasBlocks.GIFT_WRAPPING_STATION, DEFAULT_PROPS);

    public static final RegistryObject<BlockItem> MILK_AND_COOKIES =
            registerFood("milk_and_cookies", ChristmasBlocks.MILK_AND_COOKIES, BIG_FOOD_PROPS);
    public static final RegistryObject<BlockItem> LOG_CAKE =
            registerFood("log_cake", ChristmasBlocks.LOG_CAKE, BIG_FOOD_PROPS);
    public static final RegistryObject<BlockItem> CHRISTMAS_HAM =
            registerFood("christmas_ham", ChristmasBlocks.CHRISTMAS_HAM, BIG_FOOD_PROPS);
    public static final RegistryObject<BlockItem> CHRISTMAS_PUDDING =
            registerFood("christmas_pudding", ChristmasBlocks.CHRISTMAS_PUDDING, BIG_FOOD_PROPS);

    private static RegistryObject<Item> registerItem(String itemId, Item.Properties itemProps) {
        return ITEMS.register(itemId, () -> new ChristmasItem(itemProps));
    }

    private static RegistryObject<Item> registerItem(String itemId, Item.Properties itemProps, ChristmasRarity rarity) {
        return ITEMS.register(itemId, () -> new ChristmasItem(itemProps, rarity));
    }

    private static RegistryObject<BlockItem> registerItem(String itemId, Supplier<? extends Block> block, Item.Properties itemProps) {
        return ITEMS.register(itemId, () -> new ChristmasBlockItem(block.get(), itemProps));
    }

    private static RegistryObject<BlockItem> registerItem(String itemId, Supplier<? extends Block> block, Item.Properties itemProps, ChristmasRarity rarity) {
        return ITEMS.register(itemId, () -> new ChristmasBlockItem(block.get(), itemProps, rarity));
    }

    private static RegistryObject<Item> registerFood(String foodId, Item.Properties itemProps) {
        return ITEMS.register(foodId, () -> new ChristmasItem(itemProps));
    }

    private static RegistryObject<BlockItem> registerFood(String foodId, Supplier<Block> block, Item.Properties itemProps) {
        return ITEMS.register(foodId, () -> new ChristmasBlockItem(block.get(), itemProps));
    }

    private static RegistryObject<Item> registerCustomItem(String itemId, final Supplier<? extends Item> sup) {
        return ITEMS.register(itemId, sup);
    }

    private static RegistryObject<BlockItem> registerCustomBlockItem(String itemId, final Supplier<? extends BlockItem> sup) {
        return ITEMS.register(itemId, sup);
    }

    public static Supplier<Item> spawnEggOf(Supplier<EntityType<? extends Mob>> entityType, int backgroundColor, int highlightColor) {
        return () -> new ForgeSpawnEggItem(entityType::get, backgroundColor, highlightColor, new Item.Properties().tab(CreativeModeTab.TAB_MISC));
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
