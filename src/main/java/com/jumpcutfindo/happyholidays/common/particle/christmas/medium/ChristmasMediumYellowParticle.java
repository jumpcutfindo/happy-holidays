package com.jumpcutfindo.happyholidays.common.particle.christmas.medium;

import com.jumpcutfindo.happyholidays.common.particle.christmas.ParticleColor;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasMediumYellowParticle extends ChristmasMediumParticle {
    public static final String PARTICLE_ID = "christmas_medium_yellow_particle";
    public static final int COLOR = ParticleColor.YELLOW.getColor();

    public ChristmasMediumYellowParticle(ClientWorld world, double xCoord, double yCoord, double zCoord, double xSpeed,
                                         double ySpeed, double zSpeed, IAnimatedSprite sprites) {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, COLOR, sprites);
    }
}
