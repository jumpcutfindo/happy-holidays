package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasFeatures;

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
                    ChristmasFeatures.PATCH_WILD_PRESENT);
        }
    }
    
    @SubscribeEvent
    public static void handleEntitySpawningStuff(BiomeLoadingEvent event) {
        // Register spawning biomes
        if (event.getCategory() != Biome.Category.NETHER || event.getCategory() != Biome.Category.OCEAN || event.getCategory() != Biome.Category.THEEND) {
            event.getSpawns().addSpawn(EntityClassification.CREATURE,
                    new MobSpawnInfo.Spawners(ChristmasEntities.GINGERBREAD_MAN.get(),
                            GingerbreadManEntity.SPAWN_WEIGHT, GingerbreadManEntity.MIN_SPAWN_COUNT,
                            GingerbreadManEntity.MAX_SPAWN_COUNT));
        }
    }
}
