package com.jumpcutfindo.happyholidays.common.guide;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

public class Guide {
    public final String id;
    public final String title;
    public final String description;

    public List<Chapter> chapters;

    public Guide(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;

        this.chapters = Lists.newArrayList();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
