package com.jumpcutfindo.happyholidays.common.particle.christmas.small;

import com.jumpcutfindo.happyholidays.common.particle.christmas.ParticleColor;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumParticle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasSmallYellowParticle extends ChristmasMediumParticle {
    public static final String PARTICLE_ID = "christmas_small_yellow_particle";
    public static final int COLOR = ParticleColor.YELLOW.getColor();

    public ChristmasSmallYellowParticle(ClientWorld world, double xCoord, double yCoord, double zCoord, double xSpeed,
                                        double ySpeed, double zSpeed, IAnimatedSprite sprites) {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, COLOR, sprites);
    }
}