package com.jumpcutfindo.happyholidays.client.screen.guides.pages;

import com.jumpcutfindo.happyholidays.client.screen.guides.lines.IPageLine;
import com.mojang.blaze3d.matrix.MatrixStack;

public interface IPage {
    void draw(MatrixStack matrixStack);

    IPageLine getLineAtPos(double x, double y);
}
