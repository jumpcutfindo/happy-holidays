package com.jumpcutfindo.happyholidays.client.screen.guides.lines;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.common.guide.sections.ImageSection;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

public class ImageLine implements IPageLine {
    public static final int IMAGES_WIDTH = 512;
    public static final int IMAGES_HEIGHT = 512;

    private final GuideScreen guideScreen;
    private final ImageSection image;

    public ImageLine(GuideScreen guideScreen, ImageSection image) {
        this.guideScreen = guideScreen;
        this.image = image;
    }

    public ImageSection getImage() {
        return image;
    }

    @Override
    public void draw(MatrixStack matrixStack, int xPos, int yPos) {
        Minecraft.getInstance().getTextureManager().bind(image.getSource());

        float scale = image.getScale();

        AbstractGui.blit(matrixStack, xPos, yPos, image.getX(), image.getY(), image.getWidth(),
                image.getHeight(),
                (int) (IMAGES_WIDTH * scale), (int) (IMAGES_HEIGHT * scale));
    }
}
