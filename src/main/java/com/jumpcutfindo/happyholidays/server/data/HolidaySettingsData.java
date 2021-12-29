package com.jumpcutfindo.happyholidays.server.data;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class HolidaySettingsData extends SavedData {
    public static final Date DEFAULT_START = new Date(1970, Calendar.JANUARY, 1);
    public static final Date DEFAULT_END = new Date(1970, Calendar.DECEMBER, 31);

    private Map<String, DateInfo> holidayPeriodMap = Map.of(
            "christmas", new DateInfo(DEFAULT_START, DEFAULT_END)
    );

    @Override
    public CompoundTag save(CompoundTag tag) {
        return null;
    }

    private class DateInfo {
        private final Date start, end;

        public DateInfo(Date start, Date end) {
            this.start = start;
            this.end = end;
        }

        public Date getStart() {
            return start;
        }

        public Date getEnd() {
            return end;
        }

        /*
            Checks whether the interval contains the given date
         */
        public boolean intervalContains(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            int startDay = calendar.get(Calendar.DAY_OF_MONTH);
            int startMonth = calendar.get(Calendar.MONTH);

            calendar.setTime(end);
            int endDay = calendar.get(Calendar.DAY_OF_MONTH);
            int endMonth = calendar.get(Calendar.MONTH);

            calendar.setTime(date);
            int dateDay = calendar.get(Calendar.DAY_OF_MONTH);
            int dateMonth = calendar.get(Calendar.MONTH);

            return (dateMonth >= startMonth && dateDay > startDay) && (dateMonth <= endMonth && dateDay < endDay);
        }

        public boolean startEquals(Date date) {
            return date.equals(start);
        }

        public boolean endEquals(Date date) {
            return date.equals(end);
        }
    }
}
