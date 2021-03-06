package com.jumpcutfindo.happyholidays.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadManEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.NutcrackerEntity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasPlacements;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NaturalEventHandler {
    @SubscribeEvent
    public static void registerFeatures(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
        });
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onAddingPlacements(BiomeLoadingEvent event) {
        // Handle wild present generation
        if (isOverworld(event.getCategory())) {
            if (isCold(event.getCategory())) {
                event.getGeneration().addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, ChristmasPlacements.PATCH_WILD_PRESENTS_COLD);
            } else {
                event.getGeneration().addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, ChristmasPlacements.PATCH_WILD_PRESENTS);
            }
        }
    }
    
    @SubscribeEvent
    public static void onEntitySpawning(BiomeLoadingEvent event) {
        // Register spawning biomes
        if (event.getCategory() != Biome.BiomeCategory.NETHER
                && event.getCategory() != Biome.BiomeCategory.OCEAN
                && event.getCategory() != Biome.BiomeCategory.RIVER
                && event.getCategory() != Biome.BiomeCategory.THEEND) {
            event.getSpawns().addSpawn(MobCategory.CREATURE,
                    new MobSpawnSettings.SpawnerData(ChristmasEntities.GINGERBREAD_MAN.get(),
                            GingerbreadManEntity.SPAWN_WEIGHT, GingerbreadManEntity.MIN_SPAWN_COUNT,
                            GingerbreadManEntity.MAX_SPAWN_COUNT)
            );

            event.getSpawns().addSpawn(MobCategory.CREATURE,
                    new MobSpawnSettings.SpawnerData(ChristmasEntities.NUTCRACKER.get(),
                            NutcrackerEntity.SPAWN_WEIGHT, NutcrackerEntity.MIN_SPAWN_COUNT,
                            NutcrackerEntity.MAX_SPAWN_COUNT)
            );
        }
    }

    private static boolean isOverworld(Biome.BiomeCategory category) {
        return category != Biome.BiomeCategory.NETHER && category != Biome.BiomeCategory.THEEND;
    }

    private static boolean isCold(Biome.BiomeCategory category) {
        return category == Biome.BiomeCategory.ICY;
    }
}
