package com.jumpcutfindo.happyholidays.common.guide.sections;

import com.google.gson.annotations.SerializedName;

public class SerializedSection {
    // Content for an image section
    public String source;
    public int x, y, width, height;

    // Content for a text section
    public String type;
    public String title;
    public String caption;
    public String content;
}
