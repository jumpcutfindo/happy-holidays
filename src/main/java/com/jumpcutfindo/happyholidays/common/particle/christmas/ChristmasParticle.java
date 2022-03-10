package com.jumpcutfindo.happyholidays.common.particle.christmas;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChristmasParticle extends TextureSheetParticle {
    // Particle IDs
    private static final String ID_FORMAT = "%s_%s_%s";
    private static final String CHRISTMAS_NAME = "christmas";
    private static final String SMALL_NAME = "small";
    private static final String MEDIUM_NAME = "medium";

    private static final String RED_NAME = "red";
    private static final String BLUE_NAME = "blue";
    private static final String GREEN_NAME = "green";
    private static final String YELLOW_NAME = "yellow";
    private static final String GOLD_NAME = "gold";
    private static final String SILVER_NAME = "silver";

    public static final String SMALL_RED_ID, SMALL_BLUE_ID, SMALL_GREEN_ID,
            SMALL_YELLOW_ID, SMALL_GOLD_ID, SMALL_SILVER_ID;
    public static final String MEDIUM_RED_ID, MEDIUM_BLUE_ID, MEDIUM_GREEN_ID,
            MEDIUM_YELLOW_ID, MEDIUM_GOLD_ID, MEDIUM_SILVER_ID;

    static {
        // Initialise particles
        SMALL_RED_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, SMALL_NAME, RED_NAME);
        SMALL_BLUE_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, SMALL_NAME, BLUE_NAME);
        SMALL_GREEN_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, SMALL_NAME, GREEN_NAME);
        SMALL_YELLOW_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, SMALL_NAME, YELLOW_NAME);
        SMALL_GOLD_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, SMALL_NAME, GOLD_NAME);
        SMALL_SILVER_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, SMALL_NAME, SILVER_NAME);

        MEDIUM_RED_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, MEDIUM_NAME, RED_NAME);
        MEDIUM_BLUE_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, MEDIUM_NAME, BLUE_NAME);
        MEDIUM_GREEN_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, MEDIUM_NAME, GREEN_NAME);
        MEDIUM_YELLOW_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, MEDIUM_NAME, YELLOW_NAME);
        MEDIUM_GOLD_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, MEDIUM_NAME, GOLD_NAME);
        MEDIUM_SILVER_ID = String.format(ID_FORMAT, CHRISTMAS_NAME, MEDIUM_NAME, SILVER_NAME);
    }

    private final SpriteSet sprites;
    private int color;

    public ChristmasParticle(ClientLevel world,
                                   double xCoord, double yCoord, double zCoord,
                                   double xSpeed, double ySpeed, double zSpeed,
                                   int color,
                                   SpriteSet sprites) {
        super(world, xCoord, yCoord, zCoord);
        this.sprites = sprites;
        this.xd = xSpeed + (Math.random() * 2.0D - 1.0D) * (double) 0.2F;
        this.yd = ySpeed + (Math.random() * 2.0D) * (double) 0.2F;
        this.zd = zSpeed + (Math.random() * 2.0D - 1.0D) * (double) 0.2F;

        this.color = color;

        this.lifetime = (int)(4.0D / ((double)this.random.nextFloat() * 0.8D + 0.2D)) + 1;
        this.setSpriteFromAge(sprites);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
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

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;
        private final int color;

        public Factory(SpriteSet sprites, int color) {
            this.sprites = sprites;
            this.color = color;
        }

        public Particle createParticle(SimpleParticleType p_199234_1_, ClientLevel p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            return new ChristmasParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_, p_199234_9_, p_199234_11_,
                    p_199234_13_, this.color, this.sprites).multiplyColor();
        }
    }
}
