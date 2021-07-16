package com.bayobayobayo.happyholidays.common.entity.christmas;

import java.util.Random;

import com.bayobayobayo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.bayobayobayo.happyholidays.common.RegistryHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class GingerbreadEntities implements ChristmasEntities {
    public static final String GINGERBREAD_ENTITIES_ID = "gingerbread_entities";

    private static final int SPAWN_PROBABILITY = 300;
    private static final int MIN_SPAWN_COUNT = 2;
    private static final int MAX_SPAWN_COUNT = 5;

    private RegistryObject<EntityType<GingerbreadManEntity>> gingerbreadManObject;
    private RegistryObject<EntityType<SoggyGingerbreadManEntity>> soggyGingerbreadManObject;

    public GingerbreadEntities() {
    }

    @Override
    public void createAttributes(EntityAttributeCreationEvent event) {
        event.put(gingerbreadManObject.get(),
                MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .build()
        );

        event.put(soggyGingerbreadManObject.get(),
                MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.18D)
                .build()
        );
    }

    @Override
    public void registerEntities() {
        gingerbreadManObject = RegistryHandler.ENTITY_TYPES.register(GingerbreadManEntity.ENTITY_ID,
                () -> EntityType.Builder.of(GingerbreadManEntity::new, EntityClassification.AMBIENT)
                        .sized(0.8f, 2.0f)
                        .build(GingerbreadManEntity.ENTITY_ID)
        );

        soggyGingerbreadManObject = RegistryHandler.ENTITY_TYPES.register(SoggyGingerbreadManEntity.ENTITY_ID,
                () -> EntityType.Builder.of(SoggyGingerbreadManEntity::new, EntityClassification.AMBIENT)
                        .sized(0.8f, 2.0f)
                        .build(SoggyGingerbreadManEntity.ENTITY_ID)
        );
    }

    @Override
    public void configureEntities() {
        // Register entity rendering
        RenderingRegistry.registerEntityRenderingHandler(
                gingerbreadManObject.get(),
                GingerbreadPersonEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                soggyGingerbreadManObject.get(),
                GingerbreadPersonEntityRenderer::new
        );

        // Register spawning rules
        EntitySpawnPlacementRegistry.register(
                gingerbreadManObject.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                GingerbreadEntities::checkGingerbreadSpawnRules
        );
    }

    @Override
    public void configureEntitySpawning(BiomeLoadingEvent event) {
        // Register spawning biomes
        if (event.getCategory() != Biome.Category.NETHER || event.getCategory() != Biome.Category.OCEAN || event.getCategory() != Biome.Category.THEEND) {
            event.getSpawns().addSpawn(EntityClassification.CREATURE,
                    new MobSpawnInfo.Spawners(gingerbreadManObject.get(), SPAWN_PROBABILITY, MIN_SPAWN_COUNT, MAX_SPAWN_COUNT));
        }
    }

    private static boolean checkGingerbreadSpawnRules(EntityType<? extends GingerbreadPersonEntity> entity, IWorld world, SpawnReason spawnReason, BlockPos pos, Random rand) {
        return world.getRawBrightness(pos,0) > 8;
    }

    public String getId() {
        return GINGERBREAD_ENTITIES_ID;
    }

    public RegistryObject<EntityType<GingerbreadManEntity>> getGingerbreadManObject() {
        return gingerbreadManObject;
    }

    public RegistryObject<EntityType<SoggyGingerbreadManEntity>> getSoggyGingerbreadManObject() {
        return soggyGingerbreadManObject;
    }
}