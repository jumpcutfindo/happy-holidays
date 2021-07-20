package com.bayobayobayo.happyholidays.client.screen;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.container.christmas.ChristmasStarContainer;
import com.bayobayobayo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ChristmasStarScreen extends ContainerScreen<ChristmasStarContainer> {
    private static final ResourceLocation CHRISTMAS_STAR_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/container/christmas_star_block.png");

    public ChristmasStarScreen(ChristmasStarContainer screenContainer, PlayerInventory playerInv,
                               ITextComponent title) {
        super(screenContainer, playerInv, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderComponentHoverEffect(matrixStack, null, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.textureManager.bind(CHRISTMAS_STAR_GUI);

        // Draw container GUI
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Draw progress bar
        // Note: blit(stack, startPos, endPos, imgStartX, imgStartY, imgWidth, imgHeight)
        this.blit(matrixStack, x + 19, y + 54, 0, 166,
                (int) (((float) this.menu.getCurrentPoints() / (float) ChristmasStarTileEntity.MAX_POINTS) * 135.0F),
                6);

        // Draw text
        String christmasPointsString = String.format("%d / %d (Tier %d)", this.menu.getCurrentPoints(),
                ChristmasStarTileEntity.MAX_POINTS, this.menu.getCurrentTier());
        this.font.draw(matrixStack, christmasPointsString,
                x + 88 - (int) (this.font.width(christmasPointsString) / 2), y + 61,
                4210752);

    }

    @Override
    protected void renderTooltip(MatrixStack p_230459_1_, int p_230459_2_, int p_230459_3_) {
        super.renderTooltip(p_230459_1_, p_230459_2_, p_230459_3_);
    }
}
