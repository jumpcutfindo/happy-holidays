package com.jumpcutfindo.happyholidays.client.screen.guides;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.IPageLine;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.ImageLine;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.TextLine;
import com.jumpcutfindo.happyholidays.client.screen.guides.pages.ContentPage;
import com.jumpcutfindo.happyholidays.client.screen.guides.pages.IPage;
import com.jumpcutfindo.happyholidays.client.screen.guides.pages.TitlePage;
import com.jumpcutfindo.happyholidays.common.guide.Chapter;
import com.jumpcutfindo.happyholidays.common.guide.Guide;
import com.jumpcutfindo.happyholidays.common.guide.sections.ISection;
import com.jumpcutfindo.happyholidays.common.guide.sections.ImageSection;
import com.jumpcutfindo.happyholidays.common.guide.sections.TextSection;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

public class GuideProcessor {
    private float IMAGE_SCALE_VALUE = 0.5f;

    private final GuideScreen guideScreen;
    private final Guide guide;

    private IPage currentPage;
    private int currentPageIndex;
    Map<Integer, Integer> chapterPageMap;

    IPage titlePage, tocPage;
    List<IPage> pages;

    public GuideProcessor(GuideScreen guideScreen, Guide guide) {
        this.guideScreen = guideScreen;
        this.guide = guide;
        this.pages = Lists.newArrayList();

        this.titlePage = new TitlePage(guideScreen, guide);
        this.pages.add(this.titlePage);

        this.chapterPageMap = Maps.newHashMap();

        this.generateContentPages();

        setCurrentPage(0);
    }

    public void draw(MatrixStack matrixStack) {
            this.currentPage.draw(matrixStack);
    }

    public void generateContentPages() {
        FontRenderer font = guideScreen.getFontRenderer();

        List<List<IPageLine>> contentProcessors = Lists.newArrayList();

        int chapterCount = 1;
        for (Chapter chapter : guide.getChapters()) {
            List<IPageLine> chapterProcessors = Lists.newArrayList();
            String chapterTitle = String.format("%d. %s", chapterCount, chapter.getTitle());

            // Chapter title and empty line after
            chapterProcessors.addAll(font.split(ITextProperties.of(chapterTitle,
                    Style.EMPTY.applyFormat(TextFormatting.BOLD)), GuideScreen.PAGE_WIDTH).stream().map(processor -> new TextLine(guideScreen, processor)).collect(Collectors.toList()));
            chapterProcessors.add(new TextLine(guideScreen, IReorderingProcessor.EMPTY));

            // Add attached image below
            if (chapter.getImage() != null) {
                ImageSection newImage = chapter.getImage().scale(IMAGE_SCALE_VALUE);

                chapterProcessors.add(new ImageLine(guideScreen, newImage));

                // Add buffer to accommodate image
                for (int i = 0; i < newImage.getHeight() / 9; i++) {
                    chapterProcessors.add(new TextLine(guideScreen, IReorderingProcessor.EMPTY));
                }
            }

            int sectionCount = 1;
            for (ISection section : chapter.getSections()) {
                if (section instanceof TextSection) {
                    TextSection textSection = (TextSection) section;

                    String sectionTitle = String.format("%d.%d. %s", chapterCount, sectionCount, textSection.getTitle());

                    // Section title
                    chapterProcessors.addAll(font.split(ITextProperties.of(sectionTitle,
                            Style.EMPTY.applyFormat(TextFormatting.ITALIC)), GuideScreen.PAGE_WIDTH).stream().map(processor -> new TextLine(guideScreen, processor)).collect(Collectors.toList()));

                    chapterProcessors.addAll(font.split(ITextProperties.of(textSection.getContent()),
                            GuideScreen.PAGE_WIDTH).stream().map(processor -> new TextLine(guideScreen, processor)).collect(Collectors.toList()));

                    chapterProcessors.add(new TextLine(guideScreen, IReorderingProcessor.EMPTY));
                    sectionCount++;
                } else {
                    ImageSection imageSection = ((ImageSection) section).scale(IMAGE_SCALE_VALUE);

                    chapterProcessors.add(new ImageLine(guideScreen, imageSection));

                    // Add buffer to accommodate image
                    for (int i = 0; i < imageSection.getHeight() / 9 + 1; i++) {
                        chapterProcessors.add(new TextLine(guideScreen, IReorderingProcessor.EMPTY));
                    }
                }
            }

            chapterCount++;

            contentProcessors.add(chapterProcessors);
        }

        int linesPerPage = GuideScreen.PAGE_HEIGHT / 9;

        for (int chapter = 0; chapter < contentProcessors.size(); chapter++) {
            List<IPageLine> chapterProcessors = contentProcessors.get(chapter);
            chapterPageMap.put(chapter, pages.size());

            // Add empty lines as buffer
            int processorPages = chapterProcessors.size() / (linesPerPage * 2) + 1;
            final int currProcessorsSize = chapterProcessors.size();
            for (int i = 0; i < (processorPages * (linesPerPage * 2) - currProcessorsSize); i++) {
                chapterProcessors.add(new TextLine(guideScreen, IReorderingProcessor.EMPTY));
            }

            for (int i = 0; i < chapterProcessors.size() / (linesPerPage * 2); i++) {
                int start = i * (linesPerPage * 2);

                ContentPage contentPage = new ContentPage(
                        this.guideScreen,
                        chapterProcessors.subList(start, start + linesPerPage),
                        chapterProcessors.subList(start + linesPerPage, start + linesPerPage * 2)
                );

                pages.add(contentPage);
            }
        }
    }

    public int getNumPages() {
        return pages.size();
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void pageForward() {
        if (this.currentPageIndex < this.getNumPages() - 1) {
            this.currentPageIndex++;
        }

        this.currentPage = pages.get(currentPageIndex);
    }

    public void pageBack() {
        if (this.currentPageIndex > 0) {
            this.currentPageIndex--;
        }

        this.currentPage = pages.get(currentPageIndex);
    }

    public void setCurrentPage(int index) {
        if (index >= 0 && this.currentPageIndex < this.getNumPages()) {
            this.currentPageIndex = index;
            this.currentPage = pages.get(index);
        }
    }
}
