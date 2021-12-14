package com.jumpcutfindo.happyholidays.server.data;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class SantaSavedData extends SavedData {
    public static final String DATA_NAME = HappyHolidaysMod.MOD_ID + "_santa";

    private static final long SUMMON_COOLDOWN = 72000; // 3 Minecraft days is the default

    private boolean hasSummonedBefore;
    private boolean hasDefeatedBefore;
    private long lastSummonTime;
    private long nextSummonTime;
    private long summonCooldown = SUMMON_COOLDOWN;

    public SantaSavedData() {
    }

    public void summoned(long currTime) {
        this.lastSummonTime = currTime;
        this.nextSummonTime = currTime + summonCooldown;
        this.hasSummonedBefore = true;
    }

    public void setNextSummonTime(long time) {
        this.nextSummonTime = time;
    }

    private void setLastSummonTime(long time) {
        this.lastSummonTime = time;
    }

    public boolean isDefeated() {
        return hasDefeatedBefore;
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

    public void setSummonCooldown(long summonCooldown) {
        this.summonCooldown = summonCooldown;
    }

    public long getNextSummonTime() {
        return this.nextSummonTime;
    }

    public boolean canSummon(long gameTime) {
        return gameTime > this.nextSummonTime;
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        nbt.putLong("LastSummonTime", lastSummonTime);
        nbt.putLong("NextSummonTime", nextSummonTime);
        nbt.putBoolean("HasSummonedBefore", hasSummonedBefore);
        nbt.putBoolean("HasDefeatedBefore", hasDefeatedBefore);
        nbt.putLong("SummonCooldown", summonCooldown);

        return nbt;
    }

    public static SantaSavedData createFromTag(CompoundTag tag) {
        SantaSavedData newData = new SantaSavedData();

        newData.setNextSummonTime(tag.getLong("NextSummonTime"));
        newData.setLastSummonTime(tag.getLong("LastSummonTime"));
        newData.setHasSummonedBefore(tag.getBoolean("HasSummonedBefore"));
        newData.setHasDefeatedBefore(tag.getBoolean("HasDefeatedBefore"));
        newData.setSummonCooldown(tag.getLong("SummonCooldown"));

        return newData;
    }

    public static SantaSavedData retrieve(ServerLevel serverLevel) {
        return serverLevel.getDataStorage().computeIfAbsent(
                SantaSavedData::createFromTag,
                SantaSavedData::new,
                SantaSavedData.DATA_NAME
        );
    }
}
