package com.jumpcutfindo.happyholidays.client.screen.guides.pages;

import java.util.List;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.IPageLine;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.ImageLine;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.TextLine;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.IReorderingProcessor;

public class ContentPage implements IPage {
    private final GuideScreen guideScreen;
    private final List<IPageLine> leftPageProcessors, rightPageProcessors;

    public ContentPage(GuideScreen guideScreen, List<IPageLine> leftPageProcessors,
                       List<IPageLine> rightPageProcessors) {
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
            IPageLine pageLine = this.leftPageProcessors.get(i);

            if (pageLine instanceof TextLine) {
                pageLine.draw(matrixStack, x + GuideScreen.PAGE_LEFT_X_START, y + GuideScreen.PAGE_Y_START + (i * 9));
            } else {
                ImageLine imageLine = (ImageLine) pageLine;
                pageLine.draw(
                        matrixStack,
                        x + GuideScreen.PAGE_LEFT_X_START + GuideScreen.PAGE_WIDTH / 2 - imageLine.getImage().getWidth() / 2,
                        y + GuideScreen.PAGE_Y_START + (i * 9)
                );
            }

        }

        // Draw text of right page
        for (int i = 0; i < rightPageProcessors.size(); i ++) {
            IPageLine pageLine = this.rightPageProcessors.get(i);

            if (pageLine instanceof TextLine) {
                pageLine.draw(matrixStack, x + GuideScreen.PAGE_RIGHT_X_START, y + GuideScreen.PAGE_Y_START + (i * 9));
            } else {
                ImageLine imageLine = (ImageLine) pageLine;
                pageLine.draw(
                        matrixStack,
                        x + GuideScreen.PAGE_RIGHT_X_START + GuideScreen.PAGE_WIDTH / 2 - imageLine.getImage().getWidth() / 2,
                        y + GuideScreen.PAGE_Y_START + (i * 9)
                );
            }
        }
    }
}
