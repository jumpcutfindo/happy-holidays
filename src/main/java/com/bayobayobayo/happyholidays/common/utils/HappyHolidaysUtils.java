package com.bayobayobayo.happyholidays.common.utils;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class HappyHolidaysUtils {
    private static final double BOUNDING_BOX_UNIT = 1.0 / 16.0;

    public static VoxelShape createVoxelShape(double xMin, double yMin, double zMin, double xMax, double yMax,
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
}
