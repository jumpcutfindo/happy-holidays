package com.jumpcutfindo.happyholidays.common.registry;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasBlueMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasGoldMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasGreenMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasRedMediumParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasSilverMediumParticle;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<BasicParticleType> CHRISTMAS_GREEN_PARTICLE =
            PARTICLES.register(ChristmasGreenMediumParticle.PARTICLE_ID, () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> CHRISTMAS_RED_PARTICLE =
            PARTICLES.register(ChristmasRedMediumParticle.PARTICLE_ID, () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> CHRISTMAS_GOLD_PARTICLE =
            PARTICLES.register(ChristmasGoldMediumParticle.PARTICLE_ID, () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> CHRISTMAS_SILVER_PARTICLE =
            PARTICLES.register(ChristmasSilverMediumParticle.PARTICLE_ID, () -> new BasicParticleType(true));
    public static final RegistryObject<BasicParticleType> CHRISTMAS_BLUE_PARTICLE =
            PARTICLES.register(ChristmasBlueMediumParticle.PARTICLE_ID, () -> new BasicParticleType(true));
}
