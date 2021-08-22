package com.jumpcutfindo.happyholidays.common.registry;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasBlueParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasGoldParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasGreenParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasRedParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasSilverParticle;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<BasicParticleType> CHRISTMAS_GREEN_PARTICLE =
            PARTICLES.register(ChristmasGreenParticle.PARTICLE_ID, () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> CHRISTMAS_RED_PARTICLE =
            PARTICLES.register(ChristmasRedParticle.PARTICLE_ID, () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> CHRISTMAS_GOLD_PARTICLE =
            PARTICLES.register(ChristmasGoldParticle.PARTICLE_ID, () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> CHRISTMAS_SILVER_PARTICLE =
            PARTICLES.register(ChristmasSilverParticle.PARTICLE_ID, () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> CHRISTMAS_BLUE_PARTICLE =
            PARTICLES.register(ChristmasBlueParticle.PARTICLE_ID, () -> new BasicParticleType(true));
}
