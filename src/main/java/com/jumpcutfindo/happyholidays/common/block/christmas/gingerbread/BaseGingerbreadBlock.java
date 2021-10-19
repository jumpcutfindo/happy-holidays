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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;

public class BaseGingerbreadBlock extends ChristmasBlock implements IGingerbreadBlock {
    public static final BlockBehaviour.Properties DOUGH_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.SNOW)
                    .strength(0.5f)
                    .sound(SoundType.FUNGUS);

    public static final BlockBehaviour.Properties COOKED_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.SNOW)
                    .strength(1.0f)
                    .sound(SoundType.FUNGUS);

    public static final BlockBehaviour.Properties SOGGY_PROPERTIES =
            BlockBehaviour.Properties
                    .of(Material.SNOW)
                    .strength(0.25f)
                    .sound(SoundType.FUNGUS);

    public static final Item.Properties ITEM_PROPERITES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public static final String DOUGH_BLOCK_ID = "gingerbread_dough_block";
    public static final String COOKED_BLOCK_ID = "gingerbread_block";
    public static final String SOGGY_BLOCK_ID = "soggy_gingerbread_block";

    public static final String DOUGH_BRICKS_ID = "gingerbread_dough_bricks";
    public static final String COOKED_BRICKS_ID = "gingerbread_bricks";
    public static final String SOGGY_BRICKS_ID = "soggy_gingerbread_bricks";

    public static final String DOUGH_TILES_ID = "gingerbread_dough_tiles";
    public static final String COOKED_TILES_ID = "gingerbread_tiles";
    public static final String SOGGY_TILES_ID = "soggy_gingerbread_tiles";

    public Supplier<BlockState> soggySupplier;

    public BaseGingerbreadBlock(BlockBehaviour.Properties blockProperties) {
        super(blockProperties);
    }

    @Override
    public void setSoggyResult(Supplier<BlockState> soggySupplier) {
        this.soggySupplier = soggySupplier;
    }

    @Override
    public BlockState getSoggyResult() {
        return this.soggySupplier != null ? this.soggySupplier.get() : null;
    }

    @Override
    public boolean isSoggifiable() {
        return soggySupplier != null;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState expectedState = super.getStateForPlacement(context);

        return getPlacementState(context, expectedState, soggySupplier);
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  LevelAccessor level, BlockPos blockPos, BlockPos otherBlockPos) {
        return getUpdatedState(blockState, direction, otherBlockState, level, blockPos, otherBlockPos, soggySupplier);
    }

    public static BlockState getPlacementState(BlockPlaceContext context, BlockState defaultBlockState, Supplier<BlockState> soggyStateSupplier) {
        // Check if can place the block at this spot (using BlockItem#canPlace but in static form)
        if (!BlockUtils.canPlace(context, defaultBlockState)) return defaultBlockState;

        // Check if the block can be soggified
        if (defaultBlockState.getBlock() instanceof IGingerbreadBlock gingerbreadBlock && gingerbreadBlock.isSoggifiable()) {
            BlockPos pos = context.getClickedPos();

            // For waterloggable blocks, consider the situation where we place the block inside water straightaway
            if (defaultBlockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                if (context.getLevel().getBlockState(pos).is(Blocks.WATER)) {
                    if (!context.getLevel().isClientSide()) playSoggyEffects((ServerLevel) context.getLevel(), pos);
                    return soggyStateSupplier.get().getBlock().withPropertiesOf(defaultBlockState);
                }
            }

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
        if (blockState.getBlock() instanceof IGingerbreadBlock gingerbreadBlock && gingerbreadBlock.isSoggifiable() && BlockUtils.isWet(otherBlockState)) {
            if (!level.isClientSide()) {
                playSoggyEffects((ServerLevel) level, blockPos);
            }
            return soggyStateSupplier.get().getBlock().withPropertiesOf(blockState);
        }

        return blockState;
    }

    public static boolean onLiquidPlaced(LevelAccessor level, BlockPos blockPos, BlockState blockState, boolean flag) {
        if (flag && blockState.getBlock() instanceof IGingerbreadBlock gingerbreadBlock && gingerbreadBlock.isSoggifiable()) {
            level.setBlock(blockPos, gingerbreadBlock.getSoggyResult().getBlock().withPropertiesOf(blockState).setValue(BlockStateProperties.WATERLOGGED, true), 2);
            if (!level.isClientSide()) playSoggyEffects((ServerLevel) level, blockPos);
        }

        return flag;
    }

    public static void playSoggyEffects(ServerLevel level, BlockPos blockPos) {
        level.playSound(null, blockPos, ChristmasSounds.GINGERBREAD_SOGGIFIED.get(), SoundSource.BLOCKS, 1.0f, 1.0f);

        level.sendParticles(ParticleTypes.SPLASH, blockPos.getX() + 0.5D, blockPos.getY() + 0.5D,
                blockPos.getZ() + 0.5D,
                10, 0.25D, 0.25D, 0.25D, 0.0D);
    }

    public static boolean isWaterAround(Level level, BlockPos pos) {
        return BlockUtils.isWet(level.getBlockState(pos))
                || BlockUtils.isWet(level.getBlockState(pos.above()))
                || BlockUtils.isWet(level.getBlockState(pos.north()))
                || BlockUtils.isWet(level.getBlockState(pos.south()))
                || BlockUtils.isWet(level.getBlockState(pos.east()))
                || BlockUtils.isWet(level.getBlockState(pos.west()));
    }

    public static class Builder {
        private boolean soggifiable;
        BaseGingerbreadBlock resultantBlock;

        public static Builder create() {
            return new Builder();
        }

        public Builder dough() {
            this.soggifiable = true;
            resultantBlock = new BaseGingerbreadBlock(BaseGingerbreadBlock.DOUGH_PROPERTIES);
            return this;
        }

        public Builder cooked() {
            this.soggifiable = true;
            resultantBlock = new BaseGingerbreadBlock(BaseGingerbreadBlock.COOKED_PROPERTIES);
            return this;
        }

        public Builder soggy() {
            this.soggifiable = false;
            resultantBlock = new BaseGingerbreadBlock(BaseGingerbreadBlock.SOGGY_PROPERTIES);
            return this;
        }

        public Builder soggyResult(SoggyResult soggyResult) {
            if (!soggifiable) throw new IllegalStateException("Cannot have a soggy block turn soggy again!");

            resultantBlock.setSoggyResult(soggyResult.getSupplier());
            return this;
        }

        public Supplier<ChristmasBlock> build() {
            return () -> resultantBlock;
        }
    }

    public enum SoggyResult {
        BLOCK(() -> ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get().defaultBlockState()),
        BRICKS(() -> ChristmasBlocks.SOGGY_GINGERBREAD_BRICKS.get().defaultBlockState()),
        TILES(() -> ChristmasBlocks.SOGGY_GINGERBREAD_TILES.get().defaultBlockState());

        private final Supplier<BlockState> soggyResultSupplier;

        SoggyResult(Supplier<BlockState> soggyResultSupplier) {
            this.soggyResultSupplier = soggyResultSupplier;
        }

        public Supplier<BlockState> getSupplier() {
            return soggyResultSupplier;
        }
    }
}
