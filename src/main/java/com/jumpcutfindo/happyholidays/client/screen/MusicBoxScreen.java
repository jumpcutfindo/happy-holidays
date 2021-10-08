package com.jumpcutfindo.happyholidays.client.screen;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.container.christmas.musicbox.MusicBoxContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fmlclient.gui.GuiUtils;

public class MusicBoxScreen extends AbstractContainerScreen<MusicBoxContainer> {
    public static final ResourceLocation MUSIC_BOX_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/container/music_box.png");

    private MusicBoxContainer container;

    private PlayPauseButton playPauseButton;
    private LoopButton loopButton;

    public MusicBoxScreen(MusicBoxContainer screenContainer, Inventory inventory, Component title) {
        super(screenContainer, inventory, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 184;

        this.inventoryLabelY = this.imageHeight - 94;

        this.container = screenContainer;
    }

    @Override
    protected void init() {
        super.init();

        this.createMusicControlButtons();
        this.loopButton.setLoop(container.isLooping());
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
        RenderSystem.setShaderTexture(0, MUSIC_BOX_GUI);

        // Draw container GUI
        int x = (this.width - this.getXSize()) / 2;
        int y = (this.height - this.getYSize()) / 2;

        blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Draw current selected
        int selectedSlot = container.getSelectedSlot();

        blit(matrixStack, x + 6 + (selectedSlot % 9) * 18, y + 16 + (selectedSlot / 9) * 18, 176, 32, 20, 20);
    }

    public void createMusicControlButtons() {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        this.playPauseButton = this.addRenderableWidget(new PlayPauseButton(x + 80, y + 72, 16, 16,  (onPress) -> toggleMusic()));
        this.loopButton = this.addRenderableWidget(new LoopButton(x + 98, y + 72, 16, 16,  (onPress) -> toggleLoop()));
    }

    public void toggleMusic() {
        if (this.playPauseButton.isPlay()) {
            this.playPauseButton.setPlay(false);
            this.container.updateState(true, false);
        } else {
            this.playPauseButton.setPlay(true);
            this.container.updateState(true, false);
        }
    }

    public void toggleLoop() {
        if (this.loopButton.isLoop()) {
            this.loopButton.setLoop(false);
            this.container.updateState(false, true);
        } else {
            this.loopButton.setLoop(true);
            this.container.updateState(false, true);
        }
    }

    public void drawTooltip(PoseStack matrixStack, Component textComponent,  int mouseX, int mouseY) {
        GuiUtils.drawHoveringText(matrixStack,
                Lists.newArrayList(textComponent),
                mouseX, mouseY,
                this.width, this.height,
                -1,
                this.font);
    }

    public class PlayPauseButton extends Button {
        private static final String PLAY_BUTTON_TOOLTIP = "container.happyholidays.music_box.play_button_tooltip";
        private static final String STOP_BUTTON_TOOLTIP = "container.happyholidays.music_box.stop_button_tooltip";

        public boolean isPlay;

        public PlayPauseButton(int p_93721_, int p_93722_, int p_93723_, int p_93724_,
                               OnPress p_93726_) {
            super(p_93721_, p_93722_, p_93723_, p_93724_, TextComponent.EMPTY, p_93726_);
            this.isPlay = false;
        }

        @Override
        public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float p_93749_) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, MUSIC_BOX_GUI);

            int i = 176;
            int j = 0;

            if (this.isPlay) {
                i += 16;
            }

            blit(matrixStack, this.x, this.y, i, j, 16, 16, 256, 256);

            if (this.isHovered()) {
                if (this.isPlay) drawTooltip(matrixStack, new TranslatableComponent(STOP_BUTTON_TOOLTIP), mouseX, mouseY);
                else drawTooltip(matrixStack, new TranslatableComponent(PLAY_BUTTON_TOOLTIP), mouseX, mouseY);
            }
        }

        public boolean isPlay() {
            return isPlay;
        }

        public void setPlay(boolean isPlay) {
            this.isPlay = isPlay;
        }
    }

    public class LoopButton extends Button {
        private static final String LOOP_BUTTON_TOOLTIP = "container.happyholidays.music_box.play_button_tooltip";

        public boolean isLoop;

        public LoopButton(int p_93721_, int p_93722_, int p_93723_, int p_93724_,
                          OnPress p_93726_) {
            super(p_93721_, p_93722_, p_93723_, p_93724_, TextComponent.EMPTY, p_93726_);
        }

        @Override
        public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float p_93749_) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, MUSIC_BOX_GUI);

            int i = 176;
            int j = 16;

            if (!this.isLoop) i += 16;

            blit(matrixStack, this.x, this.y, i, j, 16, 16, 256, 256);

            if (this.isHovered()) drawTooltip(matrixStack, new TranslatableComponent(LOOP_BUTTON_TOOLTIP), mouseX, mouseY);
        }

        public boolean isLoop() {
            return isLoop;
        }

        public void setLoop(boolean isLoop) {
            this.isLoop = isLoop;
        }
    }

}
