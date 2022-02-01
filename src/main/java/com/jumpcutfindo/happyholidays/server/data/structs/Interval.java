package com.jumpcutfindo.happyholidays.server.data.structs;

public class Interval {
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

    public boolean contains(YearlessDate current) {
        if (start.getMonth() > end.getMonth()) return current.isAfter(start) || current.isBefore(end);
        else return current.isAfter(start) && current.isBefore(end);
    }
}
