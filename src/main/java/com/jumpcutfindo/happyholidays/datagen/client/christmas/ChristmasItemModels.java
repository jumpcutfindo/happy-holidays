package com.jumpcutfindo.happyholidays.datagen.client.christmas;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Sets;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ChristmasItemModels extends ItemModelProvider {
    public static final String ITEM_GENERATED = "item/generated";
    public static final String ITEM_SPAWN_EGG = "item/template_spawn_egg";

    public ChristmasItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, HappyHolidaysMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerBlocksWithModel();
        registerBlocksWithItemTextures();
        registerItemsWithTexture();
        registerItemsWithLayeredTextures();
    }

    // For block items that inherit from the block model itself
    private void registerBlocksWithModel() {
        Set<Pair<Item, String>> blockItems = Sets.newHashSet(
                blockModelPair(ChristmasItems.BABY_PRESENT.get()),
                blockModelPair(ChristmasItems.ADULT_PRESENT.get()),
                blockModelPair(ChristmasItems.ELDER_PRESENT.get()),
                blockModelPair(ChristmasItems.EXPLOSIVE_PRESENT.get()),

                blockModelPair(ChristmasItems.GRINCH_ORNAMENT.get()),
                blockModelPair(ChristmasItems.SANTA_ELF_ORNAMENT.get()),
                blockModelPair(ChristmasItems.NUTCRACKER_ORNAMENT.get()),

                blockModelPair(ChristmasItems.RED_BAUBLE.get()),
                blockModelPair(ChristmasItems.BLUE_BAUBLE.get()),
                blockModelPair(ChristmasItems.YELLOW_BAUBLE.get()),
                blockModelPair(ChristmasItems.GREEN_BAUBLE.get()),
                blockModelPair(ChristmasItems.GOLD_BAUBLE.get()),
                blockModelPair(ChristmasItems.SILVER_BAUBLE.get()),

                blockModelPair(ChristmasItems.BIG_RED_BAUBLE.get()),
                blockModelPair(ChristmasItems.BIG_BLUE_BAUBLE.get()),
                blockModelPair(ChristmasItems.BIG_YELLOW_BAUBLE.get()),
                blockModelPair(ChristmasItems.BIG_GREEN_BAUBLE.get()),
                blockModelPair(ChristmasItems.BIG_GOLD_BAUBLE.get()),
                blockModelPair(ChristmasItems.BIG_SILVER_BAUBLE.get()),

                blockModelPair(ChristmasItems.CREEPER_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.SKELETON_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.WITHER_SKELETON_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.ZOMBIE_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.DROWNED_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.BLAZE_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.GHAST_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.PHANTOM_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.PIG_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.COW_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.CHICKEN_HEAD_ORNAMENT.get()),
                blockModelPair(ChristmasItems.SHEEP_HEAD_ORNAMENT.get()),

                blockModelPair(ChristmasItems.GINGERBREAD_BLOCK.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_SLAB.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_STAIRS.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_WALL.get(), "gingerbread_wall_inventory"),

                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_SLAB.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_STAIRS.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_WALL.get(), "gingerbread_dough_wall_inventory"),

                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get()),
                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_SLAB.get()),
                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_STAIRS.get()),
                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_WALL.get(), "soggy_gingerbread_wall_inventory"),

                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_BRICK_STAIRS.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_BRICK_SLAB.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_BRICK_WALL.get(), "gingerbread_dough_brick_wall_inventory"),

                blockModelPair(ChristmasItems.GINGERBREAD_BRICKS.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_BRICK_STAIRS.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_BRICK_SLAB.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_BRICK_WALL.get(), "gingerbread_brick_wall_inventory"),

                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get()),
                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_BRICK_STAIRS.get()),
                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_BRICK_SLAB.get()),
                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_BRICK_WALL.get(), "soggy_gingerbread_brick_wall_inventory"),

                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_TILES.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_TILE_STAIRS.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_TILE_SLAB.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_DOUGH_TILE_WALL.get(), "gingerbread_dough_tile_wall_inventory"),

                blockModelPair(ChristmasItems.GINGERBREAD_TILES.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_TILE_STAIRS.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_TILE_SLAB.get()),
                blockModelPair(ChristmasItems.GINGERBREAD_TILE_WALL.get(), "gingerbread_tile_wall_inventory"),

                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_TILES.get()),
                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_TILE_STAIRS.get()),
                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_TILE_SLAB.get()),
                blockModelPair(ChristmasItems.SOGGY_GINGERBREAD_TILE_WALL.get(), "soggy_gingerbread_tile_wall_inventory"),

                blockModelPair(ChristmasItems.CANDY_CANE_BLOCK.get()),
                blockModelPair(ChristmasItems.CANDY_CANE_STAIRS.get()),
                blockModelPair(ChristmasItems.CANDY_CANE_SLAB.get()),
                blockModelPair(ChristmasItems.CANDY_CANE_WALL.get(), "candy_cane_wall_inventory"),

                blockModelPair(ChristmasItems.CANDY_CANE_BRICKS.get()),
                blockModelPair(ChristmasItems.CANDY_CANE_BRICK_STAIRS.get()),
                blockModelPair(ChristmasItems.CANDY_CANE_BRICK_SLAB.get()),
                blockModelPair(ChristmasItems.CANDY_CANE_BRICK_WALL.get(), "candy_cane_brick_wall_inventory"),

                blockModelPair(ChristmasItems.CANDY_CANE_TILES.get()),
                blockModelPair(ChristmasItems.CANDY_CANE_TILE_STAIRS.get()),
                blockModelPair(ChristmasItems.CANDY_CANE_TILE_SLAB.get()),
                blockModelPair(ChristmasItems.CANDY_CANE_TILE_WALL.get(), "candy_cane_tile_wall_inventory"),

                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_BLOCK.get(), "festive_candy_cane_block_xo"),
                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_STAIRS.get(), "festive_candy_cane_stairs_xo"),
                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_SLAB.get(), "festive_candy_cane_slab_xo"),
                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_WALL.get(), "festive_candy_cane_wall_inventory"),

                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_BRICKS.get(), "festive_candy_cane_bricks_xo"),
                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_BRICK_STAIRS.get(), "festive_candy_cane_brick_stairs_xo"),
                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_BRICK_SLAB.get(), "festive_candy_cane_brick_slab_xo"),
                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_BRICK_WALL.get(), "festive_candy_cane_brick_wall_inventory"),

                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_TILES.get(), "festive_candy_cane_tiles_xo"),
                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_TILE_STAIRS.get(), "festive_candy_cane_tile_stairs_xo"),
                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_TILE_SLAB.get(), "festive_candy_cane_tile_slab_xo"),
                blockModelPair(ChristmasItems.FESTIVE_CANDY_CANE_TILE_WALL.get(), "festive_candy_cane_tile_wall_inventory"),

                blockModelPair(ChristmasItems.GIFT_WRAPPING_STATION.get()),
                blockModelPair(ChristmasItems.MUSIC_BOX.get())
        );

        for (Pair<Item, String> itemPair : blockItems) blockWithCustomModel(itemPair.getKey(), itemPair.getValue());
    }

    // For block items with a specific item texture made for them
    private void registerBlocksWithItemTextures() {
        Set<Item> blockItems = Sets.newHashSet(
                ChristmasItems.BABY_PRESENT_ORNAMENT.get(),
                ChristmasItems.ADULT_PRESENT_ORNAMENT.get(),
                ChristmasItems.ELDER_PRESENT_ORNAMENT.get(),
                ChristmasItems.CANDY_CANE_ORNAMENT.get(),
                ChristmasItems.GINGERBREAD_MAN_ORNAMENT.get(),
                ChristmasItems.NUTCRACKER_ORNAMENT.get(),

                ChristmasItems.RED_CHRISTMAS_LIGHTS.get(),
                ChristmasItems.BLUE_CHRISTMAS_LIGHTS.get(),
                ChristmasItems.YELLOW_CHRISTMAS_LIGHTS.get(),
                ChristmasItems.GREEN_CHRISTMAS_LIGHTS.get(),
                ChristmasItems.GOLD_CHRISTMAS_LIGHTS.get(),
                ChristmasItems.SILVER_CHRISTMAS_LIGHTS.get(),

                ChristmasItems.RED_CHRISTMAS_BELLS.get(),
                ChristmasItems.BLUE_CHRISTMAS_BELLS.get(),
                ChristmasItems.YELLOW_CHRISTMAS_BELLS.get(),
                ChristmasItems.GREEN_CHRISTMAS_BELLS.get(),
                ChristmasItems.GOLD_CHRISTMAS_BELLS.get(),
                ChristmasItems.SILVER_CHRISTMAS_BELLS.get(),

                ChristmasItems.RED_TINSEL.get(),
                ChristmasItems.BLUE_TINSEL.get(),
                ChristmasItems.YELLOW_TINSEL.get(),
                ChristmasItems.GREEN_TINSEL.get(),
                ChristmasItems.GOLD_TINSEL.get(),
                ChristmasItems.SILVER_TINSEL.get(),

                ChristmasItems.RED_STOCKING.get(),
                ChristmasItems.BLUE_STOCKING.get(),
                ChristmasItems.YELLOW_STOCKING.get(),
                ChristmasItems.GREEN_STOCKING.get(),
                ChristmasItems.GOLD_STOCKING.get(),
                ChristmasItems.SILVER_STOCKING.get(),

                ChristmasItems.CHRISTMAS_WREATH.get(),
                ChristmasItems.FROST.get(),
                ChristmasItems.SANTA_LIST.get(),

                ChristmasItems.CHRISTMAS_HAM.get(),
                ChristmasItems.CHRISTMAS_PUDDING.get(),
                ChristmasItems.LOG_CAKE.get(),
                ChristmasItems.MILK_AND_COOKIES.get(),

                ChristmasItems.CHRISTMAS_STAR.get()
        );

        for (Item item : blockItems) blockWithItemTexture(item, itemId(item));
    }

    // For items in general
    private void registerItemsWithTexture() {
        Set<Pair<Item, String>> items = Sets.newHashSet(
                itemTexturePair(ChristmasItems.RED_CHRISTMAS_GIFT_ITEM.get()),
                itemTexturePair(ChristmasItems.BLUE_CHRISTMAS_GIFT_ITEM.get()),
                itemTexturePair(ChristmasItems.YELLOW_CHRISTMAS_GIFT_ITEM.get()),
                itemTexturePair(ChristmasItems.GREEN_CHRISTMAS_GIFT_ITEM.get()),
                itemTexturePair(ChristmasItems.GOLD_CHRISTMAS_GIFT_ITEM.get()),
                itemTexturePair(ChristmasItems.SILVER_CHRISTMAS_GIFT_ITEM.get()),

                itemTexturePair(ChristmasItems.DYE_BOWL.get()),
                itemTexturePair(ChristmasItems.RED_CHRISTMAS_DYE.get()),
                itemTexturePair(ChristmasItems.BLUE_CHRISTMAS_DYE.get()),
                itemTexturePair(ChristmasItems.YELLOW_CHRISTMAS_DYE.get()),
                itemTexturePair(ChristmasItems.GREEN_CHRISTMAS_DYE.get()),
                itemTexturePair(ChristmasItems.GOLD_CHRISTMAS_DYE.get()),
                itemTexturePair(ChristmasItems.SILVER_CHRISTMAS_DYE.get()),

                itemTexturePair(ChristmasItems.SHEET_MUSIC_ANGELS_ON_HIGH.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_CAROL_OF_THE_BELLS.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_DECK_THE_HALLS.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_FROSTY_THE_SNOWMAN.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_GOD_REST_GENTLEMEN.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_HERE_COMES_SANTA.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_JINGLE_BELL_ROCK.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_JINGLE_BELLS.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_JOY_TO_THE_WORLD.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_RUDOLPH.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_SILENT_NIGHT.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_SLEIGH_RIDE.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_THE_FIRST_NOEL.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_WE_THREE_KINGS.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_WE_WISH_YOU.get(), "christmas_sheet_music"),
                itemTexturePair(ChristmasItems.SHEET_MUSIC_WHITE_CHRISTMAS.get(), "christmas_sheet_music"),

                itemTexturePair(ChristmasItems.EGGNOG.get()),

                itemTexturePair(ChristmasItems.CANDY_CANE.get()),
                itemTexturePair(ChristmasItems.FESTIVE_CANDY_CANE.get()),
                itemTexturePair(ChristmasItems.ENCHANTED_CANDY_CANE.get(), "candy_cane"),
                itemTexturePair(ChristmasItems.SANTA_ELF_BELL.get()),

                itemTexturePair(ChristmasItems.GINGERBREAD_COOKIE.get()),
                itemTexturePair(ChristmasItems.RAW_GINGERBREAD.get()),

                itemTexturePair(ChristmasItems.THREAD.get()),
                itemTexturePair(ChristmasItems.ENCHANTED_THREAD.get(), "thread"),

                itemTexturePair(ChristmasItems.WALNUT.get()),
                itemTexturePair(ChristmasItems.EXPLOSIVE_WALNUT.get()),
                itemTexturePair(ChristmasItems.SUGARED_WALNUT.get()),
                itemTexturePair(ChristmasItems.METALLIC_WALNUT.get()),
                itemTexturePair(ChristmasItems.HALVED_WALNUT.get()),

                itemTexturePair(ChristmasItems.SWAGGER_STICK.get()),
                itemTexturePair(ChristmasItems.PATROL_ORDERS.get()),

                itemTexturePair(ChristmasItems.SANTA_HAT.get()),
                itemTexturePair(ChristmasItems.ENCHANTED_SANTA_HAT.get(), "santa_hat"),
                itemTexturePair(ChristmasItems.SANTA_TOP.get()),
                itemTexturePair(ChristmasItems.SANTA_BOTTOM.get()),
                itemTexturePair(ChristmasItems.SANTA_BOOTS.get()),

                itemTexturePair(ChristmasItems.SANTA_ELF_HAT.get()),
                itemTexturePair(ChristmasItems.SANTA_ELF_TOP.get()),
                itemTexturePair(ChristmasItems.SANTA_ELF_BOTTOM.get()),
                itemTexturePair(ChristmasItems.SANTA_ELF_BOOTS.get()),

                itemTexturePair(ChristmasItems.SNOWMAN_HEADPIECE.get()),
                itemTexturePair(ChristmasItems.SNOWMAN_TOP.get()),
                itemTexturePair(ChristmasItems.SNOWMAN_BOTTOM.get()),

                itemTexturePair(ChristmasItems.CANDY_CANE_HEADPIECE.get()),
                itemTexturePair(ChristmasItems.CANDY_CANE_TOP.get()),
                itemTexturePair(ChristmasItems.CANDY_CANE_BOTTOM.get()),
                itemTexturePair(ChristmasItems.CANDY_CANE_BOOTS.get()),

                itemTexturePair(ChristmasItems.REINDEER_HEADPIECE.get()),
                itemTexturePair(ChristmasItems.REINDEER_TOP.get()),
                itemTexturePair(ChristmasItems.REINDEER_BOTTOM.get()),
                itemTexturePair(ChristmasItems.REINDEER_BOOTS.get()),

                itemTexturePair(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get()),
                itemTexturePair(ChristmasItems.HOLLY.get()),
                itemTexturePair(ChristmasItems.PRESENT_SCRAPS.get()),
                itemTexturePair(ChristmasItems.SNOW_GLOBE.get()),
                itemTexturePair(ChristmasItems.TOY_PARTS_REQUEST.get())
        );

        for (Pair<Item, String> item : items) itemWithTexture(item.getKey(), item.getValue());

        Set<Item> spawnEggs = Sets.newHashSet(
                ChristmasItems.SANTA_ELF_SPAWN_EGG.get(),
                ChristmasItems.GINGERBREAD_MAN_SPAWN_EGG.get(),
                ChristmasItems.GRINCH_SPAWN_EGG.get(),
                ChristmasItems.NUTCRACKER_SPAWN_EGG.get(),
                ChristmasItems.HAPPY_SANTA_SPAWN_EGG.get(),
                ChristmasItems.ANGRY_SANTA_SPAWN_EGG.get()
        );

        for (Item spawnEgg : spawnEggs) spawnEggWithTemplate(spawnEgg);
    }

    // For items with multiple layers
    private void registerItemsWithLayeredTextures() {
        Set<Pair<Item, List<ResourceLocation>>> items = Sets.newHashSet(
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_TEMPLATE.get(), List.of(resourceOfItem("alphabet_ornament_template"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_A.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_a"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_B.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_b"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_C.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_c"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_D.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_d"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_E.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_e"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_F.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_f"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_G.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_g"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_H.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_h"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_I.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_i"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_J.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_j"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_K.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_k"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_L.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_l"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_M.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_m"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_N.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_n"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_O.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_o"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_P.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_p"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_Q.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_q"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_R.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_r"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_S.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_s"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_T.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_t"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_U.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_u"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_V.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_v"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_W.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_w"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_X.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_x"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_Y.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_y"))),
                Pair.of(ChristmasItems.ALPHABET_ORNAMENT_Z.get(), List.of(resourceOfItem("alphabet_ornament_template"), resourceOfBlock("alphabet_z")))
        );

        for (Pair<Item, List<ResourceLocation>> itemPair : items) {
            itemWithSpecificTextureResource(itemPair.getKey(), itemPair.getValue().toArray(new ResourceLocation[0]));
        }
    }

    private Pair<Item, String> itemTexturePair(Item item) {
        return Pair.of(item, itemId(item));
    }

    private Pair<Item, String> itemTexturePair(Item item, String textureId) {
        return Pair.of(item, textureId);
    }

    private Pair<Item, String> blockModelPair(Item item) {
        return Pair.of(item, itemId(item));
    }

    private Pair<Item, String> blockModelPair(Item item, String modelId) {
        return Pair.of(item, modelId);
    }

    private void itemWithTexture(Item item, String... textureId) {
        String itemId = itemId(item);

        ItemModelBuilder builder = withExistingParent(itemId, ITEM_GENERATED);
        for (int i = 0; i < textureId.length; i++) {
            builder = builder.texture("layer" + i, resourceOfItem(textureId[i]));
        }
    }

    private void itemWithSpecificTextureResource(Item item, ResourceLocation... resourceLocations) {
        String itemId = itemId(item);

        ItemModelBuilder builder = withExistingParent(itemId, ITEM_GENERATED);
        for (int i = 0; i < resourceLocations.length; i++) {
            builder = builder.texture("layer" + i, resourceLocations[i]);
        }
    }

    private void spawnEggWithTemplate(Item item) {
        String itemId = itemId(item);
        ItemModelBuilder builder = withExistingParent(itemId, ITEM_SPAWN_EGG);
    }

    private void blockWithCustomModel(Item item, String customModelId) {
        withExistingParent(itemId(item), resourceOfBlock(customModelId));
    }

    private void blockWithItemTexture(Item item, String textureId) {
        String itemId = itemId(item);
        withExistingParent(itemId, ITEM_GENERATED).texture("layer0", resourceOfItem(textureId));
    }

    private ResourceLocation resourceOfBlock(String id) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "block/" + id);
    }

    private ResourceLocation resourceOfItem(String id) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, "item/" + id);
    }

    private String itemId(Item item) {
        return item.getRegistryName().getPath();
    }
}
