package com.jumpcutfindo.happyholidays.server.command;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommandHandler {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new HappyHolidaysCommand(event.getDispatcher());
    }
}
