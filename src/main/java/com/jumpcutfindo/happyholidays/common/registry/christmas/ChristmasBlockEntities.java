package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.ChristmasStarBlockEntity;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.GiftWrapperBlockEntity;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.MusicBoxBlockEntity;
import com.jumpcutfindo.happyholidays.common.block.entity.christmas.StockingBlockEntity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ChristmasStarBlockEntity>> CHRISTMAS_STAR =
            registerBlockEntity("christmas_star", ChristmasStarBlockEntity::new, ChristmasBlocks.CHRISTMAS_STAR);

    public static final RegistryObject<BlockEntityType<MusicBoxBlockEntity>> MUSIC_BOX =
            registerBlockEntity("music_box", MusicBoxBlockEntity::new, ChristmasBlocks.MUSIC_BOX);

    public static final RegistryObject<BlockEntityType<GiftWrapperBlockEntity>> GIFT_WRAPPER =
            registerBlockEntity("gift_wrapping_station", GiftWrapperBlockEntity::new, ChristmasBlocks.GIFT_WRAPPING_STATION);

    public static final RegistryObject<BlockEntityType<StockingBlockEntity>> STOCKING =
            registerBlockEntity("stocking", StockingBlockEntity::new, ChristmasBlocks.RED_STOCKING);

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(String id, BlockEntityType.BlockEntitySupplier<T> entitySupplier, Supplier<Block> blockSupplier) {
        return BLOCK_ENTITY.register(id, () -> BlockEntityType.Builder.of(entitySupplier, blockSupplier.get()).build(null));
    }
}
