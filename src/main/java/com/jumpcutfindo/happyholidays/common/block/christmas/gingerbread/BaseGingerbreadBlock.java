package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasBlock;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
                                  LevelAccessor world, BlockPos blockPos, BlockPos otherBlockPos) {
        if (blockState.getBlock() instanceof Soggifiable) {
            return world.getBlockState(otherBlockPos).is(Blocks.WATER) ? ChristmasBlocks.SOGGY_GINGERBREAD_BLOCK.get().defaultBlockState() : blockState;
        } else {
            return blockState;
        }
    }
}
