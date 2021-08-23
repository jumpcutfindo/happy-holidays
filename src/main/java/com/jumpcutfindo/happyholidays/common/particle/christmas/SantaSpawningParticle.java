package com.jumpcutfindo.happyholidays.common.particle.christmas;

import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SantaSpawningParticle extends SpriteTexturedParticle {
    private final double destX, destY, destZ;

    public SantaSpawningParticle(ClientWorld world, double x, double y,
                                 double z) {
        super(world, x, y, z);

        // Record the current position
        this.destX = x;
        this.destY = y;
        this.destZ = z;

        // Move the position of the particle randomly around Santa
        this.x = this.x + Math.random() * BaseSantaEntity.ENTITY_BOX_SIZE;
        this.y = this.y + Math.random() * BaseSantaEntity.ENTITY_BOX_HEIGHT;
        this.z = this.z + Math.random() * BaseSantaEntity.ENTITY_BOX_SIZE;

        // Speed should be a function of Santa's position and the particle position
        this.xd = Math.random() * (this.x - this.destX);
        this.yd = Math.random() * (this.y - this.destY);
        this.zd = Math.random() * (this.z - this.destZ);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
}
