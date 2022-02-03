package com.jumpcutfindo.happyholidays.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.server.command.arguments.IntervalArgument;
import com.jumpcutfindo.happyholidays.server.command.arguments.YearlessDateArgument;

import net.minecraft.commands.synchronization.ArgumentTypes;
import net.minecraft.commands.synchronization.EmptyArgumentSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArgumentTypeHandler {
    @SubscribeEvent
    public static void registerArgumentTypes(FMLCommonSetupEvent event) {
        event.enqueueWork(ArgumentTypeHandler::doRegistration);
    }

    private static void doRegistration() {
        ArgumentTypes.register(resourceStringOf("yearless_date"), YearlessDateArgument.class, new EmptyArgumentSerializer<>(YearlessDateArgument::yearlessDate));
        ArgumentTypes.register(resourceStringOf("interval"), IntervalArgument.class, new EmptyArgumentSerializer<>(IntervalArgument::interval));
    }

    private static String resourceStringOf(String resourceName) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, resourceName).toString();
    }
}
