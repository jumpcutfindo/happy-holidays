package com.bayobayobayo.happyholidays.client.screen;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.container.christmas.ChristmasStarContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ChristmasStarScreen extends ContainerScreen<ChristmasStarContainer> {
    private static final ResourceLocation CHRISTMAS_STAR_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/container/christmas_star_block");

    public ChristmasStarScreen(ChristmasStarContainer screenContainer, PlayerInventory playerInv,
                               ITextComponent title) {
        super(screenContainer, playerInv, title);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderComponentHoverEffect(matrixStack, null, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.textureManager.bind(CHRISTMAS_STAR_GUI);

        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.getXSize(), this.getYSize());
    }

    @Override
    public int getGuiLeft() {
        return 0;
    }

    @Override
    public int getGuiTop() {
        return 0;
    }

    @Override
    public int getXSize() {
        return 175;
    }

    @Override
    public int getYSize() {
        return 201;
    }
}
