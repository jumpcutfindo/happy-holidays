package com.jumpcutfindo.happyholidays.common.capabilities.christmas;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class NaughtyNiceProvider implements ICapabilitySerializable<CompoundNBT> {
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

    @Override
    public CompoundNBT serializeNBT() {
        if (CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY.writeNBT(meter, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY != null) {
            CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY.readNBT(meter, null, nbt);
        }
    }
}
