package com.bayobayobayo.happyholidays;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bayobayobayo.happyholidays.common.RegistryHandler;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HappyHolidaysMod.MOD_ID)
public class HappyHolidaysMod
{
    public static final String MOD_ID = "happyholidays";
    private static final Logger LOGGER = LogManager.getLogger();

    public HappyHolidaysMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        bus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register registries
        RegistryHandler.BLOCKS.register(bus);
        RegistryHandler.ITEMS.register(bus);

        // Register holiday modules
        ModuleHandler.registerModules();

        // Client-related loading
        bus.addListener(this::onClientLoaded);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void onClientLoaded(final FMLClientSetupEvent event) {
        ModuleHandler.configureModules();
    }

}
