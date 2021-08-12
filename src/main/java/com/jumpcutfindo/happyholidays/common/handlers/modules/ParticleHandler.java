package com.jumpcutfindo.happyholidays.common.handlers.modules;

import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasGreenParticle;
import com.jumpcutfindo.happyholidays.common.registry.ParticleRegistry;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;

public class ParticleHandler {
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_GREEN_PARTICLE.get(),
                ChristmasGreenParticle.Factory::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_RED_PARTICLE.get(),
                ChristmasGreenParticle.Factory::new);
    }
}
