package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.entity.christmas.IChristmasEntity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ExplosivePresentEntity extends Entity implements IAnimatable, IChristmasEntity {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(ExplosivePresentEntity.class, EntityDataSerializers.INT);

    public static final float ENTITY_BOX_SIZE = 1.0f;
    public static final float ENTITY_BOX_HEIGHT = 1.0f;

    private AnimationFactory factory = new AnimationFactory(this);

    private int life = 40;

    @Nullable
    private LivingEntity owner;

    public ExplosivePresentEntity(EntityType<?> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();

        this.setLife(40);

        this.noPhysics = false;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    private void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 2.5F, Explosion.BlockInteraction.NONE);
        this.remove(RemovalReason.DISCARDED);
    }

    public void setOwner(LivingEntity entity) {
        this.owner = entity;
    }

    @Nullable
    public LivingEntity getOwner() {
        return owner;
    }

    @Override
    public void tick() {
        super.tick();

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));

        if (--life <= 0) {
            this.explode();
        }

        if (!this.isNoGravity() && !this.isOnGround()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        if (this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
        }

        if (!this.level.isClientSide() && this.life > 0) this.spawnIgniteParticles();
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpTo(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
        super.lerpTo(p_180426_1_, p_180426_3_, p_180426_5_, p_180426_7_, p_180426_8_, p_180426_9_, p_180426_10_);
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpMotion(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
        super.lerpMotion(p_70016_1_, p_70016_3_, p_70016_5_);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, 40);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> p_184206_1_) {
        if (DATA_FUSE_ID.equals(p_184206_1_)) {
            this.life = this.entityData.get(DATA_FUSE_ID);
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        nbt.putShort("Fuse", (short) this.getLife());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        if (nbt.contains("Fuse")) this.setLife(nbt.getShort("Fuse"));
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    private void spawnIgniteParticles() {
        Vec3 pos = this.position();

        double d0 = (Math.random() * 0.1D) + 0.25D;
        double d1 = (Math.random() * 0.1D) + 0.25D;
        double d2 = (Math.random() * 0.1D) + 0.25D;

        SimpleParticleType particleType = ParticleTypes.SMOKE;

        ((ServerLevel) this.level).sendParticles(particleType, pos.x, pos.y + d1, pos.z, 2, d0, d1, d2, 0.0D);
    }
}
