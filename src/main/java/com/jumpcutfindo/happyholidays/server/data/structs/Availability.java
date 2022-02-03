package com.jumpcutfindo.happyholidays.server.data.structs;

public enum Availability {
    ALWAYS(0),
    NEVER(1),
    INTERVAL_ONLY(2);

    private final int id;
    Availability(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Availability ofId(int id) {
        Availability availability;

        switch (id) {
            case 0 -> availability = Availability.ALWAYS;
            case 1 -> availability = Availability.NEVER;
            default -> availability = Availability.INTERVAL_ONLY;
        }

        return availability;
    }

    public static Availability fromString(String s) {
        Availability availability;
        switch (s) {
            case "ALWAYS" -> availability = Availability.ALWAYS;
            case "NEVER" -> availability = Availability.NEVER;
            default -> availability = Availability.INTERVAL_ONLY;
        }

        return availability;
    }
}
