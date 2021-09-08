package com.jumpcutfindo.happyholidays.common.block.christmas.presents;

import java.util.Random;

import com.jumpcutfindo.happyholidays.common.registry.BlockRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.server.ServerWorld;

public class AdultPresentBlock extends PresentBlock {
    public static final String BLOCK_ID = "adult_present";
    public static final VoxelShape SHAPE = VoxelShapes.or(
            box(3.5, 0.0, 3.5, 12.5, 6.0, 12.5),
            box(2.5, 6.0, 2.5, 13.5, 8.0, 13.5)
    );

    public AdultPresentBlock() {
        super(BLOCK_ID, SHAPE);
    }
}
