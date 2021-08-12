package com.jumpcutfindo.happyholidays.common.particle.christmas;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasRedParticle extends ChristmasParticle{
    public static final String PARTICLE_ID = "christmas_red_particle";

    public ChristmasRedParticle(ClientWorld world, double xCoord, double yCoord, double zCoord, double xSpeed,
                                  double ySpeed, double zSpeed, IAnimatedSprite sprites) {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, sprites);
    }
}
