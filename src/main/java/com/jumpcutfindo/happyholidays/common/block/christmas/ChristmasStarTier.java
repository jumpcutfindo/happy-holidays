package com.jumpcutfindo.happyholidays.common.block.christmas;

import net.minecraft.util.StringRepresentable;

public enum ChristmasStarTier implements StringRepresentable {
    TIER_0("tier_0"),
    TIER_1("tier_1"),
    TIER_2("tier_2"),
    TIER_3("tier_3"),
    TIER_4("tier_4"),
    TIER_5("tier_5");

    private final String name;

    private ChristmasStarTier(String name) {
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
