package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.utils.BlockUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BaseGingerbreadBlock extends ChristmasBlock {
    public static final Item.Properties ITEM_PROPERITES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public BaseGingerbreadBlock(BlockBehaviour.Properties blockProperties) {
        super(blockProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState expectedState = super.getStateForPlacement(context);

        return getPlacementState(context, expectedState, () -> ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get().defaultBlockState());
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  LevelAccessor level, BlockPos blockPos, BlockPos otherBlockPos) {
        return getUpdatedState(blockState, direction, otherBlockState, level, blockPos, otherBlockPos, () -> ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get().defaultBlockState());
    }

    public static BlockState getPlacementState(BlockPlaceContext context, BlockState defaultBlockState, Supplier<BlockState> soggyStateSupplier) {
        // For waterloggable blocks, consider the situation where we place the block inside water straightaway
        if (defaultBlockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
            if (context.getLevel().getBlockState(context.getClickedPos()).is(Blocks.WATER)) {
                if (!context.getLevel().isClientSide()) playSoggyEffects((ServerLevel) context.getLevel(), context.getClickedPos());
                return soggyStateSupplier.get().getBlock().withPropertiesOf(defaultBlockState);
            }
        }

        // Check if the block can be soggified
        if (defaultBlockState.getBlock() instanceof Soggifiable) {
            BlockPos pos = context.getClickedPos();

            // Check if there's water around in the form of actual water or waterloggable blocks
            if (isWaterAround(context.getLevel(), pos)) {
                if (!context.getLevel().isClientSide()) playSoggyEffects((ServerLevel) context.getLevel(), pos);
                return soggyStateSupplier.get().getBlock().withPropertiesOf(defaultBlockState);
            }
        }

        return defaultBlockState;
    }

    public static BlockState getUpdatedState(BlockState blockState, Direction direction, BlockState otherBlockState,
                                             LevelAccessor level, BlockPos blockPos, BlockPos otherBlockPos,
                                             Supplier<BlockState> soggyStateSupplier) {
        // Check if neighbouring blocks have become wet
        if (blockState.getBlock() instanceof Soggifiable && BlockUtils.isWet(otherBlockState)) {
            if (!level.isClientSide()) {
                playSoggyEffects((ServerLevel) level, blockPos);
            }
            return soggyStateSupplier.get().getBlock().withPropertiesOf(blockState);
        }

        return blockState;
    }

    public static void playSoggyEffects(ServerLevel level, BlockPos blockPos) {
        level.playSound(null, blockPos, ChristmasSounds.GINGERBREAD_SOGGIFIED.get(), SoundSource.BLOCKS, 1.0f, 1.0f);

        level.sendParticles(ParticleTypes.SPLASH, blockPos.getX() + 0.5D, blockPos.getY() + 0.5D,
                blockPos.getZ() + 0.5D,
                10, 0.25D, 0.25D, 0.25D, 0.0D);
    }

    public static boolean isWaterAround(Level level, BlockPos pos) {
        return BlockUtils.isWet(level.getBlockState(pos.above()))
                || BlockUtils.isWet(level.getBlockState(pos.north()))
                || BlockUtils.isWet(level.getBlockState(pos.south()))
                || BlockUtils.isWet(level.getBlockState(pos.east()))
                || BlockUtils.isWet(level.getBlockState(pos.west()));
    }
}
