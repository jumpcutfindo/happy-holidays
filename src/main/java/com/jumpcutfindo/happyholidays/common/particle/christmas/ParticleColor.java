package com.jumpcutfindo.happyholidays.common.particle.christmas;

public enum ParticleColor {
    RED(16711680),
    BLUE(255),
    YELLOW(16776960),
    GREEN(65280),
    GOLD(16764738),
    SILVER(16777215);

    private final int color;
    ParticleColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
