package com.jumpcutfindo.happyholidays.client.screen.guide.pages;

import com.jumpcutfindo.happyholidays.client.screen.guide.lines.IPageLine;
import com.mojang.blaze3d.vertex.PoseStack;

public interface IPage {
    void draw(PoseStack matrixStack);

    IPageLine getLineAtPos(double x, double y);
}
