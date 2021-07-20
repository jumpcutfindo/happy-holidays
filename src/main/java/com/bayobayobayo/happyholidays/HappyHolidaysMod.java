package com.bayobayobayo.happyholidays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bayobayobayo.happyholidays.client.entity.GingerbreadPersonEntityRenderer;
import com.bayobayobayo.happyholidays.common.entity.christmas.GingerbreadEntities;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;
import com.bayobayobayo.happyholidays.common.registry.BlockRegistry;
import com.bayobayobayo.happyholidays.common.registry.ContainerTypeRegistry;
import com.bayobayobayo.happyholidays.common.registry.EntityRegistry;
import com.bayobayobayo.happyholidays.common.registry.ItemRegistry;
import com.bayobayobayo.happyholidays.common.registry.TileEntityRegistry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HappyHolidaysMod.MOD_ID)
public class HappyHolidaysMod {
    public static final String MOD_ID = "happyholidays";
    private static final Logger LOGGER = LogManager.getLogger();

    public HappyHolidaysMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::setEntityAttributes);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register registries
        EntityRegistry.ENTITY_TYPES.register(bus);
        BlockRegistry.BLOCKS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        TileEntityRegistry.TILE_ENTITY_TYPE.register(bus);
        ContainerTypeRegistry.CONTAINER_TYPE.register(bus);

        GeckoLib.initialize();
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(ModuleHandler::preInit);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModuleHandler.onClientSetup();
    }

    public void setEntityAttributes(final EntityAttributeCreationEvent event) {
        GingerbreadEntities.createAttributes(event);
    }

    @SubscribeEvent
    public void registerEntitySpawns(final BiomeLoadingEvent event) {
        GingerbreadEntities.configureEntitySpawning(event);
    }


    public static class HappyHolidaysGroup extends ItemGroup {
        public HappyHolidaysGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack makeIcon() {
            return BlockRegistry.ELDER_PRESENT_BLOCK.get().asItem().getDefaultInstance();
        }
    }
}
