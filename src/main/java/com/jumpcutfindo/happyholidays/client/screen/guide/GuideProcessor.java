package com.jumpcutfindo.happyholidays.client.screen.guide;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.client.screen.guide.lines.EmptyLine;
import com.jumpcutfindo.happyholidays.client.screen.guide.lines.IPageLine;
import com.jumpcutfindo.happyholidays.client.screen.guide.lines.ImageLine;
import com.jumpcutfindo.happyholidays.client.screen.guide.lines.ItemLine;
import com.jumpcutfindo.happyholidays.client.screen.guide.lines.RecipeLine;
import com.jumpcutfindo.happyholidays.client.screen.guide.lines.TextLine;
import com.jumpcutfindo.happyholidays.client.screen.guide.pages.ContentPage;
import com.jumpcutfindo.happyholidays.client.screen.guide.pages.IPage;
import com.jumpcutfindo.happyholidays.client.screen.guide.pages.TitlePage;
import com.jumpcutfindo.happyholidays.common.guide.Chapter;
import com.jumpcutfindo.happyholidays.common.guide.Guide;
import com.jumpcutfindo.happyholidays.common.guide.sections.ISection;
import com.jumpcutfindo.happyholidays.common.guide.sections.ImageSection;
import com.jumpcutfindo.happyholidays.common.guide.sections.ItemSection;
import com.jumpcutfindo.happyholidays.common.guide.sections.RecipeSection;
import com.jumpcutfindo.happyholidays.common.guide.sections.TextSection;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ClickEvent;

public class GuideProcessor {
    private float IMAGE_SCALE_VALUE = 0.5f;

    private final GuideScreen guideScreen;
    private final Guide guide;

    private IPage currentPage;
    private int currentPageIndex;
    Map<Integer, Integer> chapterPageMap;

    IPage titlePage;
    List<IPage> tocPages;
    List<IPage> contentPages;
    List<IPage> pages;

    public GuideProcessor(GuideScreen guideScreen, Guide guide) {
        this.guideScreen = guideScreen;
        this.guide = guide;
        this.pages = Lists.newArrayList();

        this.titlePage = new TitlePage(guideScreen, guide);
        this.pages.add(this.titlePage);

        this.chapterPageMap = Maps.newHashMap();

        this.generateContentPages();
        this.generateTableOfContentsPage();

        this.pages.addAll(tocPages);
        this.pages.addAll(contentPages);

        setCurrentPage(0, false);
    }

    public void draw(PoseStack matrixStack) {
        this.currentPage.draw(matrixStack);
    }

    public void generateContentPages() {
        Font font = guideScreen.getFontRenderer();
        contentPages = Lists.newArrayList();

        int linesPerPage = GuideScreen.PAGE_HEIGHT / 9;

        // Multiple content processors for multiple chapters (we want to leave spacing between one chapter and the next)
        List<List<IPageLine>> contentProcessors = Lists.newArrayList();

        int chapterCount = 1;
        for (Chapter chapter : guide.getChapters()) {
            List<IPageLine> chapterProcessors = Lists.newArrayList();
            String chapterTitle = String.format("%d. %s", chapterCount, chapter.getTitle());

            // Chapter title and empty line after
            chapterProcessors.addAll(font.split(FormattedText.of(chapterTitle,
                    Style.EMPTY.applyFormat(ChatFormatting.BOLD)), GuideScreen.PAGE_WIDTH).stream().map(processor -> new TextLine(guideScreen, processor)).collect(Collectors.toList()));
            chapterProcessors.add(new EmptyLine(guideScreen, EmptyLine.Type.SPACING));

            // Add attached image below
            if (chapter.getImage() != null) {
                ImageSection newImage = chapter.getImage().scale(IMAGE_SCALE_VALUE);

                chapterProcessors.add(new ImageLine(guideScreen, newImage));

                // Add empty lines as buffer to accommodate image
                for (int i = 0; i < newImage.getHeight() / 9; i++) {
                    chapterProcessors.add(new EmptyLine(guideScreen, EmptyLine.Type.BUFFER));
                }
            }

            int sectionCount = 1;
            for (ISection section : chapter.getSections()) {
                if (section == null) continue;

                if (section instanceof TextSection) {
                    TextSection textSection = (TextSection) section;

                    // Section numbering and title
                    if (textSection.getTitle() != null) {
                        String sectionTitle = String.format("%d.%d. %s", chapterCount, sectionCount, textSection.getTitle());
                        chapterProcessors.addAll(font.split(FormattedText.of(sectionTitle,
                                Style.EMPTY.applyFormat(ChatFormatting.ITALIC)), GuideScreen.PAGE_WIDTH).stream().map(processor -> new TextLine(guideScreen, processor)).collect(Collectors.toList()));

                        sectionCount++;
                    }

                    if (textSection.getContent() != null) {
                        chapterProcessors.addAll(font.split(FormattedText.of(textSection.getContent()),
                                GuideScreen.PAGE_WIDTH).stream().map(processor -> new TextLine(guideScreen, processor)).collect(Collectors.toList()));
                    }

                    // Spacing
                    chapterProcessors.add(new EmptyLine(guideScreen, EmptyLine.Type.SPACING));
                } else if (section instanceof ImageSection){
                    // Image sections will simply just display the image
                    ImageSection imageSection = ((ImageSection) section).scale(IMAGE_SCALE_VALUE);

                    // Check whether there is a need to add spacing to the next page
                    int imageLines = imageSection.getHeight() / 9;
                    int linesRemaining = (linesPerPage - chapterProcessors.size() % linesPerPage);

                    if (linesRemaining < imageLines) {
                        for (int i = 0; i < linesRemaining; i++) chapterProcessors.add(new EmptyLine(guideScreen,
                                EmptyLine.Type.SPACING));
                    }

                    // Add image
                    chapterProcessors.add(new ImageLine(guideScreen, imageSection));

                    // Add empty lines as buffer to accommodate image
                    for (int i = 0; i < imageSection.getHeight() / 9; i++) {
                        chapterProcessors.add(new EmptyLine(guideScreen, EmptyLine.Type.BUFFER));
                    }
                } else if (section instanceof ItemSection) {
                    ItemSection itemSection = ((ItemSection) section);

                    // Check whether there is a need to add spacing to the next page
                    int itemLines = 2;
                    int linesRemaining = (linesPerPage - chapterProcessors.size() % linesPerPage);
                    if (linesRemaining < itemLines) {
                        for (int i = 0; i < linesRemaining; i++) chapterProcessors.add(new EmptyLine(guideScreen,
                                EmptyLine.Type.SPACING));
                    }

                    // Add item line
                    chapterProcessors.add(new ItemLine(guideScreen, itemSection));

                    // Add empty lines as buffer to accommodate items
                    for (int i = 0; i < itemLines; i++) {
                        chapterProcessors.add(new EmptyLine(guideScreen, EmptyLine.Type.BUFFER));
                    }
                } else if (section instanceof RecipeSection) {
                    RecipeSection recipeSection = (RecipeSection) section;

                    // Check whether there is a need to add spacing to the next page
                    int itemLines = 6;
                    int linesRemaining = (linesPerPage - chapterProcessors.size() % linesPerPage);
                    if (linesRemaining < itemLines) {
                        for (int i = 0; i < linesRemaining; i++) chapterProcessors.add(new EmptyLine(guideScreen,
                                EmptyLine.Type.SPACING));
                    }

                    // Add recipe line
                    chapterProcessors.add(new RecipeLine(guideScreen, recipeSection));

                    // Add empty lines as buffer to accommodate items
                    for (int i = 0; i < itemLines; i++) {
                        chapterProcessors.add(new EmptyLine(guideScreen, EmptyLine.Type.BUFFER));
                    }
                }
            }

            chapterCount++;

            contentProcessors.add(chapterProcessors);
        }

        // Assembly of the lines created
        for (int chapter = 0; chapter < contentProcessors.size(); chapter++) {
            List<IPageLine> chapterProcessors = contentProcessors.get(chapter);
            chapterPageMap.put(chapter, contentPages.size() + 1);

            // Add empty lines as spacing between this and next chapter
            int processorPages = chapterProcessors.size() / (linesPerPage * 2) + 1;
            final int currProcessorsSize = chapterProcessors.size();
            for (int i = 0; i < (processorPages * (linesPerPage * 2) - currProcessorsSize); i++) {
                chapterProcessors.add(new EmptyLine(guideScreen, EmptyLine.Type.SPACING));
            }

            for (int i = 0; i < chapterProcessors.size() / (linesPerPage * 2); i++) {
                int start = i * (linesPerPage * 2);

                ContentPage contentPage = new ContentPage(
                        this.guideScreen,
                        chapterProcessors.subList(start, start + linesPerPage),
                        chapterProcessors.subList(start + linesPerPage, start + linesPerPage * 2)
                );

                contentPages.add(contentPage);
            }
        }
    }

    public void generateTableOfContentsPage() {
        Font font = guideScreen.getFontRenderer();
        List<IPageLine> tableOfContentsProcessors = Lists.newArrayList();

        for (int i = 0; i < guide.getChapters().size(); i++) {
            if (i == 0) {
                // Create title and spacing
                tableOfContentsProcessors.addAll(font.split(FormattedText.of(guide.getTableOfContentsTitle(),
                        Style.EMPTY.applyFormat(ChatFormatting.BOLD)), GuideScreen.PAGE_WIDTH).stream().map(processor -> new TextLine(guideScreen, processor)).collect(Collectors.toList()));

                tableOfContentsProcessors.add(new EmptyLine(guideScreen, EmptyLine.Type.SPACING));
            }

            Chapter chapter = guide.getChapters().get(i);
            int pageIndex = chapterPageMap.get(i);
            tableOfContentsProcessors.addAll(font.split(FormattedText.of(String.format("%d. %s", i + 1, chapter.getTitle()),
                    Style.EMPTY.applyFormat(ChatFormatting.ITALIC).withClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, Integer.toString(pageIndex)))),
                    GuideScreen.PAGE_WIDTH).stream().map(processor -> new TextLine(guideScreen, processor)).collect(Collectors.toList()));
        }

        int linesPerPage = GuideScreen.PAGE_HEIGHT / 9;

        // Add empty lines as spacing
        int processorPages = tableOfContentsProcessors.size() / (linesPerPage * 2) + 1;
        final int currProcessorsSize = tableOfContentsProcessors.size();
        for (int i = 0; i < (processorPages * (linesPerPage * 2) - currProcessorsSize); i++) {
            tableOfContentsProcessors.add(new EmptyLine(guideScreen, EmptyLine.Type.SPACING));
        }

        List<IPage> contentPages = Lists.newArrayList();

        // We add one as there needs to be a minimum of one page
        for (int i = 0; i < tableOfContentsProcessors.size() / (linesPerPage * 2); i++) {
            int start = i * (linesPerPage * 2);

            ContentPage contentPage = new ContentPage(
                    this.guideScreen,
                    tableOfContentsProcessors.subList(start, start + linesPerPage),
                    tableOfContentsProcessors.subList(start + linesPerPage, start + linesPerPage * 2)
            );

            contentPages.add(contentPage);
        }

        tocPages = contentPages;
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

    public boolean setCurrentPage(int index, boolean isContentPageIndex) {
        if (isContentPageIndex) {
            int actualIndex = index + 1 + this.tocPages.size();

            if (actualIndex >= 0 && actualIndex < this.getNumPages()) {
                this.currentPageIndex = actualIndex;
                this.currentPage = pages.get(currentPageIndex);
            }

            return true;
        } else {
            if (index >= 0 && index < this.getNumPages()) {
                this.currentPageIndex = index;
                this.currentPage = pages.get(index);

                return true;
            }
        }
        return false;
    }

    public boolean isTablePage() {
        return tocPages.contains(this.currentPage);
    }

    public boolean isTitlePage() {
        return titlePage.equals(this.currentPage);
    }

    public IPageLine getLineAt(double mouseX, double mouseY) {
        return currentPage.getLineAtPos(mouseX, mouseY);
    }

    public Style getClickedComponentStyleAt(double mouseX, double mouseY) {
        IPageLine line = currentPage.getLineAtPos(mouseX, mouseY);

        if (line instanceof TextLine) {
            return guideScreen.getFontRenderer().getSplitter().componentStyleAtWidth(((TextLine) line).getProcessor(), 16);
        }

        return null;
    }
}
