package com.jumpcutfindo.happyholidays.client.screen.guides.lines;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.util.FormattedCharSequence;

public class EmptyLine implements IPageLine {
    private final GuideScreen guideScreen;
    private final EmptyLine.Type type;

    public EmptyLine(GuideScreen guideScreen, EmptyLine.Type type) {
        this.guideScreen = guideScreen;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void draw(PoseStack matrixStack, int xPos, int yPos) {
        guideScreen.getFontRenderer().draw(matrixStack, FormattedCharSequence.EMPTY, xPos, yPos, 4210752);
    }

    @Override
    public void setHovered(boolean isHovered) {
    }

    public enum Type {
        BUFFER,
        SPACING
    }
}
