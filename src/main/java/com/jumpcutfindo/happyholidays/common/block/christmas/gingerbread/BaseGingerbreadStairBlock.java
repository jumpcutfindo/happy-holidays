package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BaseGingerbreadStairBlock extends StairBlock {
    public BaseGingerbreadStairBlock(Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        BlockState blockState = super.getStateForPlacement(context);

        if (this.defaultBlockState().getBlock() instanceof Soggifiable) {
            if (BaseGingerbreadBlock.isWaterAround(context.getLevel(), pos)) {
                if (!context.getLevel().isClientSide()) BaseGingerbreadBlock.playSoggyEffects((ServerLevel) context.getLevel(), pos);
                return ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get().withPropertiesOf(blockState);
            } else {
                return blockState;
            }
        } else {
            return blockState;
        }
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  LevelAccessor level, BlockPos blockPos, BlockPos otherBlockPos) {
        if (blockState.getBlock() instanceof Soggifiable && level.getBlockState(otherBlockPos).is(Blocks.WATER)) {
            if (!level.isClientSide()) {
                BaseGingerbreadBlock.playSoggyEffects((ServerLevel) level, blockPos);
            }
            return ChristmasBlocks.SOGGY_GINGERBREAD_STAIRS.get().withPropertiesOf(blockState);
        } else {
            return blockState;
        }
    }
}
