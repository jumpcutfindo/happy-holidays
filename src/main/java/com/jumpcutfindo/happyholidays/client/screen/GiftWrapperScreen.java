package com.jumpcutfindo.happyholidays.client.screen;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.container.christmas.gifts.GiftWrapperContainer;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.GiftWrapperTileEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GiftWrapperScreen extends AbstractContainerScreen<GiftWrapperContainer> {
    private static final ResourceLocation GIFT_WRAPPER_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/container/gift_wrapping_station.png");

    public final GiftWrapperContainer container;

    public GiftWrapperScreen(GiftWrapperContainer screenContainer, Inventory playerInv,
                             Component title) {
        super(screenContainer, playerInv, title);

        this.container = screenContainer;

        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 189;

        this.inventoryLabelX = 8;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderComponentHoverEffect(matrixStack, null, mouseX, mouseY);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        this.minecraft.textureManager.bindForSetup(GIFT_WRAPPER_GUI);

        // Draw container GUI
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;

        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Draw text
        this.font.draw(matrixStack, "Gifts", x + 51, y + 20, 4210752);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int i, int j) {
        this.font.draw(matrixStack, this.getTileEntity().getDisplayName(), (float)this.inventoryLabelX,
                (float)this.inventoryLabelY
                , 4210752);
    }

    @Override
    protected void renderTooltip(PoseStack p_230459_1_, int p_230459_2_, int p_230459_3_) {
        super.renderTooltip(p_230459_1_, p_230459_2_, p_230459_3_);
    }

    public GiftWrapperTileEntity getTileEntity() {
        return this.menu.tileEntity;
    }
}
