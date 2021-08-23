package com.jumpcutfindo.happyholidays.client.screen;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.container.christmas.star.ChristmasStarContainer;
import com.jumpcutfindo.happyholidays.common.handlers.PacketHandler;
import com.jumpcutfindo.happyholidays.common.network.christmas.SummonSantaPacket;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.network.PacketDistributor;

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
    protected void init() {
        super.init();
        this.createMenuControls();
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
        Minecraft.getInstance().textureManager.bind(CHRISTMAS_STAR_GUI);

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
        TranslationTextComponent tierComponent = new TranslationTextComponent("block.happyholidays"
                + ".christmas_star_block.tier", tier);
        if (this.menu.tileEntity.isBonusActive()) tierComponent.append(new TranslationTextComponent("block"
                + ".happyholidays.christmas_star_block.bonus"));
        this.font.draw(matrixStack, tierComponent, x + this.titleLabelX, y + this.titleLabelY, 4210752);

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

    private void createMenuControls() {
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;

        this.addButton(new SummonSantaButton(this, x + 72, y + 39, 32, 31, DialogTexts.GUI_DONE, (pred) -> {
            Minecraft.getInstance().setScreen((Screen) null);

            PacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                    new SummonSantaPacket(this.menu.tileEntity.getBlockPos()));
        }));
    }

    public ChristmasStarTileEntity getTileEntity() {
        return this.menu.getTileEntity();
    }

    public void drawTooltip(MatrixStack matrixStack, ITextComponent textComponent,  int mouseX, int mouseY) {
        GuiUtils.drawHoveringText(matrixStack,
                Lists.newArrayList(textComponent),
                mouseX, mouseY,
                this.width, this.height,
                -1,
                this.font);
    }

    public class SummonSantaButton extends Button {
        private static final String SUMMON_SANTA_TOOLTIP = "block.happyholidays.christmas_star_block.summon_tooltip";

        private final ChristmasStarScreen screen;
        public SummonSantaButton(ChristmasStarScreen screen, int p_i232255_1_, int p_i232255_2_, int p_i232255_3_,
                                int p_i232255_4_, ITextComponent p_i232255_5_, IPressable p_i232255_6_) {
            super(p_i232255_1_, p_i232255_2_, p_i232255_3_, p_i232255_4_, p_i232255_5_, p_i232255_6_);

            this.screen = screen;
        }

        public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float p_230431_4_) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

            if (this.screen.getTileEntity().getCurrentTier() == 5) {
                Minecraft.getInstance().getTextureManager().bind(CHRISTMAS_STAR_GUI);

                blit(matrixStack, this.x, this.y, 176, 31, 32, 31);

                if (this.isHovered) {
                    drawTooltip(matrixStack, new TranslationTextComponent(SUMMON_SANTA_TOOLTIP), mouseX, mouseY);
                }
            }
        }
    }
}
