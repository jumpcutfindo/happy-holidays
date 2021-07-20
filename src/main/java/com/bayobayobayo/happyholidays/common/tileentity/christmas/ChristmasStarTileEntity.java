package com.bayobayobayo.happyholidays.common.tileentity.christmas;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.container.christmas.ChristmasStarContainer;
import com.bayobayobayo.happyholidays.common.registry.TileEntityRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ChristmasStarTileEntity extends LockableLootTileEntity implements ChristmasTileEntity  {
    public static final String TILE_ENTITY_ID = "christmas_star_block";

    private static int SLOTS = 1;

    protected NonNullList<ItemStack> items = NonNullList.withSize(SLOTS, ItemStack.EMPTY);

    public ChristmasStarTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public ChristmasStarTileEntity() {
        this(TileEntityRegistry.CHRISTMAS_STAR_ENTITY_TYPE.get());
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + HappyHolidaysMod.MOD_ID + "." + TILE_ENTITY_ID);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInv) {
        return new ChristmasStarContainer(id, playerInv, this);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }


    @Override
    public int getContainerSize() {
        return SLOTS;
    }

    @Override
    public void load(BlockState blockState, CompoundNBT nbt) {
        super.load(blockState, nbt);

        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        // TODO: Check loot and read?
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        return super.save(nbt);
        // TODO: Try save?
    }
}
