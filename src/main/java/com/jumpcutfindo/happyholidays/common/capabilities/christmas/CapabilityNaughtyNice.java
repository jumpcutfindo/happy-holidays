package com.jumpcutfindo.happyholidays.common.capabilities.christmas;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CapabilityNaughtyNice {
    @CapabilityInject(INaughtyNiceHandler.class)
    public static Capability<INaughtyNiceHandler> NAUGHTY_NICE_CAPABILITY = null;
}
