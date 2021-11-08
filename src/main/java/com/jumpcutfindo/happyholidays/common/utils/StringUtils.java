package com.jumpcutfindo.happyholidays.common.utils;

public class StringUtils {
    public static String convertTicksToString(long ticks) {
        long seconds = ticks / 20;

        return String.format("%dm %ds", seconds / 60, seconds % 60);
    }
}
