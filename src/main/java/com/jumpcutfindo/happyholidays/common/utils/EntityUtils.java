package com.jumpcutfindo.happyholidays.common.utils;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EntityUtils {
    public static List<Player> findPlayersInRadius(Level world, Vec3 pos, double radius) {
        AABB box = new AABB(new BlockPos(pos)).inflate(radius);

        return world.getEntitiesOfClass(Player.class, box);
    }
}
