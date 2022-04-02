package com.jumpcutfindo.happyholidays.common.tags.christmas;

import com.jumpcutfindo.happyholidays.HappyHolidaysMod;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ChristmasTags {

    public class Blocks {
        public static final TagKey<Block> BAUBLES = blockTag("baubles");
        public static final TagKey<Block> BIG_BAUBLES = blockTag("big_baubles");
        public static final TagKey<Block> TINSEL = blockTag("tinsel");
        public static final TagKey<Block> CHRISTMAS_LIGHTS = blockTag("lights");
        public static final TagKey<Block> CHRISTMAS_BELLS = blockTag("bells");
        public static final TagKey<Block> STOCKINGS = blockTag("stockings");
        public static final TagKey<Block> DECORATIONS = blockTag("decorations");
        public static final TagKey<Block> HEAD_ORNAMENTS = blockTag("head_ornaments");
        public static final TagKey<Block> GINGERBREAD_BLOCKS = blockTag("gingerbread_blocks");
        public static final TagKey<Block> CANDY_CANE_BLOCKS = blockTag("candy_cane_blocks");
        public static final TagKey<Block> STAR_AFFECTED_BLOCKS = blockTag("star_affected_blocks");
        public static final TagKey<Block> GINGERBREAD_MEN_SPAWNABLE_ON = blockTag("gingerbread_men_spawnable_on");
        public static final TagKey<Block> NUTCRACKER_SPAWNABLE_ON = blockTag("nutcracker_spawnable_on");
        public static final TagKey<Block> HEAT_EMITTING_BLOCKS = blockTag("heat_emitting_blocks");
    }

    public class Items {
        public static final TagKey<Item> BAUBLES = itemTag("baubles");
        public static final TagKey<Item> BIG_BAUBLES = itemTag("big_baubles");
        public static final TagKey<Item> TINSEL = itemTag("tinsel");
        public static final TagKey<Item> CHRISTMAS_LIGHTS = itemTag("lights");
        public static final TagKey<Item> CHRISTMAS_BELLS = itemTag("bells");
        public static final TagKey<Item> STOCKINGS = itemTag("stockings");
        public static final TagKey<Item> DECORATIONS = itemTag("decorations");
        public static final TagKey<Item> HEAD_ORNAMENTS = itemTag("head_ornaments");
        public static final TagKey<Item> BASIC_ORNAMENTS = itemTag("basic_ornaments");
        public static final TagKey<Item> RARE_ORNAMENTS = itemTag("rare_ornaments");
        public static final TagKey<Item> LEGENDARY_ORNAMENTS = itemTag("legendary_ornaments");
        public static final TagKey<Item> GIFTS = itemTag("gifts");
        public static final TagKey<Item> FOODS = itemTag("foods");
        public static final TagKey<Item> LARGE_FOODS = itemTag("large_foods");
        public static final TagKey<Item> SHEET_MUSIC = itemTag("sheet_music");
        public static final TagKey<Item> GINGERBREAD_BLOCKS = itemTag("gingerbread_blocks");
        public static final TagKey<Item> CANDY_CANE_BLOCKS = itemTag("candy_cane_blocks");
        public static final TagKey<Item> STAR_AFFECTED_BLOCKS = itemTag("star_affected_blocks");
        public static final TagKey<Item> SANTA_ELF_BASIC_REQUESTABLES = itemTag("santa_elf_basic_requestables");
        public static final TagKey<Item> SANTA_ELF_INTERMEDIATE_REQUESTABLES = itemTag("santa_elf_intermediate_requestables");
        public static final TagKey<Item> SANTA_ELF_ADVANCED_REQUESTABLES = itemTag("santa_elf_advanced_requestables");
        public static final TagKey<Item> SANTA_ELF_ADVANCED_REQUESTABLES_ALTERNATE = itemTag("santa_elf_advanced_requestables_alternate");
        public static final TagKey<Item> NUTCRACKER_FOOD = itemTag("nutcracker_food");
        public static final TagKey<Item> HEAT_EMITTING_ITEMS = itemTag("heat_emitting_items");
        public static final TagKey<Item> SPECIAL_WALNUTS = itemTag("special_walnuts");
        public static final TagKey<Item> NUTCRACKER_CARRIABLES = itemTag("nutcracker_carriables");
    }

    public class Entities {
        public static final TagKey<EntityType<?>> CANDY_CANE_EXPLODERS = entityTag("candy_cane_exploders");
        public static final TagKey<EntityType<?>> DEBUFFABLE_BY_STAR = entityTag("debuffable_by_star");
    }

    private static TagKey<Block> blockTag(String name) {
        return BlockTags.create(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/" + name));
    }

    private static TagKey<Item> itemTag(String name) {
        return ItemTags.create(new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/" + name));
    }

    private static TagKey<EntityType<?>> entityTag(String name) {
        return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(HappyHolidaysMod.MOD_ID, "christmas/" + name));
    }
}
