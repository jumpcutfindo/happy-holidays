package com.jumpcutfindo.happyholidays.common.entity.christmas.santa.angry;

import java.util.List;

import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class AngrySantaEntity extends BaseSantaEntity {
    public static final DataParameter<Integer> SANTA_PHASE = EntityDataManager.defineId(AngrySantaEntity.class,
            DataSerializers.INT);

    public static final String ENTITY_ID = "angry_santa";

    // TODO: Adjust values to appropriate level
    public static final int ATTACK_PHASE_SWITCH_TIMER_MAX = 100;
    public static final int ATTACK_PHASE_SWITCH_TIMER_MIN = 30;

    public static final int ATTACK_SLEIGH_CHARGE_TIME = 10;
    public static final int ATTACK_SLEIGH_INTERVAL = 40;
    public static final int ATTACK_SLEIGH_LIFETIME = 80;

    public static final int ATTACK_PRESENTS_CONSIDERATION_RADIUS = 30;
    public static final int ATTACK_PRESENTS_INTERVAL = 30;

    public static final int ATTACK_TELEPORT_CHARGE_TIME = 40;
    public static final int ATTACK_TELEPORT_INTERVAL = 100;
    public static final int ATTACK_TELEPORT_CONSIDERATION_RADIUS = 30;
    public static final float ATTACK_TELEPORT_DAMAGE = 8.0f;

    private Phase currentPhase = Phase.SLEIGHS;

    public AngrySantaEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1.0f, 4));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

        this.goalSelector.addGoal(0, new PhaseSwitchGoal(this));
        this.goalSelector.addGoal(1, new SleighAttackGoal(this));
        this.goalSelector.addGoal(1, new ExplosivePresentsAttackGoal(this));
        this.goalSelector.addGoal(1, new TeleportGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(SANTA_PHASE, Phase.SLEIGHS.ordinal());
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();

        this.currentPhase = Phase.SLEIGHS;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource dmgSource) {
        return dmgSource.isExplosion();
    }

    public void setPhase(Phase phase) {
        this.currentPhase = phase;
        this.entityData.set(SANTA_PHASE, phase.ordinal());
    }

    public void changePhaseRandomly() {
        Phase newPhase = Phase.values()[this.random.nextInt(Phase.values().length)];
        this.currentPhase = newPhase;
        this.entityData.set(SANTA_PHASE, newPhase.ordinal());
    }

    public void fireHorizontalSleighs() {
        Vector3d[] horizontals = {
                new Vector3d(1.0, 0.0, 0.0),
                new Vector3d(-1.0, 0.0, 0.0),
                new Vector3d(0.0, 0.0, 1.0),
                new Vector3d(0.0, 0.0, -1.0)
        };

        for (Vector3d horizontal : horizontals) {
            this.fireSleigh(horizontal);
        }
    }

    public void fireDiagonalSleighs() {
        Vector3d[] diagonals = {
                new Vector3d(1.0, 0.0, 1.0),
                new Vector3d(1.0, 0.0, -1.0),
                new Vector3d(-1.0, 0.0, -1.0),
                new Vector3d(-1.0, 0.0, 1.0)
        };

        for (Vector3d diagonal : diagonals) {
            this.fireSleigh(diagonal);
        }
    }

    public void fireSleigh(Vector3d vector) {
        SleighEntity sleighEntity = EntityRegistry.SLEIGH.get().create(this.level).setRotation(vector);
        sleighEntity.moveTo(this.position().add(sleighEntity.getForward().multiply(3.0d, 3.0d, 3.0d)));

        // TODO: Change to some santa sound
        this.playSound(SoundEvents.EGG_THROW, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(sleighEntity);

        for (int i = 0; i < 5; i ++) {
            ((ServerWorld) this.level).sendParticles(ParticleTypes.SMOKE,
                    sleighEntity.position().x + random.nextDouble(),
                    sleighEntity.position().y + random.nextDouble(),
                    sleighEntity.position().z + random.nextDouble(),
                    1, 0D, 0D, 0D, 0D);
        }
    }

    public void spawnExplosives(List<PlayerEntity> playerEntities) {
        for (PlayerEntity playerEntity : playerEntities) {
            if (playerEntity.isAlive()) {
                Vector3d playerPos = playerEntity.position();
                ExplosivePresentEntity explosive = EntityRegistry.EXPLOSIVE_PRESENT.get().create(this.level);

                explosive.moveTo(playerPos.add(new Vector3d(0.0d, 4.0d, 0.0d)));

                this.level.addFreshEntity(explosive);
            }
        }
    }

    public void teleportAttack(Vector3d position) {
        // TODO: Charge up animation

        // Teleport to location and stomp the ground
        this.level.playSound(null, this.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 1.0f,
                1.0f);
        this.teleportTo(position.x, position.y + 3.0D, position.z);
        this.level.playSound(null, this.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 1.0f,
                1.0f);

        List<PlayerEntity> playerEntities = this.level.getEntitiesOfClass(PlayerEntity.class,
                this.getBoundingBox().inflate(4.0));

        for (PlayerEntity playerEntity : playerEntities) {
            playerEntity.hurt(DamageSource.mobAttack(this), ATTACK_TELEPORT_DAMAGE);
        }

    }

    @Override
    public void playerTouch(PlayerEntity playerEntity) {
        if (this.getBoundingBox().intersects(playerEntity.getBoundingBox())) {
            playerEntity.hurt(DamageSource.GENERIC, playerEntity.getHealth() - 2.0f);
            playerEntity.setDeltaMovement(
                    this.random.nextDouble(),
                    this.random.nextDouble() * 2.0d,
                    this.random.nextDouble()
            );
        }
    }

    public Phase getPhase() {
        return this.currentPhase;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level.isClientSide()) {
            this.currentPhase = Phase.values()[this.entityData.get(SANTA_PHASE)];
        }
    }

    @Override
    public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (currentPhase == Phase.PRESENTS) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.summon", true));
        } else if (event.isMoving()) event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.walk", true));
        else event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.santa.idle", true));

        return PlayState.CONTINUE;
    }
}
