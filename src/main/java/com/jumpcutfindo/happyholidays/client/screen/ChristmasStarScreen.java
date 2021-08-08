package com.jumpcutfindo.happyholidays.client.screen;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.container.christmas.star.ChristmasStarContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ChristmasStarScreen extends ContainerScreen<ChristmasStarContainer> {
    private static final ResourceLocation CHRISTMAS_STAR_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/container/christmas_star_block.png");

    private static final int[] STAR_HEIGHTS = new int [] { 0, 6, 12, 18, 24, 31 };

    public ChristmasStarScreen(ChristmasStarContainer screenContainer, PlayerInventory playerInv,
                               ITextComponent title) {
        super(screenContainer, playerInv, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 190;

        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderComponentHoverEffect(matrixStack, null, mouseX, mouseY);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.textureManager.bind(CHRISTMAS_STAR_GUI);

        // Draw container GUI
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Draw progress bar (note: must be before all the text stuff)
        // Note: blit(stack, startPos, endPos, imgStartX, imgStartY, imgWidth, imgHeight)
        int tier = this.menu.getCurrentTier();
        this.blit(matrixStack,
                x + 72, y + 39 + (31 - STAR_HEIGHTS[tier]),
                176, (31 - STAR_HEIGHTS[tier]),
                32, STAR_HEIGHTS[tier]
        );

        // Draw tier
        String christmasTierString = String.format("Tier %d", tier);
        this.font.draw(matrixStack, christmasTierString, x + this.titleLabelX, y + this.titleLabelY, 4210752);

        // Draw text
        String christmasPointsString = String.format("%d", this.menu.getCurrentPoints());
        this.font.draw(matrixStack, christmasPointsString,
                x + 89 - (int) (this.font.width(christmasPointsString) / 2), y + 52,
                4210752);

    }

    @Override
    protected void renderTooltip(MatrixStack p_230459_1_, int p_230459_2_, int p_230459_3_) {
        super.renderTooltip(p_230459_1_, p_230459_2_, p_230459_3_);
    }

    @Override
    protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
        this.font.draw(p_230451_1_, this.inventory.getDisplayName(), (float) this.inventoryLabelX,
                (float) this.inventoryLabelY, 4210752);
    }
}
