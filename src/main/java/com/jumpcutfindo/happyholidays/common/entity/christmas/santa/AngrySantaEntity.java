package com.jumpcutfindo.happyholidays.common.entity.christmas.santa;

import com.jumpcutfindo.happyholidays.common.registry.EntityRegistry;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class AngrySantaEntity extends BaseSantaEntity {
    public static final String ENTITY_ID = "angry_santa";

    public static final int ATTACK_SLEIGH_CHARGE_TIME = 10;
    public static final int ATTACK_SLEIGH_INTERVAL = 40;
    public static final int ATTACK_SLEIGH_LIFETIME = 80;

    private Phase currentPhase = Phase.SLEIGHS;

    public AngrySantaEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

        this.goalSelector.addGoal(1, new SleighAttackGoal(this));
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();

        this.currentPhase = Phase.SLEIGHS;
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

        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(sleighEntity);

        for (int i = 0; i < 5; i ++) {
            ((ServerWorld) this.level).sendParticles(ParticleTypes.SMOKE,
                    sleighEntity.position().x + random.nextDouble(),
                    sleighEntity.position().y + random.nextDouble(),
                    sleighEntity.position().z + random.nextDouble(),
                    1, 0D, 0D, 0D, 0D);
        }
    }

    public void spawnExplosives() {

    }

    public void teleportRandomly() {

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
        // TODO: Add random switching of phases
    }

    public static class SleighAttackGoal extends Goal {
        private AngrySantaEntity santaEntity;

        private int sleighAttackTimer;
        private boolean isDiagonal;

        public SleighAttackGoal(AngrySantaEntity santaEntity) {
            this.santaEntity = santaEntity;
        }

        @Override
        public boolean canUse() {
            return santaEntity.getPhase() == Phase.SLEIGHS;
        }

        @Override
        public void start() {
            this.sleighAttackTimer = AngrySantaEntity.ATTACK_SLEIGH_INTERVAL;
        }

        @Override
        public void tick() {
            if (--sleighAttackTimer <= 0) {
                if (!isDiagonal) {
                    this.santaEntity.fireHorizontalSleighs();
                } else {
                    this.santaEntity.fireDiagonalSleighs();
                }

                isDiagonal = !isDiagonal;
                sleighAttackTimer = AngrySantaEntity.ATTACK_SLEIGH_INTERVAL;
            }
        }
    }

    private enum Phase {
        SLEIGHS, PRESENTS, TELEPORT;
    }
}
