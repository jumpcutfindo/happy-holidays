package com.jumpcutfindo.happyholidays.common.capabilities.christmas;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class NaughtyNiceProvider implements ICapabilityProvider {
    private final NaughtyNiceMeter meter = new NaughtyNiceMeter();
    private final LazyOptional<INaughtyNiceHandler> meterOptional = LazyOptional.of(() -> meter);

    public void invalidate() {
        meterOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return meterOptional.cast();
    }
}
