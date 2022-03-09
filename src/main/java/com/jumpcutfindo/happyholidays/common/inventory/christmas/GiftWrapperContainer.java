package com.jumpcutfindo.happyholidays.common.inventory.christmas;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.common.item.christmas.gifts.ChristmasGiftItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasContainers;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.GiftWrapperBlockEntity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class GiftWrapperContainer extends AbstractContainerMenu {
    public static final String CONTAINER_ID = "gift_wrapping_station";

    public final GiftWrapperBlockEntity blockEntity;
    private final ContainerLevelAccess canInteractWithCallable;

    private final Inventory playerInv;

    public Slot stringSlot, paperSlot, dyeSlot, resultSlot;
    public List<Slot> giftItemSlots;

    public GiftWrapperContainer(final int windowId, final Inventory playerInv,
                                final GiftWrapperBlockEntity blockEntity) {
        super(ChristmasContainers.GIFT_WRAPPER_CONTAINER.get(), windowId);

        this.blockEntity = blockEntity;
        this.playerInv = playerInv;

        canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        // Other slots
        stringSlot = this.addSlot(new Slot(blockEntity, 0, 16, 16));
        paperSlot = this.addSlot(new Slot(blockEntity, 1, 16, 41));
        dyeSlot = this.addSlot(new Slot(blockEntity, 2, 16, 66));

        giftItemSlots = Lists.newArrayList();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                giftItemSlots.add(this.addSlot(new Slot(blockEntity, 3 + (i * 3) + j, 51 + j * 18, 32 + i * 18)));
            }
        }

        resultSlot = this.addSlot(new GiftWrapperResultSlot(blockEntity, 9, 140, 40));

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

    public GiftWrapperContainer(final int windowId, final Inventory playerInv, final FriendlyByteBuf data) {
        this(windowId, playerInv, GiftWrapperContainer.getBlockEntity(playerInv, data));
    }

    @Override
    public void removed(Player p_75134_1_) {
        super.removed(p_75134_1_);
        this.playerInv.stopOpen(p_75134_1_);
    }

    public static void handleSlotsChanged(int containerId, int stateId, Level world, Player playerEntity,
                               Container playerInv, Container giftWrapperInv) {
        if (!world.isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) playerEntity;
            ItemStack itemStack = ItemStack.EMPTY;

            if (checkAllItemsPresent(giftWrapperInv)) {
                itemStack = GiftWrapperBlockEntity.assembleGift(playerEntity, giftWrapperInv);
            }

            giftWrapperInv.setItem(GiftWrapperBlockEntity.RESULT_SLOT_INDEX, itemStack);
            giftWrapperInv.setChanged();

            serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(containerId, stateId,
                    GiftWrapperBlockEntity.RESULT_SLOT_INDEX, itemStack));
        }
    }

    public static boolean checkAllItemsPresent(Container giftWrapperInv) {
        ItemStack stringItems = giftWrapperInv.getItem(GiftWrapperBlockEntity.STRING_SLOT_INDEX);
        ItemStack paperItems = giftWrapperInv.getItem(GiftWrapperBlockEntity.PAPER_SLOT_INDEX);
        ItemStack dyeItems = giftWrapperInv.getItem(GiftWrapperBlockEntity.DYE_SLOT_INDEX);

        if (ItemStack.isSame(stringItems, Items.STRING.getDefaultInstance()) && stringItems.getCount() >= 2
                && ItemStack.isSame(paperItems, Items.PAPER.getDefaultInstance()) && paperItems.getCount() >= 4
                && GiftWrapperBlockEntity.isValidColourModifier(dyeItems)
        ) {
            for (int i = GiftWrapperBlockEntity.GIFT_ITEMS_SLOT_INDEX_START; i <= GiftWrapperBlockEntity.GIFT_ITEMS_SLOT_INDEX_END; i++) {
                if (!giftWrapperInv.getItem(i).isEmpty() && !(giftWrapperInv.getItem(i).getItem() instanceof ChristmasGiftItem)) return true;
            }
        }

        return false;
    }

    @Override
    public void slotsChanged(Container inventory) {
        this.canInteractWithCallable.execute((world, blockPos) -> {
            handleSlotsChanged(this.containerId, this.getStateId(), world, this.playerInv.player, this.playerInv,
                    this.blockEntity);
        });
    }

    @Override
    public boolean stillValid(Player playerEntity) {
        return this.playerInv.stillValid(playerEntity);
    }

    private static GiftWrapperBlockEntity getBlockEntity(final Inventory playerInv, final FriendlyByteBuf data) {
        Objects.requireNonNull(playerInv, "Player inventory cannot be null");
        Objects.requireNonNull(data, "Packet buffer cannot be null");

        final BlockEntity te = playerInv.player.getCommandSenderWorld().getBlockEntity(data.readBlockPos());
        if (te instanceof GiftWrapperBlockEntity) {
            return (GiftWrapperBlockEntity) te;
        }

        throw new IllegalStateException("Incorrect tile entity!");
    }

    @Override
    public ItemStack quickMoveStack(Player playerEntity, int selectedSlot) {
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
                } else if (GiftWrapperBlockEntity.isValidColourModifier(itemStack)) {
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
