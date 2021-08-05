package com.jumpcutfindo.happyholidays.common.container.christmas.gifts;

import java.util.List;
import java.util.Objects;

import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.ChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.registry.ContainerTypeRegistry;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.GiftWrapperTileEntity;
import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;

public class GiftWrapperContainer extends Container {
    public static final String CONTAINER_ID = "gift_wrapper_block";

    public final GiftWrapperTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    private final PlayerInventory playerInv;

    public Slot stringSlot, paperSlot, dyeSlot, resultSlot;
    public List<Slot> giftItemSlots;

    public GiftWrapperContainer(final int windowId, final PlayerInventory playerInv,
                                final GiftWrapperTileEntity tileEntity) {
        super(ContainerTypeRegistry.GIFT_WRAPPER_CONTAINER.get(), windowId);

        this.tileEntity = tileEntity;
        this.playerInv = playerInv;

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

        resultSlot = this.addSlot(new GiftWrapperResultSlot(tileEntity, 9, 140, 40));

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

    public GiftWrapperContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, GiftWrapperContainer.getTileEntity(playerInv, data));
    }

    @Override
    public void removed(PlayerEntity p_75134_1_) {
        super.removed(p_75134_1_);
        this.playerInv.stopOpen(p_75134_1_);
    }

    public static void handleSlotsChanged(int containerId, World world, PlayerEntity playerEntity,
                               IInventory playerInv, IInventory giftWrapperInv) {
        if (!world.isClientSide) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) playerEntity;
            ItemStack itemStack = ItemStack.EMPTY;

            if (checkAllItemsPresent(giftWrapperInv)) {
                itemStack = GiftWrapperTileEntity.assembleGift(playerEntity, giftWrapperInv);
            }

            giftWrapperInv.setItem(GiftWrapperTileEntity.RESULT_SLOT_INDEX, itemStack);
            giftWrapperInv.setChanged();

            serverPlayer.connection.send(new SSetSlotPacket(containerId, GiftWrapperTileEntity.RESULT_SLOT_INDEX, itemStack));
        }
    }

    public static boolean checkAllItemsPresent(IInventory giftWrapperInv) {
        ItemStack stringItems = giftWrapperInv.getItem(GiftWrapperTileEntity.STRING_SLOT_INDEX);
        ItemStack paperItems = giftWrapperInv.getItem(GiftWrapperTileEntity.PAPER_SLOT_INDEX);
        ItemStack dyeItems = giftWrapperInv.getItem(GiftWrapperTileEntity.DYE_SLOT_INDEX);

        if (ItemStack.isSame(stringItems, Items.STRING.getDefaultInstance()) && stringItems.getCount() >= 2
                && ItemStack.isSame(paperItems, Items.PAPER.getDefaultInstance()) && paperItems.getCount() >= 4
                && GiftWrapperTileEntity.isValidColourModifier(dyeItems)
        ) {
            for (int i = GiftWrapperTileEntity.GIFT_ITEMS_SLOT_INDEX_START; i <= GiftWrapperTileEntity.GIFT_ITEMS_SLOT_INDEX_END; i++) {
                if (!giftWrapperInv.getItem(i).isEmpty() && !(giftWrapperInv.getItem(i).getItem() instanceof ChristmasGiftItem)) return true;
            }
        }

        return false;
    }

    @Override
    public void slotsChanged(IInventory inventory) {
        this.canInteractWithCallable.execute((world, blockPos) -> {
            handleSlotsChanged(this.containerId, world, this.playerInv.player, this.playerInv, this.tileEntity);
        });
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        return this.playerInv.stillValid(playerEntity);
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
                } else if (GiftWrapperTileEntity.isValidColourModifier(itemStack)) {
                    if (!this.moveItemStackTo(itemStack1, dyeSlot.getSlotIndex(), dyeSlot.getSlotIndex() + 1,
                            false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.moveItemStackTo(itemStack1, 3, 3 + giftItemSlots.size(),
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
