package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasBlueParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasGoldParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasGreenParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasSilverParticle;
import com.jumpcutfindo.happyholidays.common.registry.ParticleRegistry;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleHandler {

    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_GREEN_PARTICLE.get(),
                ChristmasGreenParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_RED_PARTICLE.get(),
                ChristmasGreenParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_GOLD_PARTICLE.get(),
                ChristmasGoldParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_SILVER_PARTICLE.get(),
                ChristmasSilverParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_BLUE_PARTICLE.get(),
                ChristmasBlueParticle.Factory::new);
    }
}
