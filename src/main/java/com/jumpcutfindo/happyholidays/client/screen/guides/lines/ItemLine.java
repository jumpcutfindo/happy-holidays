package com.jumpcutfindo.happyholidays.client.screen.guides.lines;

import java.util.List;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.common.guide.sections.ItemSection;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;

public class ItemLine implements IPageLine {
    public static final int ITEM_WIDTH = 18;
    public static final int ITEM_HEIGHT = 18;

    private final GuideScreen guideScreen;
    private final ItemSection itemSection;

    private boolean isHovered;

    public ItemLine(GuideScreen guideScreen, ItemSection itemSection) {
        this.guideScreen = guideScreen;
        this.itemSection = itemSection;
    }

    @Override
    public void draw(PoseStack matrixStack, int xPos, int yPos) {
        List<ItemStack> items = itemSection.getItems();

        int firstX = xPos + (GuideScreen.PAGE_WIDTH - ITEM_WIDTH * items.size()) / 2;

        // Draw backgrounds
        RenderSystem.setShaderTexture(0, guideScreen.guideBookGUI);
        for (int i = 0; i < items.size(); i ++) {
            GuideScreen.blit(matrixStack, firstX + ITEM_WIDTH * i - 1, yPos - 1, 337, 62, 18, 18, 512, 512);
        }

        ItemRenderer itemRenderer = guideScreen.getItemRenderer();
        for (int i = 0; i < items.size(); i ++) {
            itemRenderer.renderGuiItem(items.get(i), firstX + ITEM_WIDTH * i, yPos);
        }

        this.drawTooltip(matrixStack);
    }

    public void drawTooltip(PoseStack matrixStack) {
        if (this.isHovered) {
            ItemStack itemStack = getItemAtPos(guideScreen.mouseX, guideScreen.mouseY);

            if (itemStack != null) guideScreen.renderTooltip(matrixStack, itemStack.getHoverName(), (int) guideScreen.mouseX, (int) guideScreen.mouseY);
        }
    }

    public void setHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }

    public ItemStack getItemAtPos(double x, double y) {
        int screenX = (int) (x - (guideScreen.width - guideScreen.bgWidth) / 2.0D);
        int screenY = (int) (y - (guideScreen.height - guideScreen.bgHeight) / 2.0D);

        if (screenY > GuideScreen.PAGE_Y_START && screenY <= GuideScreen.PAGE_Y_END) {
            if (screenX > GuideScreen.PAGE_LEFT_X_START && screenX <= GuideScreen.PAGE_LEFT_X_END) {
                // Left page
                int lineX = screenX - GuideScreen.PAGE_LEFT_X_START;
                List<ItemStack> items = itemSection.getItems();
                int firstX = (GuideScreen.PAGE_WIDTH - ITEM_WIDTH * items.size()) / 2;

                int index = (lineX - firstX) > 0 ? (lineX - firstX) / ITEM_WIDTH : -1;
                return index >= 0 && index <= items.size() - 1 ? items.get(index) : null;
            } else if (screenX > GuideScreen.PAGE_RIGHT_X_START && screenX <= GuideScreen.PAGE_RIGHT_X_END) {
                // Right page
                int lineX = screenX - GuideScreen.PAGE_RIGHT_X_START;

                List<ItemStack> items = itemSection.getItems();
                int firstX = (GuideScreen.PAGE_WIDTH - ITEM_WIDTH * items.size()) / 2;

                int index = (lineX - firstX) > 0 ? (lineX - firstX) / ITEM_WIDTH : -1;
                return index >= 0 && index <= items.size() - 1 ? items.get(index) : null;
            }
        }

        return null;
    }
}
