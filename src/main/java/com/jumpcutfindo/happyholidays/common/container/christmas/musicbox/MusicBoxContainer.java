package com.jumpcutfindo.happyholidays.common.container.christmas.musicbox;

import java.util.Objects;

import com.jumpcutfindo.happyholidays.common.block.entity.christmas.MusicBoxBlockEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasContainers;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class MusicBoxContainer extends AbstractContainerMenu {
    public static final String CONTAINER_ID = "music_box";

    public final MusicBoxBlockEntity blockEntity;
    private final ContainerLevelAccess canInteractWithCallable;

    private final Inventory playerInv;

    public MusicBoxContainer(final int windowId, final Inventory playerInv,
                                final MusicBoxBlockEntity blockEntity) {
        super(ChristmasContainers.MUSIC_BOX_CONTAINER.get(), windowId);

        this.blockEntity = blockEntity;
        this.playerInv = playerInv;

        canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        int i = 3;
        int j = 9;

        for(int k = 0; k < 3; ++k) {
            for(int l = 0; l < 9; ++l) {
                this.addSlot(new MusicBoxSlot(blockEntity, l + k * 9, 8 + l * 18, 18 + k * 18));
            }
        }

        for(int i1 = 0; i1 < 3; ++i1) {
            for(int k1 = 0; k1 < 9; ++k1) {
                this.addSlot(new Slot(playerInv, k1 + i1 * 9 + 9, 8 + k1 * 18, 102 + i1 * 18));
            }
        }

        for(int j1 = 0; j1 < 9; ++j1) {
            this.addSlot(new Slot(playerInv, j1, 8 + j1 * 18, 160));
        }
    }

    public MusicBoxContainer(final int windowId, final Inventory playerInv, final FriendlyByteBuf data) {
        this(windowId, playerInv, MusicBoxContainer.getBlockEntity(playerInv, data));
    }

    private static MusicBoxBlockEntity getBlockEntity(final Inventory playerInv, final FriendlyByteBuf data) {
        Objects.requireNonNull(playerInv, "Player inventory cannot be null");
        Objects.requireNonNull(data, "Packet buffer cannot be null");

        final BlockEntity te = playerInv.player.getCommandSenderWorld().getBlockEntity(data.readBlockPos());
        if (te instanceof MusicBoxBlockEntity) {
            return (MusicBoxBlockEntity) te;
        }

        throw new IllegalStateException("Incorrect tile entity!");
    }

    public ItemStack quickMoveStack(Player p_40199_, int p_40200_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_40200_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_40200_ < this.blockEntity.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.blockEntity.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.blockEntity.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public void removed(Player p_40197_) {
        super.removed(p_40197_);
        this.playerInv.stopOpen(p_40197_);
    }

    public boolean isLooping() {
        return blockEntity.isLooping();
    }

    public boolean isPlaying() {
        return blockEntity.isPlaying();
    }

    public int getSelectedSlot() {
        return blockEntity.getSelectedSlot();
    }

    @Override
    public boolean stillValid(Player player) {
        return this.playerInv.stillValid(player);
    }
}
