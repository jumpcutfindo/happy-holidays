package com.bayobayobayo.happyholidays.common.block.christmas.decorations;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.block.christmas.ChristmasBlock;
import com.bayobayobayo.happyholidays.common.utils.HappyHolidaysUtils;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class OrnamentBlock extends ChristmasBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final EnumProperty<AttachFace> ATTACH_FACE = BlockStateProperties.ATTACH_FACE;

    private static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                    .of(Material.GLASS)
                    .harvestLevel(-1)
                    .strength(0.1f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .noCollission();

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ItemGroup.TAB_DECORATIONS);


    private static final VoxelShape NORMAL_SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(6.0, 0.0, 6.0, 10.0, 5.0, 10.0)
    );
    private static final VoxelShape HANGING_SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(6.0, 9.5, 6.0, 10.0, 16.0, 10.0)
    );
    private static final VoxelShape WALL_SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(6.0, 0.0, 4.0, 10.0, 6.0, 0.0)
    );

    public OrnamentBlock(String blockId) {
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERTIES);

        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(ATTACH_FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH)
        );
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getClickedFace();
        BlockPos blockpos = context.getClickedPos();
        World world = context.getLevel();
        Direction.Axis direction$axis = direction.getAxis();

        if (direction$axis == Direction.Axis.Y) {
            // Check whether face clicked is facing up or down
            return this.defaultBlockState()
                    .setValue(ATTACH_FACE, direction == Direction.DOWN && isLeaves(world.getBlockState(blockpos.above())) ? AttachFace.CEILING : AttachFace.FLOOR);
        } else {
            // Check whether face clicked is a leaf block
            boolean isLeafBlockClicked = direction$axis == Direction.Axis.X
                    && (direction == Direction.EAST && (isLeaves(world.getBlockState(blockpos.west())))
                    || direction == Direction.WEST && isLeaves(world.getBlockState(blockpos.east())))
                    || direction$axis == Direction.Axis.Z
                    && (direction == Direction.SOUTH && isLeaves(world.getBlockState(blockpos.north()))
                    || direction == Direction.NORTH && isLeaves(world.getBlockState(blockpos.south()))
            );

            BlockState blockstate1 =
                    this.defaultBlockState()
                            .setValue(FACING, direction.getOpposite())
                            .setValue(ATTACH_FACE, isLeafBlockClicked ? AttachFace.WALL : AttachFace.FLOOR);

            if (isLeafBlockClicked) {
                return blockstate1;
            }

            // If previous failed (block is not a leaf), check if block below is sturdy
            boolean isBelowSturdy = world.getBlockState(blockpos.below()).isFaceSturdy(world, blockpos.below(), Direction.UP);
            blockstate1 = blockstate1
                    .setValue(ATTACH_FACE, isBelowSturdy ? AttachFace.FLOOR : AttachFace.CEILING);
            if (isBelowSturdy && blockstate1.canSurvive(context.getLevel(), context.getClickedPos())) {
                return blockstate1;
            }
        }

        return null;
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(ATTACH_FACE, FACING);
    }

    private boolean isLeaves(BlockState blockState) {
        return blockState.getBlock().is(BlockTags.LEAVES);
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(blockRegistryObject.get(), RenderType.translucent());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, IBlockReader blockReader, BlockPos blockPos,
                               ISelectionContext context) {
        Direction direction = blockState.getValue(FACING);
        AttachFace attachFace = blockState.getValue(ATTACH_FACE);

        if (attachFace == AttachFace.FLOOR) {
            return NORMAL_SHAPE;
        } else if (attachFace == AttachFace.CEILING) {
            return HANGING_SHAPE;
        } else {
            if (direction == Direction.NORTH) {
                return WALL_SHAPE;
            } else if (direction == Direction.SOUTH) {
                return HappyHolidaysUtils.rotateShape(WALL_SHAPE, Rotation.CLOCKWISE_180);
            } else if (direction == Direction.EAST) {
                return HappyHolidaysUtils.rotateShape(WALL_SHAPE, Rotation.CLOCKWISE_90);
            } else {
                return HappyHolidaysUtils.rotateShape(WALL_SHAPE, Rotation.COUNTERCLOCKWISE_90);
            }
        }
    }
}
