package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.ornaments.head;

import com.jumpcutfindo.happyholidays.common.block.christmas.decorations.OrnamentBlock;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class HeadOrnamentBlock extends OrnamentBlock {
    public static final VoxelShape[] HEAD_ORNAMENT_SHAPES = new VoxelShape[] {
        Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D),
        Block.box(4.0D, 4.5D, 4.0D, 12.0D, 12.5D, 12.0D),
        Block.box(4.0D, 0.0D, 0.0D, 12.0D, 10.0D, 8.0D)
    };

    public HeadOrnamentBlock(String blockId) {
        super(blockId, HEAD_ORNAMENT_SHAPES);
    }
}
