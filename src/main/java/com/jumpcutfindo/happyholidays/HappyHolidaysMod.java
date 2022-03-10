package com.jumpcutfindo.happyholidays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasContainers;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEffects;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.proxies.CommonProxy;
import com.jumpcutfindo.happyholidays.proxies.Proxy;
import com.jumpcutfindo.happyholidays.proxies.client.ClientProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HappyHolidaysMod.MOD_ID)
public class HappyHolidaysMod {
    public static final String MOD_ID = "happyholidays";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static Proxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public HappyHolidaysMod() {
        GeckoLibMod.DISABLE_IN_DEV = true;
        GeckoLib.initialize();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register registries
        ChristmasEntities.ENTITY_TYPES.register(bus);
        ChristmasBlocks.BLOCKS.register(bus);
        ChristmasItems.ITEMS.register(bus);
        ChristmasBlockEntities.BLOCK_ENTITY.register(bus);
        ChristmasContainers.CONTAINER.register(bus);
        ChristmasSounds.SOUNDS.register(bus);
        ChristmasEffects.EFFECTS.register(bus);
        ChristmasParticles.PARTICLES.register(bus);
    }
}
