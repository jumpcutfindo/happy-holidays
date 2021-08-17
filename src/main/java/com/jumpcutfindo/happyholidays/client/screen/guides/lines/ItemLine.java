package com.jumpcutfindo.happyholidays.client.screen.guides.lines;

import java.util.List;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.common.guide.sections.ItemSection;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;

public class ItemLine implements IPageLine{
    public static final int ITEM_WIDTH = 16;
    public static final int ITEM_HEIGHT = 16;

    private final GuideScreen guideScreen;
    private final ItemSection itemSection;

    public ItemLine(GuideScreen guideScreen, ItemSection itemSection) {
        this.guideScreen = guideScreen;
        this.itemSection = itemSection;
    }

    @Override
    public void draw(MatrixStack matrixStack, int xPos, int yPos) {
        List<ItemStack> items = itemSection.getItems();

        int firstX = xPos + (GuideScreen.PAGE_WIDTH - ITEM_WIDTH * items.size()) / 2;

        ItemRenderer itemRenderer = guideScreen.getItemRenderer();

        for (int i = 0; i < items.size(); i ++) {
            itemRenderer.renderGuiItem(items.get(i), firstX + ITEM_WIDTH * i, yPos);
        }
    }
}
