package com.jumpcutfindo.happyholidays.common.block.christmas.decorations.misc;

import com.jumpcutfindo.happyholidays.common.block.WallDecorationBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FrostBlock extends WallDecorationBlock {
    public static final String BLOCK_ID = "frost";

    public static final BlockBehaviour.Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.ICE)
                    .strength(0.1f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .noCollission();

    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0 ,16.0, 16.0,0.5);

    public FrostBlock() {
        super(BLOCK_PROPERTIES, SHAPE);
    }
}
