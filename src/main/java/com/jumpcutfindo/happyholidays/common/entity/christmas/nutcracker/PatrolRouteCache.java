package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class PatrolRouteCache {
    private static ItemStack cachedPatrolOrders = ItemStack.EMPTY;
    private static PatrolRoute cachedRoute = new PatrolRoute();
    private static List<RouteParticleSpawnPos> particleSpawnLocations;

    public static PatrolRoute getCachedRoute() {
        return cachedRoute;
    }

    public static boolean compareAndUpdate(ItemStack patrolOrders) {
        if (isSame(patrolOrders)) return true;
        else {
            CompoundTag patrolOrdersTag = patrolOrders.getOrCreateTag();
            PatrolRoute patrolRoute = new PatrolRoute();
            if (patrolOrdersTag.contains("PatrolRoute")) {
                patrolRoute.deserializeTag(patrolOrdersTag.getCompound("PatrolRoute"));
            }

            cachedPatrolOrders = patrolOrders.copy();
            cachedRoute = patrolRoute;

            generateParticleSpawnPoints();

            return false;
        }
    }

    public static boolean isSame(ItemStack patrolOrders) {
        return ItemStack.isSameItemSameTags(patrolOrders, cachedPatrolOrders);
    }

    public static void generateParticleSpawnPoints() {
        particleSpawnLocations = new ArrayList<>();

        if (cachedRoute.getPoints().size() == 1) particleSpawnLocations.add(new RouteParticleSpawnPos(cachedRoute.getStart(), true));

        for (int i = 1; i < cachedRoute.getPoints().size(); i++) {
            BlockPos pointA = cachedRoute.getPoints().get(i-1);
            BlockPos pointB = cachedRoute.getPoints().get(i);

            particleSpawnLocations.add(new RouteParticleSpawnPos(pointA, true));

            if (pointA.getX() == pointB.getX()) {
                if (pointA.getZ() >= pointB.getZ()) {
                    for (int j = pointA.getZ(); j > pointB.getZ(); j--) {
                        particleSpawnLocations.add(new RouteParticleSpawnPos(pointA.getX(), pointA.getY(), j, false));
                    }
                } else {
                    for (int j = pointA.getZ(); j < pointB.getZ(); j++) {
                        particleSpawnLocations.add(new RouteParticleSpawnPos(pointA.getX(), pointA.getY(), j, false));
                    }
                }
            } else if (pointA.getZ() == pointB.getZ()) {
                if (pointA.getX() >= pointB.getX()) {
                    for (int j = pointA.getX(); j > pointB.getX(); j--) {
                        particleSpawnLocations.add(new RouteParticleSpawnPos(j, pointA.getY(), pointA.getZ(), false));
                    }
                } else {
                    for (int j = pointA.getX(); j < pointB.getX(); j++) {
                        particleSpawnLocations.add(new RouteParticleSpawnPos(j, pointA.getY(), pointA.getZ(), false));
                    }
                }
            }

            particleSpawnLocations.add(new RouteParticleSpawnPos(pointB,true));
        }
    }

    public static List<RouteParticleSpawnPos> getParticleSpawnLocations() {
        return particleSpawnLocations;
    }

    public static class RouteParticleSpawnPos extends BlockPos {
        private final boolean isPrimaryPoint;
        public RouteParticleSpawnPos(BlockPos pos, boolean isPrimaryPoint) {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.isPrimaryPoint = isPrimaryPoint;
        }

        public RouteParticleSpawnPos(int x, int y, int z, boolean isPrimaryPoint) {
            super(x, y, z);
            this.isPrimaryPoint = isPrimaryPoint;
        }

        public boolean isPrimary() {
            return this.isPrimaryPoint;
        }
    }
}
