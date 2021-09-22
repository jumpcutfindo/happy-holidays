package com.jumpcutfindo.happyholidays.common.particle.christmas.small;

import com.jumpcutfindo.happyholidays.common.particle.christmas.ParticleColor;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumParticle;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasSmallRedParticle extends ChristmasMediumParticle {
    public static final String PARTICLE_ID = "christmas_small_red_particle";
    public static final int COLOR = ParticleColor.RED.getColor();

    public ChristmasSmallRedParticle(ClientLevel world, double xCoord, double yCoord, double zCoord,
                                     double xSpeed, double ySpeed, double zSpeed,
                                     SpriteSet sprites) {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, COLOR, sprites);
    }
}