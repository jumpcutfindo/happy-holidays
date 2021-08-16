package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class WallDecorationBlock extends ChristmasBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    private final VoxelShape shape;

    public WallDecorationBlock(String blockId, Properties properties, VoxelShape shape) {
        super(blockId, properties, ITEM_PROPERTIES);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
        );

        this.shape = shape;
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(this, RenderType.cutoutMipped());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos,
                               ISelectionContext context) {
        Direction direction = blockState.getValue(FACING);

        if (direction == Direction.SOUTH) {
            return shape;
        } else if (direction == Direction.NORTH) {
            return HappyHolidaysUtils.rotateShape(shape, Rotation.CLOCKWISE_180);
        } else if (direction == Direction.WEST) {
            return HappyHolidaysUtils.rotateShape(shape, Rotation.CLOCKWISE_90);
        } else {
            // Direction.EAST
            return HappyHolidaysUtils.rotateShape(shape, Rotation.COUNTERCLOCKWISE_90);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction clickedFaceDirection = context.getClickedFace();
        if (clickedFaceDirection == Direction.UP || clickedFaceDirection == Direction.DOWN) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        } else {
            return this.defaultBlockState().setValue(FACING, clickedFaceDirection);
        }
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  IWorld world, BlockPos pos1, BlockPos pos2) {
        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos position) {
        Direction facingDirection = blockState.getValue(FACING);

        BlockState onBlockState = facingDirection == Direction.NORTH ? world.getBlockState(position.south())
                : facingDirection == Direction.SOUTH ? world.getBlockState(position.north())
                : facingDirection == Direction.EAST ? world.getBlockState(position.west())
                : facingDirection == Direction.WEST ? world.getBlockState(position.east()) : null;


        if (onBlockState != null) {
            return onBlockState.isFaceSturdy(world, position, facingDirection);
        }

        return false;
    }
}
