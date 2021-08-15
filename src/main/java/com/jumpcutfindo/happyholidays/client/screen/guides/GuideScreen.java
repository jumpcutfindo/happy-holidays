package com.jumpcutfindo.happyholidays.client.screen.guides;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.guide.Guide;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ChangePageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class GuideScreen extends Screen {
    private static final ResourceLocation GUIDE_BOOK_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/guide/guide_book.png");

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

    private ChangePageButton forwardButton;
    private ChangePageButton backButton;

    public GuideScreen(Guide guide) {
        super(new StringTextComponent("Guide Book"));
        this.guide = guide;

        this.bgWidth = 337;
        this.bgHeight = 200;

        this.textureWidth = 512;
        this.textureHeight = 512;
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

        this.minecraft.getTextureManager().bind(GUIDE_BOOK_GUI);

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

        this.forwardButton = this.addButton(new GuideChangePageButton(x + 302, y + 182, true,
                (p_214159_1_) -> this.pageForward(), true));
        this.backButton = this.addButton(new GuideChangePageButton(x + 275, y + 182, false,
                (p_214158_1_) -> this.pageBack(), true));
        this.updateButtonVisibility();
    }

    private void updateButtonVisibility() {
        this.forwardButton.visible = guideProcessor.getCurrentPageIndex() < guideProcessor.getNumPages() - 1;
        this.backButton.visible = guideProcessor.getCurrentPageIndex() > 0;
    }

    public class GuideCloseButton extends Button {
        public GuideCloseButton(int p_i232255_1_, int p_i232255_2_, int p_i232255_3_,
                                int p_i232255_4_, ITextComponent p_i232255_5_, IPressable p_i232255_6_) {
            super(p_i232255_1_, p_i232255_2_, p_i232255_3_, p_i232255_4_, p_i232255_5_, p_i232255_6_);
        }

        public void renderButton(MatrixStack p_230431_1_, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bind(GuideScreen.GUIDE_BOOK_GUI);
            int i = 338;
            int j = 26;

            blit(p_230431_1_, this.x, this.y, i, j, 18, 18, textureWidth, textureHeight);
        }
    }

    public class GuideChangePageButton extends ChangePageButton {
        private final boolean isForward;
        private final boolean playTurnSound;

        public GuideChangePageButton(int p_i51079_1_, int p_i51079_2_, boolean p_i51079_3_,
                                     IPressable p_i51079_4_,
                                     boolean p_i51079_5_) {
            super(p_i51079_1_, p_i51079_2_, p_i51079_3_, p_i51079_4_, p_i51079_5_);

            this.isForward = p_i51079_3_;
            this.playTurnSound = p_i51079_5_;
        }

        public void renderButton(MatrixStack p_230431_1_, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bind(GuideScreen.GUIDE_BOOK_GUI);
            int i = 338;
            int j = 0;
            if (this.isHovered()) {
                i += 23;
            }

            if (!this.isForward) {
                j += 13;
            }

            blit(p_230431_1_, this.x, this.y, i, j, 23, 13, textureWidth, textureHeight);
        }
    }
}
