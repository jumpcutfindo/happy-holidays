package com.bayobayobayo.happyholidays.common.container.christmas;

import java.util.List;
import java.util.Objects;

import com.bayobayobayo.happyholidays.common.registry.ContainerTypeRegistry;
import com.bayobayobayo.happyholidays.common.tileentity.christmas.GiftWrapperTileEntity;
import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class GiftWrapperContainer extends Container {
    public static final String CONTAINER_ID = "gift_wrapper_block";

    public final GiftWrapperTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;
    private final IInventory container;

    public Slot stringSlot, paperSlot, dyeSlot, resultSlot;
    public List<Slot> giftItemSlots;

    public GiftWrapperContainer(final int windowId, final PlayerInventory playerInv,
                                final GiftWrapperTileEntity tileEntity) {
        super(ContainerTypeRegistry.GIFT_WRAPPER_CONTAINER.get(), windowId);

        this.tileEntity = tileEntity;
        this.container = playerInv;

        canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

        // Other slots
        stringSlot = this.addSlot(new Slot(tileEntity, 0, 16, 16));
        paperSlot = this.addSlot(new Slot(tileEntity, 1, 16, 41));
        dyeSlot = this.addSlot(new Slot(tileEntity, 2, 16, 66));

        giftItemSlots = Lists.newArrayList();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                giftItemSlots.add(this.addSlot(new Slot(tileEntity, 3 + (i * 3) + j, 51 + j * 18, 32 + i * 18)));
            }
        }

        resultSlot = this.addSlot(new Slot(tileEntity, 9, 140, 40));

        // Main player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 181 - (4 - row) * 18 - 10));
            }
        }

        // Player hot bar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 157));
        }
    }

    public GiftWrapperContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, GiftWrapperContainer.getTileEntity(playerInv, data));
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

    private static GiftWrapperTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "Player inventory cannot be null");
        Objects.requireNonNull(data, "Packet buffer cannot be null");

        final TileEntity te = playerInv.player.getCommandSenderWorld().getBlockEntity(data.readBlockPos());
        if (te instanceof GiftWrapperTileEntity) {
            return (GiftWrapperTileEntity) te;
        }

        throw new IllegalStateException("Incorrect tile entity!");
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerEntity, int selectedSlot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(selectedSlot);

        if (slot != null && slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();
            if (slot.equals(stringSlot) || slot.equals(paperSlot) || slot.equals(dyeSlot) || giftItemSlots.contains(slot) || slot.equals(resultSlot)) {
                if (!this.moveItemStackTo(itemStack1, 10, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (ItemStack.isSame(itemStack1, Items.STRING.getDefaultInstance())) {
                    if (!this.moveItemStackTo(itemStack1, stringSlot.getSlotIndex(), stringSlot.getSlotIndex() + 1,
                            false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (ItemStack.isSame(itemStack1, Items.PAPER.getDefaultInstance())) {
                    if (!this.moveItemStackTo(itemStack1, paperSlot.getSlotIndex(), paperSlot.getSlotIndex() + 1,
                            false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemStack1.getItem() instanceof DyeItem) {
                    if (!this.moveItemStackTo(itemStack1, dyeSlot.getSlotIndex(), dyeSlot.getSlotIndex() + 1,
                            false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.moveItemStackTo(itemStack1, giftItemSlots.get(0).getSlotIndex(), giftItemSlots.size(),
                            false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (itemStack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerEntity, itemStack1);
        }

        return ItemStack.EMPTY;
    }
}
