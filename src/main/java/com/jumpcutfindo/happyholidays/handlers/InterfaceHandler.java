package com.jumpcutfindo.happyholidays.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.screen.ChristmasStarScreen;
import com.jumpcutfindo.happyholidays.client.screen.GiftWrapperScreen;
import com.jumpcutfindo.happyholidays.client.screen.MusicBoxScreen;
import com.jumpcutfindo.happyholidays.client.screen.NutcrackerInventoryScreen;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasContainers;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InterfaceHandler {
    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        MenuScreens.register(ChristmasContainers.CHRISTMAS_STAR_CONTAINER.get(), ChristmasStarScreen::new);
        MenuScreens.register(ChristmasContainers.GIFT_WRAPPER_CONTAINER.get(), GiftWrapperScreen::new);
        MenuScreens.register(ChristmasContainers.MUSIC_BOX_CONTAINER.get(), MusicBoxScreen::new);
        MenuScreens.register(ChristmasContainers.NUTCRACKER_CONTAINER.get(), NutcrackerInventoryScreen::new);
    }
}
