package com.jumpcutfindo.happyholidays.client.screen.guides;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.screen.guides.lines.IPageLine;
import com.jumpcutfindo.happyholidays.common.guide.Guide;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.PageButton;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.fmlclient.gui.GuiUtils;

public class GuideScreen extends Screen {
    public static final ResourceLocation DEFAULT_BOOK_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/guide/guide_book.png");
    public static final ResourceLocation CHRISTMAS_GUIDE_BOOK_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
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

    private PageButton forwardButton;
    private PageButton backButton;
    private PageButton tableOfContentsButton;

    private RecipeManager recipeManager;

    private IPageLine hoveredLine;

    public double mouseX, mouseY;

    public GuideScreen(Guide guide) {
        super(new TextComponent("Guide Book"));
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

        this.recipeManager = this.minecraft != null ? this.minecraft.level.getRecipeManager() : null;
    }

    @Override
    public void render(PoseStack matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(matrixStack);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.setShaderTexture(0, this.guideBookGUI);

        // Draw background and buttons
        int x = (this.width - this.bgWidth) / 2;
        int y = (this.height - this.bgHeight) / 2;

        blit(matrixStack, x, y, 0, 0, this.bgWidth, this.bgHeight, this.textureWidth, this.textureHeight);

        super.render(matrixStack, p_230430_2_, p_230430_3_, p_230430_4_);

        // Draw content
        this.guideProcessor.draw(matrixStack);
    }

    public Font getFontRenderer() {
        return this.font;
    }

    public ItemRenderer getItemRenderer() {
        return this.itemRenderer;
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

        this.addRenderableWidget(new GuideCloseButton(x + 338 - 9, y - 9, 18, 18, CommonComponents.GUI_DONE, (p_214161_1_) -> {
            this.minecraft.setScreen((Screen)null);
        }));
    }

    protected void createPageControlButtons() {
        int x = (this.width - this.bgWidth) / 2;
        int y = (this.height - this.bgHeight) / 2;

        this.forwardButton = this.addRenderableWidget(new GuideChangePageButton(x + 306, y + 198, true,
                (p_214159_1_) -> this.pageForward(), true));
        this.backButton = this.addRenderableWidget(new GuideChangePageButton(x + 281, y + 198, false,
                (p_214158_1_) -> this.pageBack(), true));
        this.tableOfContentsButton = this.addRenderableWidget(new GuideTableOfContentsButton(x + 259, y + 195, true,
                (p_onPress_1_) -> this.pageTableOfContents(), true));
        this.updateButtonVisibility();
    }

    private void updateButtonVisibility() {
        this.forwardButton.visible = guideProcessor.getCurrentPageIndex() < guideProcessor.getNumPages() - 1;
        this.backButton.visible = guideProcessor.getCurrentPageIndex() > 0;

        this.tableOfContentsButton.visible = !guideProcessor.isTablePage() && !guideProcessor.isTitlePage();
    }

    private void setMousePos(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    public void mouseMoved(double mouseX, double mouseY) {
        this.setMousePos(mouseX, mouseY);
        IPageLine pageLine = guideProcessor.getLineAt(mouseX, mouseY);

        if (pageLine != null) {
            if (this.hoveredLine == null) {
                this.hoveredLine = pageLine;
                pageLine.setHovered(true);
            } else if (!this.hoveredLine.equals(pageLine)) {
                this.hoveredLine.setHovered(false);

                this.hoveredLine = pageLine;
                pageLine.setHovered(true);
            }
        }
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

    public void drawTooltip(PoseStack matrixStack, Component textComponent,  int mouseX, int mouseY) {
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
                                int p_i232255_4_, Component p_i232255_5_, OnPress p_i232255_6_) {
            super(p_i232255_1_, p_i232255_2_, p_i232255_3_, p_i232255_4_, p_i232255_5_, p_i232255_6_);
        }

        public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float p_230431_4_) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, guideBookGUI);
            int i = 338;
            int j = 26;

            blit(matrixStack, this.x, this.y, i, j, 18, 18, textureWidth, textureHeight);

            if (this.isHovered) {
                drawTooltip(matrixStack, new TranslatableComponent(CLOSE_BUTTON_TOOLTIP), mouseX, mouseY);
            }
        }
    }

    public class GuideChangePageButton extends PageButton {
        private static final String FORWARD_BUTTON_TOOLTIP = "guide.happyholidays.forward_button.tooltip";
        private static final String BACKWARD_BUTTON_TOOLTIP = "guide.happyholidays.backward_button.tooltip";

        private final boolean isForward;
        private final boolean playTurnSound;

        public GuideChangePageButton(int p_i51079_1_, int p_i51079_2_, boolean p_i51079_3_,
                                     OnPress p_i51079_4_,
                                     boolean p_i51079_5_) {
            super(p_i51079_1_, p_i51079_2_, p_i51079_3_, p_i51079_4_, p_i51079_5_);

            this.isForward = p_i51079_3_;
            this.playTurnSound = p_i51079_5_;
        }

        public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float p_230431_4_) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, guideBookGUI);
            int i = 338;
            int j = 0;
            if (this.isHovered()) {
                i += 23;
            }

            if (!this.isForward) {
                j += 13;
            }

            blit(matrixStack, this.x, this.y, i, j, 23, 13, textureWidth, textureHeight);

            if (this.isHovered) {
                if (this.isForward) {
                    drawTooltip(matrixStack, new TranslatableComponent(FORWARD_BUTTON_TOOLTIP), mouseX, mouseY);
                } else {
                    drawTooltip(matrixStack, new TranslatableComponent(BACKWARD_BUTTON_TOOLTIP), mouseX, mouseY);
                }
            }
        }
    }

    public class GuideTableOfContentsButton extends PageButton {
        private static final String TABLE_OF_CONTENTS_TOOLTIP = "guide.happyholidays.table_of_contents_button.tooltip";

        private final boolean playTurnSound;

        public GuideTableOfContentsButton(int p_i51079_1_, int p_i51079_2_, boolean p_i51079_3_,
                                     OnPress p_i51079_4_,
                                     boolean p_i51079_5_) {
            super(p_i51079_1_, p_i51079_2_, p_i51079_3_, p_i51079_4_, p_i51079_5_);

            this.playTurnSound = p_i51079_5_;
        }

        public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float p_230431_4_) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, guideBookGUI);
            int i = 338;
            int j = 44;
            if (this.isHovered()) {
                i += 19;
            }

            blit(matrixStack, this.x, this.y, i, j, 19, 18, textureWidth, textureHeight);

            if (this.isHovered) {
                drawTooltip(matrixStack, new TranslatableComponent(TABLE_OF_CONTENTS_TOOLTIP), mouseX, mouseY);
            }
        }
    }
}
