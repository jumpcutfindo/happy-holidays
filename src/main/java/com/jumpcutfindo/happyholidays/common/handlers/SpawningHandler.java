package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.gingerbread.GingerbreadPersonEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.grinch.GrinchEntity;
import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpawningHandler {
    @SubscribeEvent
    public static void handleEntityPlacementStuff(FMLClientSetupEvent event) {
        // Register spawning rules
        EntitySpawnPlacementRegistry.register(
                EntityRegistry.GINGERBREAD_MAN.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                GingerbreadPersonEntity::checkGingerbreadSpawnRules
        );

        EntitySpawnPlacementRegistry.register(
                EntityRegistry.GRINCH.get(),
                EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                GrinchEntity::checkGrinchSpawnRules
        );
    }
}
