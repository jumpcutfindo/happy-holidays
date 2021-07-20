package com.bayobayobayo.happyholidays.common.block.christmas.decorations;

import net.minecraft.util.IStringSerializable;

public enum WallDecorationShape implements IStringSerializable {
    STRAIGHT("straight"),
    LEFT_FACE("left_face"),
    RIGHT_FACE("right_face"),
    SIDE_FACE("side_face"),
    ALL_FACE("all_face");

    private final String name;

    private WallDecorationShape(String name) {
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