package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasEntity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
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

public class SleighEntity extends Entity implements IAnimatable, ChristmasEntity {

    public static final float ENTITY_BOX_SIZE = 32.0f / 16.0f;
    public static final float ENTITY_BOX_HEIGHT = 12.0f / 16.0f;

    @OnlyIn(Dist.CLIENT)
    private double lxd;
    @OnlyIn(Dist.CLIENT)
    private double lyd;
    @OnlyIn(Dist.CLIENT)
    private double lzd;

    private AnimationFactory factory = new AnimationFactory(this);

    private int remainingCharging = AngrySantaEntity.ATTACK_SLEIGH_CHARGE_TIME;
    private boolean isCharged;
    private int remainingLifespan = AngrySantaEntity.ATTACK_SLEIGH_LIFETIME;
    private boolean isMoving;

    public SleighEntity(EntityType<? extends Entity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();

        this.remainingCharging = AngrySantaEntity.ATTACK_SLEIGH_CHARGE_TIME;
        this.remainingLifespan = AngrySantaEntity.ATTACK_SLEIGH_LIFETIME;

        this.noPhysics = false;
    }

    @Override
    public void tick() {
        super.tick();
        this.move(MoverType.SELF, this.getDeltaMovement());

        if (this.remainingCharging > 0) {
            this.setDeltaMovement(getForward().multiply(0.01D, 0.01D, 0.01D));
        }

        if (!this.isCharged && --this.remainingCharging <= 0) {
            this.setDeltaMovement(getForward());
            this.isCharged = true;
            this.isMoving = true;
        }

        if (this.isMoving && --this.remainingLifespan <= 0) {
            this.explode(false);
        }

        if (!this.isNoGravity() && !this.isOnGround()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        if (this.isDiagonal() && (this.getDeltaMovement().x == 0.0d || this.getDeltaMovement().z == 0.0d)) {
            this.explode(false);
        }

        if (!this.isDiagonal() && this.getDeltaMovement().x == 0.0d && this.getDeltaMovement().z == 0.0d) {
            this.explode(false);
        }
    }

    @Override
    public void playerTouch(Player playerEntity) {
        super.playerTouch(playerEntity);

        if (playerEntity.getBoundingBox().intersects(this.getBoundingBox())) {
            this.explode(true);
        }
    }

    @Override
    public boolean canSpawnSprintParticle() {
        return true;
    }

    public SleighEntity setRotation(Vec3 vector) {
        this.setYRot((float) (Math.atan2(vector.z, vector.x)) * 180.0f / (float) Math.PI);

        return this;
    }

    public boolean isDiagonal() {
        return this.getYRot() % 90.0f != 0.0f;
    }

    private void explode(boolean isHitPlayer) {
        float explosivePower = isHitPlayer ? 3.0F : 1.0F;

        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), explosivePower, Explosion.BlockInteraction.NONE);
        this.remove(RemovalReason.DISCARDED);
    }

    @OnlyIn(Dist.CLIENT)
    public void lerpTo(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
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
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("RemainingCharging")) this.remainingCharging = tag.getInt("RemainingCharging");
        this.isCharged = tag.getBoolean("IsCharged");
        if (tag.contains("RemainingLifespan")) this.remainingLifespan = tag.getInt("RemainingLifespan");
        this.isMoving = tag.getBoolean("IsMoving");
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("RemainingCharging", this.remainingCharging);
        tag.putBoolean("IsCharged", this.isCharged);
        tag.putInt("RemainingLifespan", this.remainingLifespan);
        tag.putBoolean("IsMoving", this.isMoving);
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
}
