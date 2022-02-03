package com.jumpcutfindo.happyholidays.server.data.structs;

import java.util.Map;

public class Interval {
    public static final Map<String, Interval> SEASONAL_HOLIDAY_INTERVALS = Map.ofEntries(
            Map.entry("christmas", Interval.of(12, 1, 12, 31))
    );

    private final YearlessDate start, end;

    public Interval(YearlessDate start, YearlessDate end) {
        this.start = start;
        this.end = end;
    }

    public YearlessDate getStart() {
        return start;
    }

    public YearlessDate getEnd() {
        return end;
    }

    public boolean isValid() {
        return start.isValid() && end.isValid();
    }

    public boolean contains(YearlessDate current) {
        if (start.getMonth() > end.getMonth()) return current.isAfter(start) || current.isBefore(end);
        else return current.isAfter(start) && current.isBefore(end);
    }

    public static boolean isValidString(String s) {
        return s.matches("([0-9]){2}-([0-9]){2} ([0-9]){2}-([0-9]){2}");
    }

    public static Interval of(int startMonth, int startDay, int endMonth, int endDay) {
        return new Interval(new YearlessDate(startMonth, startDay), new YearlessDate(endMonth, endDay));
    }

    public static Interval all() {
        return Interval.of(1, 1, 12, 31);
    }
}
