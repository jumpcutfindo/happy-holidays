package com.bayobayobayo.happyholidays.common.registry;

import com.bayobayobayo.happyholidays.HappyHolidaysMod;
import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadManEntity;
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
                    () -> EntityType.Builder.of(GingerbreadManEntity::new, EntityClassification.AMBIENT)
                            .sized(0.8f, 2.0f)
                            .build(GingerbreadManEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<SoggyGingerbreadManEntity>> SOGGY_GINGERBREAD_MAN =
            ENTITY_TYPES.register(SoggyGingerbreadManEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(SoggyGingerbreadManEntity::new, EntityClassification.AMBIENT)
                            .sized(0.8f, 2.0f)
                            .build(SoggyGingerbreadManEntity.ENTITY_ID)
            );
}
