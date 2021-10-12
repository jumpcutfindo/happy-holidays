package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;

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

public class BaseGingerbreadBlock extends ChristmasBlock {
    public static final Item.Properties ITEM_PROPERITES =
            new Item.Properties().tab(HappyHolidaysMod.HAPPY_HOLIDAYS_GROUP);

    public BaseGingerbreadBlock(BlockBehaviour.Properties blockProperties) {
        super(blockProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (this.defaultBlockState().getBlock() instanceof Soggifiable) {
            BlockPos pos = context.getClickedPos();

            if (isWaterAround(context.getLevel(), pos)) {
                if (!context.getLevel().isClientSide()) playSoggyEffects((ServerLevel) context.getLevel(), pos);
                return ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get().defaultBlockState();
            } else {
                return super.getStateForPlacement(context);
            }
        } else {
            return super.getStateForPlacement(context);
        }
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  LevelAccessor level, BlockPos blockPos, BlockPos otherBlockPos) {
        if (blockState.getBlock() instanceof Soggifiable && level.getBlockState(otherBlockPos).is(Blocks.WATER)) {
            if (!level.isClientSide()) {
                playSoggyEffects((ServerLevel) level, blockPos);
            }
            return ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get().defaultBlockState();
        } else {
            return blockState;
        }
    }

    public static void playSoggyEffects(ServerLevel level, BlockPos blockPos) {
        level.playSound(null, blockPos, ChristmasSounds.GINGERBREAD_SOGGIFIED.get(), SoundSource.BLOCKS, 1.0f, 1.0f);

        level.sendParticles(ParticleTypes.SPLASH, blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D,
                10, 0.25D, 0.25D, 0.25D, 0.0D);
    }

    public static boolean isWaterAround(Level level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(Blocks.WATER)
                || level.getBlockState(pos.below()).is(Blocks.WATER)
                || level.getBlockState(pos.north()).is(Blocks.WATER)
                || level.getBlockState(pos.south()).is(Blocks.WATER)
                || level.getBlockState(pos.east()).is(Blocks.WATER)
                || level.getBlockState(pos.west()).is(Blocks.WATER);
    }
}
