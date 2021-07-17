package com.bayobayobayo.happyholidays.common.block.christmas.decorations;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.RegistryHandler;
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

public class TinselBlock extends ChristmasBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private static final Properties BLOCK_PROPERTIES =
            AbstractBlock.Properties
                .of(Material.WOOL)
                .harvestLevel(-1)
                .strength(0.1f)
                .sound(SoundType.WOOL)
                .noOcclusion()
                .noCollission();

    private static final Item.Properties ITEM_PROPERTIES =
            new Item.Properties().tab(ModuleHandler.HAPPY_HOLIDAYS_GROUP);

    private static final VoxelShape SHAPE = VoxelShapes.or(
            HappyHolidaysUtils.createShape(0.0, 0.0, 0.0, 16.0, 16.0 ,1.0)
    );

    public TinselBlock(String blockId) {
        super(blockId, BLOCK_PROPERTIES, ITEM_PROPERTIES);

        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    public void registerBlock() {
        blockRegistryObject = RegistryHandler.BLOCKS.register(blockId, () -> this);
        blockItemRegistryObject = RegistryHandler.ITEMS.register(
                blockId, () -> new BlockItem(blockRegistryObject.get(), ITEM_PROPERTIES));
    }

    @Override
    public void configureBlock() {
        RenderTypeLookup.setRenderLayer(blockRegistryObject.get(), RenderType.translucent());
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction clickedFaceDirection = context.getClickedFace();

        return this.defaultBlockState().setValue(FACING, clickedFaceDirection.getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(FACING);
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
        return this.canSurvive(blockState, world, pos1) ? blockState : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState blockState, IWorldReader world, BlockPos position) {
        Direction direction = blockState.getValue(FACING);

        return direction == Direction.NORTH ? !world.getBlockState(position.north()).isAir()
                : direction == Direction.SOUTH ? !world.getBlockState(position.south()).isAir()
                : direction == Direction.EAST ? !world.getBlockState(position.east()).isAir()
                : direction == Direction.WEST && !world.getBlockState(position.west()).isAir();
    }
}
