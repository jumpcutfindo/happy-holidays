package com.jumpcutfindo.happyholidays.common.capabilities.christmas;

import net.minecraft.nbt.CompoundNBT;

public class NaughtyNiceMeter implements INaughtyNiceHandler {
    private int naughty, nice;

    @Override
    public int getValue() {
        return nice - naughty;
    }

    @Override
    public void addNaughty(int value) {
        naughty += value;
    }

    @Override
    public void addNice(int value) {
        nice += value;
    }

    @Override
    public int getNaughty() {
        return naughty;
    }

    @Override
    public int getNice() {
        return nice;
    }

    @Override
    public boolean isNaughty() {
        return naughty > nice;
    }

    @Override
    public boolean isNice() {
        return nice > naughty;
    }

    public CompoundNBT writeToNBT(CompoundNBT nbt) {
        nbt.putInt("NaughtyValue", this.getNaughty());
        nbt.putInt("NiceValue", this.getNice());

        return nbt;
    }

    public NaughtyNiceMeter readFromNBT(CompoundNBT nbt) {
        this.naughty = nbt.getInt("NaughtyValue");
        this.nice = nbt.getInt("NiceValue");

        return this;
    }
}
