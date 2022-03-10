package com.jumpcutfindo.happyholidays.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.screen.christmas.ChristmasStarScreen;
import com.jumpcutfindo.happyholidays.client.screen.christmas.GiftWrapperScreen;
import com.jumpcutfindo.happyholidays.client.screen.christmas.MusicBoxScreen;
import com.jumpcutfindo.happyholidays.client.screen.christmas.NutcrackerInventoryScreen;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasContainers;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InterfaceHandler {
    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        MenuScreens.register(ChristmasContainers.CHRISTMAS_STAR.get(), ChristmasStarScreen::new);
        MenuScreens.register(ChristmasContainers.GIFT_WRAPPER.get(), GiftWrapperScreen::new);
        MenuScreens.register(ChristmasContainers.MUSIC_BOX.get(), MusicBoxScreen::new);
        MenuScreens.register(ChristmasContainers.NUTCRACKER_INVENTORY.get(), NutcrackerInventoryScreen::new);
    }
}
