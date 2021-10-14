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
import net.minecraft.world.level.ItemLike;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get(), 4)
                .define('#', ChristmasItems.RAW_GINGERBREAD.get()).pattern("##").pattern("##")
                .unlockedBy("has_gingerbread_dough", has(ChristmasItems.RAW_GINGERBREAD.get()))
                .save(consumer, recipeResourceOf(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get()));
    }

    private ResourceLocation recipeResourceOf(ItemLike item) {
        String id = item.asItem().getRegistryName().getPath();
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, id + "_recipe");
    }
}
