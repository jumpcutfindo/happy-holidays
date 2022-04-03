package com.jumpcutfindo.happyholidays.common.utils;

import java.util.List;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class TagUtils {
    public static List<Block> blockContents(TagKey<Block> tag) {
        return ForgeRegistries.BLOCKS.tags().getTag(tag).stream().toList();
    }

    public static List<Item> itemContents(TagKey<Item> tag) {
        return ForgeRegistries.ITEMS.tags().getTag(tag).stream().toList();
    }

    public static List<EntityType<?>> entityTypeContents(TagKey<EntityType<?>> tag) {
        return ForgeRegistries.ENTITIES.tags().getTag(tag).stream().toList();
    }
}
