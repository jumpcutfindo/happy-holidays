package com.jumpcutfindo.happyholidays.handlers;

import java.util.List;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.entity.christmas.nutcracker.PatrolRouteCache;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HappyHolidaysMod.MOD_ID, value = Dist.CLIENT)
public class PatrolRouteRenderingHandler {
    private static int internalTick = 0;
    private static int currentParticleSpawnIndex = 0;

    @SubscribeEvent
    public static void onClientTick(TickEvent.PlayerTickEvent playerTickEvent) {
        Player player = Minecraft.getInstance().player;

        if (player != null && player.getItemInHand(InteractionHand.MAIN_HAND).is(ChristmasItems.PATROL_ORDERS.get())) {
            Level level = player.getLevel();
            if(!level.isClientSide()) return;

            ItemStack patrolOrders = player.getItemInHand(InteractionHand.MAIN_HAND);

            // Send the obtained ItemStack to update the cache in the case where there is a change (e.g. changed Patrol Orders, added new point etc.)
            // If a change was detected, we also reset this handler
            boolean isChanged = !PatrolRouteCache.compareAndUpdate(patrolOrders);
            if (isChanged) resetHandler();

            if (!PatrolRouteCache.getParticleSpawnLocations().isEmpty()) {
                if (internalTick % 30 == 0) {
                    List<BlockPos> points = PatrolRouteCache.getCachedRoute().getPoints();
                    int size = PatrolRouteCache.getCachedRoute().isComplete() ? points.size() : points.size() - 1;

                    for (int i = 1; i < size; i++) {
                        BlockPos pos = points.get(i);
                        spawnPrimaryPointParticles(level, pos);
                    }
                }

                if (internalTick % 5 == 0) {
                    PatrolRouteCache.RouteParticleSpawnPos pos = PatrolRouteCache.getParticleSpawnLocations().get(currentParticleSpawnIndex);
                    if (!pos.isPrimary()) spawnSecondaryPointParticles(level, pos);
                    addIndex();
                }

                if (!PatrolRouteCache.getCachedRoute().isComplete() && internalTick % 2 == 0) {
                    spawnStartPointParticles(level, PatrolRouteCache.getCachedRoute().getStart());

                    BlockPos pos = PatrolRouteCache.getCachedRoute().getEnd();
                    spawnCurrentPointParticles(level, pos);
                }
            }
        }

        addCounter();
    }
    private static void spawnStartPointParticles(Level level, BlockPos pos) {
        level.addParticle(ChristmasParticles.CHRISTMAS_MEDIUM_RED_PARTICLE.get(), pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, 0.0D, 0.02D, 0.0D);
    }

    private static void spawnPrimaryPointParticles(Level level, BlockPos pos) {
        level.addParticle(ParticleTypes.SQUID_INK, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, 0.0D, 0.02D, 0.0D);
    }

    private static void spawnSecondaryPointParticles(Level level, BlockPos pos) {
        level.addParticle(ParticleTypes.ELECTRIC_SPARK, pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
    }

    private static void spawnCurrentPointParticles(Level level, BlockPos pos) {
        level.addParticle(ChristmasParticles.CHRISTMAS_MEDIUM_GREEN_PARTICLE.get(), pos.getX() + 0.5D, pos.getY() + 1.5D, pos.getZ() + 0.5D, 0.0D, 0.02D, 0.0D);
    }

    private static void addCounter() {
        internalTick++;
    }

    private static void addIndex() {
        currentParticleSpawnIndex++;

        if (currentParticleSpawnIndex >= PatrolRouteCache.getParticleSpawnLocations().size()) {
            currentParticleSpawnIndex = 0;
        }
    }

    private static void resetHandler() {
        currentParticleSpawnIndex = 0;
    }
}
