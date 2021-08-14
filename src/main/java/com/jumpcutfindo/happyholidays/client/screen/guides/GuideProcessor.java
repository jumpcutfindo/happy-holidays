package com.jumpcutfindo.happyholidays.client.screen.guides;

import java.awt.Font;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.common.guide.Chapter;
import com.jumpcutfindo.happyholidays.common.guide.Guide;
import com.jumpcutfindo.happyholidays.common.guide.Section;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

public class GuideProcessor {
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

        List<List<IReorderingProcessor>> contentProcessors = Lists.newArrayList();

        int chapterCount = 1;
        for (Chapter chapter : guide.getChapters()) {
            List<IReorderingProcessor> chapterProcessors = Lists.newArrayList();
            String chapterTitle = String.format("%d. %s", chapterCount, chapter.getTitle());

            // Chapter title and empty line after
            chapterProcessors.addAll(
                    font.split(
                            ITextProperties.of(
                                    chapterTitle,
                                    Style.EMPTY.applyFormat(TextFormatting.BOLD)
                            ),
                            GuideScreen.PAGE_WIDTH)
            );
            chapterProcessors.add(IReorderingProcessor.EMPTY);

            int sectionCount = 1;
            for (Section section : chapter.getSections()) {
                String sectionTitle = String.format("%d.%d. %s", chapterCount, sectionCount, section.getTitle());

                // Section title
                chapterProcessors.addAll(font.split(ITextProperties.of(sectionTitle, Style.EMPTY.applyFormat(TextFormatting.ITALIC)), GuideScreen.PAGE_WIDTH));

                if (section.getType() == Section.Type.TEXT) {
                    chapterProcessors.addAll(font.split(ITextProperties.of(section.getContent()), GuideScreen.PAGE_WIDTH));
                }
                chapterProcessors.add(IReorderingProcessor.EMPTY);

                sectionCount++;
            }

            chapterCount++;

            contentProcessors.add(chapterProcessors);
        }

        int linesPerPage = GuideScreen.PAGE_HEIGHT / 9;

        for (int chapter = 0; chapter < contentProcessors.size(); chapter++) {
            List<IReorderingProcessor> chapterProcessors = contentProcessors.get(chapter);
            chapterPageMap.put(chapter, pages.size());

            // Add empty lines as buffer
            int processorPages = chapterProcessors.size() / (linesPerPage * 2) + 1;
            final int currProcessorsSize = chapterProcessors.size();
            for (int i = 0; i < (processorPages * (linesPerPage * 2) - currProcessorsSize); i++) {
                chapterProcessors.add(IReorderingProcessor.EMPTY);
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
