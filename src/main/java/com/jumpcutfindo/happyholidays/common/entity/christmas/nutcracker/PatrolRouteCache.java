package com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker;

import java.util.ArrayList;
import java.util.List;

import com.jumpcutfindo.happyholidays.common.item.christmas.PatrolOrdersItem;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class PatrolRouteCache {
    private static final double STEP_SIZE = 1.0d;

    private static ItemStack cachedPatrolOrders = ItemStack.EMPTY;
    private static PatrolRoute cachedRoute = new PatrolRoute();
    private static List<RouteParticleSpawnPos> particleSpawnLocations;

    public static PatrolRoute getCachedRoute() {
        return cachedRoute;
    }

    public static boolean compareAndUpdate(Level level, ItemStack patrolOrders) {
        if (isSame(patrolOrders)) return true;
        else {
            PatrolRoute patrolRoute = PatrolOrdersItem.extractRoute(patrolOrders);
            cachedPatrolOrders = patrolOrders.copy();
            cachedRoute = patrolRoute;

            generateParticleSpawnPoints(level);

            return false;
        }
    }

    public static boolean isSame(ItemStack patrolOrders) {
        return ItemStack.isSameItemSameTags(patrolOrders, cachedPatrolOrders);
    }

    public static void generateParticleSpawnPoints(Level level) {
        particleSpawnLocations = new ArrayList<>();

        if (cachedRoute.getPoints().size() == 1) particleSpawnLocations.add(new RouteParticleSpawnPos(cachedRoute.getStart(), true));

        for (int i = 1; i < cachedRoute.getPoints().size(); i++) {
            BlockPos pointA = cachedRoute.getPoints().get(i-1);
            BlockPos pointB = cachedRoute.getPoints().get(i);

            // Determine the number of steps we're gonna take
            double startX = pointA.getX() + 0.5, startY = pointA.getY() + 1.5, startZ = pointA.getZ() + 0.5;
            double endX = pointB.getX() + 0.5, endY = pointB.getY() + 1.5, endZ = pointB.getZ() + 0.5;

            double dX = (endX - startX);
            double dY = (endY - startY);
            double dZ = (endZ - startZ);

            int stepsX = (int) Math.abs(dX / STEP_SIZE);
            int stepsY = (int) Math.abs(dY / STEP_SIZE);
            int stepsZ = (int) Math.abs(dZ / STEP_SIZE);

            int steps = Math.max(Math.max(Math.max(stepsX, stepsY), stepsZ), 2);
            double stepX = dX / steps;
            double stepY = dY / steps;
            double stepZ = dZ / steps;

            // Create the points for steps
            for (int j = 0; j <= steps; j++) {
                particleSpawnLocations.add(new RouteParticleSpawnPos(startX + stepX * j, startY + stepY * j, startZ + stepZ * j, (j == 0 || j == steps)));
            }
        }
    }

    public static List<RouteParticleSpawnPos> getParticleSpawnLocations() {
        return particleSpawnLocations;
    }

    public static class RouteParticleSpawnPos extends Vec3 {
        private final boolean isPrimaryPoint;

        public RouteParticleSpawnPos(BlockPos pos, boolean isPrimaryPoint) {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.isPrimaryPoint = isPrimaryPoint;
        }

        public RouteParticleSpawnPos(double x, double y, double z, boolean isPrimaryPoint) {
            super(x, y, z);
            this.isPrimaryPoint = isPrimaryPoint;
        }

        public BlockPos getApproxBlockPos() {
            return new BlockPos((int) x, (int) y, (int) z);
        }

        public boolean isPrimary() {
            return this.isPrimaryPoint;
        }
    }
}
