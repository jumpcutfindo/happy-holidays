package com.bayobayobayo.happyholidays.common.tileentity.christmas;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
import com.bayobayobayo.happyholidays.common.container.christmas.ChristmasStarContainer;
import com.bayobayobayo.happyholidays.common.item.christmas.ChristmasItem;
import com.bayobayobayo.happyholidays.common.registry.TileEntityRegistry;

import net.minecraft.block.BeaconBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.BeaconTileEntity;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ChristmasStarTileEntity extends LockableTileEntity implements ChristmasTileEntity, ITickableTileEntity {
    public static final String TILE_ENTITY_ID = "christmas_star_block";
    public static final int SLOTS = 4;

    public static final int MAX_POINTS = 100;

    private int currentTier;
    private int currentPoints;

    protected final IIntArray dataAccess = new IIntArray() {
        @Override
        public int get(int code) {
            switch (code) {
            case 0:
                return ChristmasStarTileEntity.this.currentTier;
            case 1:
                return ChristmasStarTileEntity.this.currentPoints;
            default:
                return 0;
            }
        }

        @Override
        public void set(int code, int value) {
            switch (code) {
            case 0:
                ChristmasStarTileEntity.this.currentTier = value;
                break;
            case 1:
                ChristmasStarTileEntity.this.currentPoints = value;
                break;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

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
    public int getContainerSize() {
        return SLOTS;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int start, int end) {
        System.out.printf("Christmas Points: %d | Christmas Tier: %d%n", this.currentPoints, this.currentTier);
        return ItemStackHelper.removeItem(this.items, start, end);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.items.set(index, itemStack);
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return playerEntity.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void load(BlockState blockState, CompoundNBT nbt) {
        super.load(blockState, nbt);

        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.items);

        this.currentTier = nbt.getInt("CurrentTier");
        this.currentPoints = nbt.getInt("CurrentPoints");
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        nbt.putInt("CurrentTier", this.currentTier);
        nbt.putInt("CurrentPoints", this.currentPoints);

        return nbt;
    }

    @Override
    public void tick() {
        updatePoints();
    }

    /**
     * Updates points and tiers. Items are split into a few tiers:
     * 0. Trash tier - any basic item gives zero points.
     * 1. Basic tier - any basic Christmas items / ornaments / blocks will give 5 points each
     * 2. Pro tier - any rare Christmas ornaments
     *
     * Uniqueness: +20 points
     */
    private void updatePoints() {
        // Checks basic item points
        int newPoints = 0;
        for (ItemStack itemStack : items) {
            if (itemStack.getItem() instanceof ChristmasItem) newPoints += 5;
            else if (itemStack.getItem() instanceof BlockItem) {
                if (((BlockItem) itemStack.getItem()).getBlock() instanceof ChristmasBlock) newPoints += 5;
            }
        }

        // Checks uniqueness
        newPoints += items.stream().map(ItemStack::getItem).distinct().count() == SLOTS ? 20 : 0;

        this.currentPoints = Math.min(newPoints, 100);

        // Update tier
        if (this.currentPoints <= 20) {
            this.currentTier = 0;
        } else if (this.currentPoints <= 40) {
            this.currentTier = 1;
        } else if (this.currentPoints <= 60) {
            this.currentTier = 2;
        } else if (this.currentPoints <= 80) {
            this.currentTier = 3;
        } else {
            this.currentTier = 4;
        }
    }
}
