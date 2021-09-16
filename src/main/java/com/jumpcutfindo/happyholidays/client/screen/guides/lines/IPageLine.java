package com.jumpcutfindo.happyholidays.client.screen.guides.lines;

import com.mojang.blaze3d.vertex.PoseStack;

public interface IPageLine {
    void draw(PoseStack matrixStack, int xPos, int yPos);
    void setHovered(boolean isHovered);
}
