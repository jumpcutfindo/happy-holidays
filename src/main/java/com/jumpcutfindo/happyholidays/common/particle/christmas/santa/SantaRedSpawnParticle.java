package com.jumpcutfindo.happyholidays.common.particle.christmas.santa;

import com.jumpcutfindo.happyholidays.common.particle.christmas.ParticleColor;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SantaRedSpawnParticle extends SantaSpawnParticle {
    public static final String PARTICLE_ID = "christmas_santa_red_particle";
    public static final int COLOR = ParticleColor.RED.getColor();

    public SantaRedSpawnParticle(ClientWorld world, double x, double y, double z, IAnimatedSprite sprites) {
        super(world, x, y, z, COLOR, sprites);
    }
}
