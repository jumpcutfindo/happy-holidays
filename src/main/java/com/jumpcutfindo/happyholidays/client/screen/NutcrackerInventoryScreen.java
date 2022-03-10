package com.jumpcutfindo.happyholidays.client.screen;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.inventory.christmas.NutcrackerContainer;
import com.jumpcutfindo.happyholidays.network.christmas.NutcrackerPacket;
import com.jumpcutfindo.happyholidays.handlers.PacketHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.network.PacketDistributor;

public class NutcrackerInventoryScreen extends AbstractContainerScreen<NutcrackerContainer> {
    private static final ResourceLocation NUTCRACKER_INVENTORY_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/container/nutcracker_inventory.png");

    private final NutcrackerContainer nutcrackerContainer;

    public NutcrackerInventoryScreen(NutcrackerContainer nutcrackerContainer, Inventory inventory, Component titleComponent) {
        super(nutcrackerContainer, inventory, titleComponent);
        this.nutcrackerContainer = nutcrackerContainer;

        this.passEvents = false;
        this.imageHeight = 133;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    public void render(PoseStack p_98807_, int p_98808_, int p_98809_, float p_98810_) {
        this.renderBackground(p_98807_);
        super.render(p_98807_, p_98808_, p_98809_, p_98810_);
        this.renderTooltip(p_98807_, p_98808_, p_98809_);
    }

    protected void renderBg(PoseStack p_98802_, float p_98803_, int p_98804_, int p_98805_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, NUTCRACKER_INVENTORY_GUI);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(p_98802_, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void onClose() {
        super.onClose();

        PacketHandler.NETWORK.send(PacketDistributor.SERVER.noArg(), new NutcrackerPacket(this.nutcrackerContainer.getEntityId()));
    }
}
