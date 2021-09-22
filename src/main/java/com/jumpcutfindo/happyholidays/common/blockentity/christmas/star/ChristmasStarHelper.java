package com.jumpcutfindo.happyholidays.common.blockentity.christmas.star;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.world.WorldEvent;

public class ChristmasStarHelper {
    public static Set<BlockPos> starLocations = Sets.newHashSet();

    public static void onWorldLoad(WorldEvent.Load event) {
        clearCache();
    }

    public static void clearCache() {
        starLocations = Sets.newHashSet();
    }

    public static void cacheStarLocation(BlockPos starLocation) {
        if (starLocation != null) starLocations.add(starLocation);
    }

    public static void removeStarLocation(BlockPos starLocation) {
        starLocations.remove(starLocation);
    }

    public static ChristmasStarBlockEntity getStarInfluencingPos(Level level, BlockPos blockPos, int[] radii, int maxIndex) {
        // Find all blocks that could possibly affect the block at blockPos
        int maxRadius = radii[maxIndex];
        List<ChristmasStarBlockEntity> blockEntityList = getStarsInRadius(level, blockPos, maxRadius);

        // Iterate through the list and figure out the highest level
        ChristmasStarBlockEntity influencingBlockEntity = null;
        for (ChristmasStarBlockEntity blockEntity : blockEntityList) {
            int effectRadius = radii[blockEntity.getCurrentTier()];
            AABB effectBoundingBox = new AABB(blockEntity.getBlockPos()).inflate(effectRadius);

            // Check whether the tile entity being considered can influence the block, and whether it is at a higher
            // tier compared to the current tile entity
            if (effectBoundingBox.contains(blockPos.getX(), blockPos.getY(), blockPos.getZ())) {
                if (influencingBlockEntity == null || blockEntity.getCurrentTier() > influencingBlockEntity.getCurrentTier()) {
                    influencingBlockEntity = blockEntity;
                }
            }
        }

        return influencingBlockEntity;
    }

    public static ChristmasStarBlockEntity getStarInfluencingEntity(Level level, Vec3 vector3d) {
        return getStarInfluencingPos(level, new BlockPos(vector3d), ChristmasStarBlockEntity.ENTITY_EFFECT_RADIUS, ChristmasStarBlockEntity.MAX_RADIUS_INDEX);
    }

    public static ChristmasStarBlockEntity getStarInfluencingBlock(Level level, BlockPos pos) {
        return getStarInfluencingPos(level, pos, ChristmasStarBlockEntity.BLOCK_EFFECT_RADIUS, ChristmasStarBlockEntity.MAX_RADIUS_INDEX);
    }

    public static List<ChristmasStarBlockEntity> getStarsInRadius(Level level, BlockPos pos, int maxRadius) {
        AABB blockBoundingBox = new AABB(pos).inflate(maxRadius);

        List<BlockPos> relevantLocations = starLocations.stream().filter(starLocation -> {
            int x = starLocation.getX();
            int y = starLocation.getY();
            int z = starLocation.getZ();

            return blockBoundingBox.contains(x, y, z);
        }).collect(Collectors.toList());

        List<ChristmasStarBlockEntity> starBlockEntities = Lists.newArrayList();

        for (BlockPos starLocation : relevantLocations) {
            BlockEntity blockEntity = level.getBlockEntity(starLocation);
            if (blockEntity instanceof ChristmasStarBlockEntity) starBlockEntities.add((ChristmasStarBlockEntity) blockEntity);
        }

        return starBlockEntities;
    }
}
