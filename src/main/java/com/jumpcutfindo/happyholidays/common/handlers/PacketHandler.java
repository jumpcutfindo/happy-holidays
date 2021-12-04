package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.network.christmas.MusicBoxPacket;
import com.jumpcutfindo.happyholidays.common.network.christmas.SummonSantaPacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1.2";
    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(HappyHolidaysMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int BUILDER_ID;
    private static int MSG_ID;

    @SubscribeEvent
    public static void registerMessages(FMLCommonSetupEvent event) {
        NETWORK.messageBuilder(SummonSantaPacket.class, BUILDER_ID++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(SummonSantaPacket::encode)
                .decoder(SummonSantaPacket::new)
                .consumer(SummonSantaPacket::handle).add();

        NETWORK.registerMessage(MSG_ID++, SummonSantaPacket.class, SummonSantaPacket::encode, SummonSantaPacket::new, SummonSantaPacket::handle);

        NETWORK.messageBuilder(MusicBoxPacket.class, BUILDER_ID++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(MusicBoxPacket::encode)
                .decoder(MusicBoxPacket::new)
                .consumer(MusicBoxPacket::handle).add();

        NETWORK.registerMessage(MSG_ID++, MusicBoxPacket.class, MusicBoxPacket::encode, MusicBoxPacket::new, MusicBoxPacket::handle);
    }
}
