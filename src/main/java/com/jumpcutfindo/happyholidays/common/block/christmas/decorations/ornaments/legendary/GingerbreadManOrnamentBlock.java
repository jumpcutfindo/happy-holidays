package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.OrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.LegendaryOrnament;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GingerbreadManOrnamentBlock extends OrnamentBlock implements LegendaryOrnament {
    public static final String BLOCK_ID = "gingerbread_man_ornament";

    public static final VoxelShape[][] ORNAMENT_SHAPES = {
            { Block.box(2.5, 0.0, 2.5, 13.5, 1.0, 13.5) },
            { Block.box(2.5, 3.5, 5.5, 13.5, 14.5, 10.5) },
            { Block.box(3.0, 0.0, 0.0, 13.0, 10.0, 4.5) }
    };

    public GingerbreadManOrnamentBlock() {
        super(ORNAMENT_SHAPES);
    }
}
