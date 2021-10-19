package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BaseGingerbreadWall extends WallBlock implements IGingerbreadBlock {
    public static final String DOUGH_BLOCK_ID = "gingerbread_dough_wall";
    public static final String COOKED_BLOCK_ID = "gingerbread_wall";
    public static final String SOGGY_BLOCK_ID = "soggy_gingerbread_wall";

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
            resultantBlock = new BaseGingerbreadWall(BaseGingerbreadBlock.COOKED_RPOPERTIES);
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

        public Supplier<WallBlock> build() {
            return () -> resultantBlock;
        }
    }

    public enum SoggyResult {
        BLOCK(() -> ChristmasBlocks.SOGGY_GINGERBREAD_WALL.get().defaultBlockState());

        private final Supplier<BlockState> soggyResultSupplier;

        SoggyResult(Supplier<BlockState> soggyResultSupplier) {
            this.soggyResultSupplier = soggyResultSupplier;
        }

        public Supplier<BlockState> getSupplier() {
            return soggyResultSupplier;
        }
    }
}
