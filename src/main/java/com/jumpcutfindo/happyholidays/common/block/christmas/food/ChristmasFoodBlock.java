package com.jumpcutfindo.happyholidays.common.block.christmas.food;

import java.util.Arrays;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class ChristmasFoodBlock extends ChristmasBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    
    private VoxelShape[] shape;

    public ChristmasFoodBlock(String blockId, Properties properties, Item.Properties itemProperties, VoxelShape[] shape) {
        super(blockId, properties, itemProperties);
        this.shape = shape;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  IWorld world, BlockPos pos1, BlockPos pos2) {
        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }
    
    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos,
                               ISelectionContext context) {
        Direction direction = blockState.getValue(FACING);

        VoxelShape[] resultShapes = Arrays.copyOf(shape, shape.length);


        if (direction == Direction.SOUTH) {
            return HappyHolidaysUtils.combineShapes(resultShapes);
        } else if (direction == Direction.NORTH) {
            for (int i = 0; i < resultShapes.length; i++) {
                resultShapes[i] = HappyHolidaysUtils.rotateShape(resultShapes[i], Rotation.CLOCKWISE_180);
            }

            return HappyHolidaysUtils.combineShapes(resultShapes);
        } else if (direction == Direction.WEST) {
            for (int i = 0; i < resultShapes.length; i++) {
                resultShapes[i] = HappyHolidaysUtils.rotateShape(resultShapes[i], Rotation.CLOCKWISE_90);
            }

            return HappyHolidaysUtils.combineShapes(resultShapes);
        } else {
            // Direction.EAST
            for (int i = 0; i < resultShapes.length; i++) {
                resultShapes[i] = HappyHolidaysUtils.rotateShape(resultShapes[i], Rotation.COUNTERCLOCKWISE_90);
            }

            return HappyHolidaysUtils.combineShapes(resultShapes);
        }
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(this, RenderType.cutoutMipped());
    }

    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos position) {
        return world.getBlockState(position.below()).getMaterial().isSolid();
    }
}
