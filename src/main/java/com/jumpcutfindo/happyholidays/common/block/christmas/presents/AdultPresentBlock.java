package com.jumpcutfindo.happyholidays.common.block.christmas.presents;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

public class AdultPresentBlock extends PresentBlock {
    public static final String BLOCK_ID = "adult_present";
    public static final VoxelShape SHAPE = Shapes.or(
            box(4.0, 0.0, 4.0, 12.0, 6.0, 12.0),
            box(3.0, 6.0, 3.0, 13.0, 8.0, 13.0)
    );

    public AdultPresentBlock() {
        super(BLOCK_ID, SHAPE);
    }
}
