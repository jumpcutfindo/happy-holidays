package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ConnectedOrnamentBlock extends ChristmasBlock implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<WallDecorationShape> WALL_SHAPE = EnumProperty.create("decoration_shape",
            WallDecorationShape.class);

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    private final VoxelShape shape;

    public ConnectedOrnamentBlock(Properties blockProperties, VoxelShape shape) {
        super(blockProperties);

        this.shape = shape;

        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WALL_SHAPE, WallDecorationShape.STRAIGHT)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(this, RenderType.translucent());
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction clickedFaceDirection = context.getClickedFace();
        BlockPos clickedPos = context.getClickedPos();
        World world = context.getLevel();

        if (!clickedFaceDirection.getAxis().isHorizontal()) {
            // If clicked face is vertical, we change it to horizontal
            clickedFaceDirection = context.getHorizontalDirection().getOpposite();
        }

        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean isWaterlogged = fluidstate.getType() == Fluids.WATER;

        return this.defaultBlockState()
                .setValue(FACING, clickedFaceDirection)
                .setValue(WALL_SHAPE, getWallShapeState(world, clickedPos, clickedFaceDirection))
                .setValue(WATERLOGGED, isWaterlogged);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WALL_SHAPE, FACING, WATERLOGGED);
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

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  IWorld world, BlockPos pos1, BlockPos pos2) {
        if (blockState.getValue(WATERLOGGED)) {
            world.getLiquidTicks().scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        // Remember facing direction, we don't want to change this
        Direction facingDirection = blockState.getValue(FACING);

        // Break the block if the block cannot survive
        if (!this.canSurvive(blockState, world, pos1)) return Blocks.AIR.defaultBlockState();

        return blockState
                .setValue(FACING, facingDirection)
                .setValue(WALL_SHAPE, getWallShapeState(world, pos1, facingDirection));
    }

    @Override
    public FluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos position) {
        Direction facingDirection = blockState.getValue(FACING);

        BlockState onBlockState = facingDirection == Direction.NORTH ? world.getBlockState(position.south())
                : facingDirection == Direction.SOUTH ? world.getBlockState(position.north())
                : facingDirection == Direction.EAST ? world.getBlockState(position.west())
                : facingDirection == Direction.WEST ? world.getBlockState(position.east()) : null;


        if (onBlockState != null) {
            return onBlockState.isFaceSturdy(world, position, facingDirection) || onBlockState.getBlock().is(BlockTags.LEAVES);
        }

        return false;
    }

    private WallDecorationShape getWallShapeState(IWorld world, BlockPos blockPos, Direction facingDirection) {
        BlockPos leftPos, rightPos, oppPos;
        Direction leftDir, rightDir, oppDir;

        if (facingDirection == Direction.NORTH) {
            leftPos = blockPos.west();
            rightPos = blockPos.east();
            oppPos = blockPos.north();

            leftDir = Direction.WEST;
            rightDir = Direction.EAST;
            oppDir = Direction.NORTH;
        } else if (facingDirection == Direction.SOUTH) {
            leftPos = blockPos.east();
            rightPos = blockPos.west();
            oppPos = blockPos.south();

            leftDir = Direction.EAST;
            rightDir = Direction.WEST;
            oppDir = Direction.SOUTH;
        } else if (facingDirection == Direction.EAST) {
            leftPos = blockPos.north();
            rightPos = blockPos.south();
            oppPos = blockPos.east();

            leftDir = Direction.NORTH;
            rightDir = Direction.SOUTH;
            oppDir = Direction.EAST;
        } else {
            leftPos = blockPos.south();
            rightPos = blockPos.north();
            oppPos = blockPos.west();

            leftDir = Direction.SOUTH;
            rightDir = Direction.NORTH;
            oppDir = Direction.WEST;
        }

        boolean isLeftSupportive =
                world.getBlockState(leftPos).isFaceSturdy(world, leftPos, leftDir) || world.getBlockState(leftPos).is(BlockTags.LEAVES);
        boolean isRightSupportive =
                world.getBlockState(rightPos).isFaceSturdy(world, rightPos, rightDir) || world.getBlockState(rightPos).is(BlockTags.LEAVES);
        boolean isOppositeSupportive =
                world.getBlockState(oppPos).isFaceSturdy(world, oppPos, oppDir) || world.getBlockState(oppPos).is(BlockTags.LEAVES);

        if (isLeftSupportive && isRightSupportive && isOppositeSupportive) {
            return WallDecorationShape.ALL_FACE;
        } else if (isLeftSupportive && isRightSupportive) {
            return WallDecorationShape.SIDE_FACE;
        } else if (isLeftSupportive) {
            return WallDecorationShape.LEFT_FACE;
        } else if (isRightSupportive) {
            return WallDecorationShape.RIGHT_FACE;
        } else {
            return WallDecorationShape.STRAIGHT;
        }
    }
}
