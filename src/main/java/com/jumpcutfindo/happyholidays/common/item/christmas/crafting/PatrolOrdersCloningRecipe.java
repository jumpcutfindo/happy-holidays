package com.jumpcutfindo.happyholidays.common.item.christmas.crafting;

import com.jumpcutfindo.happyholidays.common.item.christmas.PatrolOrdersItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasRecipes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class PatrolOrdersCloningRecipe extends CustomRecipe {
    public PatrolOrdersCloningRecipe(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        int i = 0;
        ItemStack copyablePatrolOrders = ItemStack.EMPTY;

        for(int j = 0; j < container.getContainerSize(); ++j) {
            ItemStack currStack = container.getItem(j);
            if (!currStack.isEmpty()) {
                if (currStack.is(ChristmasItems.PATROL_ORDERS.get()) && PatrolOrdersItem.isCompletedPatrolOrders(currStack)) {
                    if (!copyablePatrolOrders.isEmpty()) {
                        return false;
                    }

                    copyablePatrolOrders = currStack;
                } else {
                    if (!currStack.is(ChristmasItems.PATROL_ORDERS.get()) || PatrolOrdersItem.isCompletedPatrolOrders(currStack)) {
                        return false;
                    }

                    ++i;
                }
            }
        }

        return !copyablePatrolOrders.isEmpty() && i > 0;
    }

    @Override
    public ItemStack assemble(CraftingContainer container) {
        int i = 0;
        ItemStack copyablePatrolOrders = ItemStack.EMPTY;

        for(int j = 0; j < container.getContainerSize(); ++j) {
            ItemStack currStack = container.getItem(j);
            if (!currStack.isEmpty()) {
                if (currStack.is(ChristmasItems.PATROL_ORDERS.get()) && PatrolOrdersItem.isCompletedPatrolOrders(currStack)) {
                    if (!copyablePatrolOrders.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    copyablePatrolOrders = currStack;
                } else {
                    if (!currStack.is(ChristmasItems.PATROL_ORDERS.get()) || PatrolOrdersItem.isCompletedPatrolOrders(currStack)) {
                        return ItemStack.EMPTY;
                    }

                    ++i;
                }
            }
        }

        if (!copyablePatrolOrders.isEmpty() && i >= 1) {
            ItemStack itemstack2 = copyablePatrolOrders.copy();
            itemstack2.setCount(i + 1);
            return itemstack2;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ChristmasRecipes.PATROL_ORDERS_CLONING.get();
    }
}
