package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.crafting.PatrolOrdersCloningRecipe;

import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(
            ForgeRegistries.RECIPE_SERIALIZERS,
            HappyHolidaysMod.MOD_ID
    );

    // ================= RECIPES =================
    public static final RegistryObject<SimpleRecipeSerializer<CustomRecipe>> PATROL_ORDERS_CLONING =
            RECIPES.register("crafting_special_patrolorderscloning", () -> new SimpleRecipeSerializer<>(PatrolOrdersCloningRecipe::new));
}
