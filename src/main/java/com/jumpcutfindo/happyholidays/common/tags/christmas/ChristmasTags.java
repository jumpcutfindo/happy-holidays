package com.jumpcutfindo.happyholidays.common.tags.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

public class ChristmasTags {
    public static final Tags.IOptionalNamedTag<Item> BASIC_ORNAMENTS = tag("christmas_basic_ornaments");

    public static final Tags.IOptionalNamedTag<Item> RARE_ORNAMENTS = tag("christmas_rare_ornaments");

    public static final Tags.IOptionalNamedTag<Item> LEGENDARY_ORNAMENTS = tag("christmas_legendary_ornaments");

    public static final Tags.IOptionalNamedTag<Item> FOODS = tag("christmas_foods");

    public static final Tags.IOptionalNamedTag<Item> SHEET_MUSIC = tag("christmas_sheet_music");

    private static Tags.IOptionalNamedTag<Item> tag(String name)
    {
        return ItemTags.createOptional(new ResourceLocation(HappyHolidaysMod.MOD_ID, name));
    }
}
