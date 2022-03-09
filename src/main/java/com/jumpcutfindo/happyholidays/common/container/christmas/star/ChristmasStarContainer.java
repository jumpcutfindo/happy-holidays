package com.jumpcutfindo.happyholidays.common.container.christmas.star;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasContainers;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarBlockEntity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ChristmasStarContainer extends AbstractContainerMenu {
    public static final String CONTAINER_ID = "christmas_star";

    public final ChristmasStarBlockEntity blockEntity;
    private final ContainerLevelAccess canInteractWithCallable;
    private final Container container;

    private final ContainerData data;

    private List<Slot> ornamentSlots;
    private Slot bonusSlot;

    public ChristmasStarContainer(final int windowId, final Inventory playerInv,
                                  final ChristmasStarBlockEntity blockEntity) {
        super(ChristmasContainers.CHRISTMAS_STAR_CONTAINER.get(), windowId);

        this.blockEntity = blockEntity;
        this.container = playerInv;

        this.data = blockEntity.dataAccess;
        this.addDataSlots(this.blockEntity.dataAccess);

        canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        // Christmas ornament slots (5 slots)
        this.ornamentSlots = Lists.newArrayList(
                this.addSlot(new ChristmasStarSlot(blockEntity, 0, 80, 15)),
                this.addSlot(new ChristmasStarSlot(blockEntity, 1, 48, 43)),
                this.addSlot(new ChristmasStarSlot(blockEntity, 2, 112, 43)),
                this.addSlot(new ChristmasStarSlot(blockEntity, 3, 58, 77)),
                this.addSlot(new ChristmasStarSlot(blockEntity, 4, 102, 77))
        );

        // Special slot
        this.bonusSlot = this.addSlot(new ChristmasStarBonusSlot(blockEntity, playerInv.player, 5, 152, 77));

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

    public ChristmasStarContainer(final int windowId, final Inventory playerInv, final FriendlyByteBuf data) {
        this(windowId, playerInv, ChristmasStarContainer.getBlockEntity(playerInv, data));
    }

    @Override
    public ItemStack quickMoveStack(Player playerEntity, int selectedSlot) {
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

            // Checks if the items are in the bonus slot
            if (slot.equals(bonusSlot)) {
                if (!this.moveItemStackTo(itemstack1, 6, 42, false)) {
                    return ItemStack.EMPTY;
                }
            }

            // Handle the remaining slots
            if (ChristmasItems.isOrnamentItem(itemstack1)) {
                if (!this.moveItemStackTo(itemstack1,this.ornamentSlots.get(0).getSlotIndex(),
                        this.ornamentSlots.get(this.ornamentSlots.size() - 1).getSlotIndex() + 1,
                        false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (ItemStack.isSame(itemstack1, ChristmasItems.ENCHANTED_SANTA_HAT.get().getDefaultInstance())) {
                if (!this.moveItemStackTo(itemstack1, this.bonusSlot.getSlotIndex(), this.bonusSlot.getSlotIndex() + 1
                        , false)) {
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
    public void removed(Player p_75134_1_) {
        super.removed(p_75134_1_);
        this.container.stopOpen(p_75134_1_);
    }

    @Override
    public boolean stillValid(Player playerEntity) {
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

    public ChristmasStarBlockEntity getBlockEntity() {
        return blockEntity;
    }

    private static ChristmasStarBlockEntity getBlockEntity(final Inventory playerInv, final FriendlyByteBuf data) {
        Objects.requireNonNull(playerInv, "Player inventory cannot be null");
        Objects.requireNonNull(data, "Packet buffer cannot be null");

        final BlockEntity te = playerInv.player.getCommandSenderWorld().getBlockEntity(data.readBlockPos());
        if (te instanceof ChristmasStarBlockEntity starBlockEntity) {
            starBlockEntity.setSantaNextSummonTime(data.readLong());
            return (ChristmasStarBlockEntity) te;
        }

        throw new IllegalStateException("Incorrect tile entity!");
    }
}
