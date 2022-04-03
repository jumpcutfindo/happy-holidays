package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

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
            registerChristmasParticle("christmas_small_red");
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_BLUE =
            registerChristmasParticle("christmas_small_blue");
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_YELLOW =
            registerChristmasParticle("christmas_small_yellow");
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_GREEN =
            registerChristmasParticle("christmas_small_green");
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_GOLD =
            registerChristmasParticle("christmas_small_gold");
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_SILVER =
            registerChristmasParticle("christmas_small_silver");

    // Medium Christmas particles
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_RED =
            registerChristmasParticle("christmas_medium_red");
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_BLUE =
            registerChristmasParticle("christmas_medium_blue");
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_YELLOW =
            registerChristmasParticle("christmas_medium_yellow");
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_GREEN =
            registerChristmasParticle("christmas_medium_green");
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_GOLD =
            registerChristmasParticle("christmas_medium_gold");
    public static final RegistryObject<SimpleParticleType> _PARTICLE =
            registerChristmasParticle("christmas_medium_silver");

    // Santa particles
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SANTA_RED =
            PARTICLES.register("christmas_santa_red", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SANTA_GREEN =
            PARTICLES.register("christmas_santa_green", () -> new SimpleParticleType(true));

    // Christmas Star particles
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_STAR =
            PARTICLES.register("christmas_star_particle", () -> new SimpleParticleType(true));

    private static RegistryObject<SimpleParticleType> registerChristmasParticle(String particleId) {
        return PARTICLES.register(particleId, () -> new SimpleParticleType(true));
    }
}
