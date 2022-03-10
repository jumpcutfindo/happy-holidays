package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.GiftWrapperBlockEntity;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.MusicBoxBlockEntity;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.StockingBlockEntity;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ChristmasStarBlockEntity>> CHRISTMAS_STAR =
            BLOCK_ENTITY.register(
                    ChristmasStarBlockEntity.BLOCK_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            ChristmasStarBlockEntity::new,
                            ChristmasBlocks.CHRISTMAS_STAR.get()
                    ).build(null)
            );
    public static final RegistryObject<BlockEntityType<MusicBoxBlockEntity>> MUSIC_BOX =
            BLOCK_ENTITY.register(
                    MusicBoxBlockEntity.BLOCK_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            MusicBoxBlockEntity::new,
                            ChristmasBlocks.MUSIC_BOX.get()
                    ).build(null)
            );
    public static final RegistryObject<BlockEntityType<GiftWrapperBlockEntity>> GIFT_WRAPPER =
            BLOCK_ENTITY.register(
                    GiftWrapperBlockEntity.BLOCK_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            GiftWrapperBlockEntity::new,
                            ChristmasBlocks.GIFT_WRAPPING_STATION.get()
                    ).build(null)
            );
    public static final RegistryObject<BlockEntityType<StockingBlockEntity>> STOCKING =
            BLOCK_ENTITY.register(
                    StockingBlockEntity.BLOCK_ENTITY_ID,
                    () -> BlockEntityType.Builder.of(
                            StockingBlockEntity::new,
                            ChristmasBlocks.RED_STOCKING.get()
                    ).build(null)
            );

}
