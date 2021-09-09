package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadPersonEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.SoggyGingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.AngrySantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.ExplosivePresentEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.happy.HappySantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.SleighEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ChristmasEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
            ForgeRegistries.ENTITIES,
            HappyHolidaysMod.MOD_ID
    );

    public static final RegistryObject<EntityType<GingerbreadManEntity>> GINGERBREAD_MAN =
            ENTITY_TYPES.register(GingerbreadManEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(GingerbreadManEntity::new, EntityClassification.CREATURE)
                            .sized(GingerbreadPersonEntity.ENTITY_BOX_SIZE, GingerbreadPersonEntity.ENTITY_BOX_HEIGHT)
                            .build(GingerbreadManEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<SoggyGingerbreadManEntity>> SOGGY_GINGERBREAD_MAN =
            ENTITY_TYPES.register(SoggyGingerbreadManEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(SoggyGingerbreadManEntity::new, EntityClassification.CREATURE)
                            .sized(GingerbreadPersonEntity.ENTITY_BOX_SIZE, GingerbreadPersonEntity.ENTITY_BOX_HEIGHT)
                            .build(SoggyGingerbreadManEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<SantaElfEntity>> SANTA_ELF =
            ENTITY_TYPES.register(SantaElfEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(SantaElfEntity::new, EntityClassification.AMBIENT)
                            .sized(SantaElfEntity.ENTITY_BOX_SIZE, SantaElfEntity.ENTITY_BOX_HEIGHT)
                            .build(SantaElfEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<GrinchEntity>> GRINCH =
            ENTITY_TYPES.register(GrinchEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(GrinchEntity::new, EntityClassification.CREATURE)
                            .sized(GrinchEntity.ENTITY_BOX_SIZE, GrinchEntity.ENTITY_BOX_HEIGHT)
                            .build(GrinchEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<HappySantaEntity>> HAPPY_SANTA =
            ENTITY_TYPES.register(HappySantaEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(HappySantaEntity::new, EntityClassification.MISC)
                            .sized(BaseSantaEntity.ENTITY_BOX_SIZE, BaseSantaEntity.ENTITY_BOX_HEIGHT)
                            .build(HappySantaEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<AngrySantaEntity>> ANGRY_SANTA =
            ENTITY_TYPES.register(AngrySantaEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(AngrySantaEntity::new, EntityClassification.MISC)
                            .sized(BaseSantaEntity.ENTITY_BOX_SIZE, BaseSantaEntity.ENTITY_BOX_HEIGHT)
                            .build(AngrySantaEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<SleighEntity>> SLEIGH =
            ENTITY_TYPES.register(SleighEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(SleighEntity::new, EntityClassification.MISC)
                            .sized(SleighEntity.ENTITY_BOX_SIZE, SleighEntity.ENTITY_BOX_HEIGHT)
                            .build(SleighEntity.ENTITY_ID)
            );

    public static final RegistryObject<EntityType<ExplosivePresentEntity>> EXPLOSIVE_PRESENT =
            ENTITY_TYPES.register(ExplosivePresentEntity.ENTITY_ID,
                    () -> EntityType.Builder.of(ExplosivePresentEntity::new, EntityClassification.MISC)
                            .sized(ExplosivePresentEntity.ENTITY_BOX_SIZE, ExplosivePresentEntity.ENTITY_BOX_HEIGHT)
                            .clientTrackingRange(10)
                            .build(ExplosivePresentEntity.ENTITY_ID)
            );
}
