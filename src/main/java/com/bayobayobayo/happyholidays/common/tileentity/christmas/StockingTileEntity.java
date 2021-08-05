package com.bayobayobayo.happyholidays.common.tileentity.christmas;

import com.bayobayobayo.happyholidays.common.registry.TileEntityRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class StockingTileEntity extends TileEntity implements ChristmasTileEntity, ITickableTileEntity {
    public static final String TILE_ENTITY_ID = "stocking_block";

    private ItemStack stockingItem;
    private String boundPlayerName;

    public StockingTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public StockingTileEntity() {
        this(TileEntityRegistry.STOCKING_ENTITY_TYPE.get());
    }

    @Override
    public void load(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
        super.load(p_230337_1_, p_230337_2_);
    }

    @Override
    public CompoundNBT save(CompoundNBT p_189515_1_) {
        return super.save(p_189515_1_);
    }

    @Override
    public void tick() {
        assert this.level != null;
        if (this.level.isNight()) {

        }
    }
}
