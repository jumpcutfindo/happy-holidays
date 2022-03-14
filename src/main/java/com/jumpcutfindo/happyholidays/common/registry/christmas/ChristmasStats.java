package com.jumpcutfindo.happyholidays.common.registry.christmas;

import java.util.Set;

import com.google.common.collect.Sets;
import com.jumpcutfindo.happyholidays.common.registry.HappyHolidaysStat;
import com.jumpcutfindo.happyholidays.common.registry.HappyHolidaysStats;

import net.minecraft.core.Registry;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class ChristmasStats {
    public static final Set<HappyHolidaysStat> STATS = Sets.newHashSet();

    public static final HappyHolidaysStat USE_SNOW_GLOBE = createStat("use_snow_globe", StatFormatter.DEFAULT);
    public static final HappyHolidaysStat GIFTS_RECEIVED = createStat("christmas_gifts_received", StatFormatter.DEFAULT);
    public static final HappyHolidaysStat GINGERBREAD_MEN_DRIED = createStat("gingerbread_men_dried", StatFormatter.DEFAULT);
    public static final HappyHolidaysStat SANTA_ELVES_HELPED = createStat("santa_elves_helped", StatFormatter.DEFAULT);
    public static final HappyHolidaysStat GRINCHES_APPEASED = createStat("grinches_appeased", StatFormatter.DEFAULT);
    public static final HappyHolidaysStat ANGRY_SANTAS_DEFEATED = createStat("angry_santas_defeated", StatFormatter.DEFAULT);
    public static final HappyHolidaysStat HAPPY_SANTAS_RECEIVED = createStat("happy_santas_received", StatFormatter.DEFAULT);
    public static final HappyHolidaysStat NUTCRACKERS_TAMED = createStat("nutcrackers_tamed", StatFormatter.DEFAULT);

    public static void registerStats() {
        STATS.forEach(stat -> {
            Registry.register(Registry.CUSTOM_STAT, stat.getId(), stat.getResource());
            Stats.CUSTOM.get(stat.getResource(), stat.getStatFormatter());
        });
    }

    private static HappyHolidaysStat createStat(String statId, StatFormatter formatter) {
        HappyHolidaysStat stat = HappyHolidaysStats.createCustomStat(statId, formatter);
        STATS.add(stat);

        return stat;
    }
}
