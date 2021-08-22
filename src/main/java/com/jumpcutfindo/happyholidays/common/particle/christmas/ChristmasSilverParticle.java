package com.jumpcutfindo.happyholidays.common.particle.christmas;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasSilverParticle extends ChristmasParticle{
    public static final String PARTICLE_ID = "christmas_silver_particle";

    public ChristmasSilverParticle(ClientWorld world, double xCoord, double yCoord, double zCoord, double xSpeed,
                                   double ySpeed, double zSpeed, IAnimatedSprite sprites) {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, sprites);
    }
}
