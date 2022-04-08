package com.jumpcutfindo.happyholidays.common.block.entity.cny;

import com.jumpcutfindo.happyholidays.common.registry.cny.CNYBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class OfferingTableBlockEntity extends BlockEntity {

    private ItemStack itemStack = ItemStack.EMPTY;

    public OfferingTableBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public OfferingTableBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(CNYBlockEntities.OFFERING_TABLE.get(), blockPos, blockState);
    }

    public void setItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItem() {
        return this.itemStack;
    }

    public ItemStack removeItem() {
        ItemStack temp = itemStack.copy();
        this.itemStack = ItemStack.EMPTY;

        return temp;
    }

    public boolean hasItem() {
        return !this.itemStack.isEmpty();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState blockState, OfferingTableBlockEntity blockEntity) {

    }
}
