package com.jumpcutfindo.happyholidays.common.container.christmas.star;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.ContainerTypeRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
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

    private List<Slot> ornamentSlots;
    private Slot bonusSlot;

    public ChristmasStarContainer(final int windowId, final PlayerInventory playerInv,
                                  final ChristmasStarTileEntity tileEntity) {
        super(ContainerTypeRegistry.CHRISTMAS_STAR_CONTAINER.get(), windowId);

        this.tileEntity = tileEntity;
        this.container = playerInv;

        this.data = tileEntity.dataAccess;
        this.addDataSlots(this.tileEntity.dataAccess);

        canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

        // Christmas ornament slots (5 slots)
        this.ornamentSlots = Lists.newArrayList(
                this.addSlot(new Slot(tileEntity, 0, 80, 15)),
                this.addSlot(new Slot(tileEntity, 1, 48, 43)),
                this.addSlot(new Slot(tileEntity, 2, 112, 43)),
                this.addSlot(new Slot(tileEntity, 3, 58, 77)),
                this.addSlot(new Slot(tileEntity, 4, 102, 77))
        );

        // Special slot
        this.bonusSlot = this.addSlot(new ChristmasStarBonusSlot(tileEntity, 5, 152, 77));

        // Main player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 190 - (4 - row) * 18 - 10));
            }
        }

        // Player hot bar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 166));
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

            // Checks if the items are in the slots for the Christmas star
            for (Slot ornamentSlot : ornamentSlots) {
                if (slot.equals(ornamentSlot)) {
                    if (!this.moveItemStackTo(itemstack1, 6, 42, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (ChristmasItem.isOrnamentItem(itemstack)) {
                if (!this.moveItemStackTo(itemstack1, 0, 5, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (selectedSlot < 40 && !this.moveItemStackTo(itemstack1, 4, 31, false)) {
                return ItemStack.EMPTY;
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
        return this.data.get(0);
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