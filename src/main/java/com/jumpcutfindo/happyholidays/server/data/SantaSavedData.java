package com.jumpcutfindo.happyholidays.server.data;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

public class SantaSavedData extends SavedData {
    public static final String DATA_NAME = HappyHolidaysMod.MOD_ID + "_santa";

    private boolean hasSummonedBefore;
    private boolean hasDefeatedBefore;
    private long lastSummonTime;

    public SantaSavedData() {
    }

    public void setLastSummonTime(long lastSummonTime) {
        this.lastSummonTime = lastSummonTime;
        this.hasSummonedBefore = true;
    }

    public void setDefeated() {
        this.hasDefeatedBefore = true;
    }

    private void setHasSummonedBefore(boolean hasSummonedBefore) {
        this.hasSummonedBefore = hasSummonedBefore;
    }

    private void setHasDefeatedBefore(boolean hasDefeatedBefore) {
        this.hasDefeatedBefore = hasDefeatedBefore;
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
        nbt.putBoolean("HasSummonedBefore", hasSummonedBefore);
        nbt.putBoolean("HasDefeatedBefore", hasDefeatedBefore);

        return nbt;
    }

    public static SantaSavedData createFromTag(CompoundTag tag) {
        SantaSavedData newData = new SantaSavedData();

        newData.setLastSummonTime(tag.getLong("LastSummonTime"));
        newData.setHasSummonedBefore(tag.getBoolean("HasSummonedBefore"));
        newData.setHasDefeatedBefore(tag.getBoolean("HasDefeatedBefore"));

        return newData;
    }
}
