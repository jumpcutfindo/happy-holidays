package com.jumpcutfindo.happyholidays.common.guide;

import java.util.List;

import com.google.common.collect.Lists;

public class Chapter {
    public final String title;
    public final String description;

    public final List<Section> sections;

    public Chapter(String title, String description) {
        this.title = title;
        this.description = description;

        this.sections = Lists.newArrayList();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Section> getSections() {
        return sections;
    }
}
