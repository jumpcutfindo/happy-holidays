package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Sets;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class ChristmasStats {
    public static final Set<Pair<String, StatFormatter>> STATS = Sets.newHashSet();

    public static final ResourceLocation USE_SNOW_GLOBE = makeCustomStat("use_snow_globe", StatFormatter.DEFAULT);

    public static void registerStats() {
        STATS.forEach(statPair -> {
            Registry.register(Registry.CUSTOM_STAT, statPair.getKey(), resLoc(statPair.getKey()));
            Stats.CUSTOM.get(resLoc(statPair.getKey()), statPair.getValue());
        });
    }

    private static ResourceLocation makeCustomStat(String statId, StatFormatter statFormatter) {
        STATS.add(Pair.of(statId, statFormatter));
        return resLoc(statId);
    }

    private static ResourceLocation resLoc(String statId) {
        return new ResourceLocation(HappyHolidaysMod.MOD_ID, statId);
    }
}
