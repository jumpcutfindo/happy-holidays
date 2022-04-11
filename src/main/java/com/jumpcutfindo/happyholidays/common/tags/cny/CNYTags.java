package com.jumpcutfindo.happyholidays.common.tags.cny;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CNYTags {
    public static class Blocks {

    }

    public static class Items {
        public static final TagKey<Item> LEATHER_ARMOR = itemTag("leather_armor");
        public static final TagKey<Item> IRON_ARMOR = itemTag("iron_armor");
        public static final TagKey<Item> GOLD_ARMOR = itemTag("gold_armor");
        public static final TagKey<Item> DIAMOND_ARMOR = itemTag("diamond_armor");
        public static final TagKey<Item> NETHERITE_ARMOR = itemTag("netherite_armor");
    }

    public static class Entities {

    }

    private static TagKey<Block> blockTag(String name) {
        return BlockTags.create(new ResourceLocation(HappyHolidaysMod.MOD_ID, "cny/" + name));
    }

    private static TagKey<Item> itemTag(String name) {
        return ItemTags.create(new ResourceLocation(HappyHolidaysMod.MOD_ID, "cny/" + name));
    }

    private static TagKey<EntityType<?>> entityTag(String name) {
        return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(HappyHolidaysMod.MOD_ID, "cny/" + name));
    }
}
