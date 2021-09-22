package com.jumpcutfindo.happyholidays.common.blockentity.christmas;

import com.jumpcutfindo.happyholidays.common.item.christmas.music.ChristmasMusic;
import com.jumpcutfindo.happyholidays.common.item.christmas.music.SheetMusicItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.sound.christmas.MusicBoxSound;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Clearable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MusicBoxBlockEntity extends BlockEntity implements ChristmasEntityBlock, Clearable, IAnimatable {
    public static final String BLOCK_ENTITY_ID = "music_box";

    private ItemStack sheetMusic = ItemStack.EMPTY;
    private MusicBoxSound currentMusic = null;

    private AnimationFactory factory = new AnimationFactory(this);

    public MusicBoxBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state) {
        super(entityType, pos, state);
    }

    public MusicBoxBlockEntity(BlockPos pos, BlockState state) {
        this(ChristmasBlockEntities.MUSIC_BOX_ENTITY_TYPE.get(), pos, state);
    }

    @Override
    public void load(CompoundTag p_230337_2_) {
        super.load(p_230337_2_);
        if (p_230337_2_.contains("SheetMusicItem", 10)) {
            this.setSheetMusic(ItemStack.of(p_230337_2_.getCompound("SheetMusicItem")), false);
        }
    }

    public CompoundTag save(CompoundTag p_189515_1_) {
        super.save(p_189515_1_);
        if (!this.getSheetMusic().isEmpty()) {
            p_189515_1_.put("SheetMusicItem", this.getSheetMusic().save(new CompoundTag()));
        }

        return p_189515_1_;
    }

    public ItemStack getSheetMusic() {
        return this.sheetMusic;
    }

    public void setSheetMusic(ItemStack itemStack, boolean isPlayerAction) {
        ItemStack itemStackCopy = itemStack.copy();
        itemStackCopy.setCount(1);

        this.sheetMusic = itemStackCopy;
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
    public ClientboundBlockEntityDataPacket getUpdatePacket(){
        CompoundTag nbtTag = new CompoundTag();

        nbtTag.putBoolean("PlayMusic", !this.getSheetMusic().isEmpty());

        if (this.sheetMusic.getItem() instanceof SheetMusicItem) {
            nbtTag.putInt("SheetMusicId", ((SheetMusicItem) this.sheetMusic.getItem()).getMusic().getId());
        }

        return new ClientboundBlockEntityDataPacket(getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt){
        CompoundTag nbtTag = pkt.getTag();

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
}
