package com.jumpcutfindo.happyholidays.common.block.christmas.food;

import java.util.Arrays;

import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.utils.BlockUtils;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChristmasFoodBlock extends ChristmasBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    
    private VoxelShape[] shape;

    private final int nutrition;
    private final float saturation;

    public ChristmasFoodBlock(Properties properties, VoxelShape[] shape, int nutrition, float saturation) {
        super(properties);

        this.shape = shape;
        this.nutrition = nutrition;
        this.saturation = saturation;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  LevelAccessor world, BlockPos pos1, BlockPos pos2) {
        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }
    
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockReader, BlockPos blockPos,
                               CollisionContext context) {
        Direction direction = blockState.getValue(FACING);

        VoxelShape[] resultShapes = Arrays.copyOf(shape, shape.length);


        if (direction == Direction.SOUTH) {
            return BlockUtils.combineShapes(resultShapes);
        } else if (direction == Direction.NORTH) {
            for (int i = 0; i < resultShapes.length; i++) {
                resultShapes[i] = BlockUtils.rotateShape(resultShapes[i], Rotation.CLOCKWISE_180);
            }

            return BlockUtils.combineShapes(resultShapes);
        } else if (direction == Direction.WEST) {
            for (int i = 0; i < resultShapes.length; i++) {
                resultShapes[i] = BlockUtils.rotateShape(resultShapes[i], Rotation.CLOCKWISE_90);
            }

            return BlockUtils.combineShapes(resultShapes);
        } else {
            // Direction.EAST
            for (int i = 0; i < resultShapes.length; i++) {
                resultShapes[i] = BlockUtils.rotateShape(resultShapes[i], Rotation.COUNTERCLOCKWISE_90);
            }

            return BlockUtils.combineShapes(resultShapes);
        }
    }

    @Override
    public void configureBlock() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutoutMipped());
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader world, BlockPos position) {
        return world.getBlockState(position.below()).getMaterial().isSolid();
    }

    public int getNutrition() {
        return nutrition;
    }

    public float getSaturation() {
        return saturation;
    }

    public static boolean isChristmasFoodBlock(Block block) {
        return block instanceof ChristmasFoodBlock;
    }
}
