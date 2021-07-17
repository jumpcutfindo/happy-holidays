package com.bayobayobayo.happyholidays.common.block.christmas.presents;

import java.util.Random;

import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.server.ServerWorld;

public class BabyPresentBlock extends PresentBlock {
    public static final String BLOCK_ID = "baby_present_block";
    public static final VoxelShape SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(5.0, 0.0, 5.0, 11.0, 4.0, 11.0),
            HappyHolidaysUtils.createShape(4.25, 4.0, 4.25, 11.75, 5.5 ,11.75)
    );

    private BlockState nextBlockState;

    public BabyPresentBlock() {
        super(BLOCK_ID, SHAPE);
    }

    @Override
    public void configureBlock() {
        super.configureBlock();
        this.nextBlockState =
                ModuleHandler.CHRISTMAS_HANDLER.getRegisteredBlock(AdultPresentBlock.BLOCK_ID).get().defaultBlockState();
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
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, blockPos, blockState);
        }
    }
}
