package com.bayobayobayo.happyholidays.common.tileentity.christmas;

import com.bayobayobayo.happyholidays.common.item.christmas.music.ChristmasMusic;
import com.bayobayobayo.happyholidays.common.item.christmas.music.SheetMusicItem;
import com.bayobayobayo.happyholidays.common.registry.TileEntityRegistry;
import com.bayobayobayo.happyholidays.common.sound.christmas.MusicBoxSound;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IClearable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MusicBoxTileEntity extends TileEntity implements ChristmasTileEntity, IClearable, IAnimatable {
    public static final String TILE_ENTITY_ID = "music_box_block";

    private ItemStack sheetMusic = ItemStack.EMPTY;
    private MusicBoxSound currentMusic = null;

    private AnimationFactory factory = new AnimationFactory(this);

    public MusicBoxTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public MusicBoxTileEntity() {
        this(TileEntityRegistry.MUSIC_BOX_ENTITY_TYPE.get());
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
        if (p_230337_2_.contains("SheetMusicItem", 10)) {
            this.setSheetMusic(ItemStack.of(p_230337_2_.getCompound("SheetMusicItem")), false);
        }
    }

    public CompoundNBT save(CompoundNBT p_189515_1_) {
        super.save(p_189515_1_);
        if (!this.getSheetMusic().isEmpty()) {
            p_189515_1_.put("SheetMusicItem", this.getSheetMusic().save(new CompoundNBT()));
        }

        return p_189515_1_;
    }

    public ItemStack getSheetMusic() {
        return this.sheetMusic;
    }

    public void setSheetMusic(ItemStack itemStack, boolean isPlayerAction) {
        this.sheetMusic = itemStack;
        this.setChanged();

        if (this.level != null && this.level.isClientSide() && !isPlayerAction) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
        }
    }

    @Override
    public void clearContent() {
        this.sheetMusic = ItemStack.EMPTY;

        if (this.level != null && this.level.isClientSide()) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
        }
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket(){
        CompoundNBT nbtTag = new CompoundNBT();

        nbtTag.putBoolean("PlayMusic", !this.getSheetMusic().isEmpty());

        if (this.sheetMusic.getItem() instanceof SheetMusicItem) {
            nbtTag.putInt("SheetMusicId", ((SheetMusicItem) this.sheetMusic.getItem()).getMusic().getId());
        }

        return new SUpdateTileEntityPacket(getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        CompoundNBT nbtTag = pkt.getTag();

        if (nbtTag.getBoolean("PlayMusic")) {
            int id = nbtTag.getInt("SheetMusicId");

            currentMusic = SheetMusicItem.createMusicBoxSound(ChristmasMusic.getMusic(id), this.worldPosition);
            Minecraft.getInstance().getSoundManager().play(currentMusic);
        } else {
            if (currentMusic != null) {
                currentMusic.stopTrack();
                currentMusic = null;
            }
        }
    }

    public void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stopTrack();
            currentMusic = null;
        }
    }

    /*
        Handle animation stuff
     */
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.currentMusic != null) event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.playing", true));
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
}
