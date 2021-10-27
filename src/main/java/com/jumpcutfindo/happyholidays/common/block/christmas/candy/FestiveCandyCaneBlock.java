package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class FestiveCandyCaneBlock extends BaseCandyCaneBlock {
    public static final String BLOCK_ID = "festive_candy_cane_block";
    public static final String BRICKS_ID = "festive_candy_cane_bricks";
    public static final String TILES_ID = "festive_candy_cane_tiles";

    public static final EnumProperty<FestiveCandyShape> CANDY_SHAPE = EnumProperty.create("candy_shape",
            FestiveCandyShape.class);

    public FestiveCandyCaneBlock() {
        super();

        this.registerDefaultState(this.getStateDefinition().any()
            .setValue(CANDY_SHAPE, FestiveCandyShape.X_O)
        );
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockPos = context.getClickedPos();

        BlockState expectedBlockState = super.getStateForPlacement(context);

        return BaseCandyCaneBlock.updateFestiveBlockForPlacement(context, expectedBlockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(CANDY_SHAPE);
    }
}
