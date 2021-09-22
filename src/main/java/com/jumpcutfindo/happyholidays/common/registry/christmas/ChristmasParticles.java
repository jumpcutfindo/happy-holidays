package com.jumpcutfindo.happyholidays.common.registry.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumBlueParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumGoldParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumGreenParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumRedParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumSilverParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumYellowParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.santa.SantaGreenSpawnParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.santa.SantaRedSpawnParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallBlueParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallGoldParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallGreenParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallRedParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallSilverParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.small.ChristmasSmallYellowParticle;
import com.jumpcutfindo.happyholidays.common.particle.christmas.star.ChristmasStarParticle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ChristmasParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HappyHolidaysMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_RED_PARTICLE =
            PARTICLES.register(ChristmasMediumRedParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_BLUE_PARTICLE =
            PARTICLES.register(ChristmasMediumBlueParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_YELLOW_PARTICLE =
            PARTICLES.register(ChristmasMediumYellowParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_GREEN_PARTICLE =
            PARTICLES.register(ChristmasMediumGreenParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_GOLD_PARTICLE =
            PARTICLES.register(ChristmasMediumGoldParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_MEDIUM_SILVER_PARTICLE =
            PARTICLES.register(ChristmasMediumSilverParticle.PARTICLE_ID, () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_RED_PARTICLE =
            PARTICLES.register(ChristmasSmallRedParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_BLUE_PARTICLE =
            PARTICLES.register(ChristmasSmallBlueParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_YELLOW_PARTICLE =
            PARTICLES.register(ChristmasSmallYellowParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_GREEN_PARTICLE =
            PARTICLES.register(ChristmasSmallGreenParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_GOLD_PARTICLE =
            PARTICLES.register(ChristmasSmallGoldParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SMALL_SILVER_PARTICLE =
            PARTICLES.register(ChristmasSmallSilverParticle.PARTICLE_ID, () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SANTA_RED_SPAWN_PARTICLE =
            PARTICLES.register(SantaRedSpawnParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CHRISTMAS_SANTA_GREEN_SPAWN_PARTICLE =
            PARTICLES.register(SantaGreenSpawnParticle.PARTICLE_ID, () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> CHRISTMAS_STAR_PARTICLE =
            PARTICLES.register(ChristmasStarParticle.PARTICLE_ID, () -> new SimpleParticleType(true));
}
