package com.jumpcutfindo.happyholidays.common.handlers;

import java.util.Collection;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.alphabets.AlphabetBlockColor;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasStats;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTriggers;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.ColorHandlerEvent;
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

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event) {
        AlphabetBlockColor alphabetBlockColor = new AlphabetBlockColor();

        event.getBlockColors().register(alphabetBlockColor,
                ChristmasBlocks.ALPHABET_ORNAMENT_TEMPLATE.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_A.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_B.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_C.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_D.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_E.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_F.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_G.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_H.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_I.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_J.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_K.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_L.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_M.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_N.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_O.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_P.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_Q.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_R.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_S.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_T.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_U.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_V.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_W.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_X.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_Y.get(),
                ChristmasBlocks.ALPHABET_ORNAMENT_Z.get()
        );
    }
}
