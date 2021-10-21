package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.OrnamentBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.LegendaryOrnament;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GrinchOrnamentBlock extends OrnamentBlock implements LegendaryOrnament {
    public static final String BLOCK_ID = "grinch_ornament";

    public static final VoxelShape[][] ORNAMENT_SHAPES = {
            {
                    Block.box(3.0, 0.0, 3.0, 13.0, 4.0, 13.0),
                    Block.box(4.0, 4.0, 3.0, 12.0, 10.0, 13.0)
            },
            {
                    Block.box(3.0, 3.75, 3.0, 13.0, 7.75, 13.0),
                    Block.box(4.0, 7.75, 3.0, 12.0, 13.75, 13.0)
            },
            { Block.box(3.0, 0.0, 0.0, 13.0, 11.0, 10.0) }

    };

    public GrinchOrnamentBlock() {
        super(ORNAMENT_SHAPES);
    }
}
