package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

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

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState otherBlockState,
                                  LevelAccessor level, BlockPos blockPos, BlockPos otherBlockPos) {
        if (blockState.getBlock() instanceof Soggifiable && level.getBlockState(otherBlockPos).is(Blocks.WATER)) {
            level.playSound(null, blockPos, ChristmasSounds.GINGERBREAD_SOGGIFIED.get(), SoundSource.BLOCKS, 1.0f, 1.0f);

            if (!level.isClientSide()) {
                ((ServerLevel) level).sendParticles(ParticleTypes.SPLASH, blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D,
                        10, 0.25D, 0.25D, 0.25D, 0.0D);
            }

            return ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get().defaultBlockState();
        } else {
            return blockState;
        }
    }
}
