package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.function.Supplier;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.elf.SantaElfEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadPersonEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.SoggyGingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.WalnutEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.AngrySantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.ExplosivePresentEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry.SleighEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.happy.HappySantaEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(
            ForgeRegistries.ENTITIES,
            HappyHolidaysMod.MOD_ID
    );

    public static final RegistryObject<EntityType<GingerbreadManEntity>> GINGERBREAD_MAN =
            registerEntity("gingerbread_man",
                    () -> EntityType.Builder.of(GingerbreadManEntity::new, MobCategory.CREATURE)
                            .sized(GingerbreadPersonEntity.ENTITY_BOX_SIZE, GingerbreadPersonEntity.ENTITY_BOX_HEIGHT)
                            .build("gingerbread_man")
            );

    public static final RegistryObject<EntityType<SoggyGingerbreadManEntity>> SOGGY_GINGERBREAD_MAN =
            registerEntity("soggy_gingerbread_man",
                    () -> EntityType.Builder.of(SoggyGingerbreadManEntity::new, MobCategory.CREATURE)
                            .sized(GingerbreadPersonEntity.ENTITY_BOX_SIZE, GingerbreadPersonEntity.ENTITY_BOX_HEIGHT)
                            .build("soggy_gingerbread_man")
            );

    public static final RegistryObject<EntityType<SantaElfEntity>> SANTA_ELF =
            registerEntity("santa_elf",
                    () -> EntityType.Builder.of(SantaElfEntity::new, MobCategory.MISC)
                            .sized(SantaElfEntity.ENTITY_BOX_SIZE, SantaElfEntity.ENTITY_BOX_HEIGHT)
                            .build("santa_elf")
            );

    public static final RegistryObject<EntityType<GrinchEntity>> GRINCH =
            registerEntity("grinch",
                    () -> EntityType.Builder.of(GrinchEntity::new, MobCategory.MISC)
                            .sized(GrinchEntity.ENTITY_BOX_SIZE, GrinchEntity.ENTITY_BOX_HEIGHT)
                            .build("grinch")
            );

    public static final RegistryObject<EntityType<HappySantaEntity>> HAPPY_SANTA =
            registerEntity("happy_santa",
                    () -> EntityType.Builder.of(HappySantaEntity::new, MobCategory.MISC)
                            .sized(BaseSantaEntity.ENTITY_BOX_SIZE, BaseSantaEntity.ENTITY_BOX_HEIGHT)
                            .build("happy_santa")
            );

    public static final RegistryObject<EntityType<AngrySantaEntity>> ANGRY_SANTA =
            registerEntity("angry_santa",
                    () -> EntityType.Builder.of(AngrySantaEntity::new, MobCategory.MISC)
                            .sized(BaseSantaEntity.ENTITY_BOX_SIZE, BaseSantaEntity.ENTITY_BOX_HEIGHT)
                            .build("angry_santa")
            );

    public static final RegistryObject<EntityType<NutcrackerEntity>> NUTCRACKER =
            registerEntity("nutcracker",
                    () -> EntityType.Builder.of(NutcrackerEntity::new, MobCategory.CREATURE)
                            .sized(NutcrackerEntity.ENTITY_BOX_SIZE, NutcrackerEntity.ENTITY_BOX_HEIGHT)
                            .build("nutcracker")
            );

    public static final RegistryObject<EntityType<WalnutEntity>> WALNUT =
            registerEntity("walnut",
                    () -> EntityType.Builder.of(WalnutEntity::new, MobCategory.MISC)
                            .sized(WalnutEntity.ENTITY_BOX_SIZE, WalnutEntity.ENTITY_BOX_HEIGHT)
                            .build("walnut")
            );

    public static final RegistryObject<EntityType<SleighEntity>> SLEIGH =
            registerEntity("sleigh",
                    () -> EntityType.Builder.of(SleighEntity::new, MobCategory.MISC)
                            .sized(SleighEntity.ENTITY_BOX_SIZE, SleighEntity.ENTITY_BOX_HEIGHT)
                            .build("sleigh")
            );

    public static final RegistryObject<EntityType<ExplosivePresentEntity>> EXPLOSIVE_PRESENT =
            registerEntity("explosive_present",
                    () -> EntityType.Builder.of(ExplosivePresentEntity::new, MobCategory.MISC)
                            .sized(ExplosivePresentEntity.ENTITY_BOX_SIZE, ExplosivePresentEntity.ENTITY_BOX_HEIGHT)
                            .clientTrackingRange(10)
                            .build("explosive_present")
            );

    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String entityId, Supplier<? extends EntityType<T>> entitySupplier) {
        return ENTITY_TYPES.register(entityId, entitySupplier);
    }
}
