package com.jumpcutfindo.happyholidays.common.utils;

public class MathUtils {
    public static boolean isEven(Number number) {
        return ((int) number) % 2 == 0;
    }

    public static boolean isOdd(Number number) {
        return !isEven(number);
    }
}
