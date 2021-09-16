package com.jumpcutfindo.happyholidays.common.particle.christmas.small;

import com.jumpcutfindo.happyholidays.common.particle.christmas.ParticleColor;
import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumParticle;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasSmallBlueParticle extends ChristmasMediumParticle {
    public static final String PARTICLE_ID = "christmas_small_blue_particle";
    public static final int COLOR = ParticleColor.BLUE.getColor();

    public ChristmasSmallBlueParticle(ClientLevel world, double xCoord, double yCoord, double zCoord, double xSpeed,
                                      double ySpeed, double zSpeed, SpriteSet sprites) {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, COLOR, sprites);
    }
}
