package com.bayobayobayo.happyholidays.common.registry;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadManEntity;
import com.bayobayobayo.happyholidays.common.entity.christmas.GrinchEntity;
import com.bayobayobayo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.bayobayobayo.happyholidays.common.entity.christmas.SoggyGingerbreadManEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
            ForgeRegistries.ENTITIES,
            HappyHolidaysMod.MOD_ID
    );

    public static final RegistryObject<EntityType<GingerbreadManEntity>> GINGERBREAD_MAN =
            ENTITY_TYPES.register(GingerbreadManEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(GingerbreadManEntity::new, EntityClassification.CREATURE)
                            .sized(0.8f, 2.0f)
                            .build(GingerbreadManEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<SoggyGingerbreadManEntity>> SOGGY_GINGERBREAD_MAN =
            ENTITY_TYPES.register(SoggyGingerbreadManEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(SoggyGingerbreadManEntity::new, EntityClassification.CREATURE)
                            .sized(0.8f, 2.0f)
                            .build(SoggyGingerbreadManEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<SantaElfEntity>> SANTA_ELF =
            ENTITY_TYPES.register(SantaElfEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(SantaElfEntity::new, EntityClassification.AMBIENT)
                            .sized(0.8f, 22.0f / 16.0f)
                            .build(SantaElfEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<GrinchEntity>> GRINCH =
            ENTITY_TYPES.register(GrinchEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(GrinchEntity::new, EntityClassification.AMBIENT)
                            .sized(0.5f, 34.0f / 16.0f)
                            .build(GrinchEntity.ENTITY_ID)
            );
}
