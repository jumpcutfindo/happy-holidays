package com.jumpcutfindo.happyholidays.common.blockentity.christmas;

import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.container.christmas.musicbox.MusicBoxContainer;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SheetMusicItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.sound.christmas.MusicBoxSound;

import net.minecraft.client.Minecraft;
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
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
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

    private MusicBoxSound currentMusic = null;

    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);

    private boolean isPlaying = false;
    private boolean isLooping = false;

    private int currentSelectedSlot = 0;

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

    public CompoundTag save(CompoundTag tag) {
        super.save(tag);

        tag.putBoolean("IsLooping", this.isLooping);
        ContainerHelper.saveAllItems(tag, items);

        return tag;
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
        CompoundTag nbtTag = new CompoundTag();

        return new ClientboundBlockEntityDataPacket(getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt){
        CompoundTag nbtTag = pkt.getTag();
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

    public void updateState(boolean togglePlaying, boolean toggleLooping) {
        if (togglePlaying) {
            if (this.isPlaying) {
                this.stopMusic();
            }
            else {
                this.playFromStart();
            }

            this.isPlaying = !this.isPlaying;
        }

        if (toggleLooping) {
            this.isLooping = !this.isLooping;
        }
    }

    public void playFromStart() {
        ItemStack sheetMusic = ItemStack.EMPTY;

        for (int i = 0; i < SLOTS; i++) {
            if (!items.get(i).isEmpty()) {
                sheetMusic = items.get(i);
                break;
            }
        }

        if (sheetMusic.getItem() instanceof SheetMusicItem) {
            this.playMusic(((SheetMusicItem) sheetMusic.getItem()).getMusic());
        }
    }

    public void playMusic(ChristmasMusic music) {
        currentMusic = SheetMusicItem.createMusicBoxSound(music, this.worldPosition);
        Minecraft.getInstance().getSoundManager().play(currentMusic);
    }

    public void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stopTrack();
            currentMusic = null;

            this.currentSelectedSlot = 0;
        }
    }

    public boolean isLooping() {
        return this.isLooping;
    }

    /*
        Handle animation stuff
     */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.currentMusic != null && !this.currentMusic.isStopped()) event.getController().setAnimation(new AnimationBuilder().addAnimation(
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

    public static void serverTick(Level level, BlockPos pos, BlockState blockState, ChristmasStarBlockEntity blockEntity) {
        if (!level.isClientSide()) {

        }
    }
}
