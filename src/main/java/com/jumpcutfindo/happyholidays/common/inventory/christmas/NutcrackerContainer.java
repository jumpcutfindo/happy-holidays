package com.jumpcutfindo.happyholidays.common.inventory.christmas;

import java.util.concurrent.atomic.AtomicReference;

import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasContainers;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class NutcrackerContainer extends AbstractContainerMenu {

    private final Inventory playerInv;
    private final NutcrackerEntity nutcracker;

    public NutcrackerContainer(int windowId, Level level, BlockPos pos, Inventory playerInv, Player player, NutcrackerEntity nutcracker) {
        super(ChristmasContainers.NUTCRACKER_INVENTORY.get(), windowId);

        this.playerInv = playerInv;
        this.nutcracker = nutcracker;

        nutcracker.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(cap -> {
            for(int j = 0; j < 8; ++j) {
                this.addSlot(new SlotItemHandler(cap, j, 17 + j * 18, 20));
            }
        });

        for (int l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInv, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInv, i1, 8 + i1 * 18, 109));
        }
    }

    public static NutcrackerContainer createContainer(final int windowId, final Inventory playerInv, final FriendlyByteBuf data) {
        Level level = playerInv.player.getLevel();
        NutcrackerEntity nutcracker = (NutcrackerEntity) level.getEntity(data.readInt());

        return nutcracker == null ? null : new NutcrackerContainer(windowId, level, nutcracker.blockPosition(), playerInv, playerInv.player, nutcracker);
    }

    public ItemStack quickMoveStack(Player p_39651_, int p_39652_) {
        AtomicReference<ItemStack> itemStack = new AtomicReference<>(ItemStack.EMPTY);
        nutcracker.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(cap -> {
            Slot slot = this.slots.get(p_39652_);

            if (slot != null && slot.hasItem()) {
                ItemStack itemstack1 = slot.getItem();
                itemStack.set(itemstack1.copy());
                if (p_39652_ < cap.getSlots()) {
                    if (!this.moveItemStackTo(itemstack1, cap.getSlots(), this.slots.size(), true)) {
                        itemStack.set(ItemStack.EMPTY);
                    }
                } else if (!this.moveItemStackTo(itemstack1, 0, cap.getSlots(), false)) {
                    itemStack.set(ItemStack.EMPTY);
                }

                if (itemstack1.isEmpty()) {
                    slot.set(ItemStack.EMPTY);
                } else {
                    slot.setChanged();
                }
            }
        });

        return itemStack.get();
    }

    @Override
    public boolean stillValid(Player player) {
        return this.playerInv.stillValid(player);
    }

    public int getEntityId() {
        return this.nutcracker.getId();
    }
}
