package com.jumpcutfindo.happyholidays.common.handlers;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumBlueParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumGoldParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumGreenParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumRedParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumSilverParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumYellowParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.santa.SantaGreenSpawnParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.santa.SantaRedSpawnParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.santa.SantaSpawnParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallBlueParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallGoldParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallGreenParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallRedParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallSilverParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallYellowParticle;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleHandler {

    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_RED_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumRedParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_BLUE_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumBlueParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_YELLOW_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumYellowParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_GREEN_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumGreenParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_GOLD_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumGoldParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_MEDIUM_SILVER_PARTICLE.get(),
                (sprites) -> new ChristmasMediumParticle.Factory(sprites, ChristmasMediumSilverParticle.COLOR));

        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_RED_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallRedParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_BLUE_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallBlueParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_YELLOW_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallYellowParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_GREEN_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallGreenParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_GOLD_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallGoldParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SMALL_SILVER_PARTICLE.get(),
                (sprites) -> new ChristmasSmallParticle.Factory(sprites, ChristmasSmallSilverParticle.COLOR));

        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SANTA_RED_SPAWN_PARTICLE.get(),
                (sprites) -> new SantaSpawnParticle.Factory(sprites, SantaRedSpawnParticle.COLOR));
        Minecraft.getInstance().particleEngine.register(ChristmasParticles.CHRISTMAS_SANTA_GREEN_SPAWN_PARTICLE.get(),
                (sprites) -> new SantaSpawnParticle.Factory(sprites, SantaGreenSpawnParticle.COLOR));
    }
}
