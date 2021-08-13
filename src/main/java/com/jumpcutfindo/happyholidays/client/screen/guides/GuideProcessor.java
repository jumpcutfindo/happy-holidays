package com.jumpcutfindo.happyholidays.client.screen.guides;

import com.jumpcutfindo.happyholidays.common.guide.Guide;
import com.mojang.blaze3d.matrix.MatrixStack;

public class GuideProcessor {
    private final GuideScreen guideScreen;
    private final Guide guide;

    private int currentPage;

    IPage titlePage, tocPage;

    public GuideProcessor(GuideScreen guideScreen, Guide guide) {
        this.guideScreen = guideScreen;
        this.guide = guide;

        this.currentPage = 0;

        this.titlePage = new TitlePage(guideScreen, guide);
    }

    private void createTableOfContents() {

    }

    private void createChapters() {

    }

    public void draw(MatrixStack matrixStack) {
        this.titlePage.draw(matrixStack);
    }
}
