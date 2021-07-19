package com.bayobayobayo.happyholidays.common.block.christmas.decorations;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.registry.RegistryHandler;
import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
import com.bayobayobayo.happyholidays.common.handlers.ModuleHandler;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
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
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ConnectedOrnamentBlock extends ChristmasBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final EnumProperty<WallDecorationShape> WALL_SHAPE = EnumProperty.create("decoration_shape",
            WallDecorationShape.class);

    private static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                .of(Material.WOOL)
                .harvestLevel(-1)
                .strength(0.1f)
                .sound(SoundType.WOOL)
                .noOcclusion()
                .noCollission();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    private static final VoxelShape SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(0.0, 0.0, 0.0, 16.0, 16.0 ,1.0)
    );

    public ConnectedOrnamentBlock(String blockId) {
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERTIES);

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
        World level = context.getLevel();

        BlockState leftBlock = null, rightBlock = null, oppositeBlock = null;
        if (clickedFaceDirection.getAxis().isHorizontal()) {
            if (clickedFaceDirection.getOpposite() == Direction.NORTH) {
                leftBlock = level.getBlockState(clickedPos.east());
                rightBlock = level.getBlockState(clickedPos.west());
                oppositeBlock = level.getBlockState(clickedPos.south());
            } else if (clickedFaceDirection.getOpposite() == Direction.SOUTH) {
                leftBlock = level.getBlockState(clickedPos.west());
                rightBlock = level.getBlockState(clickedPos.east());
                oppositeBlock = level.getBlockState(clickedPos.north());
            } else if (clickedFaceDirection.getOpposite() == Direction.EAST) {
                leftBlock = level.getBlockState(clickedPos.south());
                rightBlock = level.getBlockState(clickedPos.north());
                oppositeBlock = level.getBlockState(clickedPos.west());
            } else {
                leftBlock = level.getBlockState(clickedPos.north());
                rightBlock = level.getBlockState(clickedPos.south());
                oppositeBlock = level.getBlockState(clickedPos.east());
            }
        } else {
            // Do not allow placement if placed on a vertical face
            return null;
        }

        return this.defaultBlockState()
                .setValue(FACING, clickedFaceDirection.getOpposite())
                .setValue(WALL_SHAPE, getWallShapeState(leftBlock, rightBlock, oppositeBlock));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(WALL_SHAPE, FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos,
                               ISelectionContext context) {
        Direction direction = blockState.getValue(FACING);

        if (direction == Direction.NORTH) {
            return SHAPE;
        } else if (direction == Direction.SOUTH) {
            return HappyHolidaysUtils.rotateShape(SHAPE, Rotation.CLOCKWISE_180);
        } else if (direction == Direction.EAST) {
            return HappyHolidaysUtils.rotateShape(SHAPE, Rotation.CLOCKWISE_90);
        } else {
            return HappyHolidaysUtils.rotateShape(SHAPE, Rotation.COUNTERCLOCKWISE_90);
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
        BlockState leftBlock = null, rightBlock = null, oppositeBlock = null;

        if (facingDirection == Direction.NORTH) {
            leftBlock = world.getBlockState(pos1.east());
            rightBlock = world.getBlockState(pos1.west());
            oppositeBlock = world.getBlockState(pos1.south());
        } else if (facingDirection == Direction.SOUTH) {
            leftBlock = world.getBlockState(pos1.west());
            rightBlock = world.getBlockState(pos1.east());
            oppositeBlock = world.getBlockState(pos1.north());
        } else if (facingDirection == Direction.EAST) {
            leftBlock = world.getBlockState(pos1.south());
            rightBlock = world.getBlockState(pos1.north());
            oppositeBlock = world.getBlockState(pos1.west());
        } else {
            leftBlock = world.getBlockState(pos1.north());
            rightBlock = world.getBlockState(pos1.south());
            oppositeBlock = world.getBlockState(pos1.east());
        }

        return this.defaultBlockState()
                .setValue(FACING, facingDirection)
                .setValue(WALL_SHAPE, getWallShapeState(leftBlock, rightBlock, oppositeBlock));
    }

    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos position) {
        Direction direction = blockState.getValue(FACING);

        return direction == Direction.NORTH ? !(world.getBlockState(position.north()).isAir() || ChristmasBlock.isDecorationBlock(world.getBlockState(position.north()).getBlock()))
                : direction == Direction.SOUTH ? !(world.getBlockState(position.south()).isAir() || ChristmasBlock.isDecorationBlock(world.getBlockState(position.south()).getBlock()))
                : direction == Direction.EAST ? !(world.getBlockState(position.east()).isAir()  || ChristmasBlock.isDecorationBlock(world.getBlockState(position.east()).getBlock()))
                : direction == Direction.WEST && !(world.getBlockState(position.west()).isAir() || ChristmasBlock.isDecorationBlock(world.getBlockState(position.west()).getBlock()));
    }

    private WallDecorationShape getWallShapeState(BlockState leftBlock, BlockState rightBlock, BlockState oppositeBlock) {
        boolean isLeftAir = leftBlock.isAir() || ChristmasBlock.isDecorationBlock(leftBlock.getBlock());
        boolean isRightAir = rightBlock.isAir() || ChristmasBlock.isDecorationBlock(rightBlock.getBlock());;
        boolean isOppositeAir = oppositeBlock.isAir() || ChristmasBlock.isDecorationBlock(oppositeBlock.getBlock());;

        if (!isLeftAir && !isRightAir && !isOppositeAir) {
            return WallDecorationShape.ALL_FACE;
        } else if (!isLeftAir && !isRightAir) {
            return WallDecorationShape.SIDE_FACE;
        } else if (!isLeftAir) {
            return WallDecorationShape.RIGHT_FACE;
        } else if (!isRightAir) {
            return WallDecorationShape.LEFT_FACE;
        } else {
            return WallDecorationShape.STRAIGHT;
        }
    }
}
