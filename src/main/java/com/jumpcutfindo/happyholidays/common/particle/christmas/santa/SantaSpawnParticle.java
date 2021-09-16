package com.jumpcutfindo.happyholidays.common.particle.christmas.santa;

import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.particle.christmas.ChristmasParticle;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SantaSpawnParticle extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final int color;
    private final double destX, destY, destZ;

    public SantaSpawnParticle(ClientLevel world, double x, double y, double z,
                              int color, SpriteSet sprites) {
        super(world, x, y, z);

        this.color = color;
        this.sprites = sprites;

        // Record the current position
        this.destX = x;
        this.destY = y;
        this.destZ = z;

        // Move the position of the particle to some position around Santa
        this.xo = this.destX + Math.random() * BaseSantaEntity.ENTITY_BOX_SIZE * (Math.random() <= 0.5 ? 1 : -1) * 2;
        this.yo = this.destY + Math.random() * BaseSantaEntity.ENTITY_BOX_HEIGHT;
        this.zo = this.destZ + Math.random() * BaseSantaEntity.ENTITY_BOX_SIZE * (Math.random() <= 0.5 ? 1 : -1) * 2;
        this.setPos(this.xo, this.yo, this.zo);

        // Random speed of particles
        this.xd = (this.xo - this.destX) * -0.1D;
        this.yd = (this.yo - this.destY) * -0.1D;
        this.zd = (this.zo - this.destZ) * -0.1D;

        this.lifetime = (int)(4.0D / ((double)this.random.nextFloat() * 0.8D + 0.2D)) + 1;

        this.hasPhysics = false;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.sprites);

            this.xd *= 0.9D;
            this.yd *= 0.9D;
            this.zd *= 0.9D;

            this.move(this.xd, this.yd, this.zd);
        }
    }

    public SantaSpawnParticle multiplyColor() {
        // Apply tint to the particle
        this.rCol *= (float)(color >> 16 & 255) / 255.0F;
        this.gCol *= (float)(color >> 8 & 255) / 255.0F;
        this.bCol *= (float)(color & 255) / 255.0F;

        return this;
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
            return new SantaSpawnParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_, this.color,
                    this.sprites).multiplyColor();
        }
    }
}
