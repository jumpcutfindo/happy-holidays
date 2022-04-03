package com.jumpcutfindo.happyholidays.client.screen.guide.pages;

import java.util.List;

import com.jumpcutfindo.happyholidays.client.screen.guide.GuideScreen;
import com.jumpcutfindo.happyholidays.client.screen.guide.lines.EmptyLine;
import com.jumpcutfindo.happyholidays.client.screen.guide.lines.IPageLine;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;

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
    public void draw(PoseStack matrixStack) {
        // Draw text of left page
        Font font = guideScreen.getFontRenderer();

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

        List<IPageLine> processors = null;

        if (screenY > GuideScreen.PAGE_Y_START && screenY <= GuideScreen.PAGE_Y_END) {
            if (screenX > GuideScreen.PAGE_LEFT_X_START && screenX <= GuideScreen.PAGE_LEFT_X_END) {
                // Left page
                processors = leftPageProcessors;
            } else if (screenX > GuideScreen.PAGE_RIGHT_X_START && screenX <= GuideScreen.PAGE_RIGHT_X_END) {
                processors = rightPageProcessors;
            }
        }

        if (processors == null) return null;

        int clickedIndex = (screenY - GuideScreen.PAGE_Y_START) / 9;
        if (clickedIndex < processors.size()) {
            IPageLine line = processors.get(clickedIndex);
            // We want to get the original line being hovered that had buffer added for it
            if (line instanceof EmptyLine && ((EmptyLine) line).getType() == EmptyLine.Type.BUFFER) {
                IPageLine currLine = line;
                int curr = clickedIndex;
                while (clickedIndex > 0 && currLine instanceof EmptyLine) {
                    currLine = processors.get(curr--);
                }

                return currLine;
            } else {
                return line;
            }
        }

        return null;
    }
}
