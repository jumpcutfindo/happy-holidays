package com.jumpcutfindo.happyholidays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasContainers;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEffects;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTileEntities;

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
        ChristmasEntities.ENTITY_TYPES.register(bus);
        ChristmasBlocks.BLOCKS.register(bus);
        ChristmasItems.ITEMS.register(bus);
        ChristmasTileEntities.TILE_ENTITY_TYPE.register(bus);
        ChristmasContainers.CONTAINER_TYPE.register(bus);
        ChristmasSounds.SOUNDS.register(bus);
        ChristmasEffects.EFFECTS.register(bus);
        ChristmasParticles.PARTICLES.register(bus);

        GeckoLib.initialize();
        GeckoLibMod.DISABLE_IN_DEV = true;
    }

    public static class HappyHolidaysGroup extends ItemGroup {
        public HappyHolidaysGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack makeIcon() {
            return ChristmasBlocks.ELDER_PRESENT.get().asItem().getDefaultInstance();
        }
    }
}
