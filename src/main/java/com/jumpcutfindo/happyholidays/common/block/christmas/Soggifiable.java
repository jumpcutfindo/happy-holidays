package com.jumpcutfindo.happyholidays.common.block.christmas;

import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockState;

public interface Soggifiable {
    void setSoggyResult(Supplier<BlockState> soggySupplier);
    BlockState getSoggyResult();

    boolean isSoggifiable();
}
