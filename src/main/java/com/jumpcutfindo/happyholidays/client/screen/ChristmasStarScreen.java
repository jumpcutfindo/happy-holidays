package com.jumpcutfindo.happyholidays.client.screen;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.container.christmas.star.ChristmasStarContainer;
import com.jumpcutfindo.happyholidays.common.network.christmas.SummonSantaPacket;
import com.jumpcutfindo.happyholidays.common.utils.StringUtils;
import com.jumpcutfindo.happyholidays.handlers.PacketHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.network.PacketDistributor;

public class ChristmasStarScreen extends AbstractContainerScreen<ChristmasStarContainer> {
    private static final ResourceLocation CHRISTMAS_STAR_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/container/christmas_star.png");

    private static final int[] STAR_HEIGHTS = new int [] { 0, 6, 12, 18, 24, 31 };

    public ChristmasStarScreen(ChristmasStarContainer screenContainer, Inventory playerInv,
                               Component title) {
        super(screenContainer, playerInv, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 190;

        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        this.createMenuControls();
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
        RenderSystem.setShaderTexture(0, CHRISTMAS_STAR_GUI);

        // Draw container GUI
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Draw progress bar (note: must be before all the text stuff)
        // Note: blit(stack, startPosX, startPosY, imgStartX, imgStartY, imgWidth, imgHeight)
        int tier = this.menu.getCurrentTier();
        this.blit(matrixStack,
                x + 72, y + 39 + (31 - STAR_HEIGHTS[tier]),
                176, (31 - STAR_HEIGHTS[tier]),
                32, STAR_HEIGHTS[tier]
        );

        // Draw tier
        TranslatableComponent tierComponent = new TranslatableComponent("block.happyholidays"
                + ".christmas_star.tier", tier);
        if (this.menu.blockEntity.isBonusActive()) tierComponent.append(new TranslatableComponent("block"
                + ".happyholidays.christmas_star.bonus"));
        this.font.draw(matrixStack, tierComponent, x + this.titleLabelX, y + this.titleLabelY, 4210752);

        // Draw text
        String christmasPointsString = String.format("%d", this.menu.getCurrentPoints());
        this.font.draw(matrixStack, christmasPointsString,
                x + 89 - (int) (this.font.width(christmasPointsString) / 2), y + 52,
                4210752);

    }

    @Override
    protected void renderTooltip(PoseStack p_230459_1_, int p_230459_2_, int p_230459_3_) {
        super.renderTooltip(p_230459_1_, p_230459_2_, p_230459_3_);
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int p_230451_2_, int p_230451_3_) {
        this.font.draw(matrixStack, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY, 4210752);
    }

    private void createMenuControls() {
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;

        this.addRenderableWidget(new SummonSantaButton(this, x + 72, y + 39, 32, 31, CommonComponents.GUI_DONE, (pred) -> {
            if (this.menu.blockEntity.getCurrentTier() >= 5) {
                Minecraft.getInstance().setScreen((Screen) null);

                PacketHandler.NETWORK.send(PacketDistributor.SERVER.noArg(),
                        new SummonSantaPacket(this.menu.blockEntity.getBlockPos()));
            }

        }));
    }

    public ChristmasStarBlockEntity getBlockEntity() {
        return this.menu.getBlockEntity();
    }

    public class SummonSantaButton extends Button {
        private static final String SUMMON_SANTA_TOOLTIP = "block.happyholidays.christmas_star.summon_tooltip";

        private final ChristmasStarScreen screen;
        public SummonSantaButton(ChristmasStarScreen screen, int p_i232255_1_, int p_i232255_2_, int p_i232255_3_,
                                int p_i232255_4_, Component p_i232255_5_, OnPress p_i232255_6_) {
            super(p_i232255_1_, p_i232255_2_, p_i232255_3_, p_i232255_4_, p_i232255_5_, p_i232255_6_);

            this.screen = screen;
        }

        public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float p_230431_4_) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            if (this.screen.getBlockEntity().getCurrentTier() == 5) {
                long gameTime = screen.getBlockEntity().getLevel().getGameTime();
                long nextSummonTime = screen.getBlockEntity().getSantaNextSummonTime();

                if (gameTime >= nextSummonTime) {
                    RenderSystem.setShaderTexture(0, CHRISTMAS_STAR_GUI);
                    blit(matrixStack, this.x, this.y, 176, 31, 32, 31);

                    if (this.isHovered) renderTooltip(matrixStack, new TranslatableComponent(SUMMON_SANTA_TOOLTIP), mouseX, mouseY);
                } else {
                    if (this.isHovered) renderTooltip(matrixStack, new TranslatableComponent("%s remaining before Santa can be summoned", StringUtils.convertTicksToString(nextSummonTime - gameTime)), mouseX, mouseY);
                }

            } else {
                if (this.isHovered) {
                    renderTooltip(matrixStack, new TranslatableComponent("block.happyholidays.christmas_star.cannot_summon_tooltip"), mouseX, mouseY);
                }
            }
        }
    }
}
