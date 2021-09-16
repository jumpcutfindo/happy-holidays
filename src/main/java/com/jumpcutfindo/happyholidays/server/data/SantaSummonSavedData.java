package com.jumpcutfindo.happyholidays.server.data;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class SantaSummonSavedData extends SavedData {
    public static final String DATA_NAME = HappyHolidaysMod.MOD_ID + "_santa";

    private boolean hasSummoned;
    private long lastSummonTime;

    public SantaSummonSavedData() {
    }

    public void setLastSummonTime(long lastSummonTime) {
        this.lastSummonTime = lastSummonTime;
    }

    public long getNextSummonTime() {
        return lastSummonTime + 24000;
    }

    public boolean canSummon(long gameTime) {
        return gameTime > getNextSummonTime();
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putLong("LastSummonTime", lastSummonTime);

        return nbt;
    }

    public static SantaSummonSavedData createFromTag(CompoundTag tag) {
        SantaSummonSavedData newData = new SantaSummonSavedData();

        newData.setLastSummonTime(tag.getLong("LastSummonTime"));

        return newData;
    }
}
