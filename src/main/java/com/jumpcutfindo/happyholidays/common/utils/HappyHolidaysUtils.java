package com.jumpcutfindo.happyholidays.common.utils;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class HappyHolidaysUtils {
    public static VoxelShape rotateShape(final VoxelShape shape, final Rotation rotationDir) {
        double x1 = shape.bounds().minX, x2 = shape.bounds().maxX;
        final double y1 = shape.bounds().minY, y2 = shape.bounds().maxY;
        double z1 = shape.bounds().minZ, z2 = shape.bounds().maxZ;

        if (rotationDir == Rotation.CLOCKWISE_90 || rotationDir == Rotation.COUNTERCLOCKWISE_90) {
            double temp = z1; // ]
            z1 = x1;   // ] x1 <-> z1
            x1 = temp; // ]

            temp = z2; // ]
            z2 = x2;   // ] x2 <-> z2
            x2 = temp; // ]
        }

        if (rotationDir == Rotation.CLOCKWISE_90 || rotationDir == Rotation.CLOCKWISE_180) {
            x1 = 1 - x1; // clockwise
            x2 = 1 - x2;
        }
        if (rotationDir == Rotation.COUNTERCLOCKWISE_90 || rotationDir == Rotation.CLOCKWISE_180) {
            z1 = 1 - z1; // counterclockwise
            z2 = 1 - z2;
        }

        return VoxelShapes.box(x1, y1, z1, x2, y2, z2);
    }

    public static VoxelShape combineShapes(VoxelShape[] voxelShapes) {
        VoxelShape result = VoxelShapes.empty();

        for (VoxelShape shape : voxelShapes) {
            result = VoxelShapes.join(result, shape, IBooleanFunction.OR);
        }

        return result;
    }

    public static BlockPos getPosInFront(Direction facingDirection, BlockPos pos, double offset) {
        BlockPos resultPos = pos;
        if (facingDirection == Direction.NORTH || facingDirection == Direction.UP || facingDirection == Direction.DOWN) {
            for (int i = 0; i < offset; i++) resultPos = resultPos.north();
        } else if (facingDirection == Direction.SOUTH) {
            for (int i = 0; i < offset; i++) resultPos = resultPos.south();
        } else if (facingDirection == Direction.EAST) {
            for (int i = 0; i < offset; i++) resultPos = resultPos.east();
        } else {
            for (int i = 0; i < offset; i++) resultPos = resultPos.west();
        }

        return resultPos;
    }

    public static String convertTicksToString(long ticks) {
        long seconds = ticks / 20;

        return String.format("%dm %ds", seconds / 60, seconds % 60);
    }

    public static BlockPos findBlockInRadius(IWorld world, BlockPos currPos, Block block, int radius) {
        BlockPos startPos = currPos.offset(-radius, -radius, -radius);

        for (int x = 0; x < radius * 2; x++) {
            for (int y = 0; y < radius * 2; y++) {
                for (int z = 0; z < radius * 2; z++) {
                    BlockPos checkingPos = startPos.offset(x, y, z);
                    if (world.getBlockState(checkingPos).getBlock().is(block)) return checkingPos;
                }
            }
        }

        return null;
    }
}
