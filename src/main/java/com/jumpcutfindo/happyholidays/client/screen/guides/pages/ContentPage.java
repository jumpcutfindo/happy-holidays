package com.jumpcutfindo.happyholidays.client.screen.guides.pages;

import java.util.List;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.IPageLine;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.ImageLine;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.ItemLine;
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
            pageLine.draw(matrixStack, x + GuideScreen.PAGE_LEFT_X_START, y + GuideScreen.PAGE_Y_START + (i * 9));
        }

        // Draw text of right page
        for (int i = 0; i < rightPageProcessors.size(); i ++) {
            IPageLine pageLine = this.rightPageProcessors.get(i);
            pageLine.draw(matrixStack, x + GuideScreen.PAGE_RIGHT_X_START, y + GuideScreen.PAGE_Y_START + (i * 9));
        }
    }

    @Override
    public IPageLine getLineAtPos(double x, double y) {
        int screenX = (int) (x - (guideScreen.width - guideScreen.bgWidth) / 2.0D);
        int screenY = (int) (y - (guideScreen.height - guideScreen.bgHeight) / 2.0D);

        if (screenY > GuideScreen.PAGE_Y_START && screenY <= GuideScreen.PAGE_Y_END) {
            if (screenX > GuideScreen.PAGE_LEFT_X_START && screenX <= GuideScreen.PAGE_LEFT_X_END) {
                int clickedIndex = (screenY - GuideScreen.PAGE_Y_START) / 9;
                if (clickedIndex < leftPageProcessors.size()) {
                    return leftPageProcessors.get(clickedIndex);
                } else {
                    HappyHolidaysMod.LOGGER.debug("Unable to get clicked item on left page");
                }
            } else if (screenX > GuideScreen.PAGE_RIGHT_X_START && screenX <= GuideScreen.PAGE_RIGHT_X_END) {
                int clickedIndex = (screenY - GuideScreen.PAGE_Y_START) / 9;
                if (clickedIndex < rightPageProcessors.size()) {
                    return rightPageProcessors.get(clickedIndex);
                } else {
                    HappyHolidaysMod.LOGGER.debug("Unable to get clicked item on right page");
                }
            }
        }

        return null;
    }
}
