package com.bayobayobayo.happyholidays.common.block.christmas.presents;

import java.util.Random;

import com.bayobayobayo.happyholidays.common.registry.BlockRegistry;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

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

public class AdultPresentBlock extends PresentBlock {
    public static final String BLOCK_ID = "present_block";
    public static final VoxelShape SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(3.5, 0.0, 3.5, 12.5, 6.0, 12.5),
            HappyHolidaysUtils.createShape(2.5, 6.0, 2.5, 13.5, 8.0, 13.5)
    );

    private BlockState nextBlockState;

    public AdultPresentBlock() {
        super(BLOCK_ID, SHAPE);
    }

    @Override
    public void configureBlock() {
        super.configureBlock();
        this.nextBlockState = BlockRegistry.ELDER_PRESENT_BLOCK.get().defaultBlockState();
    }

    @Override
    public void randomTick(BlockState blockState, ServerWorld world, BlockPos blockPos, Random random) {
        if (!world.isAreaLoaded(blockPos, 1)) return;

        // Destroy block if it isn't able to survive
        if (!this.canSurvive(blockState, world, blockPos)) this.updateShape(blockState, Direction.UP,
                world.getBlockState(blockPos.above()), world, blockPos, blockPos.above());

        boolean isGrow = net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world ,blockPos, blockState,
                random.nextInt((int)(25.0F / GROWTH_SPEED) + 1) == 0);

        if (isGrow) {
            world.setBlock(blockPos, nextBlockState, 2);
            world.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.WOOL_BREAK,
                    SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.sendParticles(ParticleTypes.CLOUD, blockPos.getX() + random.nextDouble(), blockPos.getY() + random.nextDouble(),
                    blockPos.getZ() + random.nextDouble(), 1, 0D, 0D, 0D, 0.0D);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, blockPos, blockState);
        }
    }
}