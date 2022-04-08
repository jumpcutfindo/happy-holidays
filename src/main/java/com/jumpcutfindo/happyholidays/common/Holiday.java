package com.jumpcutfindo.happyholidays.common;

public enum Holiday {
    CHRISTMAS("christmas"),
    CNY("cny");

    private final String code;

    Holiday(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Holiday ofCode(String code) {
        Holiday holiday;

        switch (code) {
        default: holiday = CHRISTMAS;
        }

        return holiday;
    }
}
