package com.jumpcutfindo.happyholidays.client.screen.guides.lines;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.common.guide.sections.ImageSection;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;

public class ImageLine implements IPageLine {
    public static final int IMAGES_WIDTH = 512;
    public static final int IMAGES_HEIGHT = 512;

    private final GuideScreen guideScreen;
    private final ImageSection image;

    private boolean isHovered;

    public ImageLine(GuideScreen guideScreen, ImageSection image) {
        this.guideScreen = guideScreen;
        this.image = image;
    }

    @Override
    public void draw(PoseStack matrixStack, int xPos, int yPos) {
        Minecraft.getInstance().getTextureManager().bindForSetup(image.getSource());

        // Centre image
        int centeredX = xPos + GuideScreen.PAGE_WIDTH / 2 - image.getWidth() / 2;

        float scale = image.getScale();

        GuideScreen.blit(matrixStack, centeredX, yPos, image.getX(), image.getY(), image.getWidth(), image.getHeight(), (int) (IMAGES_WIDTH * scale), (int) (IMAGES_HEIGHT * scale));
    }

    @Override
    public void setHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }
}
