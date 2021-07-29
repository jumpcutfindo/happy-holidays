package com.bayobayobayo.happyholidays.common.container.christmas;

import java.util.Objects;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.registry.ContainerTypeRegistry;
import com.bayobayobayo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChristmasStarContainer extends Container {
    public static final String CONTAINER_ID = "christmas_star_block";

    public final ChristmasStarTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;
    private final IInventory container;

    private final IIntArray data;

    public ChristmasStarContainer(final int windowId, final PlayerInventory playerInv,
                                  final ChristmasStarTileEntity tileEntity) {
        super(ContainerTypeRegistry.CHRISTMAS_STAR_CONTAINER.get(), windowId);

        this.tileEntity = tileEntity;
        this.container = playerInv;

        this.data = tileEntity.dataAccess;
        this.addDataSlots(this.tileEntity.dataAccess);

        canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());
        // Christmas block slots
        float spacing = ((176 - (18 * (float) ChristmasStarTileEntity.SLOTS)) / (float) (ChristmasStarTileEntity.SLOTS + 1));
        for (int i = 0; i < ChristmasStarTileEntity.SLOTS; i++) {
            this.addSlot(new Slot(tileEntity, i, (int) (spacing + (18 + spacing) * i), 25));
        }

        // Main player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
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

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerEntity, int selectedSlot) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(selectedSlot);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            // Checks if the items are in the slots for the christmas star
            if (selectedSlot == 0 || selectedSlot == 1 || selectedSlot == 2 || selectedSlot == 3) {
                if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 4, false)) {
                if (selectedSlot < 31) {
                    if (!this.moveItemStackTo(itemstack1, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (selectedSlot < 40 && !this.moveItemStackTo(itemstack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerEntity, itemstack1);
        }

        return itemstack;
    }

    @Override
    public void removed(PlayerEntity p_75134_1_) {
        super.removed(p_75134_1_);
        this.container.stopOpen(p_75134_1_);
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        return this.container.stillValid(playerEntity);
    }

    @OnlyIn(Dist.CLIENT)
    public int getCurrentPoints() {
        return this.data.get(1);
    }

    @OnlyIn(Dist.CLIENT)
    public int getCurrentTier() {
        return this.data.get(0) + 1;
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
}
