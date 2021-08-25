package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import net.minecraft.client.renderer.entity.TNTRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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

public class ExplosivePresentEntity extends Entity implements IAnimatable {
    private static final DataParameter<Integer> DATA_FUSE_ID = EntityDataManager.defineId(TNTEntity.class, DataSerializers.INT);

    public static final String ENTITY_ID = "explosive_present";

    public static final float ENTITY_BOX_SIZE = 1.0f;
    public static final float ENTITY_BOX_HEIGHT = 1.0f;

    private AnimationFactory factory = new AnimationFactory(this);

    private int life;

    public ExplosivePresentEntity(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();

        this.setLife(40);

        this.level.playSound(null, this.blockPosition(), SoundEvents.TNT_PRIMED, SoundCategory.BLOCKS, 1.0f, 1.0f);
        this.noPhysics = false;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
        this.entityData.set(DATA_FUSE_ID, life);
    }

    private void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 1.0F, Explosion.Mode.NONE);
        this.remove();
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

    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
        if (DATA_FUSE_ID.equals(p_184206_1_)) {
            this.life = this.entityData.get(DATA_FUSE_ID);
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) {
        nbt.putShort("Fuse", (short) this.getLife());
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) {
        this.setLife(nbt.getShort("Fuse"));
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
