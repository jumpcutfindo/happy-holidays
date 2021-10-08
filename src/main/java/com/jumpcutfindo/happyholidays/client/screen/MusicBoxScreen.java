package com.jumpcutfindo.happyholidays.client.screen;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.container.christmas.musicbox.MusicBoxContainer;
import com.jumpcutfindo.happyholidays.common.handlers.PacketHandler;
import com.jumpcutfindo.happyholidays.common.network.christmas.MusicBoxPacket;
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
import net.minecraftforge.fmllegacy.network.PacketDistributor;

public class MusicBoxScreen extends AbstractContainerScreen<MusicBoxContainer> {
    public static final ResourceLocation MUSIC_BOX_GUI = new ResourceLocation(HappyHolidaysMod.MOD_ID,
            "textures/gui/container/music_box.png");

    private MusicBoxContainer container;

    private PlayStopButton playStopButton;
    private LoopButton loopButton;
    private PreviousButton previousButton;
    private NextButton nextButton;

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

        blit(matrixStack, x + 6 + (selectedSlot % 9) * 18, y + 16 + (selectedSlot / 9) * 18, 176, 48, 20, 20);
    }

    public void createMusicControlButtons() {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        this.playStopButton = this.addRenderableWidget(new PlayStopButton(x + 80, y + 72, 16, 16,  (onPress) -> toggleMusic()));
        this.loopButton = this.addRenderableWidget(new LoopButton(x + 116, y + 72, 16, 16,  (onPress) -> toggleLoop()));

        this.previousButton = this.addRenderableWidget(new PreviousButton(x + 62, y + 72, 16, 16, (onPress) -> previousTrack()));
        this.nextButton = this.addRenderableWidget(new NextButton(x + 98, y + 72, 16, 16, (onPress) -> nextTrack()));
    }

    public void nextTrack() {
        PacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                MusicBoxPacket.createNextRequestPacket(this.container.blockEntity.getBlockPos()));
    }

    public void previousTrack() {
        PacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                MusicBoxPacket.createPreviousRequestPacket(this.container.blockEntity.getBlockPos()));
    }

    public void toggleMusic() {
        if (this.playStopButton.isPlay()) {
            this.playStopButton.setPlay(false);

            PacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                    MusicBoxPacket.createStopRequestPacket(this.container.blockEntity.getBlockPos()));
        } else {
            this.playStopButton.setPlay(true);

            PacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                    MusicBoxPacket.createPlayRequestPacket(this.container.blockEntity.getBlockPos()));
        }
    }

    public void toggleLoop() {
        this.loopButton.setLoop(!this.loopButton.isLoop());

        PacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(),
                MusicBoxPacket.createToggleLoopRequestPacket(this.container.blockEntity.getBlockPos()));
    }

    public void drawTooltip(PoseStack matrixStack, Component textComponent,  int mouseX, int mouseY) {
        GuiUtils.drawHoveringText(matrixStack,
                Lists.newArrayList(textComponent),
                mouseX, mouseY,
                this.width, this.height,
                -1,
                this.font);
    }

    public class PlayStopButton extends Button {
        private static final String PLAY_BUTTON_TOOLTIP = "block.happyholidays.music_box.play_button_tooltip";
        private static final String STOP_BUTTON_TOOLTIP = "block.happyholidays.music_box.stop_button_tooltip";

        public boolean isPlay;

        public PlayStopButton(int p_93721_, int p_93722_, int p_93723_, int p_93724_,
                              OnPress p_93726_) {
            super(p_93721_, p_93722_, p_93723_, p_93724_, TextComponent.EMPTY, p_93726_);
            this.isPlay = false;
        }

        @Override
        public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float p_93749_) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, MUSIC_BOX_GUI);

            this.isPlay = container.isPlaying();

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
        private static final String LOOP_BUTTON_TOOLTIP = "block.happyholidays.music_box.loop_button_tooltip";

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

    public class PreviousButton extends Button {
        private static final String PREVIOUS_BUTTON_TOOLTIP = "block.happyholidays.music_box.previous_button_tooltip";

        public PreviousButton(int p_93721_, int p_93722_, int p_93723_, int p_93724_,
                          OnPress p_93726_) {
            super(p_93721_, p_93722_, p_93723_, p_93724_, TextComponent.EMPTY, p_93726_);
        }

        @Override
        public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float p_93749_) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, MUSIC_BOX_GUI);

            int i = 176;
            int j = 32;

            blit(matrixStack, this.x, this.y, i, j, 16, 16, 256, 256);

            if (this.isHovered()) drawTooltip(matrixStack, new TranslatableComponent(PREVIOUS_BUTTON_TOOLTIP), mouseX, mouseY);
        }
    }

    public class NextButton extends Button {
        private static final String NEXT_BUTTON_TOOLTIP = "block.happyholidays.music_box.next_button_tooltip";

        public NextButton(int p_93721_, int p_93722_, int p_93723_, int p_93724_,
                              OnPress p_93726_) {
            super(p_93721_, p_93722_, p_93723_, p_93724_, TextComponent.EMPTY, p_93726_);
        }

        @Override
        public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float p_93749_) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, MUSIC_BOX_GUI);

            int i = 192;
            int j = 32;

            blit(matrixStack, this.x, this.y, i, j, 16, 16, 256, 256);

            if (this.isHovered()) drawTooltip(matrixStack, new TranslatableComponent(NEXT_BUTTON_TOOLTIP), mouseX, mouseY);
        }
    }
}
