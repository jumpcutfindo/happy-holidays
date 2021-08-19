package com.jumpcutfindo.happyholidays.common.capabilities.christmas;

public interface INaughtyNiceHandler {
    int getValue();

    void addNaughty(int value);
    void addNice(int value);

    int getNaughty();
    int getNice();

    boolean isNaughty();
    boolean isNice();
}
