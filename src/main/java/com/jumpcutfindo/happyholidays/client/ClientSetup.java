package com.jumpcutfindo.happyholidays.client;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.alphabets.AlphabetBlockColor;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public void setupClient(FMLClientSetupEvent event) {
        HappyHolidaysMod.PROXY.initClient();
    }

    @SubscribeEvent
    public static void addAlphabetColors(ColorHandlerEvent.Block event) {
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
