package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.legendary;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SantaElfOrnamentBlock extends LegendaryOrnamentBlock {
    public static final String BLOCK_ID = "santa_elf_ornament";

    public static final VoxelShape[][] ORNAMENT_SHAPES = {
            {
                    Block.box(4.5, 0.0, 4.5, 11.5, 5.0, 11.5),
                    Block.box(4.0, 5.0, 4.0, 12.0, 6.0, 12.0),
                    Block.box(4.5, 6.0, 4.5, 11.5, 7.0, 11.5),
                    Block.box(5.0, 7.0, 4.5, 11.0, 8.0, 10.5),
                    Block.box(5.5, 8.0, 4.5, 10.5, 9.0, 9.5),
                    Block.box(7.0, 9.0, 3.5, 9.0, 10.0, 7.5),
                    Block.box(7.0, 7.5, 1.5, 9.0, 9.5, 3.5)
            },
            {
                    Block.box(4.5, 4.75, 4.5, 11.5, 9.75, 11.5),
                    Block.box(4.0, 9.75, 4.0, 12.0, 10.75, 12.0),
                    Block.box(4.5, 10.75, 4.5, 11.5, 11.75, 11.5),
                    Block.box(5.0, 11.75, 4.5, 11.0, 12.75, 10.5),
                    Block.box(5.5, 12.75, 4.5, 10.5, 13.75, 9.5),
                    Block.box(7.0, 13.75, 3.5, 9.0, 14.75, 7.5),
                    Block.box(7.0, 12.25, 1.5, 9.0, 14.25, 3.5)
            },
            { Block.box(4.0, 0.0, 0.0, 12.0, 10.0, 8.0) }
    };

    public SantaElfOrnamentBlock() {
        super(BLOCK_ID, ORNAMENT_SHAPES);
    }
}
