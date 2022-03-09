package com.jumpcutfindo.happyholidays.common.block.christmas;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

public class BabyPresentBlock extends PresentBlock {
    public static final String BLOCK_ID = "baby_present";
    public static final VoxelShape SHAPE = Shapes.or(
            Block.box(5.0, 0.0, 5.0, 11.0, 4.0, 11.0),
            Block.box(4.5, 4.0, 4.5, 11.5, 5.5 ,11.5)
    );

    public BabyPresentBlock() {
        super(SHAPE);
    }
}
