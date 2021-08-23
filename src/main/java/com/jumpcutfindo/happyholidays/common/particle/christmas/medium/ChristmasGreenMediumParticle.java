package com.jumpcutfindo.happyholidays.common.particle.christmas.medium;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasGreenMediumParticle extends ChristmasMediumParticle {
    public static final String PARTICLE_ID = "christmas_green_particle";
    public static final int COLOR = 65280;

    public ChristmasGreenMediumParticle(ClientWorld world, double xCoord, double yCoord, double zCoord, double xSpeed,
                                        double ySpeed, double zSpeed, IAnimatedSprite sprites) {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, COLOR, sprites);
    }
}
