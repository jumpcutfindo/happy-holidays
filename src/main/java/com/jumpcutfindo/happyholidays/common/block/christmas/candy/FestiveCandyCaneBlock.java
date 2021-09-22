package com.jumpcutfindo.happyholidays.common.block.christmas.candy;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;

public class FestiveCandyCaneBlock extends BaseCandyCaneBlock {
    public static final EnumProperty<FestiveCandyShape> CANDY_SHAPE = EnumProperty.create("candy_shape",
            FestiveCandyShape.class);
    public static final String BLOCK_ID = "festive_candy_cane_block";

    public FestiveCandyCaneBlock() {
        super(BLOCK_ID);

        this.registerDefaultState(this.getStateDefinition().any()
            .setValue(CANDY_SHAPE, FestiveCandyShape.X_O)
        );
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockPos = context.getClickedPos();

        BlockPos[] neighbours = new BlockPos[] {
                blockPos.above(), blockPos.below(), blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west()
        };

        for (BlockPos pos : neighbours) {
            BlockState neighbourState = context.getLevel().getBlockState(pos);
            if (neighbourState.is(ChristmasBlocks.FESTIVE_CANDY_CANE_BLOCK.get())) {
                return this.defaultBlockState().setValue(CANDY_SHAPE, neighbourState.getValue(CANDY_SHAPE) == FestiveCandyShape.X_O ? FestiveCandyShape.O_X : FestiveCandyShape.X_O);
            }
        }

        return this.defaultBlockState().setValue(CANDY_SHAPE, FestiveCandyShape.X_O);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(CANDY_SHAPE);
    }
}
