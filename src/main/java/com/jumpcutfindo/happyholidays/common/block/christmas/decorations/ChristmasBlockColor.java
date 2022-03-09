package com.jumpcutfindo.happyholidays.common.block.christmas.decorations;

import net.minecraft.util.StringRepresentable;

public enum ChristmasBlockColor implements StringRepresentable {
    NONE("none"),
    RED("red"),
    BLUE("blue"),
    YELLOW("yellow"),
    GREEN("green"),
    GOLD("gold"),
    SILVER("silver");

    private final String name;

    private ChristmasBlockColor(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
