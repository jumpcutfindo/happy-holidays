package com.bayobayobayo.happyholidays.common.container.christmas;

import java.util.Objects;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.registry.ContainerTypeRegistry;
import com.bayobayobayo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class ChristmasStarContainer extends Container {
    public static final String CONTAINER_ID = "christmas_star_block";

    public final ChristmasStarTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;
    private final IInventory container;

    public ChristmasStarContainer(final int windowId, final PlayerInventory playerInv,
                                  final ChristmasStarTileEntity tileEntity) {
        super(ContainerTypeRegistry.CHRISTMAS_STAR_CONTAINER.get(), windowId);

        this.tileEntity = tileEntity;
        this.container = playerInv;

        canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());
        // Tile entity
        this.addSlot(new Slot((IInventory) tileEntity, 0, 80, 35));


        // Main player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col * row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // Player hot bar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    public ChristmasStarContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, ChristmasStarContainer.getTileEntity(playerInv, data));
    }

    private static ChristmasStarTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "Player inventory cannot be null");
        Objects.requireNonNull(data, "Packet buffer cannot be null");

        final TileEntity te = playerInv.player.getCommandSenderWorld().getBlockEntity(data.readBlockPos());
        if (te instanceof ChristmasStarTileEntity) {
            return (ChristmasStarTileEntity) te;
        }

        throw new IllegalStateException("Incorrect tile entity!");
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        return this.container.stillValid(playerEntity);
    }

    // TODO: transferStackInSlot method for quick transfer item?
}
