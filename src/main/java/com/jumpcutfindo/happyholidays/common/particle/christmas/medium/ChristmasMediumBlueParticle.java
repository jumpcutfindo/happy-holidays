package com.jumpcutfindo.happyholidays.common.particle.christmas.medium;

import com.jumpcutfindo.happyholidays.common.particle.christmas.ParticleColor;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasMediumBlueParticle extends ChristmasMediumParticle {
    public static final String PARTICLE_ID = "christmas_medium_blue_particle";
    public static final int COLOR = ParticleColor.BLUE.getColor();

    public ChristmasMediumBlueParticle(ClientWorld world, double xCoord, double yCoord, double zCoord, double xSpeed,
                                       double ySpeed, double zSpeed, IAnimatedSprite sprites) {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, COLOR, sprites);
    }
}
