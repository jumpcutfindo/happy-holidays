package com.jumpcutfindo.happyholidays.server.data.structs;

import java.util.Calendar;
import java.util.Locale;

public class YearlessDate {
    private final int month, day;

    public YearlessDate(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public boolean isAfter(YearlessDate current) {
        if (this.getMonth() > current.getMonth()) return true;
        if (this.getMonth() == current.getMonth() && this.getDay() > current.getDay()) return true;
        return false;
    }
    public boolean isBefore(YearlessDate current) {
        if (this.getMonth() < current.getMonth()) return true;
        if (this.getMonth() == current.getMonth() && this.getDay() < current.getDay()) return true;
        return false;
    }

    public boolean isSame(YearlessDate current) {
        return this.getMonth() == current.getMonth() && this.getDay() == current.getDay();
    }

    public boolean isValid() {
        return isValidMonth(month) && isValidDay(day);
    }

    public static boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }

    public static boolean isValidDay(int day) {
        return day >= 1 && day <= 31;
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, month - 1, day);

        return String.format("%s %s", calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()), getDay());
    }
}
