package com.jumpcutfindo.happyholidays.common.block.christmas.presents;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

public class ElderPresentBlock extends PresentBlock {
    public static final String BLOCK_ID = "elder_present";
    public static final VoxelShape SHAPE = Shapes.or(
            box(2.0, 0.0, 2.0, 14.0, 8.0, 14.0),
            box(1.0, 8.0, 1.0, 15.0, 11.0, 15.0)
    );

    public ElderPresentBlock() {
        super(BLOCK_ID, SHAPE);
    }
}
