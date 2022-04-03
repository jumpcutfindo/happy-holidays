package com.jumpcutfindo.happyholidays.common.capabilities.christmas;

import java.util.Optional;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class NaughtyNiceMeter implements INaughtyNiceHandler {
    public static final int VALUE_NICE_MAX = 200;
    public static final int VALUE_NICE_MIN = 10;
    public static final int VALUE_NAUGHTY_MIN = -10;
    public static final int VALUE_NAUGHTY_MAX = -200;

    private int value;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void addNaughty(int value) {
        addValue(-value);
    }

    @Override
    public void addNice(int value) {
        addValue(value);
    }

    private void addValue(int actualValue) {
        this.value += actualValue;
        if (this.value <= VALUE_NAUGHTY_MAX) this.value = VALUE_NAUGHTY_MAX;
        if (this.value >= VALUE_NICE_MAX) this.value = VALUE_NICE_MAX;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean isNaughty() {
        return value <= VALUE_NAUGHTY_MIN;
    }

    @Override
    public boolean isNice() {
        return value >= VALUE_NICE_MIN;
    }

    @Override
    public boolean isNeutral() {
        return value > VALUE_NAUGHTY_MIN && value < VALUE_NICE_MIN;
    }

    public boolean isMaxNice() {
        return value == VALUE_NICE_MAX;
    }

    public boolean isMaxNaughty() {
        return value == VALUE_NAUGHTY_MAX;
    }

    public NaughtyNiceMeter.State getState() {
        if (this.isMaxNaughty()) return State.MAX_NAUGHTY;
        else if (this.isNaughty()) return State.NAUGHTY;
        else if (this.isNeutral()) return State.NEUTRAL;
        else if (this.isMaxNice()) return State.MAX_NICE;
        else return State.NICE;
    }

    public CompoundTag writeToNBT(CompoundTag nbt) {
        nbt.putInt("NaughtyNiceValue", this.getValue());

        return nbt;
    }

    public NaughtyNiceMeter readFromNBT(CompoundTag nbt) {
        this.value = nbt.getInt("NaughtyNiceValue");

        return this;
    }

    public static void evaluateAction(Player playerEntity, NaughtyNiceAction action) {
        if (playerEntity == null) return;

        Optional<INaughtyNiceHandler> naughtyNiceCapability =
                playerEntity.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

        if (naughtyNiceCapability.isPresent()) {
            INaughtyNiceHandler naughtyNiceMeter = naughtyNiceCapability.get();

            if (action.getActionType() == NaughtyNiceAction.Type.NAUGHTY) {
                naughtyNiceMeter.addNaughty(action.getCost());
            } else {
                naughtyNiceMeter.addNice(action.getCost());
            }
        }
    }

    public static int getMeterValue(Player playerEntity) {
        if (playerEntity == null) return 0;

        Optional<INaughtyNiceHandler> naughtyNiceCapability =
                playerEntity.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

        if (naughtyNiceCapability.isPresent()) {
            NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) naughtyNiceCapability.get();
            return naughtyNiceMeter.getValue();
        }

        return 0;
    }

    public static void resetMeter(Player playerEntity) {
        if (playerEntity == null) return;

        Optional<INaughtyNiceHandler> naughtyNiceCapability =
                playerEntity.getCapability(CapabilityNaughtyNice.NAUGHTY_NICE_CAPABILITY).resolve();

        if (naughtyNiceCapability.isPresent()) {
            NaughtyNiceMeter naughtyNiceMeter = (NaughtyNiceMeter) naughtyNiceCapability.get();
            naughtyNiceMeter.setValue(0);
        }
    }

    public enum State {
        MAX_NAUGHTY, NAUGHTY, NEUTRAL, NICE, MAX_NICE
    }
}
