package com.jumpcutfindo.happyholidays.client.screen.guides;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.IReorderingProcessor;

public class ContentPage implements IPage {
    private final GuideScreen guideScreen;
    private final List<IReorderingProcessor> leftPageProcessors, rightPageProcessors;

    public ContentPage(GuideScreen guideScreen, List<IReorderingProcessor> leftPageProcessors,
                       List<IReorderingProcessor> rightPageProcessors) {
        this.guideScreen = guideScreen;

        this.leftPageProcessors = leftPageProcessors;
        this.rightPageProcessors = rightPageProcessors;
    }

    @Override
    public void draw(MatrixStack matrixStack) {
        // Draw text of left page
        FontRenderer font = guideScreen.getFontRenderer();

        int x = (guideScreen.width - guideScreen.bgWidth) / 2;
        int y = (guideScreen.height - guideScreen.bgHeight) / 2;

        for (int i = 0; i < leftPageProcessors.size(); i ++) {
            IReorderingProcessor processor = this.leftPageProcessors.get(i);
            font.draw(matrixStack, processor, x + GuideScreen.PAGE_LEFT_X_START, y + GuideScreen.PAGE_Y_START + (i * 9), 4210752);
        }

        // Draw text of right page
        for (int i = 0; i < rightPageProcessors.size(); i ++) {
            IReorderingProcessor processor = this.rightPageProcessors.get(i);
            font.draw(matrixStack, processor, x + GuideScreen.PAGE_RIGHT_X_START, y + GuideScreen.PAGE_Y_START + (i * 9), 4210752);
        }
    }
}
