package com.jumpcutfindo.happyholidays.common.guide.sections;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

public class RecipeSection implements ISection {
    public static Map<ResourceLocation, Recipe> CRAFTING_RECIPES;

    private final List<Recipe> recipes;

    public RecipeSection(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public static RecipeSection from(String[] recipeList) {
        ClientLevel level = Minecraft.getInstance().level;

        if (level != null) {
            List<Recipe> recipes = Lists.newArrayList();
            for (String recipeName : recipeList) {
                recipes.add(level.getRecipeManager().byKey(new ResourceLocation(recipeName)).get());
            }

            return new RecipeSection(recipes);
        }

        return null;
    }

    public List<Recipe> getRecipes() {
        return this.recipes;
    }
}
