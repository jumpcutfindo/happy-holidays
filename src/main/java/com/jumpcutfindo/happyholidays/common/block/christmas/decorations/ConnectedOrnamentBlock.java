package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
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
import net.minecraft.world.World;

public class ConnectedOrnamentBlock extends ChristmasBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<WallDecorationShape> WALL_SHAPE = EnumProperty.create("decoration_shape",
            WallDecorationShape.class);

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    private final VoxelShape shape;

    public ConnectedOrnamentBlock(String blockId, AbstractBlock.Properties blockProperties, VoxelShape shape) {
        super(blockId, blockProperties, ITEM_PROPERTIES);

        this.shape = shape;

        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WALL_SHAPE, WallDecorationShape.STRAIGHT)
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

        return this.defaultBlockState()
                .setValue(FACING, clickedFaceDirection)
                .setValue(WALL_SHAPE, getWallShapeState(world, clickedPos, clickedFaceDirection));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WALL_SHAPE, FACING);
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
        // Remember facing direction, we don't want to change this
        Direction facingDirection = blockState.getValue(FACING);

        // Break the block if the block cannot survive
        if (!this.canSurvive(blockState, world, pos1)) return Blocks.AIR.defaultBlockState();

        // Since block can survive, check if there are any updates to the state to be made

        return this.defaultBlockState()
                .setValue(FACING, facingDirection)
                .setValue(WALL_SHAPE, getWallShapeState(world, pos1, facingDirection));
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

        boolean isLeftSupportive = world.getBlockState(leftPos).isFaceSturdy(world, leftPos, leftDir);
        boolean isRightSupportive = world.getBlockState(rightPos).isFaceSturdy(world, rightPos, rightDir);
        boolean isOppositeSupportive = world.getBlockState(oppPos).isFaceSturdy(world, oppPos, oppDir);

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
