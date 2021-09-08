package com.jumpcutfindo.happyholidays.common.block.christmas.presents;

import java.util.Random;

import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.server.ServerWorld;

public class BabyPresentBlock extends PresentBlock {
    public static final String BLOCK_ID = "baby_present";
    public static final VoxelShape SHAPE = VoxelShapes.or(
            Block.box(5.0, 0.0, 5.0, 11.0, 4.0, 11.0),
            Block.box(4.25, 4.0, 4.25, 11.75, 5.5 ,11.75)
    );

    public BabyPresentBlock() {
        super(BLOCK_ID, SHAPE);
    }
}
