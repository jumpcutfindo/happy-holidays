package com.jumpcutfindo.happyholidays.common.particle.christmas.santa;

import com.jumpcutfindo.happyholidays.common.particle.christmas.ParticleColor;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SantaGreenSpawnParticle extends SantaSpawnParticle {
    public static final String PARTICLE_ID = "christmas_santa_green_particle";
    public static final int COLOR = ParticleColor.GREEN.getColor();

    public SantaGreenSpawnParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites) {
        super(world, x, y, z, COLOR, sprites);
    }
}
