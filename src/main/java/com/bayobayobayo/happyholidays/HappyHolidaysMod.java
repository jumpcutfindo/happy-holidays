package com.bayobayobayo.happyholidays;

import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bayobayobayo.happyholidays.common.RegistryHandler;
import com.bayobayobayo.happyholidays.common.entity.HappyHolidaysEntities;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HappyHolidaysMod.MOD_ID)
public class HappyHolidaysMod {

    public static final String MOD_ID = "happyholidays";
    private static final Logger LOGGER = LogManager.getLogger();

    public HappyHolidaysMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        bus.addListener(this::setup);
        bus.addListener(this::setEntityAttributes);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register registries
        RegistryHandler.BLOCKS.register(bus);
        RegistryHandler.ITEMS.register(bus);
        RegistryHandler.ENTITY_TYPES.register(bus);

        // Register holiday modules
        ModuleHandler.registerModules();

        // Client-related loading
        bus.addListener(this::onClientLoaded);

        GeckoLib.initialize();
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModuleHandler.configureModules();
        });
    }

    public void setEntityAttributes(final EntityAttributeCreationEvent event) {
        List<ModuleHandler> handlers = ModuleHandler.getHandlers();
        for (ModuleHandler handler : handlers) {
            HappyHolidaysEntities[] entities = handler.getEntities();

            for (HappyHolidaysEntities entitySet : entities) {
                entitySet.createAttributes(event);
            }
        }
    }

    @SubscribeEvent
    public void registerEntitySpawns(final BiomeLoadingEvent event) {
        List<ModuleHandler> handlers = ModuleHandler.getHandlers();
        for (ModuleHandler handler : handlers) {
            HappyHolidaysEntities[] entities = handler.getEntities();

            for (HappyHolidaysEntities entitySet : entities) {
                entitySet.configureEntitySpawning(event);
            }
        }
    }

    private void onClientLoaded(final FMLClientSetupEvent event) {
    }

}
