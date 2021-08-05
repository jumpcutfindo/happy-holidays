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
    public static final String BLOCK_ID = "baby_present_block";
    public static final VoxelShape SHAPE = VoxelShapes.or(
            Block.box(5.0, 0.0, 5.0, 11.0, 4.0, 11.0),
            Block.box(4.25, 4.0, 4.25, 11.75, 5.5 ,11.75)
    );

    private BlockState nextBlockState;

    public BabyPresentBlock() {
        super(BLOCK_ID, SHAPE);
    }

    @Override
    public void configureBlock() {
        super.configureBlock();
        this.nextBlockState = BlockRegistry.ADULT_PRESENT_BLOCK.get().defaultBlockState();
    }

    @Override
    public void randomTick(BlockState blockState, ServerWorld world, BlockPos blockPos, Random random) {
        if (!world.isAreaLoaded(blockPos, 1)) return;

        // Destroy block if it isn't able to survive
        if (!this.canSurvive(blockState, world, blockPos)) this.updateShape(blockState, Direction.UP,
                world.getBlockState(blockPos.above()), world, blockPos, blockPos.above());

        boolean isGrow = PresentBlock.canGrow(world, blockState, blockPos)
                && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, blockPos, blockState, random.nextInt((int)(1.0 / GROWTH_PROBABILITY)) == 0);

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
