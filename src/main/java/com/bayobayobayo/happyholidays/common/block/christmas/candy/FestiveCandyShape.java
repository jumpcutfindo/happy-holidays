package com.bayobayobayo.happyholidays.common.block.christmas.candy;

import net.minecraft.util.IStringSerializable;

public enum FestiveCandyShape implements  IStringSerializable {
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
