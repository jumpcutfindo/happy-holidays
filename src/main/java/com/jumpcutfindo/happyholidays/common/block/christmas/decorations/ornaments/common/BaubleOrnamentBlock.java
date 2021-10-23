package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common;

import com.jumpcutfindo.happyholidays.common.block.DecorationBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.BasicOrnament;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaubleOrnamentBlock extends DecorationBlock implements BasicOrnament {
    public static final String RED_BAUBLE_ID = "red_bauble";
    public static final String BLUE_BAUBLE_ID = "blue_bauble";
    public static final String YELLOW_BAUBLE_ID = "yellow_bauble";
    public static final String GREEN_BAUBLE_ID = "green_bauble";
    public static final String GOLD_BAUBLE_ID = "gold_bauble";
    public static final String SILVER_BAUBLE_ID = "silver_bauble";

    public static final VoxelShape[][] BAUBLE_SHAPES = new VoxelShape[][] {
            new VoxelShape[] { Block.box(6.0, 0.0, 6.0, 10.0, 4.0, 10.0) },
            new VoxelShape[] { Block.box(6.0, 9.5, 6.0, 10.0, 13.5, 10.0) },
            new VoxelShape[] { Block.box(6.0, 0.0, 0.0, 10.0, 6.0, 4.0) }
    };

    public BaubleOrnamentBlock() {
        super(BAUBLE_SHAPES);
    }
}
