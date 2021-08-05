package com.jumpcutfindo.happyholidays.common.block.christmas.misc;

import net.minecraft.util.IStringSerializable;

public enum ChristmasStarTier implements IStringSerializable {
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
