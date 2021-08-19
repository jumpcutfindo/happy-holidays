package com.jumpcutfindo.happyholidays.common.capabilities.christmas;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.items.CapabilityItemHandler;

public class CapabilityNaughtyNiceHandler {
    @CapabilityInject(INaughtyNiceHandler.class)
    public static Capability<INaughtyNiceHandler> NAUGHTY_NICE_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(INaughtyNiceHandler.class, new DefaultNaughtyNiceHandlerStorage(), NaughtyNiceMeter::new);
    }

    private static class DefaultNaughtyNiceHandlerStorage implements Capability.IStorage<INaughtyNiceHandler> {
        @Nullable
        @Override
        public INBT writeNBT(Capability<INaughtyNiceHandler> capability, INaughtyNiceHandler instance, Direction side) {
            if (!(instance instanceof NaughtyNiceMeter))
                throw new RuntimeException("Cannot serialize to an instance that isn't the default implementation");

            CompoundNBT nbt = new CompoundNBT();
            NaughtyNiceMeter meter = (NaughtyNiceMeter) instance;

            return meter.writeToNBT(nbt);
        }

        @Override
        public void readNBT(Capability<INaughtyNiceHandler> capability, INaughtyNiceHandler instance, Direction side, INBT nbt) {
            if (!(instance instanceof NaughtyNiceMeter))
                throw new RuntimeException("Cannot serialize to an instance that isn't the default implementation");

            CompoundNBT tags = (CompoundNBT) nbt;
            NaughtyNiceMeter meter = (NaughtyNiceMeter) instance;

            meter.readFromNBT(tags);
        }
    }
}
