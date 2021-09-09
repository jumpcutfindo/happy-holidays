package com.jumpcutfindo.happyholidays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ContainerTypeRegistry;
import com.jumpcutfindo.happyholidays.common.registry.EffectRegistry;
import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ItemRegistry;
import com.jumpcutfindo.happyholidays.common.registry.ParticleRegistry;
import com.jumpcutfindo.happyholidays.common.registry.SoundRegistry;
import com.jumpcutfindo.happyholidays.common.registry.TileEntityRegistry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HappyHolidaysMod.MOD_ID)
public class HappyHolidaysMod {
    public static final String MOD_ID = "happyholidays";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final ItemGroup HAPPY_HOLIDAYS_GROUP = new HappyHolidaysMod.HappyHolidaysGroup("happyholidaystab");

    public HappyHolidaysMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register registries
        EntityRegistry.ENTITY_TYPES.register(bus);
        BlockRegistry.BLOCKS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        TileEntityRegistry.TILE_ENTITY_TYPE.register(bus);
        ContainerTypeRegistry.CONTAINER_TYPE.register(bus);
        SoundRegistry.SOUNDS.register(bus);
        EffectRegistry.EFFECTS.register(bus);
        ParticleRegistry.PARTICLES.register(bus);

        GeckoLib.initialize();
        GeckoLibMod.DISABLE_IN_DEV = true;
    }

    public static class HappyHolidaysGroup extends ItemGroup {
        public HappyHolidaysGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack makeIcon() {
            return BlockRegistry.ELDER_PRESENT.get().asItem().getDefaultInstance();
        }
    }
}
