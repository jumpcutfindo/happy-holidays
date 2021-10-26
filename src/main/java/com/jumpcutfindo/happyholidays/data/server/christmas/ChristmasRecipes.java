package com.jumpcutfindo.happyholidays.data.server.christmas;

import java.util.function.Consumer;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class ChristmasRecipes extends RecipeProvider {
    public ChristmasRecipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        dyeBowl(consumer);
        christmasDye(consumer, ChristmasItems.RED_CHRISTMAS_DYE.get(), Items.RED_DYE);
        christmasDye(consumer, ChristmasItems.BLUE_CHRISTMAS_DYE.get(), Items.BLUE_DYE);
        christmasDye(consumer, ChristmasItems.YELLOW_CHRISTMAS_DYE.get(), Items.YELLOW_DYE);
        christmasDye(consumer, ChristmasItems.GREEN_CHRISTMAS_DYE.get(), Items.GREEN_DYE);
        christmasDye(consumer, ChristmasItems.GOLD_CHRISTMAS_DYE.get(), Items.GOLD_INGOT);
        christmasDye(consumer, ChristmasItems.SILVER_CHRISTMAS_DYE.get(), Items.IRON_INGOT);

        bauble(consumer, ChristmasBlocks.RED_BAUBLE.get(), ChristmasItems.RED_CHRISTMAS_DYE.get());
        bauble(consumer, ChristmasBlocks.BLUE_BAUBLE.get(), ChristmasItems.BLUE_CHRISTMAS_DYE.get());
        bauble(consumer, ChristmasBlocks.YELLOW_BAUBLE.get(), ChristmasItems.YELLOW_CHRISTMAS_DYE.get());
        bauble(consumer, ChristmasBlocks.GREEN_BAUBLE.get(), ChristmasItems.GREEN_CHRISTMAS_DYE.get());
        bauble(consumer, ChristmasBlocks.GOLD_BAUBLE.get(), ChristmasItems.GOLD_CHRISTMAS_DYE.get());
        bauble(consumer, ChristmasBlocks.SILVER_BAUBLE.get(), ChristmasItems.SILVER_CHRISTMAS_DYE.get());

        bigBauble(consumer, ChristmasBlocks.BIG_RED_BAUBLE.get(), ChristmasItems.RED_CHRISTMAS_DYE.get());
        bigBauble(consumer, ChristmasBlocks.BIG_BLUE_BAUBLE.get(), ChristmasItems.BLUE_CHRISTMAS_DYE.get());
        bigBauble(consumer, ChristmasBlocks.BIG_YELLOW_BAUBLE.get(), ChristmasItems.YELLOW_CHRISTMAS_DYE.get());
        bigBauble(consumer, ChristmasBlocks.BIG_GREEN_BAUBLE.get(), ChristmasItems.GREEN_CHRISTMAS_DYE.get());
        bigBauble(consumer, ChristmasBlocks.BIG_GOLD_BAUBLE.get(), ChristmasItems.GOLD_CHRISTMAS_DYE.get());
        bigBauble(consumer, ChristmasBlocks.BIG_SILVER_BAUBLE.get(), ChristmasItems.SILVER_CHRISTMAS_DYE.get());

        tinsel(consumer, ChristmasBlocks.RED_TINSEL.get(), ChristmasItems.RED_CHRISTMAS_DYE.get());
        tinsel(consumer, ChristmasBlocks.BLUE_TINSEL.get(), ChristmasItems.BLUE_CHRISTMAS_DYE.get());
        tinsel(consumer, ChristmasBlocks.YELLOW_TINSEL.get(), ChristmasItems.YELLOW_CHRISTMAS_DYE.get());
        tinsel(consumer, ChristmasBlocks.GREEN_TINSEL.get(), ChristmasItems.GREEN_CHRISTMAS_DYE.get());
        tinsel(consumer, ChristmasBlocks.GOLD_TINSEL.get(), ChristmasItems.GOLD_CHRISTMAS_DYE.get());
        tinsel(consumer, ChristmasBlocks.SILVER_TINSEL.get(), ChristmasItems.SILVER_CHRISTMAS_DYE.get());

        christmasLights(consumer, ChristmasBlocks.RED_CHRISTMAS_LIGHTS.get(), ChristmasItems.RED_CHRISTMAS_DYE.get());
        christmasLights(consumer, ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS.get(), ChristmasItems.BLUE_CHRISTMAS_DYE.get());
        christmasLights(consumer, ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS.get(), ChristmasItems.YELLOW_CHRISTMAS_DYE.get());
        christmasLights(consumer, ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS.get(), ChristmasItems.GREEN_CHRISTMAS_DYE.get());
        christmasLights(consumer, ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS.get(), ChristmasItems.GOLD_CHRISTMAS_DYE.get());
        christmasLights(consumer, ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS.get(), ChristmasItems.SILVER_CHRISTMAS_DYE.get());

        christmasBells(consumer, ChristmasBlocks.RED_CHRISTMAS_BELLS.get(), ChristmasItems.RED_CHRISTMAS_DYE.get());
        christmasBells(consumer, ChristmasBlocks.BLUE_CHRISTMAS_BELLS.get(), ChristmasItems.BLUE_CHRISTMAS_DYE.get());
        christmasBells(consumer, ChristmasBlocks.YELLOW_CHRISTMAS_BELLS.get(), ChristmasItems.YELLOW_CHRISTMAS_DYE.get());
        christmasBells(consumer, ChristmasBlocks.GREEN_CHRISTMAS_BELLS.get(), ChristmasItems.GREEN_CHRISTMAS_DYE.get());
        christmasBells(consumer, ChristmasBlocks.GOLD_CHRISTMAS_BELLS.get(), ChristmasItems.GOLD_CHRISTMAS_DYE.get());
        christmasBells(consumer, ChristmasBlocks.SILVER_CHRISTMAS_BELLS.get(), ChristmasItems.SILVER_CHRISTMAS_DYE.get());

        stocking(consumer, ChristmasBlocks.RED_STOCKING.get(), ChristmasItems.RED_CHRISTMAS_DYE.get());
        stocking(consumer, ChristmasBlocks.BLUE_STOCKING.get(), ChristmasItems.BLUE_CHRISTMAS_DYE.get());
        stocking(consumer, ChristmasBlocks.YELLOW_STOCKING.get(), ChristmasItems.YELLOW_CHRISTMAS_DYE.get());
        stocking(consumer, ChristmasBlocks.GREEN_STOCKING.get(), ChristmasItems.GREEN_CHRISTMAS_DYE.get());
        stocking(consumer, ChristmasBlocks.GOLD_STOCKING.get(), ChristmasItems.GOLD_CHRISTMAS_DYE.get());
        stocking(consumer, ChristmasBlocks.SILVER_STOCKING.get(), ChristmasItems.SILVER_CHRISTMAS_DYE.get());

        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get(), Items.GLASS, 8);
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_A.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_B.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_C.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_D.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_E.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_F.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_G.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_H.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_I.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_J.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_K.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_L.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_M.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_N.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_O.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_P.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_Q.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_R.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_S.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_T.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_U.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_V.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_W.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_X.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_Y.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());
        stonecutterResultFromBase(consumer, ChristmasBlocks.ALPHABET_ORNAMENT_Z.get(), ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get());

        babyPresent(consumer);
        candyCane(consumer);
        christmasStar(consumer);
        christmasWreath(consumer);
        foods(consumer);
        frost(consumer);
        gingerbread(consumer);
        guideBook(consumer);
        mistletoeAndHolly(consumer);
        musicBox(consumer);
        thread(consumer);
        wrappingStation(consumer);
    }

    private void dyeBowl(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasItems.DYE_BOWL.get(), 4)
                .define('P', ItemTags.PLANKS).define('#', Items.STICK)
                .pattern("  #").pattern("P P").pattern(" P ")
                .unlockedBy("has_stick", has(Items.STICK)).unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(consumer, recipeResourceOf(ChristmasItems.DYE_BOWL.get()));
    }

    private void christmasDye(Consumer<FinishedRecipe> consumer, Item christmasDye, ItemLike dyeItem) {
        ShapelessRecipeBuilder.shapeless(christmasDye, 2)
                .group(groupOf("christmas_dye"))
                .requires(ChristmasItems.DYE_BOWL.get()).requires(dyeItem)
                .unlockedBy("has_stick", has(Items.STICK)).unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(consumer, recipeResourceOf(christmasDye));
    }

    private void bauble(Consumer<FinishedRecipe> consumer, Block block, Item christmasDye) {
        ShapedRecipeBuilder.shaped(block, 4)
                .group(groupOf("christmas_bauble"))
                .define('#', Items.STRING).define('G', Items.GLASS).define('A', christmasDye)
                .pattern("# ").pattern("GA")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(block));
    }

    private void bigBauble(Consumer<FinishedRecipe> consumer, Block block, Item christmasDye) {
        ShapedRecipeBuilder.shaped(block, 4)
                .group(groupOf("christmas_big_bauble"))
                .define('#', Items.STRING).define('G', Items.GLASS).define('A', christmasDye)
                .pattern(" # ").pattern("GAG")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(block));
    }

    private void tinsel(Consumer<FinishedRecipe> consumer, Block block, Item christmasDye) {
        ShapedRecipeBuilder.shaped(block, 4)
                .group(groupOf("christmas_tinsel"))
                .define('S', Items.STRING).define('W', Items.WHITE_WOOL).define('A', christmasDye)
                .pattern("SSS").pattern("WAW")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, recipeResourceOf(block));
    }

    private void christmasLights(Consumer<FinishedRecipe> consumer, Block block, Item christmasDye) {
        ShapedRecipeBuilder.shaped(block, 4)
                .group(groupOf("christmas_lights"))
                .define('S', Items.STRING).define('R', Items.GLASS).define('G', Items.GLOWSTONE_DUST).define('A', christmasDye)
                .pattern(" S ").pattern("RGR").pattern(" A ")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(block));
    }

    private void christmasBells(Consumer<FinishedRecipe> consumer, Block block, Item christmasDye) {
        ShapedRecipeBuilder.shaped(block, 4)
                .group(groupOf("christmas_bells"))
                .define('S', Items.STRING).define('I', Items.IRON_INGOT).define('A', christmasDye)
                .pattern(" S ").pattern("IAI")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_ingot", has(Items.IRON_INGOT))
                .save(consumer, recipeResourceOf(block));
    }

    private void stocking(Consumer<FinishedRecipe> consumer, Block block, Item christmasDye) {
        ShapedRecipeBuilder.shaped(block, 1)
                .group(groupOf("christmas_stocking"))
                .define('S', Items.STRING).define('W', Items.WHITE_WOOL).define('A', christmasDye)
                .pattern("S").pattern("W").pattern("A")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, recipeResourceOf(block));
    }

    private void babyPresent(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasBlocks.BABY_PRESENT.get(), 2)
                .define('S', Items.STRING).define('#', ChristmasItems.PRESENT_SCRAPS.get()).define('W', ItemTags.WOOL)
                .pattern("S#S").pattern("WWW")
                .unlockedBy("has_scraps", has(ChristmasItems.PRESENT_SCRAPS.get())).unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BABY_PRESENT.get()));
    }

    private void candyCane(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasBlocks.CANDY_CANE_BLOCK.get(), 1)
                .group("christmas_candy_cane_block")
                .define('#', ChristmasItems.CANDY_CANE.get())
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(ChristmasItems.CANDY_CANE.get()), has(ChristmasItems.CANDY_CANE.get()))
                .save(consumer, recipeResourceOf(ChristmasBlocks.CANDY_CANE_BLOCK.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.CANDY_CANE_BLOCK.get(), 4)
                .group("christmas_candy_cane_block")
                .define('#', Items.SUGAR).define('R', Items.RED_DYE)
                .pattern("R#").pattern("#R")
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR)).unlockedBy(getHasName(Items.RED_DYE), has(Items.RED_DYE))
                .save(consumer, recipeResourceOf(ChristmasBlocks.CANDY_CANE_BLOCK.get()) + "_from_raw_items");

        ShapedRecipeBuilder.shaped(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get(), 1)
                .group("christmas_festive_candy_cane_block")
                .define('#', ChristmasItems.FESTIVE_CANDY_CANE.get())
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(ChristmasItems.FESTIVE_CANDY_CANE.get()), has(ChristmasItems.FESTIVE_CANDY_CANE.get()))
                .save(consumer, recipeResourceOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get(), 4)
                .group("christmas_festive_candy_cane_block")
                .define('#', Items.SUGAR).define('R', Items.RED_DYE).define('G', Items.GREEN_DYE)
                .pattern("R#").pattern("#G")
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR)).unlockedBy(getHasName(Items.RED_DYE), has(Items.RED_DYE)).unlockedBy(getHasName(Items.GREEN_DYE), has(Items.GREEN_DYE))
                .save(consumer, recipeResourceOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get()) + "_from_raw_items");

        ShapelessRecipeBuilder.shapeless(ChristmasItems.CANDY_CANE_ORNAMENT.get(), 1)
                .requires(Items.STRING).requires(ChristmasItems.ENCHANTED_CANDY_CANE.get())
                .unlockedBy(getHasName(ChristmasItems.ENCHANTED_CANDY_CANE.get()), has(ChristmasItems.ENCHANTED_CANDY_CANE.get()))
                .save(consumer, recipeResourceOf(ChristmasItems.CANDY_CANE_ORNAMENT.get()));

        ShapelessRecipeBuilder.shapeless(ChristmasItems.SANTA_ELF_BELL.get(), 1)
                .requires(Items.BELL).requires(ChristmasItems.ENCHANTED_CANDY_CANE.get())
                .unlockedBy(getHasName(ChristmasItems.ENCHANTED_CANDY_CANE.get()), has(ChristmasItems.ENCHANTED_CANDY_CANE.get()))
                .save(consumer, recipeResourceOf(ChristmasItems.SANTA_ELF_BELL.get()));

        twoByTwo(consumer, ChristmasBlocks.CANDY_CANE_BRICKS.get(), ChristmasBlocks.CANDY_CANE_BLOCK.get(), 4);
        twoByTwo(consumer, ChristmasBlocks.CANDY_CANE_TILES.get(), ChristmasBlocks.CANDY_CANE_BRICKS.get(), 4);

        twoByTwo(consumer, ChristmasBlocks.FESTIVE_CANDY_CANE_BRICKS.get(), ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get(), 4);
        twoByTwo(consumer, ChristmasBlocks.FESTIVE_CANDY_CANE_TILES.get(), ChristmasBlocks.FESTIVE_CANDY_CANE_BRICKS.get(), 4);
    }

    private void christmasStar(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasItems.CHRISTMAS_STAR.get())
                .define('G', Items.GOLD_INGOT).define('I', Items.IRON_BLOCK).define('_', Items.SMOOTH_STONE_SLAB)
                .pattern(" G ").pattern("GIG").pattern("G_G")
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer, recipeResourceOf(ChristmasBlocks.CHRISTMAS_STAR.get()));
    }

    private void christmasWreath(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasItems.CHRISTMAS_WREATH.get())
                .define('#', Items.STRING).define('C', ChristmasItems.HOLLY.get())
                .pattern("#C#").pattern("C C").pattern("#C#")
                .unlockedBy(getHasName(ChristmasItems.HOLLY.get()), has(ChristmasItems.HOLLY.get()))
                .save(consumer, recipeResourceOf(ChristmasBlocks.CHRISTMAS_WREATH.get()));
    }

    private void frost(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasItems.FROST.get(), 16)
                .define('#', Items.ICE)
                .pattern("###").pattern("###")
                .unlockedBy(getHasName(ChristmasItems.FROST.get()), has(ChristmasItems.FROST.get())).unlockedBy(getHasName(Items.ICE), has(Items.ICE))
                .save(consumer, recipeResourceOf(ChristmasBlocks.FROST.get()));
    }

    private void foods(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(ChristmasItems.CHRISTMAS_HAM.get())
                .requires(ChristmasItems.HOLLY.get()).requires(Items.COOKED_PORKCHOP).requires(Items.BONE)
                .unlockedBy(getHasName(ChristmasItems.HOLLY.get()), has(ChristmasItems.HOLLY.get()))
                .save(consumer, recipeResourceOf(ChristmasItems.CHRISTMAS_HAM.get()));

        ShapedRecipeBuilder.shaped(ChristmasItems.CHRISTMAS_PUDDING.get())
                .define('#', ChristmasItems.HOLLY.get()).define('W', Items.WHEAT).define('E', Items.EGG)
                .pattern(" # ").pattern(" W ").pattern("WEW")
                .unlockedBy(getHasName(ChristmasItems.HOLLY.get()), has(ChristmasItems.HOLLY.get()))
                .save(consumer, recipeResourceOf(ChristmasItems.CHRISTMAS_PUDDING.get()));

        ShapelessRecipeBuilder.shapeless(ChristmasItems.EGGNOG.get())
                .requires(Items.EGG).requires(Items.MILK_BUCKET).requires(Items.SUGAR).requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG))
                .save(consumer, recipeResourceOf(ChristmasItems.EGGNOG.get()));

        ShapedRecipeBuilder.shaped(ChristmasItems.LOG_CAKE.get())
                .define('A', Items.MILK_BUCKET).define('B', Items.SUGAR).define('C', Items.WHEAT).define('E', Items.EGG).define('G', Items.COCOA_BEANS).define('H', ChristmasItems.HOLLY.get())
                .pattern("BHB").pattern("EAE").pattern("CGC")
                .unlockedBy(getHasName(Items.EGG), has(Items.EGG)).unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(consumer, recipeResourceOf(ChristmasItems.LOG_CAKE.get()));

        ShapelessRecipeBuilder.shapeless(ChristmasItems.MILK_AND_COOKIES.get())
                .requires(Items.MILK_BUCKET).requires(Items.COOKIE).requires(Items.COOKIE).requires(Items.COOKIE)
                .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE)).unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                .save(consumer, recipeResourceOf(ChristmasItems.MILK_AND_COOKIES.get()));
    }

    private void gingerbread(Consumer<FinishedRecipe> consumer) {
        // Crafting recipes
        twoByTwo(consumer, ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get(), ChristmasItems.RAW_GINGERBREAD.get(), 1);
        slab(consumer, ChristmasItems.GINGERBREAD_DOUGH_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());
        stair(consumer, ChristmasItems.GINGERBREAD_DOUGH_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());
        wall(consumer, ChristmasItems.GINGERBREAD_DOUGH_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());

        slab(consumer, ChristmasItems.GINGERBREAD_SLAB.get(), ChristmasItems.GINGERBREAD_BLOCK.get());
        stair(consumer, ChristmasItems.GINGERBREAD_STAIRS.get(), ChristmasItems.GINGERBREAD_BLOCK.get());
        wall(consumer, ChristmasItems.GINGERBREAD_WALL.get(), ChristmasItems.GINGERBREAD_BLOCK.get());

        slab(consumer, ChristmasItems.SOGGY_GINGERBREAD_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());
        stair(consumer, ChristmasItems.SOGGY_GINGERBREAD_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());
        wall(consumer, ChristmasItems.SOGGY_GINGERBREAD_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());

        twoByTwo(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get(), 4);
        stair(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICK_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get());
        slab(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICK_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get());
        wall(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICK_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get());

        twoByTwo(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILES.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get(), 4);
        stair(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILE_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_TILES.get());
        slab(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILE_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_TILES.get());
        wall(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILE_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_TILES.get());

        twoByTwo(consumer, ChristmasItems.GINGERBREAD_BRICKS.get(), ChristmasItems.GINGERBREAD_BLOCK.get(), 4);
        stair(consumer, ChristmasItems.GINGERBREAD_BRICK_STAIRS.get(), ChristmasItems.GINGERBREAD_BRICKS.get());
        slab(consumer, ChristmasItems.GINGERBREAD_BRICK_SLAB.get(), ChristmasItems.GINGERBREAD_BRICKS.get());
        wall(consumer, ChristmasItems.GINGERBREAD_BRICK_WALL.get(), ChristmasItems.GINGERBREAD_BRICKS.get());

        twoByTwo(consumer, ChristmasItems.GINGERBREAD_TILES.get(), ChristmasItems.GINGERBREAD_BRICKS.get(), 4);
        stair(consumer, ChristmasItems.GINGERBREAD_TILE_STAIRS.get(), ChristmasItems.GINGERBREAD_TILES.get());
        slab(consumer, ChristmasItems.GINGERBREAD_TILE_SLAB.get(), ChristmasItems.GINGERBREAD_TILES.get());
        wall(consumer, ChristmasItems.GINGERBREAD_TILE_WALL.get(), ChristmasItems.GINGERBREAD_TILES.get());

        twoByTwo(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get(), 4);
        stair(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICK_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get());
        slab(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICK_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get());
        wall(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICK_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get());

        twoByTwo(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILES.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get(), 4);
        stair(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILE_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_TILES.get());
        slab(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILE_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_TILES.get());
        wall(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILE_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_TILES.get());

        // Stonecutter recipes
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());

        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_STAIRS.get(), ChristmasItems.GINGERBREAD_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_SLAB.get(), ChristmasItems.GINGERBREAD_BLOCK.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_WALL.get(), ChristmasItems.GINGERBREAD_BLOCK.get());

        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());

        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICK_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICK_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICK_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICK_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICK_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_BRICK_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());

        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILES.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILE_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_TILES.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILE_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILE_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_TILES.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILE_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILE_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_TILES.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_DOUGH_TILE_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());

        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICKS.get(), ChristmasItems.GINGERBREAD_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_STAIRS.get(), ChristmasItems.GINGERBREAD_BRICKS.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_STAIRS.get(), ChristmasItems.GINGERBREAD_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_SLAB.get(), ChristmasItems.GINGERBREAD_BRICKS.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_SLAB.get(), ChristmasItems.GINGERBREAD_BLOCK.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_WALL.get(), ChristmasItems.GINGERBREAD_BRICKS.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_WALL.get(), ChristmasItems.GINGERBREAD_BLOCK.get());

        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILES.get(), ChristmasItems.GINGERBREAD_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_STAIRS.get(), ChristmasItems.GINGERBREAD_TILES.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_STAIRS.get(), ChristmasItems.GINGERBREAD_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_SLAB.get(), ChristmasItems.GINGERBREAD_TILES.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_SLAB.get(), ChristmasItems.GINGERBREAD_BLOCK.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_WALL.get(), ChristmasItems.GINGERBREAD_TILES.get());
        stonecutterResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_WALL.get(), ChristmasItems.GINGERBREAD_BLOCK.get());

        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICK_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get());
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICK_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICK_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICK_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICK_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get());
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_BRICK_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());

        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILES.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILE_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_TILES.get());
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILE_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILE_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_TILES.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILE_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get(), 2);
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILE_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_TILES.get());
        stonecutterResultFromBase(consumer, ChristmasItems.SOGGY_GINGERBREAD_TILE_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());

        // Cooking recipes
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_COOKIE.get(), ChristmasItems.RAW_GINGERBREAD.get());

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_BLOCK.get(), ChristmasItems.GINGERBREAD_DOUGH_BLOCK.get());
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_BLOCK.get(), ChristmasItems.SOGGY_GINGERBREAD_BLOCK.get());

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_SLAB.get(), 0.05f, 100);
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_SLAB.get(), 0.05f, 100);

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_STAIRS.get());
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_STAIRS.get());

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_WALL.get());
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_WALL.get());

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICKS.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICKS.get());
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICKS.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICKS.get());

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICK_STAIRS.get());
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICK_STAIRS.get());

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICK_SLAB.get(), 0.05f, 100);
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICK_SLAB.get(), 0.05f, 100);

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_BRICK_WALL.get());
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_BRICK_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_BRICK_WALL.get());

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILES.get(), ChristmasItems.GINGERBREAD_DOUGH_TILES.get());
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILES.get(), ChristmasItems.SOGGY_GINGERBREAD_TILES.get());

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_STAIRS.get(), ChristmasItems.GINGERBREAD_DOUGH_TILE_STAIRS.get());
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_STAIRS.get(), ChristmasItems.SOGGY_GINGERBREAD_TILE_STAIRS.get());

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_SLAB.get(), ChristmasItems.GINGERBREAD_DOUGH_TILE_SLAB.get(), 0.05f, 100);
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_SLAB.get(), ChristmasItems.SOGGY_GINGERBREAD_TILE_SLAB.get(), 0.05f, 100);

        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_WALL.get(), ChristmasItems.GINGERBREAD_DOUGH_TILE_WALL.get());
        cookingResultFromBase(consumer, ChristmasItems.GINGERBREAD_TILE_WALL.get(), ChristmasItems.SOGGY_GINGERBREAD_TILE_WALL.get());
    }

    private void guideBook(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get())
                .requires(Items.BOOK).requires(ChristmasItems.GREEN_CHRISTMAS_DYE.get()).requires(ChristmasItems.RED_CHRISTMAS_DYE.get())
                .unlockedBy(getHasName(Items.DIRT), has(Items.DIRT)).unlockedBy(getHasName(Items.BOOK), has(Items.BOOK))
                .save(consumer, recipeResourceOf(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get()) + "_happyholidays");

        ShapelessRecipeBuilder.shapeless(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get())
                .requires(Items.BOOK).requires(Items.GREEN_DYE).requires(Items.RED_DYE)
                .unlockedBy(getHasName(Items.DIRT), has(Items.DIRT)).unlockedBy(getHasName(Items.BOOK), has(Items.BOOK))
                .save(consumer, recipeResourceOf(ChristmasItems.CHRISTMAS_GUIDE_BOOK.get()) + "_vanilla");
    }

    private void mistletoeAndHolly(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(ChristmasItems.HOLLY.get(), 2)
                .requires(ItemTags.LEAVES).requires(Items.SWEET_BERRIES)
                .unlockedBy("has_leaves", has(ItemTags.LEAVES)).unlockedBy(getHasName(Items.SWEET_BERRIES), has(Items.SWEET_BERRIES))
                .save(consumer, recipeResourceOf(ChristmasItems.HOLLY.get()));
    }

    private void musicBox(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasItems.MUSIC_BOX.get())
                .define('#', Items.GLASS).define('J', Items.JUKEBOX).define('G', Items.GOLD_INGOT)
                .pattern("###").pattern("#JG").pattern("###")
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS)).unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer, recipeResourceOf(ChristmasItems.MUSIC_BOX.get()));
    }

    private void thread(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasItems.THREAD.get(), 2)
                .define('/', Items.STICK).define('#', Items.STRING)
                .pattern("/").pattern("#").pattern("/")
                .unlockedBy(getHasName(Items.STRING), has(Items.STRING)).unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(consumer, recipeResourceOf(ChristmasItems.THREAD.get()));
    }

    private void wrappingStation(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasItems.GIFT_WRAPPING_STATION.get())
                .define('#', ItemTags.PLANKS).define('P', Items.PAPER).define('S', Items.STRING)
                .pattern("PSP").pattern("###").pattern(" # ")
                .unlockedBy("has_planks", has(ItemTags.PLANKS)).unlockedBy(getHasName(Items.STRING), has(Items.STRING))
                .save(consumer, recipeResourceOf(ChristmasItems.GIFT_WRAPPING_STATION.get()));
    }

    private void twoByTwo(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, int count) {
        ShapedRecipeBuilder.shaped(result, count)
                .define('#', ingredient)
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(consumer, recipeResourceOf(result));
    }

    private void slab(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient) {
        slabBuilder(result, Ingredient.of(ingredient)).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer);
    }

    private static RecipeBuilder slabBuilder(ItemLike result, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(result, 6).define('#', ingredient).pattern("###");
    }

    private void stair(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient) {
        stairBuilder(result, Ingredient.of(ingredient)).unlockedBy(getHasName(ingredient), has(ingredient)).save(consumer);
    }

    private static RecipeBuilder stairBuilder(ItemLike result, Ingredient ingredient) {
        return ShapedRecipeBuilder.shaped(result, 4).define('#', ingredient).pattern("#  ").pattern("## ").pattern("###");
    }

    private void stonecutterResultFromBase(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike source) {
        stonecutterResultFromBase(consumer, result, source, 1);
    }

    private void stonecutterResultFromBase(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike source, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(source), result, count).unlockedBy(getHasName(source), has(source)).save(consumer, stonecutterRecipeResourceOf(result, source));
    }

    private ResourceLocation stonecutterRecipeResourceOf(ItemLike result, ItemLike source) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, itemId(result) + "_from_" + itemId(source) + "_stonecutting");
    }

    private void cookingResultFromBase(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient) {
        cookingResultFromBase(consumer, result, ingredient, 0.1f, 200);
    }

    private void cookingResultFromBase(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike ingredient, float xp, int cookingDuration) {
        SimpleCookingRecipeBuilder.cooking(Ingredient.of(ingredient), result, xp, cookingDuration, RecipeSerializer.SMELTING_RECIPE)
                .unlockedBy(getHasName(ingredient), has(ingredient)).unlockedBy(getHasName(result), has(result))
                .save(consumer, recipeResourceOf(result) + "_from_" + itemId(ingredient) + "_smelting");
    }

    private String getHasName(ItemLike item) {
        return "has_" + itemId(item);
    }

    private String itemId(ItemLike item) {
        return item instanceof Item ? ((Item) item).getRegistryName().getPath() : ((Block) item).getRegistryName().getPath();
    }

    private ResourceLocation recipeResourceOf(ItemLike item) {
        String id = item.asItem().getRegistryName().getPath();
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, id);
    }

    private String groupOf(String groupName) {
        return "happyholidays:" + groupName;
    }
}
