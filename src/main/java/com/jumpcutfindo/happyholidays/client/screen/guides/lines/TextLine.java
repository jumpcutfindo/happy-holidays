package com.jumpcutfindo.happyholidays.client.screen.guides.lines;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.util.IReorderingProcessor;

public class TextLine implements IPageLine {
    private final GuideScreen guideScreen;
    private final IReorderingProcessor processor;

    private boolean isHovered;

    public TextLine(GuideScreen guideScreen, IReorderingProcessor processor) {
        this.guideScreen = guideScreen;
        this.processor = processor;
    }

    public IReorderingProcessor getProcessor() {
        return processor;
    }

    @Override
    public void draw(MatrixStack matrixStack, int xPos, int yPos) {
        guideScreen.getFontRenderer().draw(matrixStack, processor, xPos, yPos, 4210752);
    }

    @Override
    public void setHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }
}
