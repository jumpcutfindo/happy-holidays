package com.jumpcutfindo.happyholidays.common.block.christmas;

import javax.annotation.Nullable;

import com.jumpcutfindo.happyholidays.common.block.HappyHolidaysBlock;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ChristmasBlock extends HappyHolidaysBlock {
    public ChristmasBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void configureBlock() {
        // Default left empty since we don't want the block to do anything
    }

    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> p_152133_, BlockEntityType<E> p_152134_, BlockEntityTicker<? super E> p_152135_) {
        return p_152134_ == p_152133_ ? (BlockEntityTicker<A>)p_152135_ : null;
    }
}
