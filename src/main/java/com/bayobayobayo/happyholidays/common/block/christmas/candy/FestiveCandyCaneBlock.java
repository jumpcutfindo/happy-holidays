package com.bayobayobayo.happyholidays.common.block.christmas.candy;

import javax.annotation.Nullable;

import com.bayobayobayo.happyholidays.common.block.christmas.decorations.WallDecorationShape;
import com.bayobayobayo.happyholidays.common.registry.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockPos = context.getClickedPos();

        BlockPos[] neighbours = new BlockPos[] {
                blockPos.above(), blockPos.below(), blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west()
        };

        for (BlockPos pos : neighbours) {
            BlockState neighbourState = context.getLevel().getBlockState(pos);
            if (neighbourState.is(BlockRegistry.FESTIVE_CANDY_CANE_BLOCK.get())) {
                return this.defaultBlockState().setValue(CANDY_SHAPE, neighbourState.getValue(CANDY_SHAPE) == FestiveCandyShape.X_O ? FestiveCandyShape.O_X : FestiveCandyShape.X_O);
            }
        }

        return this.defaultBlockState().setValue(CANDY_SHAPE, FestiveCandyShape.X_O);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(CANDY_SHAPE);
    }
}
