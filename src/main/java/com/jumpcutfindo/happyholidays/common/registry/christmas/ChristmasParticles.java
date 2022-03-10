package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.client.particle.christmas.ChristmasParticle;
import com.jumpcutfindo.happyholidays.client.particle.christmas.ChristmasStarParticle;
import com.jumpcutfindo.happyholidays.client.particle.christmas.SantaSpawnParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChristmasParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HappyHolidaysMod.MOD_ID);

    // Small Christmas particles
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_RED =
            registerChristmasParticle(ChristmasParticle.SMALL_RED_ID);
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_BLUE =
            registerChristmasParticle(ChristmasParticle.SMALL_BLUE_ID);
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_YELLOW =
            registerChristmasParticle(ChristmasParticle.SMALL_YELLOW_ID);
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_GREEN =
            registerChristmasParticle(ChristmasParticle.SMALL_GREEN_ID);
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_GOLD =
            registerChristmasParticle(ChristmasParticle.SMALL_GOLD_ID);
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_SILVER =
            registerChristmasParticle(ChristmasParticle.SMALL_SILVER_ID);

    // Medium Christmas particles
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_RED =
            registerChristmasParticle(ChristmasParticle.MEDIUM_RED_ID);
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_BLUE =
            registerChristmasParticle(ChristmasParticle.MEDIUM_BLUE_ID);
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_YELLOW =
            registerChristmasParticle(ChristmasParticle.MEDIUM_YELLOW_ID);
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_GREEN =
            registerChristmasParticle(ChristmasParticle.MEDIUM_GREEN_ID);
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_GOLD =
            registerChristmasParticle(ChristmasParticle.MEDIUM_GOLD_ID);
    public static final RegistryObject<SimpleParticleType> _PARTICLE =
            registerChristmasParticle(ChristmasParticle.MEDIUM_SILVER_ID);

    // Santa particles
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SANTA_RED =
            PARTICLES.register(SantaSpawnParticle.RED_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SANTA_GREEN =
            PARTICLES.register(SantaSpawnParticle.GREEN_ID, () -> new SimpleParticleType(true));

    // Christmas Star particles
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_STAR =
            PARTICLES.register(ChristmasStarParticle.PARTICLE_ID, () -> new SimpleParticleType(true));

    private static RegistryObject<SimpleParticleType> registerChristmasParticle(String particleId) {
        return PARTICLES.register(particleId, () -> new SimpleParticleType(true));
    }
}
