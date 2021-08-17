package com.jumpcutfindo.happyholidays.common.guide.sections;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class RecipeSection implements ISection {
    public static Map<ResourceLocation, IRecipe> CRAFTING_RECIPES;

    private final List<IRecipe> recipes;

    public RecipeSection(List<IRecipe> recipes) {
        this.recipes = recipes;
    }

    public static RecipeSection from(String[] recipeList) {
        ClientWorld level = Minecraft.getInstance().level;

        if (level != null) {
            List<IRecipe> recipes = Lists.newArrayList();
            for (String recipeName : recipeList) {
                recipes.add(level.getRecipeManager().byKey(new ResourceLocation(recipeName)).get());
            }

            return new RecipeSection(recipes);
        }

        return null;
    }

    public List<IRecipe> getRecipes() {
        return this.recipes;
    }
}
