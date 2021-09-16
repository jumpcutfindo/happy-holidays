package com.jumpcutfindo.happyholidays.common.particle.christmas.small;

import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasParticle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasSmallParticle extends ChristmasParticle {
    public ChristmasSmallParticle(ClientLevel world,
                                   double xCoord, double yCoord, double zCoord,
                                   double xSpeed, double ySpeed, double zSpeed,
                                   int color,
                                   SpriteSet sprites) {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, color, sprites);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;
        private final int color;

        public Factory(SpriteSet sprites, int color) {
            this.sprites = sprites;
            this.color = color;
        }

        public Particle createParticle(SimpleParticleType p_199234_1_, ClientLevel p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            return new ChristmasSmallParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_, p_199234_9_,
                    p_199234_11_, p_199234_13_, this.color, this.sprites).multiplyColor();
        }
    }
}
