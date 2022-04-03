package com.jumpcutfindo.happyholidays.common.block.christmas;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasRarity;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class BaseGingerbreadWall extends WallBlock implements Soggifiable, ChristmasBlock {
    public Supplier<BlockState> soggySupplier;
    public BaseGingerbreadWall(Properties blockProperties) {
        super(blockProperties);
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

    public static class Builder {
        private boolean soggifiable;
        BaseGingerbreadWall resultantBlock;

        public static Builder create() {
            return new Builder();
        }

        public Builder dough() {
            this.soggifiable = true;
            resultantBlock = new BaseGingerbreadWall(BaseGingerbreadBlock.DOUGH_PROPERTIES);
            return this;
        }

        public Builder cooked() {
            this.soggifiable = true;
            resultantBlock = new BaseGingerbreadWall(BaseGingerbreadBlock.COOKED_PROPERTIES);
            return this;
        }

        public Builder soggy() {
            this.soggifiable = false;
            resultantBlock = new BaseGingerbreadWall(BaseGingerbreadBlock.SOGGY_PROPERTIES);
            return this;
        }

        public Builder soggyResult(BaseGingerbreadWall.SoggyResult soggyResult) {
            if (!soggifiable) throw new IllegalStateException("Cannot have a soggy block turn soggy again!");

            resultantBlock.setSoggyResult(soggyResult.getSupplier());
            return this;
        }

        public WallBlock build() {
            return resultantBlock;
        }
    }

    public enum SoggyResult {
        BLOCK(() -> ChristmasBlocks.SOGGY_GINGERBREAD_WALL.get().defaultBlockState()),
        BRICKS(() -> ChristmasBlocks.SOGGY_GINGERBREAD_BRICK_WALL.get().defaultBlockState()),
        TILES(() -> ChristmasBlocks.SOGGY_GINGERBREAD_TILE_WALL.get().defaultBlockState());

        private final Supplier<BlockState> soggyResultSupplier;

        SoggyResult(Supplier<BlockState> soggyResultSupplier) {
            this.soggyResultSupplier = soggyResultSupplier;
        }

        public Supplier<BlockState> getSupplier() {
            return soggyResultSupplier;
        }
    }
}
