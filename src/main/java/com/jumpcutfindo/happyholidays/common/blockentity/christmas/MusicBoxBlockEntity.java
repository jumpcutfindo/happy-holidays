package com.jumpcutfindo.happyholidays.common.blockentity.christmas;

import java.util.List;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.container.christmas.musicbox.MusicBoxContainer;
import com.jumpcutfindo.happyholidays.common.events.christmas.MusicBoxEvent;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SheetMusicItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.utils.EntityUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MusicBoxBlockEntity extends BaseContainerBlockEntity implements ChristmasEntityBlock, Clearable, IAnimatable {
    public static final String BLOCK_ENTITY_ID = "music_box";

    public static final int SLOTS = 27;

    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);

    private ChristmasMusic currentMusic;

    private boolean isPlaying = false;
    private boolean isLooping = false;

    private int latestAction = -1;

    private int currentSelectedSlot = 0;

    private int timeToNextTrack = -1;

    private AnimationFactory factory = new AnimationFactory(this);

    public MusicBoxBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state) {
        super(entityType, pos, state);
    }

    public MusicBoxBlockEntity(BlockPos pos, BlockState state) {
        this(ChristmasBlockEntities.MUSIC_BOX_ENTITY_TYPE.get(), pos, state);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        this.isLooping = tag.getBoolean("IsLooping");

        ContainerHelper.loadAllItems(tag, items);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.putBoolean("IsLooping", this.isLooping);
        ContainerHelper.saveAllItems(tag, items);
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent(BLOCK_ENTITY_ID);
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInv) {
        return new MusicBoxContainer(id, playerInv, this);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void clearContent() {
        this.items = NonNullList.withSize(SLOTS, ItemStack.EMPTY);

        if (this.level != null && this.level.isClientSide()) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket(){
        return ClientboundBlockEntityDataPacket.create(this, MusicBoxBlockEntity::createUpdateTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt){
        CompoundTag nbtTag = pkt.getTag();

        this.isPlaying = nbtTag.getBoolean("IsPlaying");
        this.isLooping = nbtTag.getBoolean("IsLooping");

        this.currentSelectedSlot = nbtTag.getInt("CurrentSelectedSlot");

        // Receive a packet from the server to let the client know what's that latest action, then execute it
        this.latestAction = nbtTag.getInt("LatestAction");

        this.runAction(this.latestAction);
    }

    public static CompoundTag createUpdateTag(BlockEntity blockEntity) {
        CompoundTag nbtTag = new CompoundTag();

        if (blockEntity instanceof MusicBoxBlockEntity musicBoxBlockEntity) {
            nbtTag.putBoolean("IsPlaying", musicBoxBlockEntity.isPlaying());
            nbtTag.putBoolean("IsLooping", musicBoxBlockEntity.isLooping());

            nbtTag.putInt("CurrentSelectedSlot", musicBoxBlockEntity.currentSelectedSlot);

            // Store the latest action in the update packet to notify clients
            nbtTag.putInt("LatestAction", musicBoxBlockEntity.latestAction);
        }

        return nbtTag;
    }

    public int getSelectedSlot() {
        return this.currentSelectedSlot;
    }

    public void nextSlot() {
        this.setSelectedSlot(this.currentSelectedSlot + 1);
    }

    public void setSelectedSlot(int slot) {
        if (slot < 0 || slot >= SLOTS) currentSelectedSlot = 0;
        else currentSelectedSlot = slot;
    }

    public void toggleLoop() {
        this.latestAction = 5;

        this.isLooping = !this.isLooping;
        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    public void playFromStart() {
        this.setSelectedSlot(0);
        ItemStack sheetMusic = ItemStack.EMPTY;

        for (int i = 0; i < SLOTS; i++) {
            if (!items.get(i).isEmpty()) {
                sheetMusic = items.get(i);
                this.setSelectedSlot(i);
                break;
            }
        }

        if (sheetMusic.getItem() instanceof SheetMusicItem) {
            this.playMusic(((SheetMusicItem) sheetMusic.getItem()).getMusic());
        }
    }

    public void playCurrent() {
        this.stopMusic();
        ItemStack sheetMusic = ItemStack.EMPTY;

        for (int i = currentSelectedSlot; i < SLOTS; i++) {
            if (!items.get(i).isEmpty()) {
                sheetMusic = items.get(i);
                this.setSelectedSlot(i);
                break;
            }
        }

        if (sheetMusic.isEmpty()) {
            for (int i = currentSelectedSlot; i >= 0; i--) {
                if (!items.get(i).isEmpty()) {
                    sheetMusic = items.get(i);
                    this.setSelectedSlot(i);
                    break;
                }
            }
        }

        if (sheetMusic.getItem() instanceof SheetMusicItem) {
            this.playMusic(((SheetMusicItem) sheetMusic.getItem()).getMusic());
        }
    }

    public void playPrev() {
        this.stopMusic();
        ItemStack sheetMusic = ItemStack.EMPTY;

        for (int i = currentSelectedSlot - 1; i >= 0; i--) {
            if (!items.get(i).isEmpty()) {
                sheetMusic = items.get(i);
                this.currentSelectedSlot = i;
                break;
            }
        }

        if (sheetMusic.getItem() instanceof SheetMusicItem) {
            this.playMusic(((SheetMusicItem) sheetMusic.getItem()).getMusic());
        }
    }

    public void playNext() {
        this.stopMusic();
        ItemStack sheetMusic = ItemStack.EMPTY;

        for (int i = currentSelectedSlot + 1; i < SLOTS; i++) {
            if (!items.get(i).isEmpty()) {
                sheetMusic = items.get(i);
                this.currentSelectedSlot = i;
                break;
            }
        }

        if (sheetMusic.getItem() instanceof SheetMusicItem) {
            this.playMusic(((SheetMusicItem) sheetMusic.getItem()).getMusic());
        } else {
            if (this.isLooping()) {
                this.playFromStart();
            } else {
                this.stopMusic();
            }
        }

    }

    public void playMusic(ChristmasMusic music) {
        this.latestAction = 0;
        if (this.level.isClientSide()) {
            HappyHolidaysMod.PROXY.getChristmasProxy().playChristmasMusic(this.level, this.getBlockPos(), music);
        }

        this.currentMusic = music;

        this.isPlaying = true;

        this.timeToNextTrack = ChristmasMusic.getSoundDuration(music);

        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);

        if (!this.level.isClientSide()) {
            List<Player> players = EntityUtils.findPlayersInRadius(this.level, new Vec3(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ()), 5.0d);

            for (Player player : players) {
                MusicBoxEvent.Play playEvent = new MusicBoxEvent.Play(player);
                MinecraftForge.EVENT_BUS.post(playEvent);
            }
        }
    }

    public void stopMusic() {
        this.latestAction = 1;
        if (this.level.isClientSide()) {
            HappyHolidaysMod.PROXY.getChristmasProxy().stopChristmasMusic(this.level, this.getBlockPos());
        }

        this.isPlaying = false;
        this.timeToNextTrack = -1;

        this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
    }

    private void runAction(int actionId) {
        if (!this.level.isClientSide()) return;

        switch (actionId) {
        case 0 -> playCurrent();
        case 1 -> stopMusic();
        }
    }

    public boolean isLooping() {
        return this.isLooping;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    /*
        Handle animation stuff
     */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isPlaying()) event.getController().setAnimation(new AnimationBuilder().addAnimation(
                "animation.model.playing", true));
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int getContainerSize() {
        return SLOTS;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int start, int end) {
        return ContainerHelper.removeItem(this.items, start, end);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.items.set(index, itemStack);
    }

    @Override
    public boolean stillValid(Player playerEntity) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return playerEntity.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    public static void serverTick(Level level, BlockPos pos, BlockState blockState, MusicBoxBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            if (blockEntity.isPlaying && --blockEntity.timeToNextTrack <= 0) {
                blockEntity.playNext();
            }
        }
    }
}
