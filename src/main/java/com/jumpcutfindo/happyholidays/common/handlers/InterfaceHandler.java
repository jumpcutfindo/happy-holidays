package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.screen.ChristmasStarScreen;
import com.jumpcutfindo.happyholidays.client.screen.GiftWrapperScreen;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasContainers;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class InterfaceHandler {
    @SubscribeEvent
    public static void registerScreens(FMLClientSetupEvent event) {
        ScreenManager.register(ChristmasContainers.CHRISTMAS_STAR_CONTAINER.get(), ChristmasStarScreen::new);
        ScreenManager.register(ChristmasContainers.GIFT_WRAPPER_CONTAINER.get(), GiftWrapperScreen::new);
    }
}
