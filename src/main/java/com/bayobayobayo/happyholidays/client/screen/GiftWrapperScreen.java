package com.bayobayobayo.happyholidays.client.screen;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.container.christmas.gifts.GiftWrapperContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GiftWrapperScreen extends ContainerScreen<GiftWrapperContainer> {
    private static final ResourceLocation GIFT_WRAPPER_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/container/gift_wrapper_block.png");

    public GiftWrapperScreen(GiftWrapperContainer screenContainer, PlayerInventory playerInv,
                             ITextComponent title) {
        super(screenContainer, playerInv, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 189;

        this.inventoryLabelX = 8;
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
        this.minecraft.textureManager.bind(GIFT_WRAPPER_GUI);

        // Draw container GUI
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;

        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Draw text
        this.font.draw(matrixStack, "Gifts", x + 51, y + 20, 4210752);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int i, int j) {
        this.font.draw(matrixStack, this.inventory.getDisplayName(), (float)this.inventoryLabelX, (float)this.inventoryLabelY, 4210752);
    }

    @Override
    protected void renderTooltip(MatrixStack p_230459_1_, int p_230459_2_, int p_230459_3_) {
        super.renderTooltip(p_230459_1_, p_230459_2_, p_230459_3_);
    }
}
