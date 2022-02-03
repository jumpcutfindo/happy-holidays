package com.jumpcutfindo.happyholidays.server.data;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.server.data.structs.Interval;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class HolidayIntervalData extends SavedData {
    public static final String DATA_NAME = HappyHolidaysMod.MOD_ID + "_holiday_intervals";

    public static final Map<Holiday, Map<String, Interval>> HOLIDAY_PRESETS = Util.make(Maps.newHashMap(), (map) -> {
        map.put(Holiday.CHRISTMAS, Util.make(Maps.newHashMap(), (intervalMap) -> {
            intervalMap.put("all_year", Interval.all());
            intervalMap.put("seasonal", Interval.of(12, 1, 12, 31));
        }));
    });

    private final Map<Holiday, Interval> holidayIntervalMap = Util.make(Maps.newHashMap(), (map) -> {
        map.put(Holiday.CHRISTMAS, Interval.all());
    });

    public HolidayIntervalData() {
    }

    public Interval get(Holiday holiday) {
        return holidayIntervalMap.get(holiday);
    }

    public void put(Holiday holiday, Interval interval) {
        holidayIntervalMap.put(holiday, interval);
        this.setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        for (Holiday holiday : holidayIntervalMap.keySet()) {
            String holidayCode = holiday.getCode();
            CompoundTag holidayTag = new CompoundTag();

            holidayTag.putInt("StartMonth", holidayIntervalMap.get(Holiday.CHRISTMAS).getStart().getMonth());
            holidayTag.putInt("StartDay", holidayIntervalMap.get(Holiday.CHRISTMAS).getStart().getDay());
            holidayTag.putInt("EndMonth", holidayIntervalMap.get(Holiday.CHRISTMAS).getEnd().getMonth());
            holidayTag.putInt("EndDay", holidayIntervalMap.get(Holiday.CHRISTMAS).getEnd().getDay());

            tag.put(StringUtils.capitalize(holidayCode), holidayTag);
        }
        return tag;
    }

    public static HolidayIntervalData createFromTag(CompoundTag tag) {
        HolidayIntervalData holidayIntervalData = new HolidayIntervalData();

        for (Holiday holiday : Holiday.values()) {
            String holidayCode = holiday.getCode();
            String tagName = StringUtils.capitalize(holidayCode);
            if (tag.contains(tagName)) {
                CompoundTag holidayTag = tag.getCompound(tagName);

                int startMonth = holidayTag.getInt("StartMonth");
                int startDay = holidayTag.getInt("StartDay");
                int endMonth = holidayTag.getInt("EndMonth");
                int endDay = holidayTag.getInt("EndDay");

                holidayIntervalData.put(holiday, Interval.of(startMonth, startDay, endMonth, endDay));
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
