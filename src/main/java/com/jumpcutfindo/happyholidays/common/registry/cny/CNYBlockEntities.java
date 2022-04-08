package com.jumpcutfindo.happyholidays.common.registry.cny;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.entity.cny.OfferingTableBlockEntity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CNYBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<OfferingTableBlockEntity>> OFFERING_TABLE =
            registerBlockEntity("offering_table", OfferingTableBlockEntity::new, CNYBlocks.OFFERING_TABLE);

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(String id, BlockEntityType.BlockEntitySupplier<T> entitySupplier, Supplier<Block> blockSupplier) {
        return BLOCK_ENTITY.register(id, () -> BlockEntityType.Builder.of(entitySupplier, blockSupplier.get()).build(null));
    }
}
