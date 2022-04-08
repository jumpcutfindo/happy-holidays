package com.jumpcutfindo.happyholidays.handlers;

import java.util.ArrayList;
import java.util.List;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.HappyHolidaysBlock;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.registry.HappyHolidaysStats;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTriggers;
import com.jumpcutfindo.happyholidays.common.registry.cny.CNYBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GeneralEventHandler {
    @SubscribeEvent
    public static void configureBlocks(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            List<RegistryObject<Block>> modBlocks = new ArrayList<>();
            modBlocks.addAll(ChristmasBlocks.BLOCKS.getEntries());
            modBlocks.addAll(CNYBlocks.BLOCKS.getEntries());

            for (RegistryObject<Block> block : modBlocks) {
                if (block.get() instanceof HappyHolidaysBlock) ((HappyHolidaysBlock) block.get()).configure();
            }
        });
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(CapabilityNaughtyNice.class);
        event.register(INaughtyNiceHandler.class);
    }

    @SubscribeEvent
    public static void registerTriggersAndStats(FMLCommonSetupEvent event) {
        event.enqueueWork(ChristmasTriggers::registerTriggers);
        event.enqueueWork(HappyHolidaysStats::registerStats);
    }
}
