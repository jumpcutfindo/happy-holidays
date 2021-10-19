package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class BaseGingerbreadSlabBlock extends SlabBlock implements IGingerbreadBlock {
    public static final String DOUGH_BLOCK_ID = "gingerbread_dough_slab";
    public static final String COOKED_BLOCK_ID = "gingerbread_slab";
    public static final String SOGGY_BLOCK_ID = "soggy_gingerbread_slab";

    public Supplier<BlockState> soggySupplier;

    public BaseGingerbreadSlabBlock(Properties blockProperties) {
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
        return BaseGingerbreadBlock.getUpdatedState(blockState, direction, otherBlockState, level, blockPos, otherBlockPos, soggySupplier);
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        boolean flag = super.placeLiquid(level, blockPos, blockState, fluidState);

        if (flag && this.isSoggifiable()) level.setBlock(blockPos, soggySupplier.get().getBlock().withPropertiesOf(blockState), 2);

        return flag;
    }

    public static class Builder {
        private boolean soggifiable;
        BaseGingerbreadSlabBlock resultantBlock;

        public static Builder create() {
            return new Builder();
        }

        public Builder dough() {
            this.soggifiable = true;
            resultantBlock = new BaseGingerbreadSlabBlock(BaseGingerbreadBlock.DOUGH_PROPERTIES);
            return this;
        }

        public Builder cooked() {
            this.soggifiable = true;
            resultantBlock = new BaseGingerbreadSlabBlock(BaseGingerbreadBlock.COOKED_RPOPERTIES);
            return this;
        }

        public Builder soggy() {
            this.soggifiable = false;
            resultantBlock = new BaseGingerbreadSlabBlock(BaseGingerbreadBlock.SOGGY_PROPERTIES);
            return this;
        }

        public Builder soggyResult(BaseGingerbreadSlabBlock.SoggyResult soggyResult) {
            if (!soggifiable) throw new IllegalStateException("Cannot have a soggy block turn soggy again!");

            resultantBlock.setSoggyResult(soggyResult.getSupplier());
            return this;
        }

        public Supplier<SlabBlock> build() {
            return () -> resultantBlock;
        }
    }

    public enum SoggyResult {
        BLOCK(() -> ChristmasBlocks.SOGGY_GINGERBREAD_SLAB.get().defaultBlockState());

        private final Supplier<BlockState> soggyResultSupplier;

        SoggyResult(Supplier<BlockState> soggyResultSupplier) {
            this.soggyResultSupplier = soggyResultSupplier;
        }

        public Supplier<BlockState> getSupplier() {
            return soggyResultSupplier;
        }
    }
}
