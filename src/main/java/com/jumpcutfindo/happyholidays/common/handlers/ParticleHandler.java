package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasBlueMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasGoldMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasGreenMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasRedMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasSilverMediumParticle;
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
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasGreenMediumParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_RED_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasRedMediumParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_GOLD_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasGoldMediumParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_SILVER_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasSilverMediumParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.CHRISTMAS_BLUE_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasBlueMediumParticle.COLOR));
    }
}
