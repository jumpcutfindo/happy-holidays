package com.jumpcutfindo.happyholidays.client.screen.guides.pages;

import com.jumpcutfindo.happyholidays.client.screen.guides.lines.IPageLine;
import com.mojang.blaze3d.vertex.PoseStack;

public interface IPage {
    void draw(PoseStack matrixStack);

    IPageLine getLineAtPos(double x, double y);
}
