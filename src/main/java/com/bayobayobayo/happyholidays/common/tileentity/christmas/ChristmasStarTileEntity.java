package com.bayobayobayo.happyholidays.common.tileentity.christmas;

import com.bayobayobayo.happyholidays.common.registry.TileEntityRegistry;

import net.minecraft.tileentity.TileEntityType;

public class ChristmasStarTileEntity extends ChristmasTileEntity {
    public static final String TILE_ENTITY_ID = "christmas_star_block";

    public ChristmasStarTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public ChristmasStarTileEntity() {
        this(TileEntityRegistry.CHRISTMAS_STAR_ENTITY_TYPE.get());
    }
}
