package com.jumpcutfindo.happyholidays.common.block;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.jumpcutfindo.happyholidays.common.item.HappyHolidaysTabs;
import com.jumpcutfindo.happyholidays.common.utils.BlockUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class DecorationBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<AttachFace> ATTACH_FACE = BlockStateProperties.ATTACH_FACE;

    public static final Properties BLOCK_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.DECORATION)
                    .strength(0.1f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .noCollission();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysTabs.CHRISTMAS_GROUP);

    public DecorationBlock() {
        super(BLOCK_PROPERTIES);

        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(ATTACH_FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
        );
    }

    @NotNull
    public abstract VoxelShape getFloorShape();

    @NotNull
    public abstract VoxelShape getCeilingShape();

    @NotNull
    public abstract VoxelShape getWallShape();

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction clickedFaceDirection = context.getClickedFace();
        Direction.Axis clickedFaceAxis = clickedFaceDirection.getAxis();
        BlockPos blockPos = context.getClickedPos();
        Level world = context.getLevel();

        AttachFace attachFace;
        Direction facing;

        if (clickedFaceAxis == Direction.Axis.Y) {
            attachFace = clickedFaceDirection == Direction.UP ? AttachFace.FLOOR : AttachFace.CEILING;
            facing = context.getHorizontalDirection().getOpposite();
        } else {
            attachFace = AttachFace.WALL;
            facing = clickedFaceDirection;
        }

        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean isWaterlogged = fluidstate.getType() == Fluids.WATER;

        BlockState blockState =
                this.defaultBlockState().setValue(FACING, facing).setValue(ATTACH_FACE, attachFace).setValue(WATERLOGGED, isWaterlogged);

        return canSurvive(blockState, world, blockPos) ? blockState : null;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(ATTACH_FACE, FACING, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockReader, BlockPos blockPos,
                               CollisionContext context) {
        Direction direction = blockState.getValue(FACING);
        AttachFace attachFace = blockState.getValue(ATTACH_FACE);

        VoxelShape resultShape = null;

        // Retrieve appropriate shape first
        if (attachFace == AttachFace.FLOOR) {
            resultShape = this.getFloorShape();
        } else if (attachFace == AttachFace.CEILING) {
            resultShape = this.getCeilingShape();
        } else {
            resultShape = this.getWallShape();
        }

        // Then we rotate accordingly
        if (direction == Direction.SOUTH) {
            return resultShape;
        } else if (direction == Direction.NORTH) {
            return BlockUtils.rotateShape(resultShape, Rotation.CLOCKWISE_180);
        } else if (direction == Direction.WEST) {
            return BlockUtils.rotateShape(resultShape, Rotation.CLOCKWISE_90);
        } else {
            return BlockUtils.rotateShape(resultShape, Rotation.COUNTERCLOCKWISE_90);
        }
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  LevelAccessor world, BlockPos pos1, BlockPos pos2) {
        if (blockState.getValue(WATERLOGGED)) {
            world.scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(world));
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
        AttachFace attachFace = blockState.getValue(ATTACH_FACE);

        Direction connectedDirection = facingDirection.getOpposite();

        boolean canSupportCentre = attachFace == AttachFace.FLOOR ? Block.canSupportCenter(world, position.relative(Direction.DOWN), Direction.UP)
                : attachFace == AttachFace.CEILING ? Block.canSupportCenter(world, position.relative(Direction.UP), Direction.DOWN)
                : Block.canSupportCenter(world, position.relative(connectedDirection), connectedDirection.getOpposite());

        BlockState onBlockState = attachFace == AttachFace.CEILING ? world.getBlockState(position.above())
                : facingDirection == Direction.NORTH ? world.getBlockState(position.south())
                : facingDirection == Direction.SOUTH ? world.getBlockState(position.north())
                : facingDirection == Direction.EAST ? world.getBlockState(position.west())
                : facingDirection == Direction.WEST ? world.getBlockState(position.east()) : null;

        boolean canSupportHanging = onBlockState != null && onBlockState.isFaceSturdy(world, position, facingDirection);

        boolean isLeaves = onBlockState != null && onBlockState.is(BlockTags.LEAVES);

        return canSupportCentre || canSupportHanging || isLeaves;
    }
}
