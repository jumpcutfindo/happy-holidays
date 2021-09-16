package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.ChristmasStarTileEntity;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.GiftWrapperTileEntity;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.MusicBoxTileEntity;
import com.jumpcutfindo.happyholidays.common.tileentity.christmas.StockingTileEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ChristmasTileEntities {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPE =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ChristmasStarTileEntity>> CHRISTMAS_STAR_ENTITY_TYPE =
            TILE_ENTITY_TYPE.register(
                    ChristmasStarTileEntity.TILE_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            ChristmasStarTileEntity::new,
                            ChristmasBlocks.CHRISTMAS_STAR.get()
                    ).build(null)
            );
    public static final RegistryObject<BlockEntityType<MusicBoxTileEntity>> MUSIC_BOX_ENTITY_TYPE =
            TILE_ENTITY_TYPE.register(
                    MusicBoxTileEntity.TILE_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            MusicBoxTileEntity::new,
                            ChristmasBlocks.MUSIC_BOX.get()
                    ).build(null)
            );
    public static final RegistryObject<BlockEntityType<GiftWrapperTileEntity>> GIFT_WRAPPER_ENTITY_TYPE =
            TILE_ENTITY_TYPE.register(
                    GiftWrapperTileEntity.TILE_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            GiftWrapperTileEntity::new,
                            ChristmasBlocks.GIFT_WRAPPING_STATION.get()
                    ).build(null)
            );
    public static final RegistryObject<BlockEntityType<StockingTileEntity>> STOCKING_ENTITY_TYPE =
            TILE_ENTITY_TYPE.register(
                    StockingTileEntity.TILE_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            StockingTileEntity::new,
                            ChristmasBlocks.RED_STOCKING.get()
                    ).build(null)
            );

}
