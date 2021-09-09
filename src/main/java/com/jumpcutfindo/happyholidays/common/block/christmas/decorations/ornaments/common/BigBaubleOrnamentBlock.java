package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.common;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.OrnamentBlock;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class BigBaubleOrnamentBlock extends OrnamentBlock {
    public static final String BIG_RED_BAUBLE_ID = "big_red_bauble";
    public static final String BIG_BLUE_BAUBLE_ID = "big_blue_bauble";
    public static final String BIG_YELLOW_BAUBLE_ID = "big_yellow_bauble";
    public static final String BIG_GREEN_BAUBLE_ID = "big_green_bauble";
    public static final String BIG_GOLD_BAUBLE_ID = "big_gold_bauble";
    public static final String BIG_SILVER_BAUBLE_ID = "big_silver_bauble";

    public static final VoxelShape[][] BIG_BAUBLE_SHAPES = new VoxelShape[][] {
            new VoxelShape[] { Block.box(4.0, 0.0, 4.0, 12.0, 8.0, 12.0) },
            new VoxelShape[] { Block.box(4.0, 3.75, 4.0, 12.0, 11.75, 12.0) },
            new VoxelShape[] { Block.box(4.0, 0.0, 0.0, 12.0, 11.75, 8.0) }
    };

    public BigBaubleOrnamentBlock() {
        super(BIG_BAUBLE_SHAPES);
    }
}
