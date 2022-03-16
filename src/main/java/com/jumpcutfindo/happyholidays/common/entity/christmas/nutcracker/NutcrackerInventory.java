package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.item.christmas.WalnutAmmo;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.tags.christmas.ChristmasTags;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class NutcrackerInventory extends ItemStackHandler {
    public ItemStack patrolOrders = ItemStack.EMPTY;

    public static final int SLOT_COUNT = 8;

    public NutcrackerInventory() {
        super(SLOT_COUNT);
    }

    @Override
    public int getSlots() {
        return SLOT_COUNT;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return stack.is(ChristmasTags.Items.NUTCRACKER_CARRIABLES) || stack.getItem() instanceof ArmorItem;
    }

    public WalnutAmmo getCurrentAmmo() {
        ItemStack itemStack = this.getFirstStack();

        if (itemStack.is(ChristmasItems.EXPLOSIVE_WALNUT.get())) {
            return WalnutAmmo.EXPLOSIVE;
        } else if (itemStack.is(ChristmasItems.SUGARED_WALNUT.get())) {
            return WalnutAmmo.SUGARED;
        } else if (itemStack.is(ChristmasItems.METALLIC_WALNUT.get())) {
            return WalnutAmmo.METALLIC;
        } else if (itemStack.is(ChristmasItems.HALVED_WALNUT.get())) {
            return WalnutAmmo.HALVED;
        }

        return WalnutAmmo.PLAIN;
    }

    public ItemStack getFirstStack() {
        for (ItemStack stack : this.stacks) {
            if (!stack.isEmpty()) return stack;
        }

        return ItemStack.EMPTY;
    }

    public int getFirstFilledSlot() {
        for (int i = 0; i < this.stacks.size(); i++) {
            if (!this.stacks.get(i).isEmpty()) return i;
        }

        return -1;
    }

    public void useAmmo() {
        int slot = this.getFirstFilledSlot();
        if (slot == -1) return;

        this.extractItem(slot, 1, false);
    }

    public ItemStack getPatrolOrders() {
        return this.patrolOrders;
    }

    public void setPatrolOrders(ItemStack itemStack) {
        this.patrolOrders = itemStack;
    }

    public ItemStack popPatrolOrders() {
        return this.patrolOrders;
    }

    public boolean hasPatrolOrders() {
        return !this.patrolOrders.isEmpty() && this.patrolOrders.is(ChristmasItems.PATROL_ORDERS.get());
    }

    public List<ItemStack> getAllItems() {
        return this.stacks;
    }

    public int getArmorValue() {
        int value = 0;

        for (ItemStack itemStack : this.getAllItems()) {
            if (itemStack.getItem() instanceof ArmorItem armorItem) value += armorItem.getDefense();
        }

        return value;
    }

    public boolean hasSpecialWalnuts() {
        for (ItemStack itemStack : this.stacks) {
            if (itemStack.is(ChristmasTags.Items.SPECIAL_WALNUTS)) return true;
        }

        return false;
    }

    public boolean hasArmor() {
        for (ItemStack itemStack : this.stacks) {
            if (itemStack.getItem() instanceof ArmorItem) return true;
        }

        return false;
    }

    public boolean isFullOf(Item item) {
        for (ItemStack itemStack : this.stacks) {
            if (itemStack.is(item) && itemStack.getMaxStackSize() == itemStack.getCount()) continue;
            else return false;
        }

        return true;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = super.serializeNBT();

        if (!this.patrolOrders.isEmpty()) tag.put("PatrolOrders", patrolOrders.serializeNBT());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);

        if (nbt.contains("PatrolOrders")) {
            this.patrolOrders = ChristmasItems.PATROL_ORDERS.get().getDefaultInstance();
            this.patrolOrders.deserializeNBT(nbt.getCompound("PatrolOrders"));
        }
    }
}
