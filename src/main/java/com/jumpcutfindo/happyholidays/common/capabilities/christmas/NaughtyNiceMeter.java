package com.jumpcutfindo.happyholidays.common.capabilities.christmas;

import java.util.Optional;
import java.util.UUID;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

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

    public static void evaluateAction(PlayerEntity playerEntity, NaughtyNiceAction action) {
        if (playerEntity == null) return;

        Optional<INaughtyNiceHandler> naughtyNiceCapability =
                playerEntity.getCapability(CapabilityNaughtyNiceHandler.NAUGHTY_NICE_CAPABILITY).resolve();

        if (naughtyNiceCapability.isPresent()) {
            INaughtyNiceHandler naughtyNiceMeter = naughtyNiceCapability.get();

            if (action.getActionType() == NaughtyNiceAction.Type.NAUGHTY) {
                naughtyNiceMeter.addNaughty(action.getCost());

                // TODO: Remove naughty / nice message
                playerEntity.sendMessage(new StringTextComponent(String.format("Naughty / Nice meter: %d (-%d)",
                        naughtyNiceMeter.getValue(), action.getCost())).withStyle(TextFormatting.RED),
                        UUID.randomUUID());

            } else {
                naughtyNiceMeter.addNice(action.getCost());

                // TODO: Remove naughty / nice message
                playerEntity.sendMessage(new StringTextComponent(String.format("Naughty / Nice meter: %d (+%d)",
                        naughtyNiceMeter.getValue(), action.getCost())).withStyle(TextFormatting.GREEN),
                        UUID.randomUUID());
            }
        }
    }
}
