package com.jumpcutfindo.happyholidays.server.data.structs;

import java.time.LocalDateTime;

import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.server.data.HolidayAvailabilityData;
import com.jumpcutfindo.happyholidays.server.data.HolidayIntervalData;

import net.minecraft.server.level.ServerLevel;

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

    public static boolean isAvailable(ServerLevel serverLevel, Holiday holiday, String key) {
        HolidayAvailabilityData availabilityData = HolidayAvailabilityData.retrieve(serverLevel);

        Availability availability = availabilityData.get(holiday, key);

        if (availability == Availability.ALWAYS) return true;
        else if (availability == Availability.NEVER) return false;
        else {
            HolidayIntervalData intervalData = HolidayIntervalData.retrieve(serverLevel);
            Interval interval = intervalData.get(holiday);
            YearlessDate today = new YearlessDate(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth());

            return interval.contains(today);
        }
    }
}
