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

        ShapedRecipeBuilder.shaped(ChristmasBlocks.BABY_PRESENT.get(), 2)
                .define('S', Items.STRING).define('#', ChristmasItems.PRESENT_SCRAPS.get()).define('W', ItemTags.WOOL)
                .pattern("S#S").pattern("WWW")
                .unlockedBy("has_scraps", has(ChristmasItems.PRESENT_SCRAPS.get())).unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(consumer, recipeResourceOf(ChristmasBlocks.BABY_PRESENT.get()));

        bauble(consumer, ChristmasBlocks.RED_BAUBLE.get(), Items.RED_STAINED_GLASS);
        bauble(consumer, ChristmasBlocks.BLUE_BAUBLE.get(), Items.BLUE_STAINED_GLASS);
        bauble(consumer, ChristmasBlocks.YELLOW_BAUBLE.get(), Items.YELLOW_STAINED_GLASS);
        bauble(consumer, ChristmasBlocks.GREEN_BAUBLE.get(), Items.GREEN_STAINED_GLASS);
        bauble(consumer, ChristmasBlocks.GOLD_BAUBLE.get(), Items.GLASS, Items.GOLD_INGOT);
        bauble(consumer, ChristmasBlocks.SILVER_BAUBLE.get(), Items.GLASS, Items.IRON_INGOT);

        bigBauble(consumer, ChristmasBlocks.BIG_RED_BAUBLE.get(), Items.RED_STAINED_GLASS);
        bigBauble(consumer, ChristmasBlocks.BIG_BLUE_BAUBLE.get(), Items.BLUE_STAINED_GLASS);
        bigBauble(consumer, ChristmasBlocks.BIG_YELLOW_BAUBLE.get(), Items.YELLOW_STAINED_GLASS);
        bigBauble(consumer, ChristmasBlocks.BIG_GREEN_BAUBLE.get(), Items.GREEN_STAINED_GLASS);
        bigBauble(consumer, ChristmasBlocks.BIG_GOLD_BAUBLE.get(), Items.GLASS, Items.GOLD_INGOT);
        bigBauble(consumer, ChristmasBlocks.BIG_SILVER_BAUBLE.get(), Items.GLASS, Items.IRON_INGOT);

        tinsel(consumer, ChristmasBlocks.RED_TINSEL.get(), Items.RED_WOOL);
        tinsel(consumer, ChristmasBlocks.BLUE_TINSEL.get(), Items.BLUE_WOOL);
        tinsel(consumer, ChristmasBlocks.YELLOW_TINSEL.get(), Items.YELLOW_WOOL);
        tinsel(consumer, ChristmasBlocks.GREEN_TINSEL.get(), Items.GREEN_WOOL);
        tinsel(consumer, ChristmasBlocks.GOLD_TINSEL.get(), Items.WHITE_WOOL, Items.GOLD_INGOT);
        tinsel(consumer, ChristmasBlocks.SILVER_TINSEL.get(), Items.WHITE_WOOL, Items.IRON_INGOT);

        christmasLights(consumer, ChristmasBlocks.RED_CHRISTMAS_LIGHTS.get(), Items.RED_STAINED_GLASS);
        christmasLights(consumer, ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS.get(), Items.BLUE_STAINED_GLASS);
        christmasLights(consumer, ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS.get(), Items.YELLOW_STAINED_GLASS);
        christmasLights(consumer, ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS.get(), Items.GREEN_STAINED_GLASS);
        christmasLights(consumer, ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS.get(), Items.GLASS, Items.GOLD_INGOT);
        christmasLights(consumer, ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS.get(), Items.GLASS, Items.IRON_INGOT);
    }

    private void bauble(Consumer<FinishedRecipe> consumer, Block block, ItemLike... items) {
        if (items.length == 1) {
            ShapedRecipeBuilder.shaped(block, 2)
                    .group(groupOf("christmas_bauble"))
                    .define('#', Items.STRING).define('G', items[0])
                    .pattern("#").pattern("G")
                    .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                    .save(consumer, recipeResourceOf(block));
        } else if (items.length == 2) {
            ShapedRecipeBuilder.shaped(block, 2)
                    .group(groupOf("christmas_bauble"))
                    .define('#', Items.STRING).define('G', items[0]).define('A', items[1])
                    .pattern("# ").pattern("GA")
                    .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                    .save(consumer, recipeResourceOf(block));
        }
    }

    private void bigBauble(Consumer<FinishedRecipe> consumer, Block block, ItemLike... items) {
        if (items.length == 1) {
            ShapedRecipeBuilder.shaped(block, 2)
                    .define('#', Items.STRING).define('G', items[0])
                    .group(groupOf("christmas_big_bauble"))
                    .pattern(" # ").pattern("GGG")
                    .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                    .save(consumer, recipeResourceOf(block));
        } else if (items.length == 2) {
            ShapedRecipeBuilder.shaped(block, 2)
                    .define('#', Items.STRING).define('G', items[0]).define('A', items[1])
                    .pattern(" # ").pattern("GAG")
                    .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                    .save(consumer, recipeResourceOf(block));
        }
    }

    private void tinsel(Consumer<FinishedRecipe> consumer, Block block, ItemLike... items) {
        if (items.length == 1) {
            ShapedRecipeBuilder.shaped(block, 4)
                    .group(groupOf("christmas_tinsel"))
                    .define('S', Items.STRING).define('W', items[0])
                    .pattern("SSS").pattern("WWW")
                    .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_wool", has(ItemTags.WOOL))
                    .save(consumer, recipeResourceOf(block));
        } else if (items.length == 2) {
            ShapedRecipeBuilder.shaped(block, 4)
                    .group(groupOf("christmas_tinsel"))
                    .define('S', Items.STRING).define('W', items[0]).define('A', items[1])
                    .pattern("SSS").pattern("WAW")
                    .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_wool", has(ItemTags.WOOL))
                    .save(consumer, recipeResourceOf(block));
        }
    }

    private void christmasLights(Consumer<FinishedRecipe> consumer, Block block, ItemLike... items) {
        if (items.length == 1) {
            ShapedRecipeBuilder.shaped(block, 2)
                    .group(groupOf("christmas_lights"))
                    .define('S', Items.STRING).define('R', items[0]).define('G', Items.GLOWSTONE_DUST)
                    .pattern(" S ").pattern("RGR")
                    .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                    .save(consumer, recipeResourceOf(block));
        } else if (items.length == 2) {
            ShapedRecipeBuilder.shaped(block, 2)
                    .group(groupOf("christmas_lights"))
                    .define('S', Items.STRING).define('R', items[0]).define('G', Items.GLOWSTONE_DUST).define('A', items[1])
                    .pattern(" S ").pattern("RGR").pattern(" A ")
                    .unlockedBy("has_string", has(Items.STRING)).unlockedBy("has_glass", has(Items.GLASS))
                    .save(consumer, recipeResourceOf(block));
        }
    }

    private ResourceLocation recipeResourceOf(ItemLike item) {
        String id = item.asItem().getRegistryName().getPath();
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, id + "_recipe");
    }

    private String groupOf(String groupName) {
        return "happyholidays:" + groupName;
    }
}
