package com.bayobayobayo.happyholidays.common.registry;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.block.christmas.misc.ChristmasStarBlock;
import com.bayobayobayo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;
import com.bayobayobayo.happyholidays.common.tileentity.christmas.ChristmasTileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityRegistry {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<TileEntityType<ChristmasStarTileEntity>> CHRISTMAS_STAR_ENTITY_TYPE =
            TILE_ENTITY_TYPE.register(
                    ChristmasStarTileEntity.TILE_ENTITY_ID,
                    () -> TileEntityType.Builder.of(
                            ChristmasStarTileEntity::new,
                            BlockRegistry.CHRISTMAS_STAR_BLOCK.get()
                    ).build(null)
            );
}
