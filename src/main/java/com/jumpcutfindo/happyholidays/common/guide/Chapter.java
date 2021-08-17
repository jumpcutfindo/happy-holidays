package com.jumpcutfindo.happyholidays.common.guide;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.common.guide.sections.ISection;
import com.jumpcutfindo.happyholidays.common.guide.sections.ImageSection;
import com.jumpcutfindo.happyholidays.common.guide.sections.ItemSection;
import com.jumpcutfindo.happyholidays.common.guide.sections.RecipeSection;
import com.jumpcutfindo.happyholidays.common.guide.sections.TextSection;
import com.jumpcutfindo.happyholidays.common.guide.sections.SerializedSection;

public class Chapter {
    public final String title;
    public final String description;
    public final ImageSection image;

    public List<ISection> unserializedSections;

    private List<SerializedSection> sections;


    public Chapter(String title, String description, ImageSection image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<ISection> getSections() {
        if (unserializedSections == null) {
            // Convert serialized sections into their respective components
            unserializedSections = sections.stream().map(serializedSection -> {
                if (serializedSection.type.equals("text")) {
                    return new TextSection(serializedSection.title, serializedSection.caption,
                            serializedSection.content);
                } else if (serializedSection.type.equals("image")) {
                    return new ImageSection(serializedSection.source, serializedSection.x, serializedSection.y,
                            serializedSection.width, serializedSection.height, 1.0f);
                } else if (serializedSection.type.equals("items")) {
                    return ItemSection.from(serializedSection.items);
                } else if (serializedSection.type.equals("recipes")) {
                    return RecipeSection.from(serializedSection.recipes);
                }

                return null;
            }).collect(Collectors.toList());
        }
        return unserializedSections;
    }

    public ImageSection getImage() {
        return image;
    }
}
