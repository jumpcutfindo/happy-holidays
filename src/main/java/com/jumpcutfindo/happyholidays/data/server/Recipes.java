package com.jumpcutfindo.happyholidays.data.server;

import java.util.function.Consumer;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

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

        ShapedRecipeBuilder.shaped(ChristmasBlocks.BABY_PRESENT.get(), 2)
                .define('S', Items.STRING).define('#', ChristmasItems.PRESENT_SCRAPS.get()).define('W', ItemTags.WOOL)
                .pattern("S#S").pattern("WWW")
                .unlockedBy("has_scraps", has(ChristmasItems.PRESENT_SCRAPS.get())).unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BABY_PRESENT.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.RED_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.RED_STAINED_GLASS)
                .pattern("#").pattern("G")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.RED_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.BLUE_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.BLUE_STAINED_GLASS)
                .pattern("#").pattern("G")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BLUE_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.YELLOW_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.YELLOW_STAINED_GLASS)
                .pattern("#").pattern("G")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.YELLOW_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.GREEN_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.GREEN_STAINED_GLASS)
                .pattern("#").pattern("G")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.GREEN_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.GOLD_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.GLASS).define('A', Items.GOLD_INGOT)
                .pattern("# ").pattern("GA")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.GOLD_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.SILVER_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.GLASS).define('A', Items.IRON_INGOT)
                .pattern("# ").pattern("GA")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.SILVER_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.BIG_RED_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.RED_STAINED_GLASS)
                .pattern(" # ").pattern("GGG")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BIG_RED_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.BIG_BLUE_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.BLUE_STAINED_GLASS)
                .pattern(" # ").pattern("GGG")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BIG_BLUE_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.BIG_YELLOW_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.YELLOW_STAINED_GLASS)
                .pattern(" # ").pattern("GGG")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BIG_YELLOW_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.BIG_GREEN_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.GREEN_STAINED_GLASS)
                .pattern(" # ").pattern("GGG")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BIG_GREEN_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.BIG_GOLD_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.GLASS).define('A', Items.GOLD_INGOT)
                .pattern(" # ").pattern("GAG")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BIG_GOLD_BAUBLE.get()));

        ShapedRecipeBuilder.shaped(ChristmasBlocks.BIG_SILVER_BAUBLE.get(), 2)
                .define('#', Items.STRING).define('G', Items.GLASS).define('A', Items.IRON_INGOT)
                .pattern(" # ").pattern("GAG")
                .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BIG_SILVER_BAUBLE.get()));
    }

    private void bigBaubleOf(ItemLike item, Consumer<FinishedRecipe> consumer) {
    }

    private ResourceLocation recipeResourceOf(ItemLike item) {
        String id = item.asItem().getRegistryName().getPath();
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, id + "_recipe");
    }
}
