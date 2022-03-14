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
    public static final ResourceLocation GIFTS_RECEIVED = makeCustomStat("christmas_gifts_received", StatFormatter.DEFAULT);
    public static final ResourceLocation GINGERBREAD_MEN_DRIED = makeCustomStat("gingerbread_men_dried", StatFormatter.DEFAULT);
    public static final ResourceLocation SANTA_ELVES_HELPED = makeCustomStat("santa_elves_helped", StatFormatter.DEFAULT);
    public static final ResourceLocation GRINCHES_APPEASED = makeCustomStat("grinches_appeased", StatFormatter.DEFAULT);
    public static final ResourceLocation ANGRY_SANTAS_DEFEATED = makeCustomStat("angry_santas_defeated", StatFormatter.DEFAULT);
    public static final ResourceLocation HAPPY_SANTAS_RECEIVED = makeCustomStat("happy_santas_received", StatFormatter.DEFAULT);
    public static final ResourceLocation NUTCRACKERS_TAMED = makeCustomStat("nutcrackers_tamed", StatFormatter.DEFAULT);


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
