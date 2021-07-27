package com.bayobayobayo.happyholidays.common.utils;

import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

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
}
