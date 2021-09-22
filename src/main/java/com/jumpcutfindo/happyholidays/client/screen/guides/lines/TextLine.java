package com.jumpcutfindo.happyholidays.client.screen.guides.lines;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.util.FormattedCharSequence;

public class TextLine implements IPageLine {
    private final GuideScreen guideScreen;
    private final FormattedCharSequence processor;

    private boolean isHovered;

    public TextLine(GuideScreen guideScreen, FormattedCharSequence processor) {
        this.guideScreen = guideScreen;
        this.processor = processor;
    }

    public FormattedCharSequence getProcessor() {
        return processor;
    }

    @Override
    public void draw(PoseStack matrixStack, int xPos, int yPos) {
        guideScreen.getFontRenderer().draw(matrixStack, processor, xPos, yPos, 4210752);
    }

    @Override
    public void setHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }
}
