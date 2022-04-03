package com.jumpcutfindo.happyholidays.common.registry;

import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasStats;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class HappyHolidaysStats {
    /**
     * Need to add any registration of sub-classes here
     */
    public static void registerStats() {
        ChristmasStats.registerStats();
    }

    public static int valueOf(ServerPlayer player, HappyHolidaysStat stat) {
        return player.getStats().getValue(Stats.CUSTOM.get(stat.getResource()));
    }

    public static void awardStat(ServerPlayer player, HappyHolidaysStat stat) {
        player.awardStat(stat.getResource());
    }

    public static HappyHolidaysStat createCustomStat(String statId, StatFormatter statFormatter) {
        HappyHolidaysStat stat = new HappyHolidaysStat(statId, statFormatter);
        return stat;
    }
}
