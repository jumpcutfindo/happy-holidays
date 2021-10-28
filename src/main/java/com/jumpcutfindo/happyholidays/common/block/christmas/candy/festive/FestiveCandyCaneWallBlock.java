package com.jumpcutfindo.happyholidays.common.block.christmas.candy.festive;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneWallBlock;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class FestiveCandyCaneWallBlock extends CandyCaneWallBlock {
    public static final String BLOCK_ID = "festive_candy_cane_wall";
    public static final String BRICKS_ID = "festive_candy_cane_brick_wall";
    public static final String TILES_ID = "festive_candy_cane_tile_wall";

    public static final EnumProperty<FestiveCandyShape> CANDY_SHAPE = EnumProperty.create("candy_shape", FestiveCandyShape.class);

    public FestiveCandyCaneWallBlock() {
        super();
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState expectedBlockState = super.getStateForPlacement(context);

        return CandyCaneBlock.updateFestiveBlockForPlacement(context, expectedBlockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
    }
}
