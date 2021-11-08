package com.jumpcutfindo.happyholidays.common.handlers;

import java.util.Collection;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasStats;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTriggers;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupHandler {

    @SubscribeEvent
    public static void configureBlocks(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Collection<RegistryObject<Block>> christmasBlocks = ChristmasBlocks.BLOCKS.getEntries();

            for (RegistryObject<Block> block : christmasBlocks) {
                if (block.get() instanceof ChristmasBlock) ((ChristmasBlock) block.get()).configure();
            }
        });

        event.enqueueWork(ChristmasStats::registerStats);
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(CapabilityNaughtyNice.class);
        event.register(INaughtyNiceHandler.class);
    }

    @SubscribeEvent
    public static void registerTriggers(FMLCommonSetupEvent event) {
        event.enqueueWork(ChristmasTriggers::registerTriggers);
    }
}
