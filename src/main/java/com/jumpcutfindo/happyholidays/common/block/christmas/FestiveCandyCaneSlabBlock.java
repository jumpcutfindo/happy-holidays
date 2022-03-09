package com.jumpcutfindo.happyholidays.common.block.christmas;

import javax.annotation.Nullable;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class FestiveCandyCaneSlabBlock extends CandyCaneSlabBlock {
    public static final String BLOCK_ID = "festive_candy_cane_slab";
    public static final String BRICKS_ID = "festive_candy_cane_brick_slab";
    public static final String TILES_ID = "festive_candy_cane_tile_slab";

    public static final EnumProperty<FestiveCandyShape> CANDY_SHAPE = EnumProperty.create("candy_shape", FestiveCandyShape.class);

    public FestiveCandyCaneSlabBlock() {
        super();

        this.defaultBlockState().setValue(CANDY_SHAPE, FestiveCandyShape.X_O);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState expectedBlockState = super.getStateForPlacement(context);

        return CandyCaneBlock.updateFestiveBlockForPlacement(context, expectedBlockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(CANDY_SHAPE);
    }
}
