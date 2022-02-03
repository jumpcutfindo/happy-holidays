package com.jumpcutfindo.happyholidays.server.data;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.server.data.structs.Interval;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class HolidayIntervalData extends SavedData {
    public static final Collection<String> HOLIDAY_CODES = List.of("christmas");
    public static final Map<String, Map<String, Interval>> HOLIDAY_PRESETS = Util.make(Maps.newHashMap(), (map) -> {
        map.put("christmas", Util.make(Maps.newHashMap(), (intervalMap) -> {
            intervalMap.put("all_year", Interval.all());
            intervalMap.put("seasonal", Interval.of(12, 1, 12, 31));
        }));
    });

    public static final String DATA_NAME = HappyHolidaysMod.MOD_ID + "_intervals";

    private final Map<String, Interval> holidayIntervalMap = Util.make(Maps.newHashMap(), (map) -> {
        map.put("christmas", Interval.all());
    });

    public HolidayIntervalData() {
    }

    public Interval get(String holidayCode) {
        return holidayIntervalMap.get(holidayCode);
    }

    public void put(String holidayCode, Interval interval) {
        holidayIntervalMap.put(holidayCode, interval);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        for (String holidayCode : holidayIntervalMap.keySet()) {
            CompoundTag holidayTag = new CompoundTag();

            holidayTag.putInt("StartMonth", holidayIntervalMap.get("christmas").getStart().getMonth());
            holidayTag.putInt("StartDay", holidayIntervalMap.get("christmas").getStart().getDay());
            holidayTag.putInt("EndMonth", holidayIntervalMap.get("christmas").getEnd().getMonth());
            holidayTag.putInt("EndDay", holidayIntervalMap.get("christmas").getEnd().getDay());

            tag.put(StringUtils.capitalize(holidayCode), holidayTag);
        }
        return tag;
    }

    public static HolidayIntervalData createFromTag(CompoundTag tag) {
        HolidayIntervalData holidayIntervalData = new HolidayIntervalData();

        for (String holidayCode : HOLIDAY_CODES) {
            String tagName = StringUtils.capitalize(holidayCode);
            if (tag.contains(tagName)) {
                CompoundTag holidayTag = tag.getCompound(tagName);

                int startMonth = holidayTag.getInt("StartMonth");
                int startDay = holidayTag.getInt("StartDay");
                int endMonth = holidayTag.getInt("EndMonth");
                int endDay = holidayTag.getInt("EndDay");

                holidayIntervalData.put(holidayCode, Interval.of(startMonth, startDay, endMonth, endDay));
            }
        }

        return holidayIntervalData;
    }

    public static HolidayIntervalData retrieve(ServerLevel serverLevel) {
        return serverLevel.getDataStorage().computeIfAbsent(
                HolidayIntervalData::createFromTag,
                HolidayIntervalData::new,
                HolidayIntervalData.DATA_NAME
        );
    }
}
