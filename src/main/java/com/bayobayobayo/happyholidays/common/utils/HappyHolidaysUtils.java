package com.bayobayobayo.happyholidays.common.utils;

import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class HappyHolidaysUtils {
    private static final double BOUNDING_BOX_UNIT = 1.0 / 16.0;

    public static VoxelShape createShape(double xMin, double yMin, double zMin, double xMax, double yMax,
                                         double zMax) {
        return VoxelShapes.create(new AxisAlignedBB(
                BOUNDING_BOX_UNIT * xMin,
                BOUNDING_BOX_UNIT * yMin,
                BOUNDING_BOX_UNIT * zMin,
                BOUNDING_BOX_UNIT * xMax,
                BOUNDING_BOX_UNIT * yMax,
                BOUNDING_BOX_UNIT * zMax
            )
        );
    }

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
}
