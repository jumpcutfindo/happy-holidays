package com.jumpcutfindo.happyholidays.common.container.christmas;

import java.util.Objects;

import com.jumpcutfindo.happyholidays.common.blockentity.christmas.MusicBoxBlockEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasContainers;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
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

        // Other slots

        // Main player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 189 - (4 - row) * 18 - 10));
            }
        }

        // Player hot bar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 165));
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

    @Override
    public boolean stillValid(Player player) {
        return this.playerInv.stillValid(player);
    }
}
