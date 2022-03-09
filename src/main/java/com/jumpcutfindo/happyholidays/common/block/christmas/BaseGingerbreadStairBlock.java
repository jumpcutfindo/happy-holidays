package com.jumpcutfindo.happyholidays.common.block.christmas;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasLike;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class BaseGingerbreadStairBlock extends StairBlock implements Soggifiable, ChristmasLike, ChristmasBlock {
    public static final String DOUGH_BLOCK_ID = "gingerbread_dough_stairs";
    public static final String COOKED_BLOCK_ID = "gingerbread_stairs";
    public static final String SOGGY_BLOCK_ID = "soggy_gingerbread_stairs";

    public static final String DOUGH_BRICKS_ID = "gingerbread_dough_brick_stairs";
    public static final String COOKED_BRICKS_ID = "gingerbread_brick_stairs";
    public static final String SOGGY_BRICKS_ID = "soggy_gingerbread_brick_stairs";

    public static final String DOUGH_TILES_ID = "gingerbread_dough_tile_stairs";
    public static final String COOKED_TILES_ID = "gingerbread_tile_stairs";
    public static final String SOGGY_TILES_ID = "soggy_gingerbread_tile_stairs";

    public Supplier<BlockState> soggySupplier;

    public BaseGingerbreadStairBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }

    @Override
    public void setSoggyResult(Supplier<BlockState> soggySupplier) {
        this.soggySupplier = soggySupplier;
    }

    @Override
    public boolean isSoggifiable() {
        return soggySupplier != null;
    }

    @Override
    public BlockState getSoggyResult() {
        return this.soggySupplier != null ? this.soggySupplier.get() : null;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState expectedState = super.getStateForPlacement(context);

        return BaseGingerbreadBlock.getPlacementState(context, expectedState, soggySupplier);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  LevelAccessor level, BlockPos blockPos, BlockPos otherBlockPos) {
        BlockState expectedState = super.updateShape(blockState, direction, otherBlockState, level, blockPos, otherBlockPos);

        return BaseGingerbreadBlock.getUpdatedState(expectedState, direction, otherBlockState, level, blockPos, otherBlockPos, soggySupplier);
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        boolean flag = super.placeLiquid(level, blockPos, blockState, fluidState);

        return BaseGingerbreadBlock.onLiquidPlaced(level, blockPos, blockState, flag);
    }

    @Override
    public void configure() {
    }

    @Override
    public ChristmasRarity getChristmasRarity() {
        return ChristmasRarity.COMMON;
    }

    public static class Builder {
        private boolean soggifiable;
        BaseGingerbreadStairBlock resultantBlock;

        public static Builder create() {
            return new Builder();
        }

        public Builder dough() {
            this.soggifiable = true;
            resultantBlock = new BaseGingerbreadStairBlock(ChristmasBlocks.supplierOf(ChristmasBlocks.GINGERBREAD_DOUGH_BLOCK), BaseGingerbreadBlock.DOUGH_PROPERTIES);
            return this;
        }

        public Builder cooked() {
            this.soggifiable = true;
            resultantBlock = new BaseGingerbreadStairBlock(ChristmasBlocks.supplierOf(ChristmasBlocks.GINGERBREAD_BLOCK), BaseGingerbreadBlock.COOKED_PROPERTIES);
            return this;
        }

        public Builder soggy() {
            this.soggifiable = false;
            resultantBlock = new BaseGingerbreadStairBlock(ChristmasBlocks.supplierOf(ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK), BaseGingerbreadBlock.SOGGY_PROPERTIES);
            return this;
        }

        public Builder soggyResult(SoggyResult soggyResult) {
            if (!soggifiable) throw new IllegalStateException("Cannot have a soggy block turn soggy again!");

            resultantBlock.setSoggyResult(soggyResult.getSupplier());
            return this;
        }

        public Supplier<StairBlock> build() {
            return () -> resultantBlock;
        }
    }

    public enum SoggyResult {
        BLOCK(() -> ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get().defaultBlockState()),
        BRICKS(() -> ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_STAIRS.get().defaultBlockState()),
        TILES(() -> ChristmasBlocks.SOGGY_GINGERBREAD_TILE_STAIRS.get().defaultBlockState());

        private final Supplier<BlockState> soggyResultSupplier;

        SoggyResult(Supplier<BlockState> soggyResultSupplier) {
            this.soggyResultSupplier = soggyResultSupplier;
        }

        public Supplier<BlockState> getSupplier() {
            return soggyResultSupplier;
        }
    }
}
