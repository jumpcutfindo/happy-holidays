package com.jumpcutfindo.happyholidays.common.guide.sections;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.resources.ResourceLocation;

public class ImageSection implements ISection {
    private String source;
    private int x, y, width, height;
    private float scale = 1.0f;

    private ResourceLocation resourceLocation;

    public ImageSection(String source, int x, int y, int width, int height, float scale) {
        this.source = source;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public ResourceLocation getSource() {
        if (resourceLocation == null) this.resourceLocation = new ResourceLocation(HappyHolidaysMod.MOD_ID, source);
        return this.resourceLocation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getScale() {
        return scale;
    }

    public ImageSection scale(float scale) {
        return new ImageSection(source, (int) (x * scale), (int) (y * scale), (int) (width * scale), (int) (height * scale), scale);
    }
}
