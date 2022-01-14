package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import java.util.Map;

import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.item.christmas.walnut.WalnutAmmo;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class WalnutEntity extends Projectile implements IAnimatable {
    public static final String ENTITY_ID = "walnut";

    public static final float ENTITY_BOX_SIZE = 0.25f;
    public static final float ENTITY_BOX_HEIGHT = 0.25f;

    public static final float BASE_DAMAGE = 1.0f;

    private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(WalnutEntity.class,
            EntityDataSerializers.INT);

    public static final Map<Integer, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (map) -> {
        map.put(0, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/walnut.png"));
        map.put(1, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/explosive_walnut.png"));
        map.put(2, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/sugared_walnut.png"));
        map.put(3, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/metallic_walnut.png"));
        map.put(4, new ResourceLocation(HappyHolidaysMod.MOD_ID, "textures/entity/walnut.png"));
    });

    public WalnutAmmo ammoType = WalnutAmmo.PLAIN;

    private AnimationFactory factory = new AnimationFactory(this);

    public WalnutEntity(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_TYPE_ID, 0);
    }

    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public void setAmmoType(WalnutAmmo ammoType) {
        this.ammoType = ammoType;
        this.entityData.set(DATA_TYPE_ID, WalnutAmmo.id(ammoType));
    }

    public WalnutAmmo getAmmoType() {
        return WalnutAmmo.values()[this.entityData.get(DATA_TYPE_ID)];
    }

    public ResourceLocation getResourceLocation() {
        return TEXTURE_BY_TYPE.get(this.entityData.get(DATA_TYPE_ID));
    }

    @Override
    public void shoot(double p_37266_, double p_37267_, double p_37268_, float p_37269_, float p_37270_) {
        Vec3 vec3 = (new Vec3(p_37266_, p_37267_, p_37268_)).normalize().scale((double)p_37269_);
        this.setDeltaMovement(vec3);
        double d0 = vec3.horizontalDistance();
        this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
        this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (entityHitResult.getEntity() instanceof NutcrackerEntity) return;

        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        WalnutAmmo ammoType = this.getAmmoType();
        if (ammoType == WalnutAmmo.HALVED) entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) BASE_DAMAGE / 2);
        else if (ammoType == WalnutAmmo.METALLIC) entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) BASE_DAMAGE * 2);
        else if (ammoType == WalnutAmmo.EXPLOSIVE) entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) 0);
        else entity.hurt(DamageSource.thrown(this, this.getOwner()), (float) BASE_DAMAGE);
    }

    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if (!this.level.isClientSide) {
            if (this.getAmmoType() == WalnutAmmo.EXPLOSIVE) {
                this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 1.5f, Explosion.BlockInteraction.NONE);
            }

            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    public void tick() {
        super.tick();
        HitResult hitresult = ProjectileUtil.getHitResult(this, this::canHitEntity);
        boolean flag = false;
        if (hitresult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockpos = ((BlockHitResult)hitresult).getBlockPos();
            BlockState blockstate = this.level.getBlockState(blockpos);
            if (blockstate.is(Blocks.NETHER_PORTAL)) {
                this.handleInsidePortal(blockpos);
                flag = true;
            } else if (blockstate.is(Blocks.END_GATEWAY)) {
                BlockEntity blockentity = this.level.getBlockEntity(blockpos);
                if (blockentity instanceof TheEndGatewayBlockEntity && TheEndGatewayBlockEntity.canEntityTeleport(this)) {
                    TheEndGatewayBlockEntity.teleportEntity(this.level, blockpos, blockstate, this, (TheEndGatewayBlockEntity)blockentity);
                }

                flag = true;
            }
        }

        if (hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }

        this.checkInsideBlocks();
        Vec3 vec3 = this.getDeltaMovement();
        double d2 = this.getX() + vec3.x;
        double d0 = this.getY() + vec3.y;
        double d1 = this.getZ() + vec3.z;
        this.updateRotation();
        float f;
        if (this.isInWater()) {
            for(int i = 0; i < 4; ++i) {
                float f1 = 0.25F;
                this.level.addParticle(ParticleTypes.BUBBLE, d2 - vec3.x * 0.25D, d0 - vec3.y * 0.25D, d1 - vec3.z * 0.25D, vec3.x, vec3.y, vec3.z);
            }

            f = 0.8F;
        } else {
            f = 0.99F;
        }

        this.setDeltaMovement(vec3.scale((double)f));
        if (!this.isNoGravity()) {
            Vec3 vec31 = this.getDeltaMovement();
            this.setDeltaMovement(vec31.x, vec31.y - (double)this.getGravity(), vec31.z);
        }

        this.setPos(d2, d0, d1);

        if (!this.level.isClientSide()) {
            this.createParticles();
        }
    }

    private void createParticles() {
        ServerLevel serverLevel = (ServerLevel) this.level;
        switch (this.getAmmoType()) {
        case EXPLOSIVE -> serverLevel.sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 3, 0.1, 0.0, 0.1, 0.0D);
        }
    }

    protected float getGravity() {
        return 0.03F;
    }
}
