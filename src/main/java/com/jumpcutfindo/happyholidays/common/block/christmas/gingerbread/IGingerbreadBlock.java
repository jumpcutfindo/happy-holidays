package com.jumpcutfindo.happyholidays.common.block.christmas.gingerbread;

import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockState;

public interface IGingerbreadBlock {
    void setSoggyResult(Supplier<BlockState> soggySupplier);
    boolean isSoggifiable();
}
