package com.jumpcutfindo.happyholidays.data.server;

import java.util.function.Consumer;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get(), 1)
                .define('#', ChristmasItems.RAW_GINGERBREAD.get())
                .pattern("##").pattern("##")
                .unlockedBy("has_gingerbread_dough", has(ChristmasItems.RAW_GINGERBREAD.get()))
                .save(consumer, recipeResourceOf(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get()));

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
        ShapedRecipeBuilder.shaped(block, 2)
                .group(groupOf("christmas_lights"))
                .define('S', Items.STRING).define('R', Items.GLASS).define('G', Items.GLOWSTONE_DUST).define('A', christmasDye)
                .pattern(" S ").pattern("RGR").pattern(" A ")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
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
                .define('#', ChristmasItems.CANDY_CANE.get())
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(ChristmasItems.CANDY_CANE.get()), has(ChristmasItems.CANDY_CANE.get()))
                .save(consumer, recipeResourceOf(ChristmasBlocks.CANDY_CANE_BLOCK.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.CANDY_CANE_BLOCK.get(), 4)
                .define('#', Items.SUGAR).define('R', Items.RED_DYE)
                .pattern("R#").pattern("#R")
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR)).unlockedBy(getHasName(Items.RED_DYE), has(Items.RED_DYE))
                .save(consumer, recipeResourceOf(ChristmasBlocks.CANDY_CANE_BLOCK.get()) + "_from_raw_items");

        ShapedRecipeBuilder.shaped(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get(), 1)
                .define('#', ChristmasItems.FESTIVE_CANDY_CANE.get())
                .pattern("##").pattern("##")
                .unlockedBy(getHasName(ChristmasItems.FESTIVE_CANDY_CANE.get()), has(ChristmasItems.FESTIVE_CANDY_CANE.get()))
                .save(consumer, recipeResourceOf(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get(), 4)
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
