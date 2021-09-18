package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.star.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.GiftWrapperBlockEntity;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.MusicBoxBlockEntity;
import com.jumpcutfindo.happyholidays.common.blockentity.christmas.StockingBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ChristmasBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ChristmasStarBlockEntity>> CHRISTMAS_STAR_ENTITY_TYPE =
            BLOCK_ENTITY_TYPE.register(
                    ChristmasStarBlockEntity.BLOCK_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            ChristmasStarBlockEntity::new,
                            ChristmasBlocks.CHRISTMAS_STAR.get()
                    ).build(null)
            );
    public static final RegistryObject<BlockEntityType<MusicBoxBlockEntity>> MUSIC_BOX_ENTITY_TYPE =
            BLOCK_ENTITY_TYPE.register(
                    MusicBoxBlockEntity.BLOCK_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            MusicBoxBlockEntity::new,
                            ChristmasBlocks.MUSIC_BOX.get()
                    ).build(null)
            );
    public static final RegistryObject<BlockEntityType<GiftWrapperBlockEntity>> GIFT_WRAPPER_ENTITY_TYPE =
            BLOCK_ENTITY_TYPE.register(
                    GiftWrapperBlockEntity.BLOCK_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            GiftWrapperBlockEntity::new,
                            ChristmasBlocks.GIFT_WRAPPING_STATION.get()
                    ).build(null)
            );
    public static final RegistryObject<BlockEntityType<StockingBlockEntity>> STOCKING_ENTITY_TYPE =
            BLOCK_ENTITY_TYPE.register(
                    StockingBlockEntity.BLOCK_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            StockingBlockEntity::new,
                            ChristmasBlocks.RED_STOCKING.get()
                    ).build(null)
            );

}
