package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.BasicOrnament;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChristmasBellBlock extends DecorationBlock implements BasicOrnament {
    public static final String RED_BELLS_ID = "red_christmas_bells";
    public static final String BLUE_BELLS_ID = "blue_christmas_bells";
    public static final String YELLOW_BELLS_ID = "yellow_christmas_bells";
    public static final String GREEN_BELLS_ID = "green_christmas_bells";
    public static final String GOLD_BELLS_ID = "gold_christmas_bells";
    public static final String SILVER_BELLS_ID = "silver_christmas_bells";

    public static final VoxelShape[][] BAUBLE_SHAPES = new VoxelShape[][] {
            new VoxelShape[] {
                    Shapes.or(
                            Shapes.or(
                                    Block.box(3.0, 0.0, 8.0, 8.0, 1.0, 13.0),
                                    Block.box(3.5, 1.0, 8.5, 7.5, 5.5, 12.5),
                                    Block.box(4.0, 4.5, 9.0, 7.0, 5.5, 12.0),
                                    Block.box(4.5, 5.0, 9.5, 6.5, 6.0, 11.5)
                            ),
                            Shapes.or(
                                    Block.box(8.0, 0.0, 3.0, 13.0, 1.0, 8.0),
                                    Block.box(8.5, 1.0, 3.5, 12.5, 5.0, 7.5),
                                    Block.box(9.0, 4.5, 4.0, 12.0, 5.5, 7.0),
                                    Block.box(9.5, 5.0, 4.5, 11.5, 6.0, 6.5)
                            )
                    ),
            },
            new VoxelShape[] { Block.box(1.0, 9.0, 5.5, 15.0, 16.0, 10.5) },
            new VoxelShape[] { Block.box(2.0, 4.0, 0.0, 14.0, 11.0, 5.0) }
    };

    public ChristmasBellBlock() {
        super(BAUBLE_SHAPES);
    }
}
