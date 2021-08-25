package com.jumpcutfindo.happyholidays.common.entity.christmas.santa;

import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SleighEntity extends Entity implements IAnimatable {
    public static final String ENTITY_ID = "sleigh";

    public static final float ENTITY_BOX_SIZE = 32.0f / 16.0f;
    public static final float ENTITY_BOX_HEIGHT = 12.0f / 16.0f;

    private int lSteps;
    private double lx;
    private double ly;
    private double lz;
    private double lyr;
    private double lxr;
    @OnlyIn(Dist.CLIENT)
    private double lxd;
    @OnlyIn(Dist.CLIENT)
    private double lyd;
    @OnlyIn(Dist.CLIENT)
    private double lzd;

    private AnimationFactory factory = new AnimationFactory(this);

    private int remainingCharging = Integer.MAX_VALUE;
    private boolean isCharged;
    private int remainingLifespan = Integer.MAX_VALUE;
    private boolean isMoving;

    public SleighEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void onAddedToWorld() {
        this.remainingCharging = AngrySantaEntity.ATTACK_SLEIGH_CHARGE_TIME;
        this.remainingLifespan = AngrySantaEntity.ATTACK_SLEIGH_LIFETIME;

        this.noPhysics = false;
    }



    @Override
    public void tick() {
        super.tick();

        if (this.level.isClientSide) {
            if (this.lSteps > 0) {
                double d4 = this.getX() + (this.lx - this.getX()) / (double)this.lSteps;
                double d5 = this.getY() + (this.ly - this.getY()) / (double)this.lSteps;
                double d6 = this.getZ() + (this.lz - this.getZ()) / (double)this.lSteps;

                --this.lSteps;
                this.setPos(d4, d5, d6);
            } else {
                this.reapplyPosition();
            }
        } else {
            this.move(MoverType.SELF, this.getDeltaMovement());
        }

        if (this.remainingCharging > 0) {
            this.setDeltaMovement(getForward().multiply(0.01D, 0.01D, 0.01D));
        }

        if (!this.isCharged && --this.remainingCharging <= 0) {
            this.setDeltaMovement(getForward());
            this.isCharged = true;
            this.isMoving = true;
        }

        if (this.isMoving && --this.remainingLifespan <= 0) {
            this.explode();
        }

        if (!this.isNoGravity() && !this.isOnGround()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        if (!this.isDiagonal() && this.isMoving && this.getDeltaMovement().x == 0.0d && this.getDeltaMovement().z == 0.0d) {
            this.setDeltaMovement(getForward());
            this.moveTo(this.position().add(0.0D, 1.0D, 0.0D));
        } else if (this.isDiagonal() && this.isMoving && (this.getDeltaMovement().x == 0.0d || this.getDeltaMovement().z == 0.0d)) {
            this.setDeltaMovement(getForward());
            this.moveTo(this.position().add(0.0D, 1.0D, 0.0D));
        }
    }

    @Override
    public void playerTouch(PlayerEntity playerEntity) {
        super.playerTouch(playerEntity);

        if (playerEntity.getBoundingBox().intersects(this.getBoundingBox())) {
            this.explode();
        }
    }

    @Override
    public boolean canSpawnSprintParticle() {
        return true;
    }

    public SleighEntity setRotation(Vector3d vector) {
        this.yRot = (float) (Math.atan2(vector.z, vector.x)) * 180.0f / (float) Math.PI;

        return this;
    }

    public boolean isDiagonal() {
        return this.yRot % 90.0f != 0.0f;
    }

    private void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 3.0F, Explosion.Mode.NONE);
        this.remove();
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpTo(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
        this.lx = p_180426_1_;
        this.ly = p_180426_3_;
        this.lz = p_180426_5_;
        this.lSteps = 4;
        this.setDeltaMovement(this.lxd, this.lyd, this.lzd);
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpMotion(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
        this.lxd = p_70016_1_;
        this.lyd = p_70016_3_;
        this.lzd = p_70016_5_;
        this.setDeltaMovement(this.lxd, this.lyd, this.lzd);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {

    }

    @Override
    public IPacket<?> getAddEntityPacket() {
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
}
