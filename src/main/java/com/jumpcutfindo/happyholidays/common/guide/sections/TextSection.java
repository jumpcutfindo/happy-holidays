package com.jumpcutfindo.happyholidays.common.guide.sections;

import javax.annotation.Nullable;

public class TextSection implements ISection {
    public final String title;
    public final String caption;
    public final String content;

    public TextSection(String title, @Nullable String caption, String content) {
        this.title = title;
        this.caption = caption;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getContent() {
        return content;
    }
}
