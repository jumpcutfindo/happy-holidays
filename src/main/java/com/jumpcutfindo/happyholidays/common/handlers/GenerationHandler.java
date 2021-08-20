package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.GingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.GrinchEntity;
import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;
import com.jumpcutfindo.happyholidays.common.registry.FeatureRegistry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GenerationHandler {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handleBlockGenerationStuff(BiomeLoadingEvent event) {
        // Handle wild present generation
        if (event.getCategory() != Biome.Category.NETHER || event.getCategory() != Biome.Category.THEEND) {
            event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                    FeatureRegistry.PATCH_WILD_PRESENT);
        }
    }
    
    @SubscribeEvent
    public static void handleEntitySpawningStuff(BiomeLoadingEvent event) {
        // Register spawning biomes
        if (event.getCategory() != Biome.Category.NETHER || event.getCategory() != Biome.Category.OCEAN || event.getCategory() != Biome.Category.THEEND) {
            event.getSpawns().addSpawn(EntityClassification.CREATURE,
                    new MobSpawnInfo.Spawners(EntityRegistry.GINGERBREAD_MAN.get(),
                            GingerbreadManEntity.SPAWN_WEIGHT, GingerbreadManEntity.MIN_SPAWN_COUNT,
                            GingerbreadManEntity.MAX_SPAWN_COUNT));
        }

        if (event.getCategory() != Biome.Category.OCEAN) {
            event.getSpawns().addSpawn(EntityClassification.MONSTER,
                    new MobSpawnInfo.Spawners(EntityRegistry.GRINCH.get(),
                            GrinchEntity.SPAWN_WEIGHT, 1, 1));
        }
    }
}
