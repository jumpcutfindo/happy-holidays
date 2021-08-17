package com.jumpcutfindo.happyholidays.client.screen.guides.lines;

import java.util.List;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.common.guide.sections.RecipeSection;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.CraftingScreen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;

public class RecipeLine implements IPageLine {
    public static final int ITEM_WIDTH = 18;
    public static final int ITEM_HEIGHT = 18;

    private GuideScreen guideScreen;
    private RecipeSection recipeSection;

    private int recipeIndex;
    private int internalTimer;

    private boolean isHovered;

    public RecipeLine(GuideScreen guideScreen, RecipeSection recipeSection) {
        this.guideScreen = guideScreen;
        this.recipeSection = recipeSection;

        this.recipeIndex = 0;
    }

    @Override
    public void draw(MatrixStack matrixStack, int xPos, int yPos) {
        List<IRecipe> recipes = recipeSection.getRecipes();

        int baseX = xPos + 16;
        int baseY = yPos - 1;

        // Draw backgrounds
        Minecraft.getInstance().getTextureManager().bind(guideScreen.guideBookGUI);
        GuideScreen.blit(matrixStack, baseX, baseY, 337, 80, 114, 54, 512, 512);

        // Draw recipe items, and iterate through them (just like how Minecraft does it)
        this.nextRecipe();

        // Draw items
        ItemRenderer itemRenderer = guideScreen.getItemRenderer();
        IRecipe recipe = recipes.get(this.recipeIndex);

        boolean stop = false;
        for (int j = 0; j < 3; j ++) {
            if (stop) break;
            for (int h = 0; h < 3; h ++) {
                if ((j * 3 + h) > recipe.getIngredients().size() - 1) {
                    stop = true;
                    break;
                }

                Ingredient ingredient = (Ingredient) recipe.getIngredients().get(j * 3 + h);
                ItemStack[] itemStacks = ingredient.getItems();
                if (itemStacks == null || itemStacks.length == 0) {
                } else itemRenderer.renderGuiItem(itemStacks[0], baseX + h * ITEM_WIDTH + 1,
                        baseY + j * ITEM_HEIGHT + 1);
            }
        }

        itemRenderer.renderGuiItem(recipe.getResultItem(), baseX + 97, baseY + 19);
    }

    private void nextRecipe() {
        if (++this.internalTimer == 200) {
            this.internalTimer = 0;

            if (this.recipeIndex >= this.recipeSection.getRecipes().size() - 1) {
                this.recipeIndex = 0;
            } else {
                this.recipeIndex ++;
            }
        }

    }

    @Override
    public void setHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }
}
