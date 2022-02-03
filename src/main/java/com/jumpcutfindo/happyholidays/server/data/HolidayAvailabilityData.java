package com.jumpcutfindo.happyholidays.server.data;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.server.data.structs.Availability;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class HolidayAvailabilityData extends SavedData {
    public static final String DATA_NAME = HappyHolidaysMod.MOD_ID + "_holiday_availability";

    public static final String CHRISTMAS_GRINCH_SPAWN = "canGrinchSpawn";
    public static final String CHRISTMAS_GINGERBREAD_SPAWN = "canGingerbreadSpawn";
    public static final String CHRISTMAS_NUTCRACKER_SPAWN = "canNutcrackerSpawn";
    public static final String CHRISTMAS_SPAWN_SANTA_STAR = "canSpawnSantaWithStar";
    public static final String CHRISTMAS_STAR_GIVE_BUFF = "canStarGiveBuff";
    public static final String CHRISTMAS_PRESENTS_GROW = "canPresentsGrow";
    public static final String CHRISTMAS_STOCKINGS_FILL = "canStockingsFill";

    public static final Map<Holiday, Set<String>> HOLIDAY_KEYSETS = Util.make(Maps.newHashMap(), (map) -> {
       map.put(Holiday.CHRISTMAS, Sets.newHashSet(CHRISTMAS_GRINCH_SPAWN,
               CHRISTMAS_GINGERBREAD_SPAWN, CHRISTMAS_NUTCRACKER_SPAWN, CHRISTMAS_SPAWN_SANTA_STAR,
               CHRISTMAS_STAR_GIVE_BUFF, CHRISTMAS_PRESENTS_GROW, CHRISTMAS_STOCKINGS_FILL));
    });

    private final Map<Holiday, Map<String, Availability>> holidayAvailability = Util.make(Maps.newHashMap(), (map) -> {
        map.put(Holiday.CHRISTMAS, Util.make(Maps.newHashMap(), (toggleMap) -> {
            toggleMap.put(CHRISTMAS_GRINCH_SPAWN, Availability.INTERVAL_ONLY);
            toggleMap.put(CHRISTMAS_GINGERBREAD_SPAWN, Availability.INTERVAL_ONLY);
            toggleMap.put(CHRISTMAS_NUTCRACKER_SPAWN, Availability.INTERVAL_ONLY);
            toggleMap.put(CHRISTMAS_SPAWN_SANTA_STAR, Availability.INTERVAL_ONLY);
            toggleMap.put(CHRISTMAS_STAR_GIVE_BUFF, Availability.INTERVAL_ONLY);
            toggleMap.put(CHRISTMAS_PRESENTS_GROW, Availability.INTERVAL_ONLY);
            toggleMap.put(CHRISTMAS_STOCKINGS_FILL, Availability.INTERVAL_ONLY);
        }));
    });

    public Availability get(Holiday holiday, String key) {
        return holidayAvailability.get(holiday).get(key);
    }

    public Map<String, Availability> getHolidayAvailabilities(Holiday holiday) {
        return holidayAvailability.get(holiday);
    }

    public void setAvailability(Holiday holiday, String code, Availability availability) {
        holidayAvailability.get(holiday).put(code, availability);
        this.setDirty();
    }

    public void setAvailability(Holiday holiday, String code, int id) {
        holidayAvailability.get(holiday).put(code, Availability.ofId(id));
        this.setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        for (Holiday holiday : Holiday.values()) {
            CompoundTag holidayTag = new CompoundTag();

            String holidayCode = holiday.getCode();
            String tagName = StringUtils.capitalize(holidayCode);

            for (String key : holidayAvailability.get(holiday).keySet()) {
                holidayTag.putInt(key, holidayAvailability.get(holiday).get(key).getId());
            }

            tag.put(tagName, holidayTag);
        }

        return tag;
    }

    public static HolidayAvailabilityData createFromTag(CompoundTag tag) {
        HolidayAvailabilityData availabilityData = new HolidayAvailabilityData();

        for (Holiday holiday : Holiday.values()) {
            String holidayCode = holiday.getCode();
            String tagName = StringUtils.capitalize(holidayCode);

            if (tag.contains(tagName)) {
                CompoundTag holidayTag = tag.getCompound(tagName);

                for (String key : holidayTag.getAllKeys()) {
                    availabilityData.setAvailability(holiday, key, holidayTag.getInt(key));
                }
            }
        }

        return availabilityData;
    }

    public static HolidayAvailabilityData retrieve(ServerLevel serverLevel) {
        return serverLevel.getDataStorage().computeIfAbsent(
            HolidayAvailabilityData::createFromTag,
            HolidayAvailabilityData::new,
            HolidayAvailabilityData.DATA_NAME
        );
    }
}
