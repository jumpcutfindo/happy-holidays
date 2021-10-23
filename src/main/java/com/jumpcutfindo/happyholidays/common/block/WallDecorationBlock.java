package com.jumpcutfindo.happyholidays.common.block;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.utils.BlockUtils;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WallDecorationBlock extends ChristmasBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    private final VoxelShape shape;

    public WallDecorationBlock(Properties properties, VoxelShape shape) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
        );

        this.shape = shape;
    }

    @Override
    public void configureBlock() {
        ItemBlockRenderTypes.setRenderLayer(this, RenderType.cutoutMipped());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockReader, BlockPos blockPos,
                               CollisionContext context) {
        Direction direction = blockState.getValue(FACING);

        if (direction == Direction.SOUTH) {
            return shape;
        } else if (direction == Direction.NORTH) {
            return BlockUtils.rotateShape(shape, Rotation.CLOCKWISE_180);
        } else if (direction == Direction.WEST) {
            return BlockUtils.rotateShape(shape, Rotation.CLOCKWISE_90);
        } else {
            // Direction.EAST
            return BlockUtils.rotateShape(shape, Rotation.COUNTERCLOCKWISE_90);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFaceDirection = context.getClickedFace();

        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean isWaterlogged = fluidstate.getType() == Fluids.WATER;

        if (clickedFaceDirection == Direction.UP || clickedFaceDirection == Direction.DOWN) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, isWaterlogged);
        } else {
            return this.defaultBlockState().setValue(FACING, clickedFaceDirection).setValue(WATERLOGGED, isWaterlogged);
        }
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  LevelAccessor world, BlockPos pos1, BlockPos pos2) {
        if (blockState.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Override
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader world, BlockPos position) {
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
