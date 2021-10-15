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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

        ShapedRecipeBuilder.shaped(ChristmasBlocks.BABY_PRESENT.get(), 2)
                .define('S', Items.STRING).define('#', ChristmasItems.PRESENT_SCRAPS.get()).define('W', ItemTags.WOOL)
                .pattern("S#S").pattern("WWW")
                .unlockedBy("has_scraps", has(ChristmasItems.PRESENT_SCRAPS.get())).unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BABY_PRESENT.get()));
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

    private ResourceLocation recipeResourceOf(ItemLike item) {
        String id = item.asItem().getRegistryName().getPath();
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, id + "_recipe");
    }

    private String groupOf(String groupName) {
        return "happyholidays:" + groupName;
    }
}
