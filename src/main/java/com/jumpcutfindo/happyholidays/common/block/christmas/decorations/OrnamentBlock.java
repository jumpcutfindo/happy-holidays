package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import java.util.Arrays;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
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

public class OrnamentBlock extends ChristmasBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<AttachFace> ATTACH_FACE = BlockStateProperties.ATTACH_FACE;

    public static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.DECORATION)
                    .harvestLevel(-1)
                    .strength(0.1f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .noCollission();

    public static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public VoxelShape[] normalShape, hangingShape, wallShape;

    public OrnamentBlock(String blockId, VoxelShape[][] ornamentShapes) {
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERTIES);

        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(ATTACH_FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH)
        );

        this.normalShape = ornamentShapes[0];
        this.hangingShape = ornamentShapes[1];
        this.wallShape = ornamentShapes[2];
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(this, RenderType.cutout());
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction clickedFaceDirection = context.getClickedFace();
        Direction.Axis clickedFaceAxis = clickedFaceDirection.getAxis();
        BlockPos blockPos = context.getClickedPos();
        World world = context.getLevel();

        BlockState blockState = null;

        if (clickedFaceAxis == Direction.Axis.Y) {
            blockState = this.defaultBlockState()
                    .setValue(ATTACH_FACE, clickedFaceDirection == Direction.UP ? AttachFace.FLOOR : AttachFace.CEILING)
                    .setValue(FACING, context.getHorizontalDirection().getOpposite());
        } else {
            blockState = this.defaultBlockState()
                    .setValue(ATTACH_FACE, AttachFace.WALL)
                    .setValue(FACING, clickedFaceDirection);
        }

        return canSurvive(blockState, world, blockPos) ? blockState
                : canSupportCenter(world, blockPos.below(), Direction.UP) ? this.defaultBlockState().setValue(ATTACH_FACE, AttachFace.FLOOR)
                : null;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(ATTACH_FACE, FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos,
                               ISelectionContext context) {
        Direction direction = blockState.getValue(FACING);
        AttachFace attachFace = blockState.getValue(ATTACH_FACE);

        VoxelShape[] resultShapes = null;

        if (attachFace == AttachFace.FLOOR) {
            resultShapes = Arrays.copyOf(normalShape, normalShape.length);
        } else if (attachFace == AttachFace.CEILING) {
            resultShapes = Arrays.copyOf(hangingShape, hangingShape.length);
        } else {
            resultShapes = Arrays.copyOf(wallShape, wallShape.length);
        }

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
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1,
                                  IWorld world, BlockPos pos1, BlockPos pos2) {
        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos position) {
        Direction facingDirection = blockState.getValue(FACING);
        AttachFace attachFace = blockState.getValue(ATTACH_FACE);

        Direction connectedDirection = facingDirection.getOpposite();

        boolean canSupportCentre = attachFace == AttachFace.FLOOR ? Block.canSupportCenter(world, position.relative(Direction.DOWN), Direction.UP)
                : attachFace == AttachFace.CEILING ? Block.canSupportCenter(world, position.relative(Direction.UP), Direction.DOWN)
                : Block.canSupportCenter(world, position.relative(connectedDirection), connectedDirection.getOpposite());

        BlockState onBlockState = facingDirection == Direction.NORTH ? world.getBlockState(position.south())
                : facingDirection == Direction.SOUTH ? world.getBlockState(position.north())
                : facingDirection == Direction.EAST ? world.getBlockState(position.west())
                : facingDirection == Direction.WEST ? world.getBlockState(position.east()) : null;

        boolean canSupportHanging = onBlockState != null && onBlockState.isFaceSturdy(world, position, facingDirection);

        boolean isLeaves = onBlockState != null && onBlockState.is(BlockTags.LEAVES);

        return canSupportCentre || canSupportHanging || isLeaves;
    }
}
