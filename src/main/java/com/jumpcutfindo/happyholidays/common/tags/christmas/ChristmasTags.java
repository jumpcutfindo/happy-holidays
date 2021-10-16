package com.jumpcutfindo.happyholidays.common.tags.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ChristmasTags {
    public static final Tags.IOptionalNamedTag<Block> BAUBLES = blockTag("baubles");

    public static final Tags.IOptionalNamedTag<Block> BIG_BAUBLES = blockTag("big_baubles");

    public static final Tags.IOptionalNamedTag<Block> TINSEL = blockTag("tinsel");

    public static final Tags.IOptionalNamedTag<Block> CHRISTMAS_LIGHTS = blockTag("lights");

    public static final Tags.IOptionalNamedTag<Item> BASIC_ORNAMENTS = itemTag("basic_ornaments");

    public static final Tags.IOptionalNamedTag<Item> RARE_ORNAMENTS = itemTag("rare_ornaments");

    public static final Tags.IOptionalNamedTag<Item> LEGENDARY_ORNAMENTS = itemTag("legendary_ornaments");

    public static final Tags.IOptionalNamedTag<Item> FOODS = itemTag("foods");

    public static final Tags.IOptionalNamedTag<Item> SHEET_MUSIC = itemTag("sheet_music");

    private static Tags.IOptionalNamedTag<Block> blockTag(String name) {
        return BlockTags.createOptional(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/" + name));
    }

    private static Tags.IOptionalNamedTag<Item> itemTag(String name) {
        return ItemTags.createOptional(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/" + name));
    }
}
