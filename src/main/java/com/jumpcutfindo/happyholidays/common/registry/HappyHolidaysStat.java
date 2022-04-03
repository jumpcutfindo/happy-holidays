package com.jumpcutfindo.happyholidays.common.registry;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;

public class HappyHolidaysStat {
    private final String statId;
    private final ResourceLocation resource;
    private final StatFormatter statFormatter;

    public HappyHolidaysStat(String statId, StatFormatter statFormatter) {
        this.statId = statId;
        this.resource = new ResourceLocation(HappyHolidaysMod.MOD_ID, statId);
        this.statFormatter = statFormatter;
    }

    public String getId() {
        return statId;
    }

    public ResourceLocation getResource() {
        return resource;
    }

    public StatFormatter getStatFormatter() {
        return statFormatter;
    }
}
