package com.bayobayobayo.happyholidays.common.entity.christmas;

import java.util.Random;

import com.bayobayobayo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.bayobayobayo.happyholidays.common.registry.EntityRegistry;

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
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class GingerbreadEntities {
    private static final int SPAWN_PROBABILITY = 300;
    private static final int MIN_SPAWN_COUNT = 2;
    private static final int MAX_SPAWN_COUNT = 5;

    public GingerbreadEntities() {
    }

    public static void createAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.GINGERBREAD_MAN.get(),
                MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .build()
        );

        event.put(EntityRegistry.SOGGY_GINGERBREAD_MAN.get(),
                MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.18D)
                .build()
        );
    }

    public static void configureEntities() {
        // Register entity rendering
        RenderingRegistry.registerEntityRenderingHandler(
                EntityRegistry.GINGERBREAD_MAN.get(),
                GingerbreadPersonEntityRenderer::new
        );

        RenderingRegistry.registerEntityRenderingHandler(
                EntityRegistry.SOGGY_GINGERBREAD_MAN.get(),
                GingerbreadPersonEntityRenderer::new
        );

        // Register spawning rules
        EntitySpawnPlacementRegistry.register(
                EntityRegistry.GINGERBREAD_MAN.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                GingerbreadEntities::checkGingerbreadSpawnRules
        );
    }

    public static void configureEntitySpawning(BiomeLoadingEvent event) {
        // Register spawning biomes
        if (event.getCategory() != Biome.Category.NETHER || event.getCategory() != Biome.Category.OCEAN || event.getCategory() != Biome.Category.THEEND) {
            event.getSpawns().addSpawn(EntityClassification.CREATURE,
                    new MobSpawnInfo.Spawners(EntityRegistry.GINGERBREAD_MAN.get(), SPAWN_PROBABILITY, MIN_SPAWN_COUNT,
                            MAX_SPAWN_COUNT));
        }
    }

    private static boolean checkGingerbreadSpawnRules(EntityType<? extends GingerbreadPersonEntity> entity, IWorld world, SpawnReason spawnReason, BlockPos pos, Random rand) {
        return world.getRawBrightness(pos,0) > 8;
    }
}
