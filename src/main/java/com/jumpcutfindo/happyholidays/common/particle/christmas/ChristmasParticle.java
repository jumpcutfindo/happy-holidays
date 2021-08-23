package com.jumpcutfindo.happyholidays.common.particle.christmas;

import com.jumpcutfindo.happyholidays.common.particle.christmas.medium.ChristmasMediumParticle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasParticle extends SpriteTexturedParticle {
    private final IAnimatedSprite sprites;
    private int color;

    public ChristmasParticle(ClientWorld world,
                                   double xCoord, double yCoord, double zCoord,
                                   double xSpeed, double ySpeed, double zSpeed,
                                   int color,
                                   IAnimatedSprite sprites) {
        super(world, xCoord, yCoord, zCoord);
        this.sprites = sprites;
        this.xd = xSpeed + (Math.random() * 2.0D - 1.0D) * (double) 0.2F;
        this.yd = ySpeed + (Math.random() * 2.0D) * (double) 0.2F;
        this.zd = zSpeed + (Math.random() * 2.0D - 1.0D) * (double) 0.2F;

        this.color = color;

        this.lifetime = (int)(4.0D / ((double)this.random.nextFloat() * 0.8D + 0.2D)) + 1;
        this.setSpriteFromAge(sprites);
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public ChristmasParticle multiplyColor() {
        // Apply tint to the particle
        this.rCol *= (float)(color >> 16 & 255) / 255.0F;
        this.gCol *= (float)(color >> 8 & 255) / 255.0F;
        this.bCol *= (float)(color & 255) / 255.0F;

        return this;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.sprites);
            this.yd += 0.004D;
            this.move(this.xd, this.yd, this.zd);
            this.xd *= (double)0.9F;
            this.yd *= (double)0.9F;
            this.zd *= (double)0.9F;
            if (this.onGround) {
                this.xd *= (double)0.7F;
                this.zd *= (double)0.7F;
            }
        }
    }
}
