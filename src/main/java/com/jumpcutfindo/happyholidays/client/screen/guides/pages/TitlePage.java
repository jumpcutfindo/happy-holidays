package com.jumpcutfindo.happyholidays.client.screen.guides.pages;

import java.util.Collections;
import java.util.List;

import com.jumpcutfindo.happyholidays.client.screen.guides.GuideScreen;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.IPageLine;
import com.jumpcutfindo.happyholidays.common.guide.Guide;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextProperties;

public class TitlePage implements IPage {
    public static final int LOGO_TEXTURE_X = 0;
    public static final int LOGO_TEXTURE_Y = 201;

    public static final int LOGO_WIDTH = 138;
    public static final int LOGO_HEIGHT = 63;

    private final GuideScreen guideScreen;
    private final Guide guide;

    private List<IReorderingProcessor> descriptionProcessors = Collections.emptyList();

    public TitlePage(GuideScreen guideScreen, Guide guide) {
        this.guideScreen = guideScreen;
        this.guide = guide;
    }

    @Override
    public void draw(MatrixStack matrixStack) {
        int x = (guideScreen.width - guideScreen.bgWidth) / 2;
        int y = (guideScreen.height - guideScreen.bgHeight) / 2;

        // Draw logo
        GuideScreen.blit(matrixStack, x + GuideScreen.PAGE_LEFT_X_START + 4, y + 40, LOGO_TEXTURE_X, LOGO_TEXTURE_Y,
                LOGO_WIDTH, LOGO_HEIGHT,
                guideScreen.textureWidth, guideScreen.textureHeight);

        // Draw subtext
        FontRenderer font = guideScreen.getFontRenderer();
        String guideTitle = guide.getTitle();
        font.draw(matrixStack, guideTitle, x + (int) (guideScreen.bgWidth / 4) - (int) (font.width(guideTitle) / 2),
                y + 110,
                4210752);

        // Draw description text
        int k = Math.min(GuideScreen.PAGE_HEIGHT / 9, this.descriptionProcessors.size());
        this.descriptionProcessors =
                this.guideScreen.getFontRenderer().split(ITextProperties.of(this.guide.getDescription()), GuideScreen.PAGE_WIDTH);

        for (int i = 0; i < k; i ++) {
            IReorderingProcessor processor = this.descriptionProcessors.get(i);
            font.draw(matrixStack, processor, x + GuideScreen.PAGE_RIGHT_X_START,
                    y + GuideScreen.PAGE_Y_START + (i * 9), 4210752);
        }
    }

    @Override
    public IPageLine getLineAtPos(double x, double y) {
        return null;
    }
}
