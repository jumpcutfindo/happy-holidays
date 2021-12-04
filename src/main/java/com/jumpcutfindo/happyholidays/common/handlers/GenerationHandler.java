package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GenerationHandler {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void handleBlockGenerationStuff(BiomeLoadingEvent event) {
        // Handle wild present generation
        if (event.getCategory() != Biome.BiomeCategory.NETHER || event.getCategory() != Biome.BiomeCategory.THEEND) {
        }
    }
    
    @SubscribeEvent
    public static void handleEntitySpawningStuff(BiomeLoadingEvent event) {
        // Register spawning biomes
        if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.OCEAN && event.getCategory() != Biome.BiomeCategory.THEEND) {
            event.getSpawns().addSpawn(MobCategory.CREATURE,
                    new MobSpawnSettings.SpawnerData(ChristmasEntities.GINGERBREAD_MAN.get(),
                            GingerbreadManEntity.SPAWN_WEIGHT, GingerbreadManEntity.MIN_SPAWN_COUNT,
                            GingerbreadManEntity.MAX_SPAWN_COUNT)
            );
        }
    }
}
