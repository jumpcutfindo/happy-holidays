package com.jumpcutfindo.happyholidays.common.block.christmas.candy.festive;

import static com.jumpcutfindo.happyholidays.common.block.christmas.candy.festive.FestiveCandyCaneBlock.CANDY_SHAPE;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.candy.CandyCaneSlabBlock;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class FestiveCandyCaneSlabBlock extends CandyCaneSlabBlock {
    public FestiveCandyCaneSlabBlock() {
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
        stateBuilder.add(CANDY_SHAPE);
    }
}
