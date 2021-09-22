package com.jumpcutfindo.happyholidays.common.capabilities.christmas;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class NaughtyNiceProvider implements ICapabilitySerializable<CompoundTag> {
    private final NaughtyNiceMeter meter = new NaughtyNiceMeter();
    private final LazyOptional<INaughtyNiceHandler> meterOptional = LazyOptional.of(() -> meter);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return meterOptional.cast();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        return meter.writeToNBT(tag);
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if(nbt != null) meter.readFromNBT(nbt);
    }
}
