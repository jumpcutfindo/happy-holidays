package com.jumpcutfindo.happyholidays.client.screen.guides;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.guide.Guide;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IBidiTooltip;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ChangePageButton;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class GuideScreen extends Screen {
    private static final ResourceLocation DEFAULT_BOOK_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/guide/guide_book.png");
    private static final ResourceLocation CHRISTMAS_GUIDE_BOOK_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/guide/christmas_guide_book.png");

    public static final int PAGE_LEFT_X_START = 12;
    public static final int PAGE_LEFT_X_END = 157;

    public static final int PAGE_RIGHT_X_START = 180;
    public static final int PAGE_RIGHT_X_END = 325;

    public static final int PAGE_Y_START = 12;
    public static final int PAGE_Y_END = 188;

    public static final int PAGE_WIDTH = 145;
    public static final int PAGE_HEIGHT = 176;

    private final Guide guide;
    private GuideProcessor guideProcessor;

    public final int bgWidth, bgHeight, textureWidth, textureHeight;

    public final ResourceLocation guideBookGUI;

    private ChangePageButton forwardButton;
    private ChangePageButton backButton;
    private ChangePageButton tableOfContentsButton;

    public GuideScreen(Guide guide) {
        super(new StringTextComponent("Guide Book"));
        this.guide = guide;

        this.bgWidth = 337;
        this.bgHeight = 200;

        this.textureWidth = 512;
        this.textureHeight = 512;

        if (this.guide.getId().equals("christmas")) {
            this.guideBookGUI = CHRISTMAS_GUIDE_BOOK_GUI;
        } else {
            this.guideBookGUI = DEFAULT_BOOK_GUI;
        }
    }

    @Override
    protected void init() {
        this.guideProcessor = new GuideProcessor(this, guide);

        this.createMenuControls();
        this.createPageControlButtons();
    }

    @Override
    public void render(MatrixStack matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.minecraft.getTextureManager().bind(this.guideBookGUI);

        // Draw background and buttons
        int x = (this.width - this.bgWidth) / 2;
        int y = (this.height - this.bgHeight) / 2;

        blit(matrixStack, x, y, 0, 0, this.bgWidth, this.bgHeight, this.textureWidth, this.textureHeight);

        super.render(matrixStack, p_230430_2_, p_230430_3_, p_230430_4_);

        // Draw content
        this.guideProcessor.draw(matrixStack);
    }

    public FontRenderer getFontRenderer() {
        return this.font;
    }

    protected void pageBack() {
        guideProcessor.pageBack();
        this.updateButtonVisibility();
    }

    protected void pageForward() {
        guideProcessor.pageForward();
        this.updateButtonVisibility();
    }

    protected void pageTableOfContents() {
        guideProcessor.setCurrentPage(1, false);
        this.updateButtonVisibility();
    }

    protected void createMenuControls() {
        int x = (this.width - this.bgWidth) / 2;
        int y = (this.height - this.bgHeight) / 2;

        this.addButton(new GuideCloseButton(x + 338 - 9, y - 9, 18, 18, DialogTexts.GUI_DONE, (p_214161_1_) -> {
            this.minecraft.setScreen((Screen)null);
        }));
    }

    protected void createPageControlButtons() {
        int x = (this.width - this.bgWidth) / 2;
        int y = (this.height - this.bgHeight) / 2;

        this.forwardButton = this.addButton(new GuideChangePageButton(x + 306, y + 198, true,
                (p_214159_1_) -> this.pageForward(), true));
        this.backButton = this.addButton(new GuideChangePageButton(x + 281, y + 198, false,
                (p_214158_1_) -> this.pageBack(), true));
        this.tableOfContentsButton = this.addButton(new GuideTableOfContentsButton(x + 259, y + 195, true,
                (p_onPress_1_) -> this.pageTableOfContents(), true));
        this.updateButtonVisibility();
    }

    private void updateButtonVisibility() {
        this.forwardButton.visible = guideProcessor.getCurrentPageIndex() < guideProcessor.getNumPages() - 1;
        this.backButton.visible = guideProcessor.getCurrentPageIndex() > 0;

        this.tableOfContentsButton.visible = !guideProcessor.isTablePage() && !guideProcessor.isTitlePage();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int value) {
        if (value == 0 && guideProcessor.isTablePage()) {
            Style style = guideProcessor.getClickedComponentStyleAt(mouseX, mouseY);
            if (style != null && this.handleComponentClicked(style)) {
                this.updateButtonVisibility();
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, value);
    }

    public boolean handleComponentClicked(Style p_230455_1_) {
        ClickEvent clickevent = p_230455_1_.getClickEvent();
        if (clickevent == null) {
            return false;
        } else if (clickevent.getAction() == ClickEvent.Action.CHANGE_PAGE) {
            String s = clickevent.getValue();

            try {
                int i = Integer.parseInt(s) - 1;
                return guideProcessor.setCurrentPage(i, true);
            } catch (Exception exception) {
                return false;
            }
        }

        return false;
    }

    public void drawTooltip(MatrixStack matrixStack, ITextComponent textComponent,  int mouseX, int mouseY) {
        GuiUtils.drawHoveringText(matrixStack,
                Lists.newArrayList(textComponent),
                mouseX, mouseY,
                GuideScreen.this.width, GuideScreen.this.height,
                -1,
                GuideScreen.this.font);
    }

    public class GuideCloseButton extends Button {
        private static final String CLOSE_BUTTON_TOOLTIP = "guide.happyholidays.close_button.tooltip";
        public GuideCloseButton(int p_i232255_1_, int p_i232255_2_, int p_i232255_3_,
                                int p_i232255_4_, ITextComponent p_i232255_5_, IPressable p_i232255_6_) {
            super(p_i232255_1_, p_i232255_2_, p_i232255_3_, p_i232255_4_, p_i232255_5_, p_i232255_6_);
        }

        public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float p_230431_4_) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bind(guideBookGUI);
            int i = 338;
            int j = 26;

            blit(matrixStack, this.x, this.y, i, j, 18, 18, textureWidth, textureHeight);
        }
    }

    public class GuideChangePageButton extends ChangePageButton {
        private static final String FORWARD_BUTTON_TOOLTIP = "guide.happyholidays.forward_button.tooltip";
        private static final String BACKWARD_BUTTON_TOOLTIP = "guide.happyholidays.backward_button.tooltip";

        private final boolean isForward;
        private final boolean playTurnSound;

        public GuideChangePageButton(int p_i51079_1_, int p_i51079_2_, boolean p_i51079_3_,
                                     IPressable p_i51079_4_,
                                     boolean p_i51079_5_) {
            super(p_i51079_1_, p_i51079_2_, p_i51079_3_, p_i51079_4_, p_i51079_5_);

            this.isForward = p_i51079_3_;
            this.playTurnSound = p_i51079_5_;
        }

        public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float p_230431_4_) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bind(guideBookGUI);
            int i = 338;
            int j = 0;
            if (this.isHovered()) {
                i += 23;
            }

            if (!this.isForward) {
                j += 13;
            }

            blit(matrixStack, this.x, this.y, i, j, 23, 13, textureWidth, textureHeight);
        }
    }

    public class GuideTableOfContentsButton extends ChangePageButton {
        private static final String TABLE_OF_CONTENTS_TOOLTIP = "guide.happyholidays.table_of_contents_button.tooltip";

        private final boolean playTurnSound;

        public GuideTableOfContentsButton(int p_i51079_1_, int p_i51079_2_, boolean p_i51079_3_,
                                     IPressable p_i51079_4_,
                                     boolean p_i51079_5_) {
            super(p_i51079_1_, p_i51079_2_, p_i51079_3_, p_i51079_4_, p_i51079_5_);

            this.playTurnSound = p_i51079_5_;
        }

        public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float p_230431_4_) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bind(guideBookGUI);
            int i = 338;
            int j = 44;
            if (this.isHovered()) {
                i += 19;
            }

            blit(matrixStack, this.x, this.y, i, j, 19, 18, textureWidth, textureHeight);
        }
    }
}
