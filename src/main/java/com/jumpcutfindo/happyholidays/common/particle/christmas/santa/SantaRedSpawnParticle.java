package com.jumpcutfindo.happyholidays.common.particle.christmas.santa;

import com.jumpcutfindo.happyholidays.common.particle.christmas.ParticleColor;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SantaRedSpawnParticle extends SantaSpawnParticle {
    public static final String PARTICLE_ID = "christmas_santa_red_particle";
    public static final int COLOR = ParticleColor.RED.getColor();

    public SantaRedSpawnParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites) {
        super(world, x, y, z, COLOR, sprites);
    }
}
