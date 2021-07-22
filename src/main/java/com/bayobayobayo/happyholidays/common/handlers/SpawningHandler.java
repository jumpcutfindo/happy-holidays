package com.bayobayobayo.happyholidays.common.handlers;

import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadManEntity;
import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadPersonEntity;
import com.bayobayobayo.happyholidays.common.registry.EntityRegistry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class SpawningHandler {

    public static void handleEntityPlacementStuff() {
        // Register spawning rules
        EntitySpawnPlacementRegistry.register(
                EntityRegistry.GINGERBREAD_MAN.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                GingerbreadPersonEntity::checkGingerbreadSpawnRules
        );
    }

    public static void handleEntitySpawningStuff(final BiomeLoadingEvent event) {
        // Register spawning biomes
        if (event.getCategory() != Biome.Category.NETHER || event.getCategory() != Biome.Category.OCEAN || event.getCategory() != Biome.Category.THEEND) {
            event.getSpawns().addSpawn(EntityClassification.CREATURE,
                    new MobSpawnInfo.Spawners(EntityRegistry.GINGERBREAD_MAN.get(),
                            GingerbreadManEntity.SPAWN_PROBABILITY, GingerbreadManEntity.MIN_SPAWN_COUNT,
                            GingerbreadManEntity.MAX_SPAWN_COUNT));
        }
    }
}
