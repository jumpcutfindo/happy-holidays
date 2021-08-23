package com.jumpcutfindo.happyholidays.server.data;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.storage.WorldSavedData;

public class SantaSummonSavedData extends WorldSavedData {
    public static final String DATA_NAME = HappyHolidaysMod.MOD_ID + "_santa";

    private boolean hasSummoned;
    private long lastSummonTime;

    public SantaSummonSavedData() {
        super(DATA_NAME);
    }

    public void setLastSummonTime(long lastSummonTime) {
        this.lastSummonTime = lastSummonTime;
    }

    public boolean canSummon(long gameTime) {
        return gameTime > lastSummonTime;
    }

    @Override
    public void load(CompoundNBT nbt) {
        this.lastSummonTime = nbt.getLong("LastSummonTime");
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        // TODO: Change so that it is the next time that Santa can be summoned
        nbt.putLong("LastSummonTime", lastSummonTime);

        return nbt;
    }
}
