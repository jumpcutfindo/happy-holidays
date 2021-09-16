package com.jumpcutfindo.happyholidays.client.screen.guides.lines;

import java.util.List;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.common.guide.sections.RecipeSection;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class RecipeLine implements IPageLine {
    public static final int ITEM_WIDTH = 18;
    public static final int ITEM_HEIGHT = 18;

    private GuideScreen guideScreen;
    private RecipeSection recipeSection;

    private int recipeIndex;
    private int internalTimer;

    private boolean isHovered;
    private int xPos, yPos;

    public RecipeLine(GuideScreen guideScreen, RecipeSection recipeSection) {
        this.guideScreen = guideScreen;
        this.recipeSection = recipeSection;

        this.recipeIndex = 0;
    }

    @Override
    public void draw(PoseStack matrixStack, int xPos, int yPos) {
        List<Recipe> recipes = recipeSection.getRecipes();

        int baseX = xPos + 16;
        int baseY = yPos - 1;

        this.xPos = baseX;
        this.yPos = baseY;

        // Draw backgrounds
        Minecraft.getInstance().getTextureManager().bindForSetup(guideScreen.guideBookGUI);
        GuideScreen.blit(matrixStack, baseX, baseY, 337, 80, 114, 54, 512, 512);

        // Draw recipe items, and iterate through them (just like how Minecraft does it)
        this.nextRecipe();

        // Draw items
        ItemRenderer itemRenderer = guideScreen.getItemRenderer();
        Recipe recipe = recipes.get(this.recipeIndex);

        // Default width and height
        int width = 3;
        int height = 3;

        if (recipe instanceof ShapedRecipe) {
            width = ((ShapedRecipe) recipe).getWidth();
            height = ((ShapedRecipe) recipe).getHeight();
        }

        for (int i = 0; i < height; i ++) {
            for (int j = 0; j < width; j ++) {
                int currIndex = i * width + j;

                if (currIndex >= recipe.getIngredients().size()) break;

                Ingredient ingredient = (Ingredient) recipe.getIngredients().get(currIndex);
                ItemStack[] itemStacks = ingredient.getItems();
                if (itemStacks.length > 0) {
                    itemRenderer.renderGuiItem(itemStacks[0], baseX + j * ITEM_WIDTH + 1,baseY + i * ITEM_HEIGHT + 1);
                }
            }
        }

        itemRenderer.renderGuiItem(recipe.getResultItem(), baseX + 97, baseY + 19);

        this.drawTooltip(matrixStack);
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

    public void drawTooltip(PoseStack matrixStack) {
        if (this.isHovered) {
            ItemStack itemStack = getItemAtPos(guideScreen.mouseX, guideScreen.mouseY);

            if (itemStack != null) guideScreen.drawTooltip(matrixStack, itemStack.getHoverName(), (int) guideScreen.mouseX, (int) guideScreen.mouseY);
        }
    }

    public ItemStack getItemAtPos(double x, double y) {
        int screenX = (int) (x - (guideScreen.width - guideScreen.bgWidth) / 2.0D);
        int screenY = (int) (y - (guideScreen.height - guideScreen.bgHeight) / 2.0D);

        if (screenY > GuideScreen.PAGE_Y_START && screenY <= GuideScreen.PAGE_Y_END) {
            if (screenX > GuideScreen.PAGE_LEFT_X_START && screenX <= GuideScreen.PAGE_LEFT_X_END) {
                // Left page
                int lineX = (int) x - this.xPos;
                int lineY = (int) y - this.yPos;

                if (lineX <= 54 && lineY <= 54) {
                    // Within range of crafting table grid
                    Recipe recipe = recipeSection.getRecipes().get(this.recipeIndex);
                    int i = lineX / 18;
                    int j = lineY / 18;

                    // Default width and height
                    int width = 3;
                    int height = 3;

                    if (recipe instanceof ShapedRecipe) {
                        width = ((ShapedRecipe) recipe).getWidth();
                        height = ((ShapedRecipe) recipe).getHeight();
                    }

                    int currIndex = j * width + i;
                    if (currIndex < 0 || currIndex > recipe.getIngredients().size() - 1) return null;
                    else {
                        Ingredient ingredient = (Ingredient) recipe.getIngredients().get(currIndex);
                        ItemStack[] itemStacks = ingredient.getItems();
                        if (itemStacks == null || itemStacks.length == 0) {
                        } else return itemStacks[0];
                    }
                } else if (lineX >= 97 && lineX <= 97 + 18 && lineY <= 54 && lineY >= 18) {
                    // Within range of result item grid
                    Recipe recipe = recipeSection.getRecipes().get(this.recipeIndex);

                    return recipe.getResultItem();
                }
            } else if (screenX > GuideScreen.PAGE_RIGHT_X_START && screenX <= GuideScreen.PAGE_RIGHT_X_END) {
                // Right page
                int lineX = (int) x - this.xPos;
                int lineY = (int) y - this.yPos;

                if (lineX <= 54 && lineY <= 54) {
                    // Within range of crafting table grid
                    Recipe recipe = recipeSection.getRecipes().get(this.recipeIndex);
                    int i = lineX / 18;
                    int j = lineY / 18;

                    // Default width and height
                    int width = 3;
                    int height = 3;

                    if (recipe instanceof ShapedRecipe) {
                        width = ((ShapedRecipe) recipe).getWidth();
                        height = ((ShapedRecipe) recipe).getHeight();
                    }

                    int currIndex = j * width + i;
                    if (currIndex < 0 || currIndex > recipe.getIngredients().size() - 1) return null;
                    else {
                        Ingredient ingredient = (Ingredient) recipe.getIngredients().get(currIndex);
                        ItemStack[] itemStacks = ingredient.getItems();
                        if (itemStacks == null || itemStacks.length == 0) {
                        } else return itemStacks[0];
                    }
                } else if (lineX >= 97 && lineX <= 97 + 18 && lineY <= 36 && lineY >= 18) {
                    // Within range of result item grid
                    Recipe recipe = recipeSection.getRecipes().get(this.recipeIndex);

                    return recipe.getResultItem();
                }
            }
        }

        return null;
    }

    @Override
    public void setHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }
}
