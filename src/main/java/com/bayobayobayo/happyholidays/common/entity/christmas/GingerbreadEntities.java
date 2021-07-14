package com.bayobayobayo.happyholidays.common.entity.christmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bayobayobayo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.bayobayobayo.happyholidays.common.RegistryHandler;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.PatrollerEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class GingerbreadEntities implements ChristmasEntities {
    private static final int SPAWN_PROBABILITY = 300;
    private static final int MIN_SPAWN_COUNT = 2;
    private static final int MAX_SPAWN_COUNT = 5;

    private RegistryObject<EntityType<GingerbreadManEntity>> gingerbreadManObject;

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
    }

    @Override
    public void registerEntities() {
        gingerbreadManObject = RegistryHandler.ENTITY_TYPES.register(GingerbreadManEntity.ENTITY_ID,
                () -> EntityType.Builder.of(GingerbreadManEntity::new, EntityClassification.AMBIENT)
                        .sized(0.8f, 2.0f)
                        .build(GingerbreadManEntity.ENTITY_ID)
        );
    }

    @Override
    public void configureEntities() {
        // Register entity rendering
        RenderingRegistry.registerEntityRenderingHandler(
                gingerbreadManObject.get(),
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
}
