package com.jumpcutfindo.happyholidays.common.block.christmas.food;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChristmasHamBlock extends ChristmasFoodBlock {
    public static final int MAX_BITES = 4;

    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, MAX_BITES - 1);

    public static final Properties BLOCK_PROPERTIES =
            Properties
                    .of(Material.WOOL)
                    .strength(0.25f)
                    .sound(SoundType.WOOL)
                    .noOcclusion()
                    .randomTicks();

    public ChristmasHamBlock() {
        super(BLOCK_PROPERTIES);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(BITES, 0)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(BITES, FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(BITES, 0)
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public @NotNull VoxelShape getShape() {
        return Block.box(1.0, 0.0, 1.0, 15.0, 10.0, 15.0);
    }

    @Override
    public int getNutrition() {
        return 2;
    }

    @Override
    public float getSaturation() {
        return 0.2F;
    }

    @Override
    public IntegerProperty getBitesProperty() {
        return BITES;
    }

    @Override
    public int getBites(BlockState blockState) {
        return blockState.getValue(BITES);
    }

    @Override
    public int getMaxBites() {
        return MAX_BITES;
    }
}
