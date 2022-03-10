package com.jumpcutfindo.happyholidays.client.particle.christmas;

public enum ChristmasParticleColor {
    RED(16711680),
    BLUE(255),
    YELLOW(16776960),
    GREEN(65280),
    GOLD(16764738),
    SILVER(16777215);

    private final int color;
    ChristmasParticleColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
