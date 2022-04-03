package com.jumpcutfindo.happyholidays.common.block.christmas;

import javax.annotation.Nullable;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class FestiveCandyCaneBlock extends CandyCaneBlock {
    public static final EnumProperty<FestiveCandyShape> CANDY_SHAPE = EnumProperty.create("candy_shape", FestiveCandyShape.class);

    public FestiveCandyCaneBlock() {
        super();

        this.registerDefaultState(this.getStateDefinition().any()
            .setValue(CANDY_SHAPE, FestiveCandyShape.X_O)
        );
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
