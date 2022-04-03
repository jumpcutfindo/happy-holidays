package com.jumpcutfindo.happyholidays.common.block.christmas;

import net.minecraft.util.StringRepresentable;

public enum FestiveCandyShape implements  StringRepresentable {
    X_O("x_o"),
    O_X("o_x");

    private final String name;

    private FestiveCandyShape(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
