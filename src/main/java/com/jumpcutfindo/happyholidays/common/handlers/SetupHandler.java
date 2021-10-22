package com.jumpcutfindo.happyholidays.common.handlers;

import java.util.Arrays;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.alphabets.AlphabetBlockColor;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.CapabilityNaughtyNice;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.INaughtyNiceHandler;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTriggers;

import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupHandler {

    @SubscribeEvent
    public static void configureBlocks(FMLCommonSetupEvent event) {
        ChristmasBlock[] christmasBlocks = new ChristmasBlock[]{
                ChristmasBlocks.BABY_PRESENT.get(),
                ChristmasBlocks.ADULT_PRESENT.get(),
                ChristmasBlocks.ELDER_PRESENT.get(),

                ChristmasBlocks.RED_BAUBLE.get(),
                ChristmasBlocks.BLUE_BAUBLE.get(),
                ChristmasBlocks.YELLOW_BAUBLE.get(),
                ChristmasBlocks.GREEN_BAUBLE.get(),
                ChristmasBlocks.GOLD_BAUBLE.get(),
                ChristmasBlocks.SILVER_BAUBLE.get(),

                ChristmasBlocks.BIG_RED_BAUBLE.get(),
                ChristmasBlocks.BIG_BLUE_BAUBLE.get(),
                ChristmasBlocks.BIG_YELLOW_BAUBLE.get(),
                ChristmasBlocks.BIG_GREEN_BAUBLE.get(),
                ChristmasBlocks.BIG_GOLD_BAUBLE.get(),
                ChristmasBlocks.BIG_SILVER_BAUBLE.get(),

                ChristmasBlocks.RED_TINSEL.get(),
                ChristmasBlocks.BLUE_TINSEL.get(),
                ChristmasBlocks.YELLOW_TINSEL.get(),
                ChristmasBlocks.GREEN_TINSEL.get(),
                ChristmasBlocks.GOLD_TINSEL.get(),
                ChristmasBlocks.SILVER_TINSEL.get(),

                ChristmasBlocks.RED_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.BLUE_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.YELLOW_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.GREEN_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.GOLD_CHRISTMAS_LIGHTS.get(),
                ChristmasBlocks.SILVER_CHRISTMAS_LIGHTS.get(),

                ChristmasBlocks.RED_CHRISTMAS_BELLS.get(),
                ChristmasBlocks.BLUE_CHRISTMAS_BELLS.get(),
                ChristmasBlocks.YELLOW_CHRISTMAS_BELLS.get(),
                ChristmasBlocks.GREEN_CHRISTMAS_BELLS.get(),
                ChristmasBlocks.GOLD_CHRISTMAS_BELLS.get(),
                ChristmasBlocks.SILVER_CHRISTMAS_BELLS.get(),

                ChristmasBlocks.CREEPER_HEAD_ORNAMENT.get(),
                ChristmasBlocks.SKELETON_HEAD_ORNAMENT.get(),
                ChristmasBlocks.WITHER_SKELETON_HEAD_ORNAMENT.get(),
                ChristmasBlocks.ZOMBIE_HEAD_ORNAMENT.get(),
                ChristmasBlocks.DROWNED_HEAD_ORNAMENT.get(),
                ChristmasBlocks.BLAZE_HEAD_ORNAMENT.get(),
                ChristmasBlocks.GHAST_HEAD_ORNAMENT.get(),
                ChristmasBlocks.PHANTOM_HEAD_ORNAMENT.get(),
                ChristmasBlocks.PIG_HEAD_ORNAMENT.get(),
                ChristmasBlocks.COW_HEAD_ORNAMENT.get(),
                ChristmasBlocks.CHICKEN_HEAD_ORNAMENT.get(),
                ChristmasBlocks.SHEEP_HEAD_ORNAMENT.get(),

                ChristmasBlocks.RED_STOCKING.get(),
                ChristmasBlocks.BLUE_STOCKING.get(),
                ChristmasBlocks.YELLOW_STOCKING.get(),
                ChristmasBlocks.GREEN_STOCKING.get(),
                ChristmasBlocks.GOLD_STOCKING.get(),
                ChristmasBlocks.SILVER_STOCKING.get(),

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
                ChristmasBlocks.ALPHABET_ORNAMENT_Z.get(),

                ChristmasBlocks.CHRISTMAS_WREATH.get(),
                ChristmasBlocks.FROST.get(),
                ChristmasBlocks.SANTA_LIST.get(),

                ChristmasBlocks.BABY_PRESENT_ORNAMENT.get(),
                ChristmasBlocks.ADULT_PRESENT_ORNAMENT.get(),
                ChristmasBlocks.ELDER_PRESENT_ORNAMENT.get(),
                ChristmasBlocks.CANDY_CANE_ORNAMENT.get(),
                ChristmasBlocks.SANTA_ELF_ORNAMENT.get(),
                ChristmasBlocks.GINGERBREAD_MAN_ORNAMENT.get(),
                ChristmasBlocks.GRINCH_ORNAMENT.get(),

                ChristmasBlocks.GINGERBREAD_BLOCK.get(),
                ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK.get(),
                ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get(),

                ChristmasBlocks.CANDY_CANE_BLOCK.get(),
                ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get(),

                ChristmasBlocks.MILK_AND_COOKIES.get(),
                ChristmasBlocks.LOG_CAKE.get(),
                ChristmasBlocks.CHRISTMAS_HAM.get(),
                ChristmasBlocks.CHRISTMAS_PUDDING.get(),

                ChristmasBlocks.CHRISTMAS_STAR.get(),
                ChristmasBlocks.MUSIC_BOX.get(),
                ChristmasBlocks.GIFT_WRAPPING_STATION.get()
        };

        Arrays.stream(christmasBlocks).forEach(ChristmasBlock::configureBlock);
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
