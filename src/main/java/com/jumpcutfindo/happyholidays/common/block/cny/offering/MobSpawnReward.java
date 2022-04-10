package com.jumpcutfindo.happyholidays.common.block.cny.offering;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MobSpawnReward extends OfferingReward {
    private final EntityType<? extends LivingEntity> entityType;
    private final int count;

    private MobSpawnReward(EntityType<? extends LivingEntity> entityType, int count) {
        this.entityType = entityType;
        this.count = count;
    }

    @Override
    public void execute(Level level, Player player, BlockPos blockPos) {
        Vec3 spawnPos = new Vec3(blockPos.getX() + 0.5D, blockPos.getY() + 1.0D, blockPos.getZ() + 0.5D);
        for (int i = 0; i < count; i++) {
            Entity entity = entityType.create(level);
            entity.setPos(spawnPos);
            level.addFreshEntity(entity);
        }
    }

    public static MobSpawnReward mob(EntityType<? extends LivingEntity> entityType, int count) {
        return new MobSpawnReward(entityType, count);
    }
}
